package com.example.tree_rumyancev.shared.model;

import java.io.Serializable;
import java.util.List;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeNode implements Serializable{

	private Node parentNodeId;
	private List<Node> childNodes;
	
	public Node getparentNodeId() {
		return parentNodeId;
	}
	
	public void setparentNodeId(Node parentNode) {
		this.parentNodeId = parentNode;
	}
	
	public List<Node> getChildNodes() {
		return childNodes;
	}
	
	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}

	public TreeNode(Node parentNode, List<Node> childNodes) {
		this.parentNodeId = parentNode;
		this.childNodes = childNodes;
	}
	

}
