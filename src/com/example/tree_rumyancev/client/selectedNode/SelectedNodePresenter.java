package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class SelectedNodePresenter implements NodeSelectionHandler{
	
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	
	private final SelectedNodeDisplay view;
	
	public SelectedNodePresenter(SelectedNodeDisplay view){
		this.view = view;
	}
	
	@Override
	public void onNodeSelected(Long nodeId) {
		treeService.findById(nodeId,new AsyncCallback<Node>(){
			@Override
			public void onSuccess(Node result) {
				view.showNode(result);
			}
				
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	
		
		
	}
}
