package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import vo.PageResult;


import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<TbBrand> findAll();

    //分页 条件查找
    public PageResult findPage(int pageNum, int pageSize);

    //添加
    public void addBrand(TbBrand tbBrand);

    //修改
    public TbBrand findOne(long id);

    //开始修改
    void update(TbBrand tbBrand);

    //批量删除
    void delete(long[] id);

    //条件查询 分页
    PageResult Findsearch(TbBrand tbBrand, int pageNum, int pageSize);

    //查找品牌
    List<Map> selectOption();
}
