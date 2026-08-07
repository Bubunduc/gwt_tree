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
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tree_rumyancev implements EntryPoint {

	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {

		final TreeServiceAsync treeService = GWT.create(TreeService.class);
		final NodeStore nodeStore = new NodeStore();

		SelectedNodeDisplay selectedNodeView = new SelectedNodeView();
		SelectedNodePresenter selectedNodePresenter = new SelectedNodePresenter(selectedNodeView);

		TreeDisplay treeView = new TreeView();
		TreePresenter treePresenter = new TreePresenter(treeView);

		ActionsDisplay actionsView = new ActionsView();
		ActionsPresenter actionsPresenter = new ActionsPresenter(actionsView);

		TableDisplay allNodesTable = new TableView();
		TablePresenterImpl tablePresenter = new TablePresenterImpl(allNodesTable);

		MainPanelDisplay mainPanelView = new MainPanelView(allNodesTable, treeView, actionsView, selectedNodeView);
		MainPanelPresenter mainPanelPresenter = MainPanelPresenter.builder()
				.view(mainPanelView)
				.treePresenter(treePresenter)
				.selectedNodePresenter(selectedNodePresenter)
				.tablePresenter(tablePresenter)
				.treeService(treeService)
				.nodeStore(nodeStore)
				.build();

		mainPanelPresenter.go(RootPanel.get("mainContainer"));

	}
}
