package com.example.tree_rumyancev.client.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
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
		initWidget(rootPanel);
		addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleTreeClick(event);
			}
		}, ClickEvent.getType());

	}

	private NodeViewHolder showNode(Long id) {

		NodeViewHolder holder = new NodeViewHolder(id);

		treeNodes.put(id, holder);

		return holder;

	}

	public void showChildList(List<TreeViewData> child) {
		Long parentId = child.get(0).getParentId();
		NodeViewHolder parentpanel;

		if (!treeNodes.get(parentId).equals(null)) {
			parentpanel = treeNodes.get(parentId);
		}

		else {
			Long panelId = child.get(0).getNodeId();
			parentpanel = treeNodes.get(panelId);
		}

		Set<Long> childIds = new HashSet<Long>();

		for (TreeViewData children : child) {

			NodeViewHolder childPanel = showNode(children.getNodeId());

			childPanel.addStyleName("nodeChild");

			treeNodes.put(children.getNodeId(), childPanel);
			childIds.add(children.getNodeId());

			parentpanel.add(childPanel);

		}

		parentpanel.setChildIds(childIds);

	}

	public void drawRoots(List<TreeViewData> roots) {
		for (TreeViewData root : roots) {

			rootPanel.add(showNode(root.getNodeId()));
		}

	}

	public void setNodeVisible(Long id, boolean stage) {

		Set<Long> childIds = treeNodes.get(id).getChildIds();
		ToggleButton button = treeNodes.get(id).getShowNode();

		if (stage == true) {
			button.setText("-");
		}

		else {
			button.setText("+");
		}

		if (childIds.isEmpty()) {
			button.setEnabled(false);
		}

		for (Long i : childIds) {
			treeNodes.get(i).setVisible(stage);
		}
	}

	public boolean isNodeVisible(Long id) {

		Long childId = treeNodes.get(id).getChildIds().iterator().next();
		
		return treeNodes.get(childId).isVisible();

	}

	public void setTreeHandler(TreeHandler handler) {

		this.treeWidgetHandler = handler;
	}

	private void handleTreeClick(ClickEvent event) {

		Element clickedElement = event.getNativeEvent().getEventTarget().cast();

		for (Map.Entry<Long, NodeViewHolder> entry : treeNodes.entrySet()) {
			Long nodeId = entry.getKey();
			NodeViewHolder holder = entry.getValue();

			if (holder.getShowNode().getElement().isOrHasChild(clickedElement)) {
				treeWidgetHandler.onclick(nodeId);
				return;
			}

			if (holder.getNodeName().getElement().isOrHasChild(clickedElement)) {
				treeWidgetHandler.onNodeSelected(nodeId);
				return;
			}
		}
	}

}
