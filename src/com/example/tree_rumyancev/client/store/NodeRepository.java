package com.example.tree_rumyancev.client.store;

import java.util.List;

import com.example.tree_rumyancev.client.dto.DeletedNodeData;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class NodeRepository {

	private static TreeServiceAsync treeService;
	private static NodeStore nodeStore;

	public static void init(TreeServiceAsync service, NodeStore store) {
		treeService = service;
		nodeStore = store;
	}

	public static void findAll(final AsyncCallback<List<Node>> callback) {
		treeService.findAll(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				nodeStore.clear();
				for (Node node : result) {
					nodeStore.save(node);
				}
				callback.onSuccess(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);

			}
		});
	}

	public static void create(final Node newNode, final AsyncCallback<Node> callback) {
		treeService.create(newNode, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				nodeStore.save(result);
				callback.onSuccess(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);

			}
		});
	}

	public static void update(final Node updatedNode, final AsyncCallback<Node> callback) {
		treeService.update(updatedNode, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				nodeStore.save(updatedNode);
				callback.onSuccess(updatedNode);
			}

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);

			}
		});
	}

	public static void delete(final Long deletedId, final AsyncCallback<DeletedNodeData> callback) {
		treeService.delete(deletedId, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				Long parentId = nodeStore.get(deletedId).getParentId();
				List<Long> removedIds = nodeStore.removeSubTrees(deletedId);
				callback.onSuccess(new DeletedNodeData(parentId, removedIds));
				nodeStore.remove(deletedId);
				nodeStore.clearSelection();
			}

			@Override
			public void onFailure(Throwable caught) {

				callback.onFailure(caught);

			}
		});
	}
}
