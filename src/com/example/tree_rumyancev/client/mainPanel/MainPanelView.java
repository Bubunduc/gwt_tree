package com.example.tree_rumyancev.client.mainPanel;

import com.example.tree_rumyancev.client.ServerStatus.ServerStatusDisplay;
import com.example.tree_rumyancev.client.actions.ActionsDisplay;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.PingNodeClicklHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeDisplay;
import com.example.tree_rumyancev.client.table.TableDisplay;
import com.example.tree_rumyancev.client.tree.TreeDisplay;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainPanelView implements MainPanelDisplay {

	private FlowPanel mainPanel = new FlowPanel();
	private FlowPanel topPanel = new FlowPanel();
	private final TreeDisplay treeView;
	private final TableDisplay tableView;
	private final ActionsDisplay actionsView;

	public MainPanelView(TableDisplay tableView, TreeDisplay treeView, ActionsDisplay actionsView,
			SelectedNodeDisplay selectedNodeView, ServerStatusDisplay serverStatusDisplay) {
		this.tableView = tableView;
		this.treeView = treeView;
		this.actionsView = actionsView;
		topPanel.add(treeView.asWidget());
		topPanel.add(selectedNodeView.asWidget());
		topPanel.add(serverStatusDisplay.asWidget());
		mainPanel.add(topPanel);
		mainPanel.add(actionsView.asWidget());
		mainPanel.add(tableView.asWidget());

		topPanel.setStyleName("topPanel");
	}

	// Дерево

	@Override
	public void setTreeHandler(TreeHandler handler) {
		treeView.setTreeHandler(handler);

	}

	// Таблица

	@Override
	public void setRefreshButtonHandler(RefreshButtonClickHandler handler) {
		tableView.setRefreshButtonHandler(handler);

	}

	@Override
	public void setSelectedRowHandler(SelectedRowHandler handler) {
		tableView.setSelectedRowHandler(handler);

	}

	// Панель действий

	@Override
	public void setDeleteButtonHandler(DeleteClickHandler handler) {

		actionsView.setDeleteButtonHandler(handler);
	}

	@Override
	public void setCreateRootHandler(CreateRootClickHandler createRootHandler) {
		actionsView.setCreateRootHandler(createRootHandler);

	}

	@Override
	public void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler) {
		actionsView.setCreateNodeHandler(createNodeHandler);

	}

	@Override
	public void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler) {
		actionsView.setUpdateNodeHandler(updateNodeHandler);

	}

	@Override
	public void setPingNodeHandler(PingNodeClicklHandler pingNodeClicklHandler) {
		actionsView.setPingNodeHandler(pingNodeClicklHandler);

	}

	@Override
	public Widget asWidget() {
		return mainPanel;
	}

}
