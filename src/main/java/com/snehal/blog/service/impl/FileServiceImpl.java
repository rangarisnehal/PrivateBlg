package com.snehal.blog.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snehal.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
		
		String filePath = path + File.separator + name;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		return filePath;
	}

}
