package com.rj.bd.root.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rj.bd.base.BaseController;
import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.user.entity.User;


@Controller
@RequestMapping("/root")
public class RootController{

	@Autowired
	public IRootService rootService;
	private Map<String, Object> data;
	private File upload;

	

	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestParam("r_username") String r_username,@RequestParam("r_password") String r_password){
		System.out.println("login");
		System.out.println(r_username);
		System.out.println(r_password);
		Map<String , Object> map = new HashMap<String, Object>();
		Root tRoot = new Root();
		tRoot =rootService.findRootByname(r_username);
		
		if (tRoot==null) {
			map.put("code","-1");
			map.put("msg", "用户名不存在");
			return map;
		} else {
				
			String password1 =tRoot.getR_password();
			if(password1.equals(r_password)){
				map.put("code","200");
				map.put("msg","登录成功");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("token" ,tRoot.getToken());
				map.put("data", data);
				return map;
			}else {
				map.put("code","-1");
				map.put("msg","密码错误");
				return map;
			}
		}
		
		
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Root> query(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("query");
		List<Root> list = rootService.findAll();
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> add(HttpServletResponse response ,String r_name,String r_id, String r_password, String r_username , String r_sex, String r_age, String r_remark ,HttpServletRequest request,MultipartFile uploadFile ) throws IOException {
		
		
		System.out.println(r_name);
		System.out.println(r_password);
		System.out.println(r_username);
		System.out.println(r_sex);
		System.out.println(r_age);
		System.out.println(r_remark);
		String originalFilename = uploadFile.getOriginalFilename();
		System.out.println(originalFilename);
		int lastIndexOf = originalFilename.lastIndexOf(".");
		//获取文件的后缀名 .jpg
	     String suffix = originalFilename.substring(lastIndexOf);
	     System.out.println("文件后缀"+suffix);
	     
	   //新的文件名
	     String imgname = UUID.randomUUID().toString() + suffix;
	     uploadFile.transferTo(new File("C:\\Users\\赵宇飞\\Documents\\GitHub\\Collection-system\\Maven_Project\\src\\main\\webapp\\WEB-INF\\imgs\\"+imgname));
	     r_id=UUID.randomUUID().toString();
	     Root root = new Root();
	     root.setR_id(r_id);
	     root.setR_name(r_name);
	     root.setR_img(imgname);
	     root.setR_username(r_username);
	     root.setR_password(r_password);
	     root.setR_remark(r_remark);
	     root.setR_age(r_age);
	     root.setR_sex(r_sex);
	     root.setR_img(imgname);
	     rootService.saveImg(root);
	     Map<String, Object> map=new HashMap<String, Object>();
	     map.put("code", 200);
		 map.put("msg", "上传成功");
	     return map;

		
	}
	
	
	




	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}
	
		
	
}



