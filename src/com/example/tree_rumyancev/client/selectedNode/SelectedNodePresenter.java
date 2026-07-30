package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.actions.CreateNodeRequestedHandler;
import com.example.tree_rumyancev.client.handlers.actions.CreateRootRequestedHandler;
import com.example.tree_rumyancev.client.handlers.actions.DeleteNodeRequestedHandler;
import com.example.tree_rumyancev.client.handlers.actions.UpdateNodeRequestedHandler;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateRootRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.DeleteNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.UpdateNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateNodeEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateRootEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.NodeDeleteEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.UpdateNodeEvent;
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

		eventBus.addHandler(CreateNodeRequestedEvent.TYPE, new CreateNodeRequestedHandler() {

			@Override
			public void onCreateNodeRequested(CreateNodeRequestedEvent event) {
				Node newNode = view.getNewNode();
				newNode.setParentId(newNode.getId());
				if (newNode.getId() == null || newNode.getParentId() == null || newNode.getName().isEmpty()
						|| newNode.getIp().isEmpty() || newNode.getPort() == null) {
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
						Window.alert("Ошибка при создании ветки");

					}
				});
			}
		});

		eventBus.addHandler(CreateRootRequestedEvent.TYPE, new CreateRootRequestedHandler() {

			@Override
			public void onCreateRootRequested(CreateRootRequestedEvent event) {

				Node newNode = view.getNewNode();

				if (newNode.getName().isEmpty() || newNode.getIp().isEmpty() || newNode.getPort() == null) {
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

		eventBus.addHandler(UpdateNodeRequestedEvent.TYPE, new UpdateNodeRequestedHandler() {

			@Override
			public void onUpdateNodeRequested(UpdateNodeRequestedEvent event) {
				final Node newNode = view.getNewNode();
				if (newNode.getId() == null || newNode.getName().isEmpty() || newNode.getIp().isEmpty()
						|| newNode.getPort() == null) {
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

		eventBus.addHandler(DeleteNodeRequestedEvent.TYPE, new DeleteNodeRequestedHandler() {

			@Override
			public void onDeleteNodeRequested(DeleteNodeRequestedEvent event) {

				final Node deletedNode = view.getNewNode();

				if (deletedNode.getParentId() == null) {
					Window.alert("корень удалить нельзя");
					return;
				}

				treeService.delete(deletedNode.getId(), new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						eventBus.fireEvent(new NodeDeleteEvent(deletedNode.getId()));
					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Ошибка при удалении");

					}
				});

			}
		});

	}

	public void loadNode(Node node) {
		view.showNode(node);

	}

}
