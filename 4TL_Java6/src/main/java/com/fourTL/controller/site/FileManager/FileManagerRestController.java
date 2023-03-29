package com.fourTL.controller.site.FileManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fourTL.service.FileManagerService;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
public class FileManagerRestController {
	@Autowired
	FileManagerService fileService;
	
	@GetMapping("/files/{folder}/{file}") 
	public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		return fileService.read(folder, file);
	}
	
	@PostMapping("/files/{folder}")
	public List<String> upload(@PathVariable("folder") String folder, @PathParam("files") MultipartFile[] files) {
		return fileService.save(folder, files);
	}
	
	@DeleteMapping("/files/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		fileService.delete(folder, file);
	}
	
	@GetMapping("/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder) {
		return fileService.list(folder);
	}
}
