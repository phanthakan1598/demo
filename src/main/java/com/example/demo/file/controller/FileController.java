package com.example.demo.file.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.file.constant.TypeImage;
import com.example.demo.file.exception.ImageException;
import com.example.demo.file.service.FileStorageService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	FileStorageService fileStorageService;

    @GetMapping("/images/member/{fileName:.+}")
    public ResponseEntity<Resource> viewImageMemberFile(@PathVariable String fileName, HttpServletRequest request) throws ImageException {

        Resource resource;

        try {
            resource = fileStorageService.loadFile(fileName, TypeImage.MEMBER);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw ImageException.notDetermineFileType();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }
    
    @GetMapping("/images/university/{fileName:.+}")
    public ResponseEntity<Resource> viewImageUniversityFile(@PathVariable String fileName, HttpServletRequest request) throws ImageException {

        Resource resource;

        try {
            resource = fileStorageService.loadFile(fileName, TypeImage.UNIVERSITY);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw ImageException.notDetermineFileType();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }
}
