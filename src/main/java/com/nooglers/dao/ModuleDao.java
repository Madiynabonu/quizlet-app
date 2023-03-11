package com.nooglers.dao;

import com.nooglers.domains.Folder;
import com.nooglers.domains.Module;
import com.nooglers.dto.ModuleSetDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModuleDao extends BaseDAO<Module, Integer> {

    private static final ThreadLocal<ModuleDao> moduleDaoThreadLocal = ThreadLocal.withInitial(ModuleDao::new);

    @Override
    public boolean deleteById(Integer moduleId) {

        begin();
        entityManager.createQuery("update module  m set deleted=cast(1 as short ) where m.id=?1").setParameter(1, moduleId).executeUpdate();
        entityManager.createNativeQuery("delete from folder_module fm where fm.module=?1").setParameter(1, moduleId).executeUpdate();
        commit();
        return true;

    }


    public Module save(Module module, String folderId) {

        super.save(module);

        if (!folderId.isBlank()) {
            begin();
            final FolderDao instance = FolderDao.getInstance();
            Folder folder = instance.findById(Integer.valueOf(folderId));
            module = entityManager.getReference(Module.class, module.getId()); // adding items into MANY TO MANY relation
            folder.getModules().add(module);
            module.getFolders().add(entityManager.getReference(Folder.class, folder.getId()));
            commit();
        }

        return module;
    }


    public List<Module> getAll(Integer userId) {
        return entityManager.createQuery("from module  m where m.createdBy.id=?1 and m.deleted=0 order by m.createdAt desc", Module.class).setParameter(1, userId).getResultList();
    }

    public List<Module> findAllRecentModulesByUser(Integer userId) {

        return entityManager.createQuery("from module  m where m.createdBy.id=?1 order by m.lastSeen desc", Module.class).setParameter(1, userId).getResultList();

    }

    public void updateLastSeen(Module module) {

        begin();
        module.setLastSeen(LocalDateTime.now());
        commit();
    }

    public Set<Module> getModulesByFolderId(Integer folderId) {

        final Set<Module> modules = new HashSet<>();
        final List<Integer> resultList = entityManager.createNativeQuery("select fm.module from folder_module fm where fm.folder=?1", Integer.class).setParameter(1, folderId).getResultList();


        for (Integer moduleId : resultList) {
            final Module module = entityManager.find(Module.class, moduleId);
            modules.add(module);
        }

        return modules;

    }

    public List<Module> getModules(Integer userId, short status) {

        return entityManager.createQuery("from module  m where m.createdBy.id=?1 and m.deleted=?2", Module.class).setParameter(1, userId).setParameter(2, status).getResultList();

    }

    public boolean isIncludedInFolder(Integer moduleId, Integer folderId) {

        return (Long) entityManager.createNativeQuery("select count(*) from folder_module fm where fm.module=?1 and fm.folder=?2", Long.class).setParameter(1, moduleId).setParameter(2, folderId).getSingleResult() > 0;


    }

    public Set<Module> getGroupModules(Integer groupId, Integer userId) {
        begin();
        Set<Module> result = new HashSet<>();
        List<Integer> groupIds = entityManager.createNativeQuery("""
                select cm.modules_id from class_module cm where cm.classes_id=:groupId 
                """, Integer.class).setParameter("groupId", groupId).getResultList();
        List<Module> resultList = entityManager.createQuery(
                " from module where deleted = cast( 0 as short ) and createdBy.id = :userId", Module.class)
                .setParameter("userId", userId).getResultList();
        for (Module module : resultList) {
            if (!groupIds.contains(module.getId())) {
                result.add(module);
            }
        }
        commit();
        return result;
    }



    //    @Override
//    public Module save(Module module) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(module);
//        transaction.commit();
//        return module;
//    }
//
//    public Module getModuleById(int modulId) {
//        TypedQuery<Module> query = entityManager
//                .createQuery("select m from Module m where m.deleted = 0  and m.id = ?1" , Module.class);
//        Module module = ( Module ) query.setParameter(1 , modulId).getSingleResult();
//        return module;
//    }
//
//    @Override
//    public Module update(Module module) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        Module edittingModule = entityManager.find(Module.class , module.getId());
//
//        if ( module.getName() != null ) edittingModule.setName(module.getName());
//        if ( module.getDescription() != null ) edittingModule.setDescription(module.getDescription());
//        if ( !module.isPublic() ) edittingModule.setPublic(false);
//        edittingModule.setUpdatedAt(LocalDateTime.now());
//        transaction.commit();
//        return edittingModule;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        begin();
//        Module module = entityManager.find(Module.class , id);
//        module.setDeleted(( short ) 1);
//       commit();
//        return module;
//    }
//
//    @Override
//    public List<Module> getAll() {
////        return entityManager.createQuery("select u from Users u" , User.class).getResultList();
//        return entityManager.createQuery("select m from Module  m" , Module.class).getResultList();
//    }
//
//    @Override
//    public Module get(Integer integer) {
//        return null;
//    }
    public static ModuleDao getInstance() {
        return moduleDaoThreadLocal.get();
    }
}
