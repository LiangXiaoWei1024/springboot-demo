package com.imooc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.pojo.SysUser;
@Mapper
public interface SysUserMapperCustom {
	
	List<SysUser> queryUserSimplyInfoById(String id);
}