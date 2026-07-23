package com.example.tree_rumyancev.client.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tree_rumyancev.client.handlers.event.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
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

	private Map<Long,Node> loadedNodes;
	
	private final EventBus eventBus;

	public TreePresenter(TreeDisplay treeView,EventBus eventBus ) {

		loadedNodes = new HashMap<Long, Node>();
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
				onNodeLabelClicked(loadedNodes.get(nodeId));
			}
		});
	}

	private void onNodeButtonClicked(final Long nodeId) {
		
		if (loadedNodes.containsKey(nodeId)) {
			
			if (treeView.isNodeVisible(nodeId)) {
				
				treeView.setNodeVisible(nodeId, false);
				return;
			}
			if (!treeView.isNodeVisible(nodeId)){
				
				treeView.setNodeVisible(nodeId, true);
				return;
			}
		}

		treeService.getChildrenList(nodeId, new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> children) {
				
				for(Node node : children) {
					loadedNodes.put(nodeId, node);
				}
				
				List<TreeViewData> childrenViewDataList = TreeViewData.toViewDataList(children);

				if (children == null || children.isEmpty()) {
					return;
				}

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
