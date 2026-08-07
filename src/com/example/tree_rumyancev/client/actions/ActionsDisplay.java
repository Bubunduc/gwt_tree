package com.example.tree_rumyancev.client.actions;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface ActionsDisplay extends IsWidget {

	void setDeleteButtonHandler(DeleteClickHandler handler);

	void setCreateRootHandler(CreateRootClickHandler createRootHandler);

	void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler);

	void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler);

}
