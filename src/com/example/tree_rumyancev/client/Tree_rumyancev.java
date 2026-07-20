package com.example.tree_rumyancev.client;

import com.google.gwt.core.client.EntryPoint;
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
		SelectedNodeView selectedNode = new SelectedNodeView();

		TreeDisplay treeView = new TreeView();
		TreePresenter treePresenter = new TreePresenter(treeView, selectedNode);
		treePresenter.go();
		RootPanel.get("NodesContainer").add(treeView.asWidget());

		TableDisplay allNodesTable = new TableView();
		TablePresenter tablePresenter = new TablePresenter(allNodesTable);
		tablePresenter.go();

		// RootPanel.get("CurrentNodeContainer").add(selectedNode);
		// TreeWidget tree = new TreeWidget(selectedNode);
		// RootPanel.get("NodesContainer").add(tree);

		// RootPanel.get("AllNodesTable").add(allNodesTable);
	}
}
