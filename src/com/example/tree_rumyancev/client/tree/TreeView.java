package com.example.tree_rumyancev.client.tree;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.google.gwt.user.client.ui.Widget;

public class TreeView implements TreeDisplay {

	private TreeWidget treeWidget = new TreeWidget();

	@Override
	public void showChildList(List<TreeViewData> child) {

		treeWidget.showChildList(child);
	}

	@Override
	public void setNodeVisible(Long id, boolean stage) {
		treeWidget.setNodeVisible(id, stage);
	}

	@Override
	public void drawRoots(List<TreeViewData> roots) {
		treeWidget.drawRoots(roots);
	}

	@Override
	public Widget asWidget() {
		return treeWidget;
	}

	@Override
	public void setTreeHandler(TreeHandler handler) {
		treeWidget.setTreeHandler(handler);

	}

	@Override
	public boolean isNodeVisible(Long id) {

		return treeWidget.isNodeVisible(id);

	}

	@Override
	public void eraseNode(Long id, Long parentId, List<Long> deletedChildIds) {
		treeWidget.eraseNode(id, parentId, deletedChildIds);

	}

	@Override
	public void setButtonEnabled(Long id, boolean stage) {
		treeWidget.setButtonEnabled(id, stage);
	}

	@Override
	public void drawRoot(TreeViewData root) {
		treeWidget.drawRoot(root);

	}

	@Override
	public void insertNode(TreeViewData node) {
		treeWidget.insertNode(node);

	}

	@Override
	public boolean hasNodechild(Long id) {
		return treeWidget.hasNodechild(id);
	}

	@Override
	public boolean isNodeButtonVisible(Long id) {

		return treeWidget.isNodeButtonVisible(id);
	}

	@Override
	public void colorSelectedNode(Long id, boolean stage) {
		treeWidget.colorSelectedNode(id, stage);

	}

	@Override
	public void updateNodeName(Long id, String name) {
		treeWidget.updateNodeName(id, name);
	}

}
