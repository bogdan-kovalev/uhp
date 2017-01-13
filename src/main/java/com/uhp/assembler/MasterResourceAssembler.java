package com.uhp.assembler;

import com.uhp.entity.Master;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class MasterResourceAssembler implements ResourceAssembler<Master, Resource<Master>> {
    @Override
    public Resource<Master> toResource(Master master) {
        Resource<Master> resource = new Resource<>(master);
        resource.add(new Link("/masters/" + master.getId()).withSelfRel());
        return resource;
    }
}
