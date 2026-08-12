package com.example.tree_rumyancev.client.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class TreePresenter {

	private final TreeDisplay treeView;

	private Map<Long, Boolean> loadedNodes;

	private Long selectedNodeId;

	public TreePresenter(TreeDisplay treeView) {

		loadedNodes = new HashMap<Long, Boolean>();

		this.treeView = treeView;

	}

	public void go(HasWidgets container) {

		container.add(treeView.asWidget());

	}

	public void loadData(List<Node> rootNodes) {
		List<TreeViewData> rootNodesViewDataList = TreeViewData.toViewDataList(rootNodes);
		for (TreeViewData i : rootNodesViewDataList) {
			loadedNodes.put(i.getNodeId(), false);
		}
		treeView.drawRoots(rootNodesViewDataList);
	}

	public void deleteNode(Long deletedNode, Long parentId, List<Long> deletedChildIds) {
		if (!loadedNodes.containsKey(deletedNode)) {
			Window.alert("Удаление прошло успешно");
			return;
		}
		loadedNodes.remove(deletedNode);
		loadedNodes.keySet().removeAll(deletedChildIds);
		treeView.eraseNode(deletedNode, parentId, deletedChildIds);
		if(parentId != null) {
			if (treeView.hasNodechild(parentId) == false) {
				treeView.setButtonVisible(parentId, false);
			}
		}
		
		Window.alert("Удаление прошло успешно");
	}

	public void createRoot(Node newRoot) {
		treeView.drawRoot(TreeViewData.toViewData(newRoot));
		loadedNodes.put(newRoot.getId(), false);
	}

	public void createNode(Node node) {

		if (loadedNodes.containsKey(node.getParentId()) == false) {
			return;
		}
		if (loadedNodes.get(node.getParentId()) == false) {

			return;
		}
		if (treeView.isNodeButtonVisible(node.getParentId()) == false) {
			treeView.setButtonVisible(node.getParentId(), true);
		}
		loadedNodes.put(node.getId(), false);
		treeView.insertNode(TreeViewData.toViewData(node));
	}

	public void updateNode(Node updatedNode) {
		if (!loadedNodes.containsKey(updatedNode.getId())) {
			Window.alert("Обновление прошло успешно");
			return;
		}

		treeView.updateNodeName(updatedNode.getId(), updatedNode.getName());
	}

	public void onNodeButtonClicked(Node parentNode, List<Node> childNodes) {

		if (loadedNodes.get(parentNode.getId())) {

			boolean visible = treeView.isNodeVisible(parentNode.getId());

			treeView.setNodeVisible(parentNode.getId(), !visible);
			return;

		}
		loadedNodes.put(parentNode.getId(), true);

		if (childNodes == null || childNodes.isEmpty()) {
			treeView.setButtonVisible(parentNode.getId(), false);
			treeView.setNodeVisible(parentNode.getId(), false);

			return;
		}

		for (Node child : childNodes) {
			loadedNodes.put(child.getId(), false);
		}

		List<TreeViewData> childrenViewDataList = TreeViewData.toViewDataList(childNodes);

		treeView.showChildList(childrenViewDataList);
		treeView.setNodeVisible(parentNode.getId(), true);

	}

	public void expandNode(Node parentNode, List<Node> childNodes) {

		Long parentId = parentNode.getId();

		Boolean loaded = loadedNodes.get(parentId);

		if (loaded == null) {
			return;
		}

		if (loaded) {
			treeView.setNodeVisible(parentId, true);
			return;
		}

		loadedNodes.put(parentId, true);

		for (Node child : childNodes) {
			loadedNodes.put(child.getId(), false);
		}

		treeView.showChildList(TreeViewData.toViewDataList(childNodes));

		treeView.setNodeVisible(parentId, true);
	}

	public void colorLabel(final Long id) {

		if (!loadedNodes.containsKey(id)) {
			treeView.colorSelectedNode(this.selectedNodeId, false);
			return;
		}
		if ((this.selectedNodeId == null)) {
			this.selectedNodeId = id;
			treeView.colorSelectedNode(this.selectedNodeId, true);
			return;
		}

		treeView.colorSelectedNode(this.selectedNodeId, false);

		this.selectedNodeId = id;

		treeView.colorSelectedNode(this.selectedNodeId, true);

	}

	public void setSelectedNodeId(Long id) {
		this.selectedNodeId = id;
	}

	public void setButtonVisible(Long id, boolean stage) {
		treeView.setButtonVisible(id, stage);
	}
	
	public void setStatus(String status) {
		treeView.setStatus(selectedNodeId, status);
	}
}
