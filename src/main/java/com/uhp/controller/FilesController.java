package com.uhp.controller;

import com.uhp.dto.FileDTO;
import com.uhp.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping(FilesController.PATH)
public class FilesController {
    public static final String PATH = "/api/files";

    @Autowired
    private FilesService filesService;

    @GetMapping(value = "/{id}")
    ResponseEntity<InputStreamResource> getById(@PathVariable("id") String id) {
        final FileDTO fileDTO = filesService.getFile(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileDTO.filename);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fileDTO.inputStream));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ResponseEntity<FileDTO> upload(@RequestParam("file") MultipartFile file) {
        FileDTO fileDTO = null;
        try {
            fileDTO = filesService.saveFile(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(fileDTO);
    }
}
