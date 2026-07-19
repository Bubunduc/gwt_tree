package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.shared.FieldVerifier;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;
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
		FlowPanel mainRootPanel = new FlowPanel() ;
		TreeDisplay treeView = new TreeView(mainRootPanel);
		TreePresenter treePresenter = new TreePresenter(treeView,selectedNode);
		RootPanel.get("NodesContainer").add(mainRootPanel);
		treePresenter.go();
		
	
		//RootPanel.get("CurrentNodeContainer").add(selectedNode);
		//TreeWidget tree = new TreeWidget(selectedNode);
		//RootPanel.get("NodesContainer").add(tree);
		//TableWidget allNodesTable = new TableWidget();
		//RootPanel.get("AllNodesTable").add(allNodesTable);
	}					
}
