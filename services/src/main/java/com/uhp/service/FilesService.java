package com.uhp.service;

import com.uhp.dto.FileDTO;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Bogdan Kovalev.
 */
public interface FilesService {
    FileDTO saveFile(InputStream stream, String filename);

    FileDTO getFile(String id) throws FileNotFoundException;
}
