package com.example.tree_rumyancev.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeWidget extends Composite {

	private FlowPanel rootPanel = new FlowPanel();

	private Map<Long, NodeViewHolder> treeNodes = new HashMap<>();

	public TreeWidget() {

		initWidget(rootPanel);
	}

	private NodeViewHolder showNode(Node node) {
		Long id = node.getId();

		NodeViewHolder holder = new NodeViewHolder(id);

		treeNodes.put(id, holder);

		return holder;

	}

	public void showChildList(List<Node> child) {
		Long parentId = child.get(0).getParentId();
		NodeViewHolder parentpanel;

		if (!treeNodes.get(parentId).equals(null)) {
			parentpanel = treeNodes.get(parentId);
		} else {
			Long panelId = child.get(0).getId();
			parentpanel = treeNodes.get(panelId);
		}
		Set<Long> childIds = new HashSet<Long>();

		for (Node children : child) {
			NodeViewHolder childPanel = showNode(children);

			childPanel.addStyleName("nodeChild");

			treeNodes.put(children.getId(), childPanel);
			childIds.add(children.getId());

			parentpanel.add(childPanel);

		}

		parentpanel.setChildIds(childIds);

	}

	public void drawRoots(List<Node> roots) {
		for (Node root : roots) {

			rootPanel.add(showNode(root));
		}

	}

	public void setNodeVisible(Long id, boolean stage) {
		Set<Long> childIds = treeNodes.get(id).getChildIds();
		ToggleButton button = treeNodes.get(id).getShowNode();
		if (stage == true) {
			button.setText("-");
		} else {
			button.setText("+");
		}

		for (Long i : childIds) {
			treeNodes.get(i).setVisible(stage);
		}

	}

	public void setButtonHandler(Long id, ClickHandler handler) {
		ToggleButton button = treeNodes.get(id).getShowNode();
		button.addClickHandler(handler);

	}

	public void setLabelHandler(Long id, ClickHandler handler) {
		Label label = treeNodes.get(id).getNodeName();
		label.addClickHandler(handler);

	}

}
