package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.client.selectedNode.SelectedNodeDisplay;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodePresenter;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeView;
import com.example.tree_rumyancev.client.table.TableDisplay;
import com.example.tree_rumyancev.client.table.TablePresenterImpl;
import com.example.tree_rumyancev.client.table.TableView;
import com.example.tree_rumyancev.client.tree.TreeDisplay;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.client.tree.TreeView;
import com.google.gwt.core.client.EntryPoint;
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

		EventBus eventBus = new SimpleEventBus();

		SelectedNodeDisplay selectedNodeView = new SelectedNodeView();
		SelectedNodePresenter selectedNodePresenter = new SelectedNodePresenter(selectedNodeView, eventBus);
		selectedNodePresenter.go(RootPanel.get("CurrentNodeContainer"));
		
		TreeDisplay treeView = new TreeView();
		TreePresenter treePresenter = new TreePresenter(treeView, eventBus);
		treePresenter.loadData();
		treePresenter.go(RootPanel.get("NodesContainer"));

		TableDisplay allNodesTable = new TableView();
		TablePresenterImpl tablePresenter = new TablePresenterImpl(allNodesTable);
		tablePresenter.loadData();
		tablePresenter.go(RootPanel.get("AllNodesTable"));

	}
}
