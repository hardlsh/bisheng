package com.bisheng.apps.system.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisheng.apps.system.business.ResourceBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.model.customized.ResourceModel;
import com.bisheng.services.system.model.generated.Resource;
import com.bisheng.services.system.model.generated.ResourceExample;
import com.bisheng.services.system.service.ResourceService;
import com.bisheng.services.system.vo.ResourceVo;

@Service
public class ResourceBusinessImpl implements ResourceBusiness {
	//创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(ResourceBusinessImpl.class);
	
	@Autowired
	private ResourceService resourceService;

	@Override
	public List<ResourceModel> queryUserResourceByParam(AuthParam param) {
		return resourceService.queryUserResourceByParam(param);
	}

	@Override
	public List<ResourceVo> queryPackageMenuUserResources(AuthParam param) {
		List<ResourceVo> voList = new ArrayList<>();
		List<ResourceModel> resourceList = resourceService.queryUserResourceByParam(param);
		// 先放入一级菜单
		for (ResourceModel menu : resourceList) {
			if (menu.getLevel() == 1) {
				ResourceVo vo = new ResourceVo();
				vo.setMenu(menu);
				vo.setChilds(new ArrayList<Resource>());
				voList.add(vo);
			}
		}
		// 放入二级菜单
		for (ResourceModel menu : resourceList) {
			if (menu.getLevel() == 2) {
				for (ResourceVo vo : voList) {
					if (vo.getMenu().getResourceId() == menu.getParentId()) {
						vo.getChilds().add(menu);
						break;
					}
				}
			}
		}
		logger.info("【资源管理】获取到的一级菜单长度:" + voList.size());
		return voList;
	}

	@Override
	public List<ResourceVo> queryPackageAllResources(Resource resource) {
		ResourceExample example = convertResourceToExample(resource);
		// 查询所有资源
		List<Resource> allList = resourceService.selectByExample(example);
		
		// 按可用状态进行选中
		return getPackageResources(allList, allList, AvailStatusEnum.AVAILABLE.getKey());
	}

	@Override
	public List<ResourceVo> queryPackageAllRoleResources(AuthParam param) {
		Resource resource = new Resource();
		resource.setStatus(param.getResourceStatus());
		ResourceExample example = convertResourceToExample(resource);
		// 查询所有资源
		List<Resource> allList = resourceService.selectByExample(example);
		List<ResourceModel> selectedList = resourceService.queryRoleResourceByParam(param);
		// 如果有子菜单,就不勾选父菜单
		return getPackageResourcesCancelChecked(allList, selectedList, null);
	}
	
	@Override
	public List<Resource> queryResourceByExample(Resource resource) {
		ResourceExample example = convertResourceToExample(resource);
		return resourceService.selectByExample(example);
	}
	
	@Override
	public int addResource(Resource resource) {
		return resourceService.addResource(resource);
	}
	
	@Override
	public int updateResourceById(Resource resource) {
		return resourceService.updateResourceById(resource);
	}
	
	/**
	 * 转换参数    resource-->example																							
	 */
	private ResourceExample convertResourceToExample(Resource resource){
		ResourceExample example = new ResourceExample();
		ResourceExample.Criteria criteria = example.createCriteria();
		if (resource.getStatus() != null) {
			criteria.andStatusEqualTo(resource.getStatus());
		}
		if (resource.getResourceType() != null) {
			criteria.andResourceTypeEqualTo(resource.getResourceType());
		}
		if (resource.getResourceId() != null) {
			criteria.andResourceIdEqualTo(resource.getResourceId());
		}
		if (resource.getLevel() != null) {
			criteria.andLevelEqualTo(resource.getLevel());
		}
		return example;
	}
	/**
	 * 组装资源,并进行相应选中																									
	 * @param: allList 全部资源; selectedList 需要选中的资源
	 * @param: status 可用状态的资源
	 */
	private List<ResourceVo> getPackageResources(List<Resource> allList, @SuppressWarnings("rawtypes") List selectedList, Integer status) {
		List<ResourceVo> voList = new ArrayList<>();//一级菜单
		// 选中可用的资源
		Map<Long, Resource> selectedMap = new HashMap<>();
		if (selectedList != null && selectedList.size() > 0) {
			for (int i=0; i<selectedList.size(); i++) {
				Resource res = (Resource) (selectedList.get(i));
				if (status == null) {
					selectedMap.put(res.getResourceId(), res);
				}else if (status != null && res.getStatus() == status) {
					selectedMap.put(res.getResourceId(), res);
				}
			}
		}
		// 放入一级菜单
		for (Resource res : allList) {
			if (res.getLevel() == 1) {
				ResourceVo vo = new ResourceVo();
				vo.setId(res.getResourceId());
				vo.setText(res.getResourceName());
				vo.setChildren(new ArrayList<ResourceVo>());
				if (selectedMap.containsKey(res.getResourceId())) {
					vo.setSelected(true);
				}else {
					vo.setSelected(false);
				}
                voList.add(vo);
			}
		}
		//放入二级菜单
		for (Resource res : allList) {
			if (res.getLevel() == 2) {
				for (ResourceVo resVo : voList) {
					if (resVo.getId().equals(res.getParentId())) {
						ResourceVo vo = new ResourceVo();
						vo.setId(res.getResourceId());
						vo.setText(res.getResourceName());
						vo.setChildren(new ArrayList<ResourceVo>());
						if (selectedMap.containsKey(res.getResourceId())) {
							vo.setSelected(true);
						}else {
							vo.setSelected(false);
						}
						resVo.getChildren().add(vo);
						break;
					}
				}
			}
		}
		//放入三级按钮
		for (Resource res : allList) {// 遍历全部可用的资源
			if (res.getLevel() == 3) {
				for (ResourceVo resVo : voList) {// 遍历一级菜单
					if (resVo.getChildren().size() > 0) {// 有二级菜单
						for (ResourceVo resVo2 : resVo.getChildren()) {// 遍历二级菜单
							if (resVo2.getId().equals(res.getParentId())) {
								ResourceVo vo = new ResourceVo();
								vo.setId(res.getResourceId());
								vo.setText(res.getResourceName());
								if (selectedMap.containsKey(res.getResourceId())) {
									vo.setSelected(true);
								}else {
									vo.setSelected(false);
								}
								resVo2.getChildren().add(vo);
								break;
							}
						}
					}
				}
			}
		}
		return voList;
	}
	/**
	 * 组装资源,并进行相应选中	 !!!如果有子节点,取消父节点的选中																								
	 * @param: allList 全部资源; selectedList 需要选中的资源
	 * @param: status 可用状态的资源
	 */
	private List<ResourceVo> getPackageResourcesCancelChecked(List<Resource> allList, @SuppressWarnings("rawtypes") List selectedList, Integer status) {
		List<ResourceVo> cancelChecked = getPackageResources(allList, selectedList, status);
		for (ResourceVo firstMenu : cancelChecked) {
			if (!firstMenu.getState().isSelected()) {
				continue;// 一级菜单没有被选中,直接跳过
			}
			List<ResourceVo> secondMenus = firstMenu.getChildren();
			if (secondMenus == null || secondMenus.size() == 0) {
				continue;
			}
			firstMenu.setSelected(false);// 取消一级菜单的选中
			for (ResourceVo secondMenu : secondMenus) {
				if (secondMenu.getChildren() != null && secondMenu.getChildren().size() > 0) {
					secondMenu.setSelected(false);// 取消二级菜单的选中
				}
			}
		}
		
		return cancelChecked;
	}

}
