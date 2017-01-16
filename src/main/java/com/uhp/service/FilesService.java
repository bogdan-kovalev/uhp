package com.uhp.service;

import com.uhp.dto.FileDTO;

import java.io.InputStream;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public interface FilesService {
    FileDTO saveFile(InputStream stream, String filename);

    FileDTO getFile(String id);
}
