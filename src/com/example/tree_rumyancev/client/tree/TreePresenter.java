package com.example.tree_rumyancev.client.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.dto.NodeData;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class TreePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	private TreeDisplay treeView;

	private Map<Long, Boolean> loadedNodes;

	private EventBus eventBus;

	private Long selectedNodeId;

	public TreePresenter(TreeDisplay treeView, EventBus eventBus) {

		loadedNodes = new HashMap<Long, Boolean>();

		this.treeView = treeView;
		this.eventBus = eventBus;

		// bind();

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

	public void deleteNode(Long deletedNode, Long parentId,List<Long> deletedChildIds) {
		if (!loadedNodes.containsKey(deletedNode)) {
			Window.alert("Удаление прошло успешно");
			return;
		}
		// Long parentId = loadedNodes.get(deletedNode).getNode().getParentId();
		loadedNodes.remove(deletedNode);
		loadedNodes.keySet().removeAll(deletedChildIds);
		treeView.eraseNode(deletedNode, parentId,deletedChildIds);
		//removeChild(deletedNode);
		if (treeView.hasNodechild(parentId) == false) {
			treeView.setButtonEnabled(parentId, false);
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
		if (treeView.isNodeButtonEnabled(node.getParentId()) == false) {
			treeView.setButtonEnabled(node.getParentId(), true);
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

	private void bind() {

	}

	public void onNodeButtonClicked(Node parentNode,List<Node> childNodes) {

		if (loadedNodes.get(parentNode.getId())) {

			boolean visible = treeView.isNodeVisible(parentNode.getId());

			treeView.setNodeVisible(parentNode.getId(), !visible);
			return;

		}
		loadedNodes.put(parentNode.getId(), true);

		if (childNodes == null || childNodes.isEmpty()) {
			treeView.setButtonEnabled(parentNode.getId(), false);
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

	public void colorLabel(final Long id) {

		// eventBus.fireEvent(new NodeSelectionEvent(node));

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
}
