package com.imooc.controller;




import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.pojo.TreeNode;
import com.imooc.tasks.Run;
import com.imooc.utils.TreeBuilder;
import com.imooc.utils.WeatherReportByCity;





@RestController
@RequestMapping("sys")
public class SysUserController {
 
	@Autowired
	private StringRedisTemplate strRedis;
	@Autowired
	private Run run;
	
	/**
	 * 第三方接口发送短信
	 * @return
	 */
	@RequestMapping("/sendMsg")
	public ModelAndView sendMsg()  {
		//String url = "http://v.juhe.cn/sms/send";
		 //Map params = new HashMap();//请求参数
        // params.put("mobile","13530263029");//接收短信的手机号码
        // params.put("tpl_id","106560");//短信模板ID，请参考个人中心短信模板设置
        // params.put("tpl_value","#code#=520520");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        // params.put("key","063baa07e7e40c49494ebbc38045d5a7");//应用APPKEY(应用详细页查询)
         //params.put("dtype","json");//返回数据的格式,xml或json，默认json
       //  WeatherReportByCity.sendGETRequest(url,params,"utf8");
        ModelAndView model = new ModelAndView();
        model.setViewName("/landing");
		return model;
		
		//return WeatherReportByCity.excute("深圳");
	}
	
	/**
	 * 查询天气接口
	 * @return
	 */
	@RequestMapping("/selectByWeather")
	public String aaa()  {
		return WeatherReportByCity.excute("深圳");
	}
	
