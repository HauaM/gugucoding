package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		
		log.info("basic.............");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		return "ex01";
	}
	
	@GetMapping("ex02")
	public void ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name : " + name);
		log.info("age : " + age);
	}
	
	@GetMapping("/ex02List")
	public void ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids : " +ids);
		
	}
	
	@GetMapping("/ex02Bean")
	public void ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		
	}
	
//	http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
	@GetMapping("/ex03")
	
	public void ex03(TodoDTO todo) {
		log.info("todo : " +todo);
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dataFormat, false));
	}
	
	//http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
	//int 타입의(기본형) page는 View에 전달 되지 않는다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page) {
		log.info("dto : "+ dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	//http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
	@GetMapping("/ex04MA")
	public String ex04MA(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : "+ dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	//VO,DTO등 객체를 JSON 형태로 view에 전송
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06............");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("ex07..............");
		
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload..............");
	}
	
	@ExceptionHandler(Exception.class)
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("---------------------------------");
			log.info("name :" +file.getOriginalFilename());
			log.info("size : " +file.getSize());
		});
		
	}
}
