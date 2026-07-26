package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.CreateRootEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeDeleteEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeDeleteEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeSelectionEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedNodePresenter {

	private final SelectedNodeDisplay view;

	private final EventBus eventBus;

	private Node SelectedNode;

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

				SelectedNode = event.getNode();
				loadNode(SelectedNode);

			}
		});

		view.setDeleteButtonHandler(new DeleteClickHandler() {
			
			@Override
			public void onClick() {
				eventBus.fireEvent(new NodeDeleteEvent(SelectedNode.getId()));
			}
		});
		
		view.setCreateRootHandler(new CreateRootClickHandler() {
			
			@Override
			public void onClick() {
				
				Node newNode = view.getNewNode();
				newNode.setParentId(null);
				eventBus.fireEvent(new CreateRootEvent(newNode));
				
			}
		});

	}
	public void loadNode(Node node) {
		view.showNode(node);

	}
	
}
