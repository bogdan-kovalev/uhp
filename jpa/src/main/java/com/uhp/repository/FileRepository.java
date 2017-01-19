package com.uhp.repository;

import com.uhp.entity.File;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Bogdan Kovalev.
 */
public interface FileRepository {

    String store(InputStream stream, String filename);

    File findOne(String id) throws FileNotFoundException;
}
