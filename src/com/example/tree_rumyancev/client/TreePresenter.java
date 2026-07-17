package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;

public class TreePresenter {
	
	private final TreeServiceAsync treeService;
	
	private TreeView treeView;
	
	public TreePresenter(TreeServiceAsync treeService,TreeView treeView)
	{
		this.treeView = treeView;
		this.treeService = treeService;
	}
	public void go() 
	{
		treeService.getRootNode(new AsyncCallback<Node>() {
			private FlowPanel rootPanel = new FlowPanel();
			@Override
			public void onSuccess(Node result) {
				Node rootNode = result;
				treeView.initTree(rootNode);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void bind()
	{
		
	}
	

}
