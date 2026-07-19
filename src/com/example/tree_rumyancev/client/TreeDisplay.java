package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public interface TreeDisplay {
	
	public NodeViewHolder showNode(Node node);
	public void showChildList(List<Node> child);
	public void initTree(Node node);
	public void setButtonHandler(Long id, ClickHandler handler);
	public void setLabelHandler(Long id, ClickHandler handler);
	public void setNodeVisible(Long id,boolean stage);
	
}
