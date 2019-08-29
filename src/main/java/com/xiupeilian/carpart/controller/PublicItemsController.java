package com.xiupeilian.carpart.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiupeilian.carpart.model.Brand;
import com.xiupeilian.carpart.model.Items;
import com.xiupeilian.carpart.model.Parts;
import com.xiupeilian.carpart.service.BrandService;
import com.xiupeilian.carpart.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicItemsController {
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private BrandService brandService;

    @RequestMapping("/publicItems")
    public String publicItems(Items items, Integer pageSize, Integer pageNo, String brandName, HttpServletRequest request){
        //初始化分页
        pageSize=pageSize==null?8:pageSize;
        pageNo=pageNo==null?1:pageNo;
        //查询所有货物
        List<Items> itemsList = itemsService.findItemsByQueryVo(items);
        //分页查询
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Items> page = new PageInfo<>(itemsList);
        //初始化品牌，配件数据
        List<Brand> brandList = brandService.findBrandAll();
        List<Parts> partsList = brandService.findPartsAll();
        //存储数据
        request.setAttribute("page",page);
        request.setAttribute("items",items);
        request.setAttribute("brandList",brandList);
        request.setAttribute("partsList",partsList);
        request.setAttribute("brandName",brandName);
        return "public/publicItems";
    }
}
