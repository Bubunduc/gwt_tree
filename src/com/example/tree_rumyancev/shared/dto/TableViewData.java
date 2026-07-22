package com.example.tree_rumyancev.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

public class TableViewData {

	Long nodeId;

	String name;

	String ip;

	Short port;

	public static TableViewData toViewData(Node node) {
		TableViewData result = new TableViewData();

		result.setNodeId(node.getId());
		result.setName(node.getName());
		result.setIp(node.getIp());
		result.setPort(node.getPort());

		return result;
	}

	public static List<TableViewData> toViewDataList(List<Node> nodes) {

		List<TableViewData> result = new ArrayList<TableViewData>();

		for (Node i : nodes) {

			result.add(toViewData(i));

		}

		return result;

	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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
}
