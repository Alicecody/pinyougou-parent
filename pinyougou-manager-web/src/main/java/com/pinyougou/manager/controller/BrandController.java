package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;

import org.springframework.web.bind.annotation.RequestBody;
import vo.PageResult;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    //列表
    @RequestMapping("findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    //分页
    @RequestMapping("findPage")
    public PageResult findPage(int pageNum, int pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }

    //添加
    @RequestMapping("addBrand")
    public Result addBrand(@RequestBody TbBrand tbBrand) {
        //抛出异常
        try {
            brandService.addBrand(tbBrand);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");

        }
    }

    //修改回显  根据ID回显
    @RequestMapping("findOne")
    public TbBrand findOne(long id){
      return brandService.findOne(id);
    }

    //开始修改
    @RequestMapping("update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    //批量删除
    @RequestMapping("del")
    public Result delete(long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //条件查找
    @RequestMapping("search")
    public PageResult search(@RequestBody TbBrand tbBrand,int pageNum,int pageSize){
        return  brandService.Findsearch(tbBrand,pageNum,pageSize);
    }

    //品牌下拉框
    @RequestMapping("selectOption")
    public List<Map> selectOption(){
        return brandService.selectOption();
    }

}
