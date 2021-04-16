package com.rj.bd.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rj.bd.user.entity.User;
import com.rj.bd.user.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public IUserService userService;
	private Map<String, Object> data;
	private String uId;
	private String uName;
	private String uTel;
	private String uSex;
	
	
	/**
	 * @desc  查询全部
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<User> query(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("query");
		List<User> list = userService.findAll();
		for (User user : list) {
			System.out.println(user);
		}
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
		
	}
	
	/**
	 * 上传图片和添加员工信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String , Object> add(HttpServletRequest request,MultipartFile uImgs,HttpServletResponse response,String uId,String uName,String uTel,String uSex) throws IllegalStateException, IOException{
		System.out.println(uId);
		System.out.println(uName);
		System.out.println(uTel);
		System.out.println(uSex);
		System.out.println(uImgs);
		System.out.println("add");
		String originalFilename=uImgs.getOriginalFilename();
		System.out.println(originalFilename);
		int lastIndexOf = originalFilename.lastIndexOf(".");
		//获取文件的后缀名 .jpg
	    String suffix = originalFilename.substring(lastIndexOf);
	    System.out.println("文件后缀"+suffix);
	    //新的文件名
	    String imgname = UUID.randomUUID().toString() + suffix;
	    uImgs.transferTo(new File("D:\\images\\"+imgname));
		User user = new User();
		user.setUId(uId);
		user.setUName(uName);
		user.setUSex(uSex);
		user.setUTel(uTel);
		user.setUImgs(imgname);
		System.out.println(user);
		userService.save(user);
		
		Map<String, Object> map=new HashMap<String, Object>();
	     map.put("code", 200);
		 map.put("msg", "上传成功");
	     return map;
		
	}
	
	
	/**
	 * @desc  下载图片
	 * @param response
	 * @param request
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	@ResponseBody
    public Map<String, Object> downloads(HttpServletResponse response , HttpServletRequest request ,String uId) throws Exception{
        //要下载的图片地址
		
		System.out.println(uId);
		String path = "D:\\images\\";
		String fileName = "4.jpg";
		if( userService.queryImgById(uId) == null){
			fileName ="4.jpg";
		} else{
			fileName = userService.queryImgById(uId);
		}
       
       System.out.println(fileName);
        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
      

        File file = new File(path,fileName);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        Map<String, Object> map=new HashMap<String, Object>();
	     map.put("code", 200);
		 map.put("msg", "上传成功");
	     return map;
    }
	
	/**
	 * @desc  删除
	 * @param uId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uId){
		Map<String, Object> data = new HashMap<String, Object>();
		System.out.println("delete");
		System.out.println("uId" +":"+ uId);
		userService.deleterecord(uId);
		userService.delete(uId);
		this.data = print(data, "0", "success");
		return this.data;
	}
	
	@ResponseBody
	@RequestMapping("/queryById")
	public Map<String, Object> queryById(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("findById");
		String uId=request.getParameter("uId");
		System.out.println("uId:"+uId);
		Map<String, Object> map=userService.findById(uId);
		System.out.println("map:"+map);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return map;
	}
	/**
	 * @desc  删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("update");
		User user=new User();
		user.setUId(request.getParameter("uId"));
		user.setUName(request.getParameter("uName"));
		user.setUSex(request.getParameter("uSex"));
		user.setUTel(request.getParameter("uTel"));
		System.out.println("user:"+user);
		userService.update(user);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "0");
		System.out.println("this.data"+this.data);
		
		return this.data;
	}

	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


	public void setuTel(String uTel) {
		this.uTel = uTel;
	}


	public void setuSex(String uSex) {
		this.uSex = uSex;
	}




	
	
}
