package com.example.tree_rumyancev.client.tree;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public class NodeViewHolder extends FlowPanel {
	private ToggleButton showNode;
	private Label nodeName;
	private Set<Long> childIds;

	public NodeViewHolder(Long id, String name) {
		childIds = new HashSet<Long>();
		createPanel(id, name);

	}

	private void createPanel(Long id, String name) {

		showNode = new ToggleButton("+", "-");
		nodeName = new Label(name);

		showNode.getElement().setAttribute("data-tree-id", id.toString());
		nodeName.getElement().setAttribute("data-tree-id", id.toString());
		getElement().setId("Panel " + id.toString());

		add(showNode);
		add(nodeName);

		setStyleName("nodePanel");
		nodeName.setStyleName("nodeLabel");
		showNode.setStyleName("nodeButton nodeButtonUp");
	}

	public void addChildId(Long id) {
		childIds.add(id);
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

	public void setNodeName(String name) {
		this.nodeName.setText(name);
	}

	public Set<Long> getChildIds() {
		return childIds;
	}

	public void setChildIds(Set<Long> childIds) {
		this.childIds = childIds;
	}

	public void setEnabled(boolean stage) {
		showNode.setValue(stage);

		showNode.setEnabled(stage);

	}

	public boolean isButtonEnabled() {
		return showNode.isEnabled();
	}

	public void removeFromChildList(Long id) {
		childIds.remove(id);
	}

}
