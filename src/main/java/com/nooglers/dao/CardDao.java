package com.nooglers.dao;

import com.nooglers.domains.Card;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardDao extends BaseDAO<Card, Integer> {

    private static final ThreadLocal<CardDao> classDaoInstance = ThreadLocal.withInitial(CardDao::new);
    public Card delete(Integer integer) {
        begin();
        final Card byId = findById(integer);
        byId.setDeleted((short) 1);
        commit();
        return byId;

    }
    public Card get(Integer cardid) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card = entityManager.find(Card.class, cardid);
        transaction.commit();
        return card.getDeleted() == 0 ? card : null;
    }

    @Override
    public Card save(Card card) {
        begin();
        final Module reference = entityManager.getReference(Module.class, card.getModule().getId());
        card.setModule(reference);
        entityManager.persist(card);
        commit();
        return card;
    }

    public List<Card> getCardsByModuleId(int moduleId) {
        List<Card> res = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Card> cards = entityManager.createQuery("select c from card c where c.module.id=:id and deleted=0", Card.class).setParameter("id", moduleId).getResultList();
        transaction.commit();

        for (Card card : cards) {
            Card card1 = get(card.getId());
            res.add(card1);
            entityManager.refresh(card);
        }
        return res;
    }

    public List<Card> getAllModuleCards(Integer moduleId) {

        return entityManager.createQuery("from card  c where c.module.id=?1 and c.deleted=0", Card.class)
                .setParameter(1, moduleId).getResultList();
    }

    public static CardDao getInstance() {
        return classDaoInstance.get();
    }
}
