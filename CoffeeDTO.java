package com.ssafy.sample.dto;

public class CoffeeDTO {
	private String code;
	private String name;
	private int price;
	private String beans;
	private String manager;
	
	public CoffeeDTO() {
		
	}
	
	public CoffeeDTO(String code, String name, int price, String beans, String manager) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.beans = beans;
		this.manager = manager;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBeans() {
		return beans;
	}

	public void setBeans(String beans) {
		this.beans = beans;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Coffe [code=" + code + ", name=" + name + ", price=" + price + ", beans=" + beans + ", manager="
				+ manager + "]";
	}
}