	/**
	 * 返回相应的页面
	 * @param page
	 * @return
	 */
	@RequestMapping("{page}")
	public ModelAndView MV(@PathVariable("page") String page)  {
		ModelAndView model = new ModelAndView();
		model.setViewName(page);
		return model;
	}
	
	
	/**
	 * 上传Excel并解析
	 * @param file
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scExcel")
	 public Map<?,?> scExcel(@RequestParam("file") MultipartFile file,String name) throws Exception {
		Map<String,String> map = new HashMap<>();


		 if (file.isEmpty()) {
			 	map.put("err", "请传文件过来");
			 	map.put("data", "401");
	            return map;
	        }
		 	if(file.getSize() > 1024000000) {
		 		map.put("err", "文件大小超出");
		 		map.put("data", "402");
		 		return map;
		 	}
		 	 	boolean notNull = false;
		        if (!file.getOriginalFilename().matches("^.+\\.(?i)(xls)$") && !file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
		            throw new Exception("上传文件格式不正确");
		        }
		        boolean isExcel2003 = true;
		        if (file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
		            isExcel2003 = false;
		        }
		        InputStream is = file.getInputStream();
		        Workbook wb = null;
		        if (isExcel2003) {
		            wb = new HSSFWorkbook(is);
		        } else {
		            wb = new XSSFWorkbook(is);
		        }
		        Sheet sheet = wb.getSheetAt(0);
		        if(sheet!=null){
		            notNull = true;
		        }
		        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
		            Row row = sheet.getRow(r);
		            if (row == null){
		                continue;
		            }
		 
		            
		            if( row.getCell(0).getCellType() !=1){
		                throw new Exception("导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
		            }
		            String name1 = row.getCell(0).getStringCellValue();
		 
		            if(name1 == null || name1.isEmpty()){
		                throw new Exception("导入失败(第"+(r+1)+"行,姓名未填写)");
		            }
		 
		            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		            String phone = row.getCell(1).getStringCellValue();
		            if(phone==null || phone.isEmpty()){
		                throw new Exception("导入失败(第"+(r+1)+"行,电话未填写)");
		            }
		            String add = row.getCell(2).getStringCellValue();
		            if(add==null){
		                throw new Exception("导入失败(第"+(r+1)+"行,不存在此单位或单位未填写)");
		            }
		 
		            Date date;
		            if(row.getCell(3).getCellType() !=0){
		                throw new Exception("导入失败(第"+(r+1)+"行,入职日期格式不正确或未填写)");
		            }else{
		                date = row.getCell(3).getDateCellValue();
		            }
		 
		            String des = row.getCell(4).getStringCellValue();
		        }
		        return map;
		    }
	       


	/**
	 * 上传图片
	 * @param file
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scFile")
	 public Map<?,?> saveFiles(@RequestParam("file") MultipartFile file,String name) throws Exception {
		 
		Map<String,String> map = new HashMap<>();
		 if (file.isEmpty()) {
			 	map.put("err", "请传文件过来");
			 	map.put("data", "401");
	            return map;
	        }
		 
		 	//获取文件大小
		 	
		 	if(file.getSize() > 102400) {
		 		map.put("err", "文件大小超出");
		 		map.put("data", "402");
		 		return map;
		 	}
		 	
		 	BufferedImage image;
			try {
				image = ImageIO.read(file.getInputStream());
				if (image != null) {//如果image=null 表示上传的不是图片格式
				 	System.out.println(image.getWidth());//获取图片宽度，单位px
				 	System.out.println(image.getHeight());//获取图片高度，单位px
				 	}else{
				 		map.put("err", "文件不是图片");
					 	map.put("data", "403");
					 	return map;
				 	}
			} catch (IOException e1) {
				System.out.println(e1.getMessage());//获取图片宽度，单位px
			 	e1.printStackTrace();
			}
		 	
	        // 获取文件名
	        String fileName = file.getOriginalFilename();
	        // 获取文件的后缀名
	        String suffixName = fileName.substring(fileName.lastIndexOf("."));
	        // 文件上传后的路径
	        String filePath = "C://aaa//";
	        // 解决中文问题，liunx下中文路径，图片显示问题
	         fileName = UUID.randomUUID().toString().replace("-","") + suffixName;
	         map.put("data", fileName);
	        File dest = new File(filePath + fileName);
	        // 检测是否存在目录
	        if (!dest.getParentFile().exists()) {
	            dest.getParentFile().mkdirs();
	        }
	        try {
	            file.transferTo(dest);
	            return map;
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        map.put("data", "500");
	        return map;
	}
	
	/**
	 * 解析ztree格式案例
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getArea")
	public Object getAreaList() throws IOException  {
		
		 	TreeNode treeNode1 = new TreeNode("110000","北京","100000");
	        TreeNode treeNode2 = new TreeNode("110100","北京市","110000");
	        TreeNode treeNode3 = new TreeNode("110101","东城区","110100");
	        TreeNode treeNode4 = new TreeNode("110102","西城区","110100");
	        TreeNode treeNode5 = new TreeNode("110103","朝阳区","110100");
	        TreeNode treeNode6 = new TreeNode("110104","丰台区","110100");
	        TreeNode treeNode7 = new TreeNode("110105","百脑汇","110100");
	        TreeNode treeNode8 = new TreeNode("110106","石景山区","110100");
	        TreeNode treeNode9 = new TreeNode("110107","海淀区","110100");
	        TreeNode treeNode10 = new TreeNode("110108","门头沟区","110100");
	        
	        TreeNode treeNode11 = new TreeNode("1","广东省","100000");
	        TreeNode treeNode21 = new TreeNode("2","深圳市","1");
	        TreeNode treeNode31 = new TreeNode("3","东城区","2");
	        TreeNode treeNode41 = new TreeNode("4","西城区","2");
	        TreeNode treeNode51 = new TreeNode("5","朝阳区","2");
	        TreeNode treeNode61 = new TreeNode("6","丰台区","2");
	        TreeNode treeNode71 = new TreeNode("7","百脑汇","2");
	        TreeNode treeNode81 = new TreeNode("8","石景山区","2");
	        TreeNode treeNode91 = new TreeNode("9","海淀区","2");
	        TreeNode treeNode101 = new TreeNode("10","门头沟区","2");
	        
	        List<TreeNode> list = new ArrayList<TreeNode>();
	        
	        list.add(treeNode1);
	        list.add(treeNode2);
	        list.add(treeNode3);
	        list.add(treeNode4);
	        list.add(treeNode5);
	        list.add(treeNode6);
	        list.add(treeNode7);
	        list.add(treeNode8);
	        list.add(treeNode9);
	        list.add(treeNode10);
	        
	        list.add(treeNode11);
	        list.add(treeNode21);
	        list.add(treeNode31);
	        list.add(treeNode41);
	        list.add(treeNode51);
	        list.add(treeNode61);
	        list.add(treeNode71);
	        list.add(treeNode81);
	        list.add(treeNode91);
	        list.add(treeNode101);
	 
	        List<TreeNode> trees = TreeBuilder.bulid(list);
	 
	       // List<TreeNode> trees_ = TreeBuilder.buildByRecursive(list);

		return trees;
	}
	
	
	
}
