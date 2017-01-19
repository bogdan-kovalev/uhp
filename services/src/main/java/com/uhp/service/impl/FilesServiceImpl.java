package com.uhp.service.impl;

import com.uhp.dto.FileDTO;
import com.uhp.entity.File;
import com.uhp.repository.FileRepository;
import com.uhp.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDTO saveFile(InputStream stream, String filename) {
        final String fileId = fileRepository.store(stream, filename);
        return new FileDTO(fileId, filename, null);
    }

    @Override
    public FileDTO getFile(String id) throws FileNotFoundException {
        final File file = fileRepository.findOne(id);
        return new FileDTO(id, file.getFilename(), file.getInputStream());
    }
}
