package com.rj.bd.root.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

//c层
@Controller
@RequestMapping("/root")
public class RootController{

	@Autowired
	public IRootService rootService;
	
	
	private Map<String, Object> data;

	

	
	
	/**
	 * @desc  登录验证
	 * @param r_username
	 * @param r_password
	 * @return
	 */
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
	
	
	
	
	
	/**
	 * @desc  查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<Root> query(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("query");
		List<Root> list = rootService.findAll();
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	/**
	 * @desc  上传图片
	 * @param response
	 * @param r_name
	 * @param r_id
	 * @param r_password
	 * @param r_username
	 * @param r_sex
	 * @param r_age
	 * @param r_remark
	 * @param request
	 * @param uploadFile
	 * @return
	 * @throws IOException
	 */
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
	     uploadFile.transferTo(new File("D:\\images\\"+imgname));
	     
	     r_id=UUID.randomUUID().toString();
	     System.out.println(r_id);
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
	     System.out.println(root);
	     Map<String, Object> map=new HashMap<String, Object>();
	     map.put("code", 200);
		 map.put("msg", "上传成功");
	     return map;

		
	}
	
	/**
	 * @desc  下载
	 * @param data
	 * @param code
	 * @param msg
	 * @return
	 */
	
	@RequestMapping("/download")
	@ResponseBody
	
	
	public Map<String, Object> downloads(HttpServletResponse response , HttpServletRequest request ,String r_username) throws Exception{
        //要下载的图片地址
		
		System.out.println(r_username);
		String path = "D:\\images";
		System.out.println(path);
		String fileName = "4.jpg";
		if( rootService.queryByIdAvatar(r_username) == null){
			fileName ="4.jpg";
		} else{
			fileName = rootService.queryByIdAvatar(r_username);
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
		 map.put("msg", "下载成功");
	     return map;
    }
	
	

	
	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}
	
		
	
}



