package com.techblog.controllers;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techblog.payload.FileResponse;
import com.techblog.service.FileService;

@RestController
public class FileServiceController {
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	public String path;
	
	

	@PostMapping("/upload/{postId}")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam(value = "image", required = false ) MultipartFile image,@PathVariable("postId")Integer postId) {
		String fileName=null;
		System.out.println(path);
			try {
				fileName = this.fileService.uploadImage(path, image,postId);
				System.out.println(fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				
				return new ResponseEntity<FileResponse>(new FileResponse(null,"failed"), HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			System.out.println(fileName+"exception");
			return new ResponseEntity<FileResponse>(new FileResponse(fileName,"file uploaded Sucessfully"), HttpStatus.OK);

		}

	@GetMapping(value="/recieve/{postId}",produces = MediaType.IMAGE_JPEG_VALUE )
	public void RecieveFile(@PathVariable("postId")Integer postId, ServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path,postId);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
	StreamUtils.copy(resource, response.getOutputStream());
		
	
	}
}
