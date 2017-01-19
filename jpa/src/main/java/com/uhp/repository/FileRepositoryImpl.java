package com.uhp.repository;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.uhp.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author Bogdan Kovalev.
 */
@Repository
public class FileRepositoryImpl implements FileRepository {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public String store(InputStream stream, String filename) {
        final GridFSFile gridFSFile = gridFsTemplate.store(stream, filename);
        return gridFSFile.getId().toString();
    }

    @Override
    public File findOne(String id) throws FileNotFoundException {
        final GridFSDBFile gridFSDBFile = Optional
                .ofNullable(
                        gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)))
                )
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        final InputStream inputStream = gridFSDBFile.getInputStream();
        final String filename = gridFSDBFile.getFilename();

        return new File(id, inputStream, filename);
    }
}
