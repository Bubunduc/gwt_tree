package com.example.tree_rumyancev.client.tree;

import java.util.Set;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public class NodeViewHolder extends FlowPanel {
	private ToggleButton showNode;
	private Label nodeName;
	private Set<Long> childIds;

	// public NodeViewHolder(Node node) {
	// add(showNode);

	// }

	public NodeViewHolder(Long id) {
		createPanel(id);

	}

	public NodeViewHolder(ToggleButton showNode, Label nodeName) {
		this.showNode = showNode;
		this.nodeName = nodeName;
	}

	public void createPanel(Long id) {

		showNode = new ToggleButton("+");
		nodeName = new Label("Panel" + id.toString());

		showNode.getElement().setId("NodeButton " + id.toString());
		nodeName.getElement().setId("Node Label " + id.toString());
		getElement().setId("Panel " + id.toString());
		add(showNode);
		add(nodeName);

		setStyleName("nodePanel");
		showNode.setStyleName("nodeButton");
		nodeName.setStyleName("nodeLabel");
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
