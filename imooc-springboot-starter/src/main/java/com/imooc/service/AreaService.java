package com.imooc.service;

import java.util.List;

import com.imooc.pojo.TbArea;

public interface AreaService {
	public List<TbArea> listArea();

	public int deleteArea(Integer areaId);

	public TbArea findById(Integer areaId);

	public int saveArea(TbArea area);
}
