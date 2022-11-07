package com.example.demo.file.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.example.demo.file.constant.TypeImage;
import com.example.demo.file.model.FileName;
import com.example.demo.file.util.RandomStr;
@Service
public class FileStorageService {

	private final String PREFIX_IMAGE = "img_";
	
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif", "image/bmp","image/tiff");
	private static final List<String> fileExtension = Arrays.asList("png", "jpeg", "jpg", "gif", "bmp", "tiff", "PNG", "JPEG", "JPG", "GIF", "BMP", "TIFF");
	
	@Value("${file.upload.image.member}")
    private String uploadPathDirImageMember;
	
	@Value("${file.upload.image.smallMember}")
    private String uploadPathDirImageMemberResized;
	
	@Value("${file.upload.image.university}")
    private String uploadPathDirImageUniversity;
	
	@Value("${file.upload.image.smallUniversity}")
    private String uploadPathDirImageUniversityResized;
	
	public Path getStorageImagePath(TypeImage typeImage) {
        switch (typeImage) {
            case MEMBER:
                return Paths.get(uploadPathDirImageMember).toAbsolutePath().normalize();
            case UNIVERSITY:
                return Paths.get(uploadPathDirImageUniversity).toAbsolutePath().normalize();
            case SMALL_UNIVERSITY:
                return Paths.get(uploadPathDirImageUniversityResized).toAbsolutePath().normalize();
            default:
                return Paths.get(uploadPathDirImageMemberResized).toAbsolutePath().normalize();
        }
    }
	
	public Path getStorageMemberPath() {
        return Paths.get(uploadPathDirImageMember).toAbsolutePath().normalize();
    }
	
	public String genNewFileName() {
        String timeStamp = new SimpleDateFormat("yyMMddHHmmssS").format(new Date());
        return timeStamp + new RandomStr().createRandomHEXString(8);
    }

    public String genNewFileName(int saltlength) {
        String timeStamp = new SimpleDateFormat("yyMMddHHmmssS").format(new Date());
        return timeStamp + new RandomStr().createRandomHEXString(saltlength);
    }
    
    private void varifyFileNameImage(MultipartFile file, String extension, String originalFileName) throws IOException {
        if (originalFileName.contains("..")) {
            throw new IOException("Sorry! Filename contains invalid path sequence " + originalFileName);
        }

        if (!contentTypes.contains(file.getContentType())) {
            throw new IOException("Sorry! invalid file MIME Type (" + file.getContentType() + ") " + originalFileName);
        }

        if (extension != null && extension.isEmpty()) {
            throw new IOException("Sorry! no file extension" + originalFileName);
        }

        if (!fileExtension.contains(extension)) {
            throw new IOException("Sorry! invalid file extension" + originalFileName);
        }
    }
    
    public FileName generatedFileName(MultipartFile file) throws IOException {
        // check is dir created?
        // checkDir(); -- unused

        // extension
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        varifyFileNameImage(file, extension, originalFileName);

        String generatedName = genNewFileName();
        String fileName = PREFIX_IMAGE + generatedName + "." + extension;

        return new FileName(fileName,extension);
    }
    
    public String storeImageFile(MultipartFile file,TypeImage typeImage) throws IOException {
    	
        FileName fileName = generatedFileName(file);
        
//        System.out.println("file : "+file.getContentType());
//    	System.out.println("typeImage : "+typeImage.name());
//        System.out.println("fileName : "+fileName.getFileName());
        
        
//        Path targetLocation = getStorageImagePath(typeImage);
//        BufferedImage img = ImageIO.read(file.getInputStream());
//        BufferedImage resizedImage = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 600, // max w/h
//                Scalr.OP_ANTIALIAS);
//        
//        ImageIO.write(resizedImage, fileName.getExtension(), Files.createFile(targetLocation).toFile());
//        
//        
//        
//        System.out.println("targetLocation : "+targetLocation);
//        System.out.println("img : "+img);
//        System.out.println("resizedImage : "+resizedImage);
//        
//        return fileName.getFileName();
        
        try {
            Path targetLocation = getStorageImagePath(typeImage).resolve(fileName.getFileName());
        	
            BufferedImage img = ImageIO.read(file.getInputStream());
            
            

            if (img == null) {
                throw new IllegalArgumentException("can't read image " + fileName.getFileName() + ".");
            }

            BufferedImage resizedImage = Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 600, // max w/h
                    Scalr.OP_ANTIALIAS);

            ImageIO.write(resizedImage, fileName.getExtension(), Files.createFile(targetLocation).toFile());

            
            return fileName.getFileName();
        } catch (IOException ex) {
        	
            throw new IOException("Could not store file " + fileName.getFileName() + ". Please try again!", ex);
        }
    }
    
    public Resource loadFile(String fileName,TypeImage typeImage) throws IOException {
        try {
            Path fileStorageLocation = getStorageImagePath(typeImage);
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new IOException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new IOException("File not found " + fileName, ex);
        }
    }
	
}
