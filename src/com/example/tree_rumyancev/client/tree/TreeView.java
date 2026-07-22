package com.example.tree_rumyancev.client.tree;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.tree.ShowTreeButtonHandler;
import com.example.tree_rumyancev.client.selectedNode.NodeSelectionHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class TreeView implements TreeDisplay {

	private final TreeWidget treeWidget = new TreeWidget();

	@Override
	public void showChildList(List<Node> child) {

		treeWidget.showChildList(child);
	}

	@Override
	public void setNodeVisible(Long id, boolean stage) {
		treeWidget.setNodeVisible(id, stage);
	}

	@Override
	public void drawRoots(List<Node> roots) {
		treeWidget.drawRoots(roots);
	}

	@Override
	public Widget asWidget() {
		return treeWidget;
	}
	
	@Override
	public void setButtonHandler(Long id, ClickHandler handler) {
		treeWidget.setButtonHandler(id, handler);
	}

	@Override
	public void setLabelHandler(Long id, ClickHandler handler) {
		treeWidget.setLabelHandler(id, handler);
		
	}
	

}
