package com.uhp.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.uhp.dto.FileDTO;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public FileDTO saveFile(InputStream stream, String filename) {
        final GridFSFile gridFSFile = gridFsTemplate.store(stream, filename);
        final String fileId = gridFSFile.getId().toString();
        return new FileDTO(fileId, filename, null);
    }

    @Override
    public FileDTO getFile(String id) {
        final GridFSDBFile file = findOneFile(id);
        return new FileDTO(id, file.getFilename(), file.getInputStream());
    }

    private GridFSDBFile findOneFile(String id) {
        return Optional.ofNullable(gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id))))
                .orElseThrow(() -> new EntityNotFoundException("File not found"));
    }
}
