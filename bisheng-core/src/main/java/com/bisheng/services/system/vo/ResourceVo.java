package com.bisheng.services.system.vo;

import java.util.List;

import com.bisheng.services.system.model.generated.Resource;

public class ResourceVo {
	// 菜单
	private Resource menu;
	// 子菜单
	private List<Resource> childs;

	// Node属性
	private Long id;
	private String text;
	private String type;
	private State state;
	private List<ResourceVo> children;

	public class State {
		Boolean selected;

		public Boolean isSelected() {
			return selected;
		}

		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
	}

	public void setSelected(boolean selected) {
		State state = new State();
		state.setSelected(selected);
		setState(state);
	}

	public List<ResourceVo> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceVo> children) {
		this.children = children;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Resource getMenu() {
		return menu;
	}

	public void setMenu(Resource menu) {
		this.menu = menu;
	}

	public List<Resource> getChilds() {
		return childs;
	}

	public void setChilds(List<Resource> childs) {
		this.childs = childs;
	}
}
