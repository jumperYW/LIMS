package com.jumper.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_feedback", catalog = "lims")
public class TFeedback implements Serializable{
	
	private Integer id;
	private String content;
	private String userid;
	private Date createtime;
	
	public TFeedback() {
	}

	public TFeedback(Integer id, String content, String userid, Date createtime) {
		super();
		this.id = id;
		this.content = content;
		this.userid = userid;
		this.createtime = createtime;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "userid", nullable = false, length = 20)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreattime() {
		return createtime;
	}

	public void setCreattime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}
