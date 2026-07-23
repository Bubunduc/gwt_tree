package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeSelectionEventHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedNodePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	private final SelectedNodeDisplay view;

	private final EventBus eventBus;

	public SelectedNodePresenter(SelectedNodeDisplay view, EventBus eventBus) {

		this.view = view;
		this.eventBus = eventBus;
		bind();
	}

	public void go(HasWidgets container) {
		
		container.add(view.asWidget());
		
	}
	
	private void bind() {
		eventBus.addHandler(NodeSelectionEvent.TYPE, new NodeSelectionEventHandler() {

			@Override
			public void onNodeSelected(NodeSelectionEvent event) {
				loadNode(event.getNode());

			}
		});
	}

	public void loadNode(Node node) {
		view.showNode(node);

	}
}
