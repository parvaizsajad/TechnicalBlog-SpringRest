package com.techblog.serviceImpul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techblog.entity.Post;
import com.techblog.repository.PostRepository;
import com.techblog.service.FileService;

@Service
public class FileServiceImpul implements FileService {
	
	@Autowired
	private PostRepository postRepository;
	
	

	@Override
	public String uploadImage(String path, MultipartFile file,Integer postId) throws IOException {
		
		Post post = this.postRepository.findById(postId).get();
	
		
	
		
		String ramdomId=UUID.randomUUID().toString();
	
		//get the name of file example default.png
		String name = file.getOriginalFilename();
		System.out.println(name);
		String fileNme = ramdomId.concat(name.substring(name.lastIndexOf(".")));
		
		post.setImageName(fileNme);
		this.postRepository.save(post);
		//Path where images are saved example images/default.png
		String FilePath=path+File.separator+fileNme;
		
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
	Files.copy(file.getInputStream(), Paths.get(FilePath));
		
		return fileNme;
	}

	@Override
	public InputStream getResource(String path,Integer postId) throws FileNotFoundException {
		Post post = this.postRepository.findById(postId).get();
		System.out.println(post.getImageName());
	String fullPath=path+File.separator+post.getImageName();
	
	FileInputStream fin=new FileInputStream(fullPath);
		return fin;
	}

}
