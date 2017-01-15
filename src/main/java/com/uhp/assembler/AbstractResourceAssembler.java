package com.uhp.assembler;

import com.uhp.entity.Entity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

/**
 * @author Bogdan Kovalev.
 */
abstract class AbstractResourceAssembler<T extends Entity> implements ResourceAssembler<T, Resource<T>> {
    @Override
    public Resource<T> toResource(T entity) {
        Resource<T> resource = new Resource<>(entity);
        resource.add(new Link(getPath() + "/" + entity.getId()).withSelfRel());
        return resource;
    }

    abstract String getPath();
}
