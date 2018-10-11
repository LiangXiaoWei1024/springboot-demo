package com.imooc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.mapper.TbAreaMapper;
import com.imooc.pojo.TbArea;
import com.imooc.service.AreaService;
@Service
public class AreaServiceImpl implements AreaService {
 
	@Autowired
	private TbAreaMapper areaDao;
	@Override
	public List<TbArea> listArea() {
		return areaDao.selectAll();
	}
	@Override
	public int deleteArea(Integer areaId) {
		int deleteByPrimaryKey = areaDao.deleteByPrimaryKey(areaId);
		return deleteByPrimaryKey;
	}
	@Override
	public TbArea findById(Integer areaId) {
		TbArea tbArea = new TbArea();
		tbArea.setAreaId(areaId);
		return areaDao.selectOne(tbArea);
	}
	@Override
	public int saveArea(TbArea area) {
		Integer i = null;
		if(area.getAreaId() != null){
			i = areaDao.updateByPrimaryKey(area);
		}else{
			 i = areaDao.insert(area);
		}
		return i;
	}

}
