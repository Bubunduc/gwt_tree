package com.example.tree_rumyancev.client.dto;

public class SelectedNodeData {

	private Long id;
	private Long parentId;

	private String name;
	private String ip;
	private String port;

	public SelectedNodeData(Long id, Long parentId, String name, String ip, String port) {

		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.ip = ip;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}
}