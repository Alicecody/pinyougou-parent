package com.pinyougou.sellergoos.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import vo.PageResult;

import java.util.List;
import java.util.Map;

@Service
public class Brandserviceimpl implements BrandService {

    @Autowired
    TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    //分页
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TbBrand> pageInfo= (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    }

    //添加
    @Override
    public void addBrand(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    //修改
    @Override
    public TbBrand findOne(long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    //开始修改
    @Override
    public void update(TbBrand tbBrand) {
          tbBrandMapper.updateByPrimaryKey(tbBrand);
    }


    //批量删除
    @Override
    public void delete(long[] id) {
        for (long ids : id) {
            tbBrandMapper.deleteByPrimaryKey(ids);
        }
    }

    //条件查询 + 分页
    @Override
    public PageResult Findsearch(TbBrand tbBrand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        // 模糊查询条件
            TbBrandExample tbBrandExample=new TbBrandExample();
            TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();

            //判断传来的参数是否为空
            if(tbBrand!=null){
            if(tbBrand.getName()!=null&& tbBrand.getName().length()>0){
                //模糊查询
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
        }
        Page<TbBrand> pageInfo= (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    }

    //品牌下拉框查找
    @Override
    public List<Map> selectOption() {
        return tbBrandMapper.selectOption();
    }
}
