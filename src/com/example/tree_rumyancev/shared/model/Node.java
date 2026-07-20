package com.example.tree_rumyancev.shared.model;

import java.io.Serializable;

public class Node implements Serializable {

	Long id;

	Long parentId;

	String name;

	String ip;

	Short port;

	public Node() {

	};

	public Node(Long id, Long parentId, String name, String ip, Short port) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.ip = ip;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Short getPort() {
		return port;
	}

	public void setPort(Short port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", parentId=" + parentId + ", name=" + name + ", ip=" + ip + ", port=" + port + "]";
	}

}
