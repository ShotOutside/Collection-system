package com.rj.bd.record.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.rj.bd.base.BaseController;
import com.rj.bd.record.entity.Record;
import com.rj.bd.record.service.IRecordService;
import com.rj.bd.user.entity.User;
import com.rj.bd.utils.ExcelUtils;
import com.rj.bd.way.entity.Way;

@Controller
@RequestMapping("/record")
public class RecordController{
	
	@Autowired
	public IRecordService recordService;
	private Map<String, Object> data;
	private String  cr_id;
	private String uName;
	private Integer cr_money;
	private String w_id;
	private String cr_time; 
	private String cr_remark;
	
	
	
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public List<Map<String, Object>> queryByName(HttpServletRequest request,HttpServletResponse response){
		System.out.println("query");
		User user = new User();
		user.setUName(request.getParameter("uName"));
		System.out.println(user.getUName());
		List<Map<String, Object>> list = recordService.findAll(user);
		
		System.out.println(list);
		
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response){
		System.out.println("query");
		List<Map<String, Object>> list = recordService.find();
		System.out.println(list);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;

		
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String , Object> save(HttpServletRequest request ,HttpServletResponse response) throws ParseException{
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Record record = new Record();
		record.setCr_id(request.getParameter("cr_id"));
		record.setCr_money(Integer.parseInt(request.getParameter("cr_money")));
		record.setCr_time(request.getParameter("cr_time"));
		record.setCr_remark(request.getParameter("cr_remark"));
		System.out.println("add");
		User user = new User();
		user.setUName(request.getParameter("uName"));
		String u =user.getUName();
		String string =recordService.queryByuName(u);
		System.out.println(string);
		record.setUId(string);

		Way way = new Way();
		record.setW_id(request.getParameter("w_id"));
		
		System.out.println(record);
		recordService.save(record);
		Map<String, Object> data1 = new HashMap<String, Object>();
		data1.put("src", "");
		data.put("data", data1);
		this.data = print(data, "0", "success");
		return null;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String cr_id){
		Map<String , Object> data = new HashMap<String, Object>();
		System.out.println("delete");
		System.out.println("cr_id" +":" + cr_id);
		recordService.delete(cr_id);
		this.data = print(data, "0", "success");
		
		
		return this.data;
	}
	
	
	/**
	 * 将数据导出到Excel
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/exportExcel")
	public Map<String, Object> exportExcel(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		System.out.println("exportExcel");
		List<Map<String, Object>> list=recordService.find();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		String filename = "收款记录.xls";
		ExcelUtils excelUtils = new ExcelUtils();
		excelUtils.settings(request, response, filename);
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetname = "收款记录的详细信息";
		HSSFSheet sheet = workbook.createSheet(sheetname);
		String[] tableTop = {"收款记录编号", "员工", "支付方式", "收款时间", "备注","金额"};
		String[] columnName = {"cr_id", "uName", "w_name", "cr_time", "cr_remark","cr_money"};
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < tableTop.length; i++) {
			row.createCell(i).setCellValue(tableTop[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row02 = sheet.createRow(i + 1);
			sheet.autoSizeColumn(i, true);
			Map<String, Object> map = list.get(i);
			for (int k = 0; k < columnName.length; k++) {
				try {
					row02.createCell(k).setCellValue((String)map.get(columnName[k]));
				} catch (Exception e) {
					row02.createCell(k).setCellValue((Integer)map.get(columnName[k]));
					e.printStackTrace();
				}
			}
		}
		excelUtils.setColumnAutoAdapter(sheet, list.size());
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
		return data;
	}
	
	
	//生成echarts图
	@ResponseBody
	@RequestMapping("/echarts")
	public List<Map<String, Object>> echarts(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("echarts");
		List<Map<String, Object>> list=recordService.queryEcharts();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		
		
		
		return list;
		
	}
	
	
	/**
	 * @desc  分页
	 * @param data
	 * @param code
	 * @param msg
	 * @return
	 */
	
	@RequestMapping("/all")
	@ResponseBody
	public Map<String, Object> queryAllRecord(Integer page,Integer size){
		System.out.println(page);
		System.out.println(size);
		List<Map<String,Object>> list =recordService.queryAll(page,size);
		PageInfo pageInfo =new PageInfo(list);
		System.out.println(list);
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		map.put("msg", "请求成功");
		map.put("data", pageInfo);
		return map;
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	


	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}


	public void setCr_id(String cr_id) {
		this.cr_id = cr_id;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


	public void setCr_money(Integer cr_money) {
		this.cr_money = cr_money;
	}







	public void setW_id(String w_id) {
		this.w_id = w_id;
	}


	public void setCr_time(String cr_time) {
		this.cr_time = cr_time;
	}
	

	
	
	
	
	
	
}
