package com.uhp.repository;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * @author Bogdan Kovalev.
 */
public abstract class AbstractResourceRepository<Entity, Id extends Serializable> implements ResourceRepositoryV2<Entity, Id> {

    abstract MongoRepository<Entity, Id> getRepository();

    @Override
    public Entity findOne(Id id, QuerySpec querySpec) {
        return getRepository().findOne(id);
    }

    @Override
    public ResourceList<Entity> findAll(QuerySpec querySpec) {
        return querySpec.apply(getRepository().findAll());
    }

    @Override
    public ResourceList<Entity> findAll(Iterable<Id> ids, QuerySpec querySpec) {
        return querySpec.apply(getRepository().findAll(ids));
    }

    @Override
    public <S extends Entity> S save(S s) {
        return getRepository().save(s);
    }

    @Override
    public <S extends Entity> S create(S s) {
        // does nothing
        return null;
    }

    @Override
    public void delete(Id id) {
        // does nothing
    }
}
