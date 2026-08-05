package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.client.actions.ActionsDisplay;
import com.example.tree_rumyancev.client.actions.ActionsPresenter;
import com.example.tree_rumyancev.client.actions.ActionsView;
import com.example.tree_rumyancev.client.mainPanel.MainPanelDisplay;
import com.example.tree_rumyancev.client.mainPanel.MainPanelPresenter;
import com.example.tree_rumyancev.client.mainPanel.MainPanelView;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeDisplay;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodePresenter;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeView;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.client.store.NodeStore;
import com.example.tree_rumyancev.client.table.TableDisplay;
import com.example.tree_rumyancev.client.table.TablePresenterImpl;
import com.example.tree_rumyancev.client.table.TableView;
import com.example.tree_rumyancev.client.tree.TreeDisplay;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.client.tree.TreeView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tree_rumyancev implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {
	
		final TreeServiceAsync treeService = GWT.create(TreeService.class);
		final NodeStore nodeStore = new NodeStore();

		SelectedNodeDisplay selectedNodeView = new SelectedNodeView();
		SelectedNodePresenter selectedNodePresenter = new SelectedNodePresenter(selectedNodeView);
		// selectedNodePresenter.go(RootPanel.get("CurrentNodeContainer"));

		TreeDisplay treeView = new TreeView();
		TreePresenter treePresenter = new TreePresenter(treeView);
		// treePresenter.loadData();
		// treePresenter.go(RootPanel.get("NodesContainer"));

		ActionsDisplay actionsView = new ActionsView();
		ActionsPresenter actionsPresenter = new ActionsPresenter(actionsView);
		// actionsPresenter.go(RootPanel.get("ActionButtonsContainer"));

		TableDisplay allNodesTable = new TableView();
		TablePresenterImpl tablePresenter = new TablePresenterImpl(allNodesTable);
		// tablePresenter.loadData();
		// tablePresenter.go(RootPanel.get("AllNodesTable"));

		MainPanelDisplay mainPanelView = new MainPanelView(allNodesTable, treeView, actionsView, selectedNodeView);
		MainPanelPresenter mainPanelPresenter = new MainPanelPresenter(mainPanelView, treePresenter, tablePresenter, selectedNodePresenter, nodeStore, treeService);

		mainPanelPresenter.go(RootPanel.get("mainContainer"));

	}
}
