package com.example.tree_rumyancev.client.mainPanel;

import java.util.List;

import com.example.tree_rumyancev.client.actions.ActionsDisplay;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeDisplay;
import com.example.tree_rumyancev.client.table.TableDisplay;
import com.example.tree_rumyancev.client.tree.TreeDisplay;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainPanelView implements MainPanelDisplay{
	
	private FlowPanel mainPanel = new FlowPanel();
	private FlowPanel topPanel = new FlowPanel();
	private TreeDisplay treeView;
	private SelectedNodeDisplay selectedNodeView;
	private TableDisplay tableView;
	private ActionsDisplay actionsView;
	
	public MainPanelView(TableDisplay tableView, TreeDisplay treeView, ActionsDisplay actionsView,SelectedNodeDisplay selectedNodeView) {
		this.tableView = tableView;
		this.treeView = treeView;
		this.actionsView = actionsView;
		this.selectedNodeView = selectedNodeView;
		topPanel.add(treeView.asWidget());
		topPanel.add(selectedNodeView.asWidget());
		mainPanel.add(topPanel);
		mainPanel.add(actionsView.asWidget());
		mainPanel.add(tableView.asWidget());
		
		topPanel.setStyleName("topPanel");
	}
	
	//Дерево
	
	@Override
	public void showChildList(List<TreeViewData> child) {
		treeView.showChildList(child);
		
	}

	@Override
	public void setTreeHandler(TreeHandler handler) {
		treeView.setTreeHandler(handler);
		
	}

	
	//Таблица

	@Override
	public void setRefreshButtonHandler(RefreshButtonClickHandler handler) {
		tableView.setRefreshButtonHandler(handler);
		
	}


	@Override
	public void setSelectedRowHandler(SelectedRowHandler handler) {
		tableView.setSelectedRowHandler(handler);
		
	}

	//Панель действий

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
	
	// Выбранная нода
	@Override
	public void showNode(Node node) {
		selectedNodeView.showNode(node);
		
	}

	@Override
	public Node getCurrentNode() {
		return selectedNodeView.getCurrentNode();
	}
	
	@Override
	public Widget asWidget() {
		return mainPanel;
	}

	
	
}
