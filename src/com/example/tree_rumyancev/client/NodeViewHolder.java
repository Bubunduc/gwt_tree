package com.example.tree_rumyancev.client;

import java.util.Set;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public class NodeViewHolder {
	private FlowPanel panel;
	private ToggleButton showNode;
	private Label nodeName ;
	
	private Set<Long> childIds;
	
	public NodeViewHolder(FlowPanel panel, ToggleButton showNode, Label nodeName) {
		this.panel = panel;
		this.showNode = showNode;
		this.nodeName = nodeName;
	}

	public FlowPanel getPanel() {
		return panel;
	}

	public void setPanel(FlowPanel panel) {
		this.panel = panel;
	}

	public ToggleButton getShowNode() {
		return showNode;
	}

	public void setShowNode(ToggleButton showNode) {
		this.showNode = showNode;
	}

	public Label getNodeName() {
		return nodeName;
	}

	public void setNodeName(Label nodeName) {
		this.nodeName = nodeName;
	}

	public Set<Long> getChildIds() {
		return childIds;
	}

	public void setChildIds(Set<Long> childIds) {
		this.childIds = childIds;
	}
	
	
	
}
