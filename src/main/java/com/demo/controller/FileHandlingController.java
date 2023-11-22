package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.FileDetails;
import com.demo.service.FileHandlingService;

@RestController
@RequestMapping("/file")
public class FileHandlingController {
	
	@Autowired
	private FileHandlingService fileHandlingService;
	
	@PostMapping("/save")
	public String saveFile(@ModelAttribute FileDetails fileDetails) {
		return fileHandlingService.createFile(fileDetails);
	}
	
	@GetMapping
	public List<FileDetails> getAllFileDetails(){
		return fileHandlingService.findAllFileDetails();
	}
	
	@GetMapping("/{id}")
	public FileDetails getFileDetailsById(@PathVariable Long id) {
		return fileHandlingService.findFileDetailsById(id);
	}
	
	@PutMapping("/{id}")
	public String updateFile(@ModelAttribute FileDetails fileDetails,@PathVariable Long id) {
		return fileHandlingService.modifyFile(fileDetails,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteFileDetailsById(@PathVariable Long id) {
		return fileHandlingService.removeFileDetailsById(id);
	}

}
