package com.nooglers.dao;

import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class ClassDao extends BaseDAO<Class, Integer> {
    private static final ThreadLocal<ClassDao> CLASS_DAO_THREAD_LOCAL = ThreadLocal.withInitial(ClassDao::new);

    @Override
    public Class save(Class aClass) {
        begin();
        entityManager.persist(aClass);
        commit();
        return aClass;
    }


//    @Override
//    public boolean update(Class aClass) {
//        begin();
//        aClass.setUpdatedAt(LocalDateTime.now());
//        entityManager.merge(aClass);
//        commit();
//        return true;
//    }

    @Override
    public boolean deleteById(Integer id) {
        begin();
        Class aClass = entityManager.find(Class.class , id);
        aClass.setDeleted(( short ) 1);
        commit();
        return true;
    }


    public List<Class> search(String schoolOrClassName) {
        return getAll().stream()
                .filter(aClass -> aClass.getName().contains(schoolOrClassName)
                                  || aClass.getSchoolName().contains(schoolOrClassName))
                .toList();
    }


    public List<Class> getAll() {
        return entityManager.createQuery("select u from Class u where u.deleted = 0" , Class.class)
                .getResultList();
    }

    public List<Class> getAll(Integer userId) {
        List<Class> classList = getAll().stream()
                .filter(aClass -> aClass.getDeleted() == 0 &&
                                  ( containsUser(aClass.getUsers() , userId) || aClass.getCreatedBy().equals(userId) ))
                .toList();

        for ( Class aClass : classList ) {
            entityManager.refresh(aClass);
        }

        return classList;
    }

    public Class get(Integer groupId) {
        return entityManager.createQuery("from Class  c where c.id=?1" , Class.class)
                .setParameter(1 , groupId)
                .getSingleResult();
    }

    public boolean delete(Class group) {
        entityManager.getTransaction().begin();
        group.setDeleted(( short ) 1);
        entityManager.getTransaction().commit();
        return true;

//        return entityManager.createQuery("update Class  c set c.deleted=cast(1 as short ) where c.id=?1")
//                       .setParameter(1 , groupId).executeUpdate() != 0;
    }


    //@Transactional
//    public void addMember(Integer userId, Integer classId, UserDao userDao) {
//        begin();
//        User userById = userDao.findById(userId);
//        Class classById = findById(classId);
//        classById.getUsers().add(userById);
////        entityManager.persist(userById);
//        entityManager.persist(classById);
//        commit();
//    }
    private boolean containsUser(Set<User> users , Integer userId) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getId().equals(userId))
                .findAny();
        return optionalUser.isPresent();
    }

    public boolean addClassModule(Integer groupId , Integer moduleId) {
        begin();
        try {
            entityManager.createNativeQuery("""
                            insert into class_module values (?1,?2)
                            """).setParameter(1 , groupId)
                    .setParameter(2 , moduleId).executeUpdate();
        } catch ( Exception e ) {
            entityManager.getTransaction().rollback();
            return false;
        }
        commit();
        return true;
    }

    public Set<Module> getGroupModules(Integer groupId) {
        begin();
        Set<Module> result = new HashSet<>();
        List<Integer> moduleIds = entityManager.createNativeQuery("""
                select cm.modules_id from class_module cm where cm.classes_id=:groupId 
                """ , Integer.class).setParameter("groupId" , groupId).getResultList();
        List<Module> resultList = entityManager.createQuery(
                        " from module where deleted = cast( 0 as short ) " , Module.class)
                .getResultList();
        for ( Module module : resultList ) {
            if ( moduleIds.contains(module.getId()) ) {
                result.add(module);
            }
        }
        commit();
        return result;
    }

    public void removeModule(Integer moduleId , Integer groupId) {
        begin();
        Set<Module> result = new HashSet<>();
        entityManager.createNativeQuery("""
                        delete from class_module cm where cm.modules_id = :moduleId and cm.classes_id = :groupId
                        """).setParameter("moduleId" , moduleId)
                .setParameter("groupId" , groupId).executeUpdate();
        commit();
    }

    public static ClassDao getInstance() {
        return CLASS_DAO_THREAD_LOCAL.get();
    }

    public boolean addMemberToGroup(Integer userId , Integer groupId) {
        final UserDao userDao = UserDao.getInstance();

        begin();
        final Class aClass = get(groupId);
        aClass.getUsers().add(userDao.entityManager.getReference(User.class,userId));
        aClass.setUpdatedAt(LocalDateTime.now());
        commit();

        return true;
    }
}
