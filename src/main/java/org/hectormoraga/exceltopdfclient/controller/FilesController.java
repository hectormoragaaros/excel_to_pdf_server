package org.hectormoraga.exceltopdfclient.controller;

import org.hectormoraga.exceltopdfclient.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/demo")
public class FilesController {
	@Autowired
	FilesStorageService storageService;

	@GetMapping("/")
	public String displayUploadForm(Model theModel) {
		return "index";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(Model model, @Validated @RequestParam("file") MultipartFile file) {
		String message = "";

		try {
			storageService.save(file);
		} catch (Exception e) {
			message = "Could not upload the file " + file.getOriginalFilename() + ":" + e.getMessage();
			model.addAttribute("msg", message);
		}

		return "index";
	}
}