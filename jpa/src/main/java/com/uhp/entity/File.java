package com.uhp.entity;

import lombok.Data;

import java.io.InputStream;

/**
 * @author Bogdan Kovalev.
 */
@Data
public class File implements Entity {
    private final String id;
    private final InputStream inputStream;
    private final String filename;
}
