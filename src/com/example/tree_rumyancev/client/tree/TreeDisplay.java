package com.example.tree_rumyancev.client.tree;

import java.util.List;

import com.example.tree_rumyancev.client.dto.TreeViewData;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface TreeDisplay extends IsWidget {

	void showChildList(List<TreeViewData> child);

	void setTreeHandler(TreeHandler handler);

	void setNodeVisible(Long id, boolean stage);

	void drawRoots(List<TreeViewData> roots);

	void drawRoot(TreeViewData root);

	void eraseNode(Long id, Long parentId, List<Long> deletedChildIds);

	void insertNode(TreeViewData node);

	boolean isNodeVisible(Long id);

	boolean isNodeButtonVisible(Long id);

	boolean hasNodechild(Long id);

	void setButtonVisible(Long id, boolean stage);

	void setStatus(Long id, String status);

	void colorSelectedNode(Long id, boolean stage);

	void updateNodeName(Long id, String name);

}
