package com.example.tree_rumyancev.client.tree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.tree_rumyancev.client.selectedNode.NodeSelectionHandler;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodeDisplay;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TreePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	private TreeDisplay treeView;
	private Set<Long> loadedIds = new HashSet<Long>();
	private Set<Long> expandedIds = new HashSet<Long>();

	NodeSelectionHandler nodeSelectionHandler;

	public TreePresenter(TreeDisplay treeView, NodeSelectionHandler nodeSelectionHandler) {
		this.treeView = treeView;
		this.nodeSelectionHandler = nodeSelectionHandler;
	}

	public void go() {
		treeService.getParentList(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				List<Node> rootNodes = result;
				treeView.drawRoots(rootNodes);
				for (Node node : result) {
					bindNodeHandlers(node);
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void bindNodeHandlers(final Node node) {
		treeView.setButtonHandler(node.getId(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				onNodeButtonClicked(node);
			}
		});

		treeView.setLabelHandler(node.getId(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				onNodeLabelClicked(node);
			}
		});
	}

	private void onNodeButtonClicked(final Node node) {
		final Long nodeId = node.getId();

		if (expandedIds.contains(nodeId)) {
			treeView.setNodeVisible(nodeId, false);
			expandedIds.remove(nodeId);
			return;
		}

		if (loadedIds.contains(nodeId)) {
			treeView.setNodeVisible(nodeId, true);
			expandedIds.add(nodeId);
			return;
		}

		treeService.getChildrenList(nodeId, new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> children) {
				loadedIds.add(nodeId);
				expandedIds.add(nodeId);

				if (children == null || children.isEmpty()) {
					return;
				}

				treeView.showChildList(children);

				treeView.setNodeVisible(nodeId, true);

				for (Node child : children) {
					bindNodeHandlers(child);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	private void onNodeLabelClicked(Node node) {
		nodeSelectionHandler.onNodeSelected(node);
	}

}
