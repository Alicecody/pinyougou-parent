package com.pinyougou.sellergoos.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;

import vo.PageResult;
import vo.Specification;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification ) {
		//获取规格实体
		TbSpecification tbspecification1 = specification.getSpecification();
		specificationMapper.insert(tbspecification1);

		//获取规格选项集合
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		//循环添加
		for (TbSpecificationOption option:specificationOptionList){
			//循环添加tbSpecificationOption.getSpecId 从specification获取id
			option.setSpecId(specification.getSpecification().getId());
			specificationOptionMapper.insert(option);//添加
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification ){
		//获取规格实体
		TbSpecification tbspecification1 = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbspecification1);

		TbSpecificationOptionExample tbSpecificationOptionExample=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();

		criteria.andSpecIdEqualTo(tbspecification1.getId());
		specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);

		//循环插入规格选项
		for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
			specificationOption.setSpecId(specification.getSpecification().getId());
			specificationOptionMapper.insert(specificationOption);
		}


	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){

		Specification specification=new Specification();
		//获取规格实体
		TbSpecification tbSpecification =specificationMapper.selectByPrimaryKey(id);
		specification.setSpecification(tbSpecification);



		TbSpecificationOptionExample tbSpecificationOptionExample=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();

		criteria.andSpecIdEqualTo(id);

		List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);
        specification.setSpecificationOptionList(tbSpecificationOptions);

		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//删除规格表
			specificationMapper.deleteByPrimaryKey(id);
            //删除规格选项表
			TbSpecificationOptionExample tbSpecificationOptionExample=new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
		}		
	}
	

	/*
	  搜索
	 */
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	//规格列表下拉
    @Override
    public List<Map> selectspecList() {
			return specificationMapper.selectspecList();
    }

}
