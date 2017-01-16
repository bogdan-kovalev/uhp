package com.uhp.repository;

import io.katharsis.queryParams.RequestParams;
import io.katharsis.repository.ResourceRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public abstract class AbstractResourceRepository<T, K extends Serializable> implements ResourceRepository<T, K> {

    abstract MongoRepository<T, K> getRepository();

    @Override
    public T findOne(K id, RequestParams requestParams) {
        return getRepository().findOne(id);
    }

    @Override
    public Iterable<T> findAll(RequestParams requestParams) {
        return getRepository().findAll();
    }

    @Override
    public Iterable<T> findAll(Iterable<K> ids, RequestParams requestParams) {
        return getRepository().findAll(ids);
    }

    @Override
    public void delete(K id) {
        getRepository().delete(id);
    }

    @Override
    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }
}
