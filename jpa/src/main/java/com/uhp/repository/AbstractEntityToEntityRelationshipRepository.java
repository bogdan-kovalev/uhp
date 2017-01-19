package com.uhp.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;

import java.io.Serializable;

/**
 * @author Bogdan Kovalev.
 */
public abstract class AbstractEntityToEntityRelationshipRepository<EntityA, EntityB, Id extends Serializable>
        implements RelationshipRepository<EntityA, Id, EntityB, Id> {
    @Override
    public void setRelation(EntityA entityA, Id id, String s) {

    }

    @Override
    public void setRelations(EntityA entityA, Iterable<Id> relationIds, String fieldName) {

    }

    @Override
    public void addRelations(EntityA entityA, Iterable<Id> iterable, String s) {

    }

    @Override
    public void removeRelations(EntityA entityA, Iterable<Id> iterable, String s) {

    }

    @Override
    public EntityB findOneTarget(Id id, String s, QueryParams queryParams) {
        return null;
    }

    @Override
    public Iterable<EntityB> findManyTargets(Id id, String s, QueryParams queryParams) {
        return null;
    }
}
