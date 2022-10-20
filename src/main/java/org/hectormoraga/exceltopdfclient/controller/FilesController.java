package org.hectormoraga.exceltopdfclient.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*import java.util.List;
import java.util.stream.Collectors;

import org.hectormoraga.exceltopdfclient.message.ResponseMessage;
import org.hectormoraga.exceltopdfclient.model.FileInfo;
import org.hectormoraga.exceltopdfclient.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@CrossOrigin("http://localhost:8081")
public class FilesController {

	/*
	 * Funciona en la version que envia archivos a postman
	 * 
	 * @Autowired FilesStorageService storageService;
	 * 
	 * @PostMapping("/upload") public ResponseEntity<ResponseMessage>
	 * uploadFile(@RequestParam("file") MultipartFile file) { String message = "";
	 * try { storageService.save(file);
	 * 
	 * message = "Uploaded the file successfully: " + file.getOriginalFilename();
	 * return ResponseEntity.status(HttpStatus.OK).body(new
	 * ResponseMessage(message)); } catch (Exception e) { message =
	 * "Could not upload the file: " + file.getOriginalFilename() + "!"; return
	 * ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
	 * ResponseMessage(message)); } }
	 * 
	 * @GetMapping("/files") public ResponseEntity<List<FileInfo>> getListFiles() {
	 * List<FileInfo> fileInfos = storageService.loadAll().map(path -> { String
	 * filename = path.getFileName().toString(); String url =
	 * MvcUriComponentsBuilder .fromMethodName(FilesController.class, "getFile",
	 * path.getFileName().toString()).build().toString();
	 * 
	 * return new FileInfo(filename, url); }).collect(Collectors.toList());
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(fileInfos); }
	 * 
	 * @GetMapping("/files/{filename:.+}")
	 * 
	 * @ResponseBody public ResponseEntity<Resource> getFile(@PathVariable String
	 * filename) { Resource file = storageService.load(filename); return
	 * ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename=\"" + file.getFilename() + "\"") .body(file); }
	 */

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	@GetMapping("/uploadimage")
	public String displayUploadForm() {
		return "imageupload/index";
	}

	@PostMapping("/upload")
	public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
		return "imageupload/index";
	}
}