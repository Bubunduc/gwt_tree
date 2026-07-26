package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface SelectedNodeDisplay extends IsWidget {
	public void initSelectedNodeTable();

	public void showNode(Node node);

	void setDeleteButtonHandler(DeleteClickHandler handler);

	void setCreateRootHandler(CreateRootClickHandler createRootHandler);

	void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler);

	void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler);
	
	Node getNewNode();

	Widget asWidget();
}
