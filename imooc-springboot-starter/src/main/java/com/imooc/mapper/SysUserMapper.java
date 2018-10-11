package com.imooc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.pojo.SysUser;
import com.imooc.utils.MyMapper;
@Mapper
public interface SysUserMapper extends MyMapper<SysUser> {
}