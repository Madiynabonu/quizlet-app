package com.nooglers.dao;

import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import jakarta.persistence.EntityTransaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentDao extends BaseDAO<Document,Integer>{

   private static final ThreadLocal<DocumentDao> DOCUMENT_DAO=ThreadLocal.withInitial(DocumentDao::new);
    @Override
    public Document save(Document document) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(document);
        transaction.commit();
        return document;
    }

//    @Override
    public boolean update(Document document) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Document document1 = entityManager.merge(document);
        transaction.commit();
        return true;
    }

//    @Override
    protected Document delete(Integer integer) {
        return null;
    }

//    @Override
    protected List<Document> getAll() {
        return null;
    }

//    @Override
    public Document get(Integer integer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Document document = entityManager.find(Document.class, integer);
        transaction.commit();
        return document;
    }

    public Document findByGeneratedName(String filename) {
        return entityManager.createQuery("select d from document d where generatedFileName=:gfn", Document.class).setParameter("gfn", filename).getSingleResult();
    }

    public static DocumentDao getInstance() {
        return DOCUMENT_DAO.get();
    }
}
