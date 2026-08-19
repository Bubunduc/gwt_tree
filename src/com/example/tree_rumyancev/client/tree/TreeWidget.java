package com.example.tree_rumyancev.client.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tree_rumyancev.client.dto.TreeViewData;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeWidget extends Composite {

	private FlowPanel rootPanel;

	private Map<Long, NodeViewHolder> treeNodes;

	private TreeHandler treeWidgetHandler;

	public TreeWidget() {

		treeNodes = new HashMap<>();
		rootPanel = new FlowPanel();
		rootPanel.setStyleName("tree");
		initWidget(rootPanel);
		addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleTreeClick(event);
			}
		}, ClickEvent.getType());

	}

	private NodeViewHolder showNode(Long id, String name) {

		NodeViewHolder holder = new NodeViewHolder(id, name);

		treeNodes.put(id, holder);

		return holder;

	}

	public void insertNode(TreeViewData node) {

		Long id = node.getNodeId();
		Long parentId = node.getParentId();

		NodeViewHolder parentPanel = treeNodes.get(parentId);
		NodeViewHolder childPanel = showNode(id, node.getName());
		childPanel.addStyleName("nodeChild");
		parentPanel.addChildId(id);
		parentPanel.add(childPanel);

	}

	public void showChildList(List<TreeViewData> child) {
		Long parentId = child.get(0).getParentId();
		NodeViewHolder parentpanel;

		if (treeNodes.get(parentId) != null) {
			parentpanel = treeNodes.get(parentId);
		}

		else {
			Long panelId = child.get(0).getNodeId();
			parentpanel = treeNodes.get(panelId);
		}

		Set<Long> childIds = new HashSet<Long>();

		for (TreeViewData children : child) {

			NodeViewHolder childPanel = showNode(children.getNodeId(), children.getName());

			childPanel.addStyleName("nodeChild");

			childIds.add(children.getNodeId());

			parentpanel.add(childPanel);

		}

		parentpanel.setChildIds(childIds);

	}

	public void drawRoots(List<TreeViewData> roots) {
		for (TreeViewData root : roots) {

			rootPanel.add(showNode(root.getNodeId(), root.getName()));
		}

	}

	public void drawRoot(TreeViewData root) {

		rootPanel.add(showNode(root.getNodeId(), root.getName()));

	}

	public void setNodeVisible(Long id, boolean stage) {

		Set<Long> childIds = treeNodes.get(id).getChildIds();
		ToggleButton button = treeNodes.get(id).getShowNode();

		if (childIds == null || childIds.isEmpty()) {
			return;
		}
		if (stage == true) {

			button.setValue(true);

		}

		else {
			button.setValue(false);

		}

		for (Long i : childIds) {
			treeNodes.get(i).setVisible(stage);
		}
	}

	public boolean isNodeVisible(Long id) {
		NodeViewHolder holder = treeNodes.get(id);

		Set<Long> childIds = holder.getChildIds();

		if (childIds == null || childIds.isEmpty()) {
			return false;
		}

		Long childId = childIds.iterator().next();

		NodeViewHolder childHolder = treeNodes.get(childId);

		if (childHolder == null) {
			return false;
		}

		return childHolder.isVisible();
	}

	public boolean isNodeButtonVisible(Long id) {
		NodeViewHolder holder = treeNodes.get(id);
		return holder.isButtonVisible();

	}

	public void setTreeHandler(TreeHandler handler) {

		this.treeWidgetHandler = handler;
	}

	public void eraseNode(Long id, Long parentId, List<Long> deletedChildIds) {
		NodeViewHolder nodeToRemove = treeNodes.remove(id);
		if (nodeToRemove != null) {
			nodeToRemove.removeFromParent();
		}

		if (parentId != null) {
			NodeViewHolder parentNode = treeNodes.get(parentId);
			if (parentNode != null) {
				parentNode.removeFromChildList(id);
			}
		}
		if (deletedChildIds != null && !deletedChildIds.isEmpty()) {
			for (Long childId : deletedChildIds) {
				NodeViewHolder childNode = treeNodes.remove(childId);
				if (childNode != null) {
					childNode.removeFromParent();
				}
			}
		}
	}

	public boolean hasNodechild(Long id) {

		return !treeNodes.get(id).getChildIds().isEmpty();
	}

	public void setButtonVisible(Long id, boolean stage) {
		treeNodes.get(id).setButtonVisible(stage);
	}

	private void handleTreeClick(ClickEvent event) {

		Element clickedElement = event.getNativeEvent().getEventTarget().cast();
		String idValue = clickedElement.getAttribute("data-tree-id");
		if ((idValue == null) || (idValue.isEmpty())) {
			return;
		}
		Long holderId = Long.valueOf(idValue);

		NodeViewHolder holder = treeNodes.get(holderId);

		if (holder.getShowNode().getElement().isOrHasChild(clickedElement)) {
			treeWidgetHandler.onClick(holderId);
			return;
		}

		if (holder.getNodeName().getElement().isOrHasChild(clickedElement)) {
			treeWidgetHandler.onNodeSelected(holderId);
			return;
		}

	}

	public void updateNodeName(Long id, String name) {
		NodeViewHolder updatedHolder = treeNodes.get(id);
		updatedHolder.setNodeName(name);
	}

	public void colorSelectedNode(Long id, boolean stage) {
		NodeViewHolder selectedHolder = treeNodes.get(id);
		if (selectedHolder == null) {
			return;
		}
		selectedHolder.colorNode(stage);

	}
	
	public void setStatus(Long id,String status) {
		treeNodes.get(id).setStatus(status);
	}
}
