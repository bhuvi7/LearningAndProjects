package com.example.demo.EntityModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String menuName;
	String menuType;
	Integer menuId;
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(Integer id, String menuName, String menuType, Integer menuId) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuId = menuId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuType=" + menuType + ", menuId=" + menuId + "]";
	}
	
	
	
	

}
