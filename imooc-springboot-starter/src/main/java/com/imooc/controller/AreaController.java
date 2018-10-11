package com.imooc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.mapper.TbAreaMapper;
import com.imooc.pojo.TbArea;
import com.imooc.service.AreaService;

@RestController
@RequestMapping("/superadmin")
public class AreaController {
  
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/listarea")
	public Map<?,?> listArea(){
		Map<Object, Object> hashMap = new HashMap<>();
		List<TbArea> select = areaService.listArea();
		hashMap.put("areaList", select);
		return hashMap;
	}
	
	@RequestMapping("/deletearea")
	public Map<?,?> deleteArea(Integer areaId){
		Map<Object, Object> hashMap = new HashMap<>();
		int deleteArea = areaService.deleteArea(areaId);
		boolean d=true;
		if(deleteArea<1){
			d=false;
		}
		hashMap.put("success", d);
		return hashMap;
	}
	
	@RequestMapping("/area")
	public Map<?,?> area(Integer areaId){
		Map<Object, Object> hashMap = new HashMap<>();
		TbArea area = areaService.findById(areaId);
		hashMap.put("area", area);
		return hashMap;
	}
	
	@RequestMapping("/saveArea")
	public Map<?,?> saveArea(@RequestBody TbArea area){
		Map<Object, Object> hashMap = new HashMap<>();
		Integer i = areaService.saveArea(area);
		boolean b = true;
		if(i<0 || i== null){
			b = false;
		}
		hashMap.put("success", b);
		return hashMap;
	}
	
}
