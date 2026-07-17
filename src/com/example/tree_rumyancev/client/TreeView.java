package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.example.tree_rumyancev.shared.model.TreeNode;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public interface TreeView {
	
	public FlowPanel showNode(Node node);
	public void showChildList(List<Node> child);
	public void initTree(Node node);
	


}
