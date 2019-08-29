package com.xiupeilian.carpart.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiupeilian.carpart.model.SysUser;
import com.xiupeilian.carpart.service.UserService;
import com.xiupeilian.carpart.vo.StaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 员工管理模板
 * @Author: Tu Xu
 * @CreateDate: 2019/8/22 9:44
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toStaffList")
    public String toStaffList(Integer pageSize,Integer pageNum,HttpServletRequest request){
        pageSize=pageSize==null?10:pageSize;
        pageNum=pageNum==null?1:pageNum;
        PageHelper.startPage(pageNum,pageSize);
        //获取登录用户
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        //根据用户对应的companyId查询同一公司下所有员工；
        List<SysUser> list = userService.findUserByCompanyId(user.getCompanyId());
        PageInfo<SysUser> staffList = new PageInfo<>(list);
        request.setAttribute("staffList",staffList);
        return "staff/staffList";
    }

    @RequestMapping("/findStaffName")
    public String findStaffName(StaffVo vo, HttpServletRequest request){
        vo.setPageSize(vo.getPageSize()==null?10:vo.getPageSize());
        vo.setPageNum(vo.getPageNum()==null?1:vo.getPageNum());
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        vo.setCompanyId(user.getCompanyId());
        System.out.println("VocompanyId:"+vo.getCompanyId());
        List<SysUser> list = userService.findUserByCompanyIdAndUserName(vo);
        PageInfo<SysUser> staffList = new PageInfo<>(list);
        request.setAttribute("StaffVo",vo);
        request.setAttribute("staffList",staffList);
        return "staff/staffList";
    }



}
