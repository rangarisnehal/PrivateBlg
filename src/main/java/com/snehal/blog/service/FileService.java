package com.snehal.blog.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadFile(String path, MultipartFile file) throws IOException;

}
