package com.example.tree_rumyancev.client.tree;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.tree.ShowTreeButtonHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.selectedNode.NodeSelectionHandler;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface TreeDisplay extends IsWidget {

	void showChildList(List<TreeViewData> child);
	
	void setTreeHandler(TreeHandler handler);

	void setNodeVisible(Long id, boolean stage);

	void drawRoots(List<TreeViewData> roots);

}
