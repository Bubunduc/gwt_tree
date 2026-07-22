package com.example.tree_rumyancev.client.tree;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.tree.ShowTreeButtonHandler;
import com.example.tree_rumyancev.client.selectedNode.NodeSelectionHandler;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface TreeDisplay extends IsWidget {

	void showChildList(List<TreeViewData> child);

	void setButtonHandler(Long id, ClickHandler handler);
	
//	void setButtonHandler(Long id, NodeSelectionHandler handler);
//
//	void setLabelHandler(Long id, ShowTreeButtonHandler handler);
	
	void setLabelHandler(Long id, ClickHandler handler);

	void setNodeVisible(Long id, boolean stage);

	void drawRoots(List<TreeViewData> roots);

}
