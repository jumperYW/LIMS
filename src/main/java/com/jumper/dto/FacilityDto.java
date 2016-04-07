package com.jumper.dto;

import java.util.Date;

public class FacilityDto {

	private Integer id;
	private String facid;
	private String name;
	private String productor;
	private String type;
	private String director;
	private Date buydate;
	private Integer num;
	private String location;
	private int state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFacid() {
		return facid;
	}
	public void setFacid(String facid) {
		this.facid = facid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public FacilityDto() {}
	public FacilityDto(Integer id, String facid, String name, String productor, String type, String director,
			Date buydate, Integer num, String location, int state) {
		super();
		this.id = id;
		this.facid = facid;
		this.name = name;
		this.productor = productor;
		this.type = type;
		this.director = director;
		this.buydate = buydate;
		this.num = num;
		this.location = location;
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "FacilityDto [id=" + id + ", facid=" + facid + ", name=" + name + ", productor=" + productor + ", type="
				+ type + ", director=" + director + ", buydate=" + buydate + ", num=" + num + ", location=" + location
				+ ", state=" + state + "]";
	}
	
}
