package com.example.tree_rumyancev.client.mainPanel;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.PingNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.google.gwt.user.client.ui.Widget;

public interface MainPanelDisplay {

	// Хендлеры таблицы
	void setRefreshButtonHandler(final RefreshButtonClickHandler handler);

	void setSelectedRowHandler(final SelectedRowHandler handler);

	// Хендлеры дерева
	void setTreeHandler(TreeHandler handler);

	// Хендлеры панели действий
	void setDeleteButtonHandler(DeleteClickHandler handler);

	void setCreateRootHandler(CreateRootClickHandler createRootHandler);

	void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler);

	void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler);
	
	void setPingNodeHandler(PingNodeClickHandler pingNodeClickHandler);

	Widget asWidget();
}
