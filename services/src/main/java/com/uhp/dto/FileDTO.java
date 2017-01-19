package com.uhp.dto;

import lombok.AllArgsConstructor;

import java.io.InputStream;

/**
 * @author Bogdan Kovalev.
 */
@AllArgsConstructor
public class FileDTO {
    public String id;
    public String filename;
    public InputStream inputStream;
}
