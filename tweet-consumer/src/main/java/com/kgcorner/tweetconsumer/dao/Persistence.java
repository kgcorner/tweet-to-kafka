package com.kgcorner.tweetconsumer.dao;


import com.kgcorner.tweetconsumer.dao.model.TweetModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 24/07/21
 */

@Transactional
@Repository
public class Persistence {

    @PersistenceContext
    private EntityManager entityManager;

    public TweetModel createTweetModel(TweetModel model) {
        entityManager.persist(model);
        return model;
    }

    public List<TweetModel> getTweets(String trend, int offset, int itemCount) {
        String hql = "from TweetModel where term=?1";
        Query query = entityManager.createQuery(hql);
        query.setParameter(1, trend);
        query.setMaxResults(itemCount);
        query.setFirstResult(offset);
        List resultList = query.getResultList();
        return resultList;
    }
}