package com.uhp.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.uhp.dto.FileDTO;
import com.uhp.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

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
        final GridFSDBFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        return new FileDTO(file.getId().toString(), file.getFilename(), file.getInputStream());
    }
}
