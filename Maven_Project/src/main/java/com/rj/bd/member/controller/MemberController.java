package com.rj.bd.member.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.base.BaseController;
import com.rj.bd.member.entity.Member;
import com.rj.bd.member.service.IMemberService;
import com.rj.bd.utils.ExcelUtils;


/**
 * member模块的控制器
 * @author LGZ
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController{
	
	@Autowired
	public IMemberService memberService;
	
	private Map<String, Object> data;
	
	private Member member;
	
	/**
	 * @desc  查询全部
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("query");
		List<Map<String, Object>> list = memberService.findAll();
//		for (Map<String, Object> map : list) {
//			System.out.println(map);
//		}
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	/**
	 * @desc  添加
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(HttpServletRequest request,HttpServletResponse response){
		System.out.println("add");
		Member member=new Member();
		member.setM_id(UUID.randomUUID().toString());
		member.setM_name(request.getParameter("m_name"));
		member.setM_sex(request.getParameter("m_sex"));
		member.setM_tel(request.getParameter("m_tel"));
		member.setG_id(request.getParameter("g_id"));
		System.out.println(member);
		memberService.save(member);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return this.data;
	}
	
	/**
	 * @desc  条件查询
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryByName")
	public List<Map<String, Object>> queryByName(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("queryByName");
		Member member=new Member();
		member.setM_name(request.getParameter("m_name"));
		List<Map<String, Object>> list=memberService.findByName(member);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
		
	}
	
	/**
	 * @desc  删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("delete");

		Member member = new Member();
		member.setM_id(request.getParameter("m_id"));
		memberService.delete(member.getM_id());
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");

		return this.data;
	}
	
	/**
	 * @DEsc  查询一条
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody	
	@RequestMapping("/queryById")
	public Map<String, Object> queryById(HttpServletRequest request,HttpServletResponse response){
		System.out.println("queryById");
		
		String m_id=request.getParameter("m_id");
		System.out.println("m_id:"+m_id);
		Map<String, Object> map=memberService.findById(m_id);
		System.out.println("map:"+map);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		
		
		
		return map;
		
	}
	
	
	/**
	 * @desc  修改
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(HttpServletRequest request,HttpServletResponse response){
		System.out.println("update");
		Member member=new Member();
		member.setM_id(request.getParameter("m_id"));
		member.setM_name(request.getParameter("m_name"));
		member.setM_sex(request.getParameter("m_sex"));
		member.setM_tel(request.getParameter("m_tel"));
		member.setG_id(request.getParameter("g_id"));
		//System.out.println(member);
		memberService.update(member);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return this.data;
		
	}
	
	/**
	 * @desc  导出excel
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		
		System.out.println("exportExcel");
		List<Map<String , Object>> list =memberService.findAll();
		System.out.println(list);
		
		String filename = "会员名单.xls";
		ExcelUtils excelUtils = new ExcelUtils();
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetname = "会员的详细信息";
		HSSFSheet sheet = workbook.createSheet(sheetname);
		String[] tableTop = {"会员ID", "会员姓名", "性别", "电话", "会员等级"};
		String[] columnName ={"m_id", "m_name", "m_sex", "m_tel", "g_name"};
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < tableTop.length; i++) {
			row.createCell(i).setCellValue(tableTop[i]);
		}
		System.out.println("1");
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row02 = sheet.createRow(i + 1);
			sheet.autoSizeColumn(i, true);
			Map<String, Object> map = list.get(i);
			System.out.println(map);
			for (int k = 0; k < columnName.length; k++) {
				System.out.println("2");
					row02.createCell(k).setCellValue((String)map.get(columnName[k]));
			}
			
		}
		System.out.println("3");
		excelUtils.setColumnAutoAdapter(sheet, list.size());
		response.setContentType("application/ms-excel;charset=UTF-8");
 		response.setHeader("Content-Disposition", "attachment;filename="
				.concat(String.valueOf(URLEncoder.encode("报表" + ".xls",
						"UTF-8"))));
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
//		System.out.println(data);
//		return data;
	}
	
	
	/**
	 * 饼状图（会员情况）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/echartsb")
	public List<Map<String, Object>> echartsb(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("echartsb");
		List<Map<String, Object>> list=memberService.queryEchartsb();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		
		return list;
		
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

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
	
	

}
