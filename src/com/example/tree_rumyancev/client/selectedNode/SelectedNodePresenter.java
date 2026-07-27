package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.event.CreateNodeEvent;
import com.example.tree_rumyancev.client.handlers.event.CreateRootEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeDeleteEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.event.UpdateNodeEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeSelectionEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedNodePresenter {

	private final SelectedNodeDisplay view;

	private final EventBus eventBus;

	private Node SelectedNode;

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

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

		view.setCreateNodeHandler(new CreateNodeClickHandler() {

			@Override
			public void onClick() {
				Node newNode = view.getNewNode();
				newNode.setParentId(newNode.getId());
				if (newNode.getId() == null || 
						newNode.getParentId() == null || 
						newNode.getName().isEmpty() || 
						newNode.getIp().isEmpty() || 
						newNode.getPort() == null) {
					Window.alert("Использование пустых полей не допускается");
					return;
				}

				treeService.create(newNode, new AsyncCallback<Node>() {

					@Override
					public void onSuccess(Node result) {

						eventBus.fireEvent(new CreateNodeEvent(result));

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {

				Node newNode = view.getNewNode();
				
				if ( 
						newNode.getName().isEmpty() || 
						newNode.getIp().isEmpty() || 
						newNode.getPort() == null) {
					Window.alert("Использование пустых полей не допускается");
					return;
				}
				
				newNode.setParentId(null);

				treeService.create(newNode, new AsyncCallback<Node>() {

					@Override
					public void onSuccess(Node result) {
						// TODO Auto-generated method stub
						eventBus.fireEvent(new CreateRootEvent(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ошибка присоздании корня");

					}
				});

			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {
				final Node newNode = view.getNewNode();
				
				if (newNode.getId() == null || 
						newNode.getParentId() == null || 
						newNode.getName().isEmpty() || 
						newNode.getIp().isEmpty() || 
						newNode.getPort() == null) {
					Window.alert("Использование пустых полей не допускается");
					return;
				}
				
				treeService.update(newNode, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {

						if (newNode.getId() == null) {

							Window.alert("Id ветви может быть null");
							return;

						}

						eventBus.fireEvent(new UpdateNodeEvent(newNode));

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ошибка при обновлении ветви");

					}
				});

			}
		});

		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {
				eventBus.fireEvent(new NodeDeleteEvent(SelectedNode.getId()));
			}
		});

	}

	public void loadNode(Node node) {
		view.showNode(node);

	}

}
