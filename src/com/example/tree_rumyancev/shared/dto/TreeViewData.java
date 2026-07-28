package com.example.tree_rumyancev.shared.dto;
import java.util.ArrayList;
import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
public class TreeViewData {
	
	private Long nodeId;
	
	private Long parentId;
	
	private String name;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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
	
	public static TreeViewData toViewData(Node node) 
	{
		TreeViewData result = new TreeViewData();
		result.setNodeId(node.getId());
		result.setParentId(node.getParentId());
		result.setName(node.getName());
		return result;
		
	}
	
	public static List<TreeViewData> toViewDataList(List<Node> nodes)
	{
		List<TreeViewData> result = new ArrayList<TreeViewData>();
		for (Node i : nodes) {
			result.add(TreeViewData.toViewData(i));
		}
		return result;
	}
}
