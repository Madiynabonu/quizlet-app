package com.nooglers.dao;

import com.nooglers.domains.Folder;
import jakarta.persistence.EntityTransaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FolderDao extends BaseDAO<Folder, Integer> {

    private static final ThreadLocal<FolderDao> FOLDER_DAO_THREAD_LOCAL = ThreadLocal.withInitial(FolderDao::new);


    //    @Override

    public Folder save(Folder folder) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(folder);
        transaction.commit();
        return folder;
    }
    //    @Override

    public boolean update(Folder folder) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Folder edittingFolder = entityManager.find(Folder.class , folder.getId());

        if ( folder.getTitle() != null ) edittingFolder.setTitle(folder.getTitle());
        if ( folder.getDescription() != null ) edittingFolder.setDescription(folder.getDescription());

        edittingFolder.setUpdatedAt(LocalDateTime.now());
        transaction.commit();
        return true;
    }
    //    @Override

    public Folder delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Folder folder = entityManager.find(Folder.class , id);
        int res = entityManager.createQuery("update folder  f set f.deleted=cast(1 as short) where f.id=?1")
                .setParameter(1 , id).executeUpdate();
        transaction.commit();

        return new Folder();
    }
    //    @Override

    protected List<Folder> getAll() {
        return null;
    }

    public List<Folder> getAllUserFolders(Integer userId) {

        return entityManager.createQuery("from folder  f where f.deleted=0 and f.createdBy.id=?1 order by createdAt desc " , Folder.class)
                .setParameter(1 , userId).getResultList();
    }


    //    @Override

    public Folder get(@NonNull Integer id) {
        return entityManager.find(Folder.class , id);
    }
    public Module getModule(@NonNull Integer id) {
        return entityManager.find(Module.class , id);
    }

    public List<Folder> getFoldersById(Integer id) {
        return ( List<Folder> ) entityManager.createQuery("select * from folder where id= " + id).getResultList();
    }

    public boolean unsetModule(Integer mid , Integer fid) {
        begin();
        final boolean result = entityManager.createNativeQuery("delete from folder_module fm where fm.module=?1 and fm.folder=?2")
                                  .setParameter(1 , mid)
                                  .setParameter(2 , fid).executeUpdate() != 0;
        commit();

        return result;
    }




    public static FolderDao getInstance() {
        return FOLDER_DAO_THREAD_LOCAL.get();
    }
}
