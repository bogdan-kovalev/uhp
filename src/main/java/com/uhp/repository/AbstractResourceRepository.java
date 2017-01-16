package com.uhp.repository;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.DefaultResourceList;
import io.katharsis.resource.list.ResourceList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public abstract class AbstractResourceRepository<EntityClass, IdClass extends Serializable> implements ResourceRepositoryV2<EntityClass, IdClass> {

    abstract MongoRepository<EntityClass, IdClass> getRepository();

    @Override
    public EntityClass findOne(IdClass id, QuerySpec querySpec) {
        return getRepository().findOne(id);
    }

    @Override
    public ResourceList<EntityClass> findAll(QuerySpec querySpec) {
        final DefaultResourceList<EntityClass> defaultResourceList = new DefaultResourceList<>();
        defaultResourceList.addAll(getRepository().findAll());
        return defaultResourceList;
    }

    @Override
    public ResourceList<EntityClass> findAll(Iterable<IdClass> ids, QuerySpec querySpec) {
        final DefaultResourceList<EntityClass> defaultResourceList = new DefaultResourceList<>();
        getRepository().findAll(ids).forEach(defaultResourceList::add);
        return defaultResourceList;
    }

    @Override
    public <S extends EntityClass> S save(S s) {
        return getRepository().save(s);
    }

    @Override
    public <S extends EntityClass> S create(S s) {
        return getRepository().save(s);
    }

    @Override
    public void delete(IdClass id) {
        getRepository().delete(id);
    }
}
