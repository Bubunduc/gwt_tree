package com.example.tree_rumyancev.client.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.handlers.event.CreateNodeEvent;
import com.example.tree_rumyancev.client.handlers.event.CreateRootEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeDeleteEvent;
import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.event.UpdateNodeEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.CreateNodeEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.CreateRootEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeDeleteEventHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.UpdateNodeEventHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.dto.NodeData;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TreePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	private TreeDisplay treeView;

	private Map<Long, NodeData> loadedNodes;

	private final EventBus eventBus;

	public TreePresenter(TreeDisplay treeView, EventBus eventBus) {

		loadedNodes = new HashMap<Long, NodeData>();

		this.treeView = treeView;
		this.eventBus = eventBus;

		bind();

	}

	public void go(HasWidgets container) {

		container.add(treeView.asWidget());

	}

	public void loadData() {

		treeService.getParentList(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				List<Node> rootNodes = result;

				for (Node node : rootNodes) {

					loadedNodes.put(node.getId(), new NodeData(node));

				}

				List<TreeViewData> rootNodesViewDataList = TreeViewData.toViewDataList(rootNodes);

				treeView.drawRoots(rootNodesViewDataList);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка загрузки корней");

			}
		});
	}

	private void bind() {
		treeView.setTreeHandler(new TreeHandler() {
			@Override
			public void onclick(Long nodeId) {
				onNodeButtonClicked(nodeId);
			}

			@Override
			public void onNodeSelected(Long nodeId) {
				NodeData nodeData = loadedNodes.get(nodeId);
				onNodeLabelClicked(nodeData.getNode());
			}
		});

		eventBus.addHandler(NodeDeleteEvent.TYPE, new NodeDeleteEventHandler() {

			@Override
			public void onDelete(NodeDeleteEvent event) {
				final Long deletedNode = event.getId();

				if (loadedNodes.get(deletedNode).getNode().getParentId() == null) {
					Window.alert("корень удалить нельзя");
					return;
				}

				treeService.delete(deletedNode, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						loadedNodes.remove(deletedNode);
						treeView.eraseNode(deletedNode);
						removeChild(deletedNode);
						Window.alert("Удаление прошло успешно");

					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Ошибка при удалении");

					}
				});

			}
		});

		eventBus.addHandler(CreateRootEvent.TYPE, new CreateRootEventHandler() {

			@Override
			public void onCreateRoot(CreateRootEvent event) {

				Node newRoot = event.getNode();

				treeView.drawRoot(TreeViewData.toViewData(newRoot));
				loadedNodes.put(newRoot.getId(), new NodeData(newRoot));

			}
		});

		eventBus.addHandler(UpdateNodeEvent.TYPE, new UpdateNodeEventHandler() {

			@Override
			public void onUpdate(UpdateNodeEvent event) {
				Node updatedNode = event.getNode();

				loadedNodes.get(updatedNode.getId()).setNode(updatedNode);

				Window.alert("Обновление прошло успешно");
			}
		});
		
		eventBus.addHandler(CreateNodeEvent.TYPE, new CreateNodeEventHandler() {
			
			@Override
			public void onCreateNode(CreateNodeEvent event) {
				Node node = event.getNode();
				
				loadedNodes.put(node.getId(), new NodeData(node));
				
				if(loadedNodes.get(node.getParentId()).isChildrenLoaded() == false) {
					
					return;
				}
				if (treeView.isNodeButtonEnabled(node.getParentId()) == false) {
					treeView.setButtonEnabled(node.getParentId(), true);
				}
				treeView.insertNode(TreeViewData.toViewData(node));
				
				
			}
		});
	}

	private void removeChild(Long parentId) {
		List<Long> childIds = new ArrayList<Long>();

		for (Map.Entry<Long, NodeData> entry : loadedNodes.entrySet()) {
			Node node = entry.getValue().getNode();

			if (parentId.equals(node.getParentId())) {
				childIds.add(node.getId());
			}
		}

		for (Long childId : childIds) {
			removeChild(childId);
			loadedNodes.remove(childId);
		}
	}

	private void onNodeButtonClicked(final Long nodeId) {

		NodeData nodeData = loadedNodes.get(nodeId);

		if (nodeData.isChildrenLoaded()) {

			boolean visible = treeView.isNodeVisible(nodeId);

			treeView.setNodeVisible(nodeId, !visible);
			return;

		}

		treeService.getChildrenList(nodeId, new AsyncCallback<List<Node>>() {
			@Override
			public void onSuccess(List<Node> children) {

				NodeData parentData = loadedNodes.get(nodeId);

				if (parentData == null) {
					return;
				}

				parentData.setChildrenLoaded(true);

				if (children == null || children.isEmpty()) {
					treeView.setButtonEnabled(nodeId, false);
					treeView.setNodeVisible(nodeId, false);

					return;
				}

				for (Node child : children) {
					loadedNodes.put(child.getId(), new NodeData(child));
				}

				List<TreeViewData> childrenViewDataList = TreeViewData.toViewDataList(children);

				treeView.showChildList(childrenViewDataList);
				treeView.setNodeVisible(nodeId, true);

			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ошибка загрузки дочерних узлов");

			}
		});
	}

	private void onNodeLabelClicked(final Node node) {

		eventBus.fireEvent(new NodeSelectionEvent(node));

	}

}
