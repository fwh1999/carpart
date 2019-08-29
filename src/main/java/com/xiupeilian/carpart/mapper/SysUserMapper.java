package com.xiupeilian.carpart.mapper;

import com.xiupeilian.carpart.base.BaseMapper;
import com.xiupeilian.carpart.model.SysUser;
import com.xiupeilian.carpart.vo.LoginVo;
import com.xiupeilian.carpart.vo.StaffVo;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findUserByLoginNameAndPassword(LoginVo vo);

    SysUser findUserByLoginNameAndEmail(LoginVo vo);

    List<SysUser> findUserByCompanyId(Integer companyId);

    List<SysUser> findUserByCompanyIdAndUserName(StaffVo vo);

    SysUser findUserByLoginName(String loginName);

    SysUser findUserByPhone(String telnum);

    SysUser findUserByEmail(String email);
}