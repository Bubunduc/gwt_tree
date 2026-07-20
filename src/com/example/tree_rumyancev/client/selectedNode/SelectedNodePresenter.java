package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.shared.model.Node;

public class SelectedNodePresenter implements NodeSelectionHandler{
	private final SelectedNodeDisplay view;
	
	public SelectedNodePresenter(SelectedNodeDisplay view){
		this.view = view;
	}
	
	@Override
	public void onNodeSelected(Node node) {
		view.showNode(node);
		
	}
}
