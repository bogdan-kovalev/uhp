package com.uhp.controller;

import com.uhp.assembler.MasterResourceAssembler;
import com.uhp.entity.Master;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.repository.MastersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping("/masters")
public class MasterController {

    @Autowired
    private MastersRepository masterRepository;
    @Autowired
    private MasterResourceAssembler masterResourceAssembler;

    @GetMapping
    ResponseEntity<List<Resource<Master>>> getMasters() {
        return ResponseEntity.ok(masterRepository.findAll()
                .stream()
                .map(masterResourceAssembler::toResource)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Resource<Master>> getMasterById(@PathVariable("id") String id) {
        final Master master = masterRepository.findById(id);
        if (master == null) {
            throw new EntityNotFoundException("Master not found - id: " + id);
        }
        final Resource<Master> masterResource = masterResourceAssembler.toResource(master);
        return ResponseEntity.ok(masterResource);
    }
}
