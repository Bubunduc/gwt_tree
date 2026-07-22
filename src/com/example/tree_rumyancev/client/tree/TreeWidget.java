package com.example.tree_rumyancev.client.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.shared.dto.TreeViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.dom.client.Element;

public class TreeWidget extends Composite {

	private FlowPanel rootPanel ;

	private Map<Long, NodeViewHolder> treeNodes;

	private TreeHandler treeWidgetHandler;
	
	public TreeWidget() {
		
		treeNodes = new HashMap<>();
		init();
		initWidget(rootPanel);
		addDomHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			//Window.alert(event.getRelativeElement().toString());
			Element clickedElement =  event
		                .getNativeEvent()
		                .getEventTarget().cast();
			Element target = Element.as(event.getNativeEvent().getEventTarget());
			Label l = Label.wrap(target);
			Window.alert(l.toString());
			
			}
		}, ClickEvent.getType());
		
	}
	private void init() {
		rootPanel = new FlowPanel();
	}
	private NodeViewHolder showNode(TreeViewData node) {
		
		Long id = node.getNodeId();

		NodeViewHolder holder = new NodeViewHolder(id);

		treeNodes.put(id, holder);

		return holder;

	}

	public void showChildList(List<TreeViewData> child) {
		Long parentId = child.get(0).getParentId();
		NodeViewHolder parentpanel;

		if (!treeNodes.get(parentId).equals(null)) {
			parentpanel = treeNodes.get(parentId);
		} else {
			Long panelId = child.get(0).getNodeId();
			parentpanel = treeNodes.get(panelId);
		}
		Set<Long> childIds = new HashSet<Long>();

		for (TreeViewData children : child) {
			NodeViewHolder childPanel = showNode(children);

			childPanel.addStyleName("nodeChild");

			treeNodes.put(children.getNodeId(), childPanel);
			childIds.add(children.getNodeId());

			parentpanel.add(childPanel);

		}

		parentpanel.setChildIds(childIds);

	}

	public void drawRoots(List<TreeViewData> roots) {
		for (TreeViewData root : roots) {

			rootPanel.add(showNode(root));
		}

	}
	
	private NodeViewHolder getNodeViewHolder()
	{
		return null;
	}

	public void setNodeVisible(Long id, boolean stage) {
		Set<Long> childIds = treeNodes.get(id).getChildIds();
		ToggleButton button = treeNodes.get(id).getShowNode();
		if (stage == true) {
			button.setText("-");
		} else {
			button.setText("+");
		}
		if (childIds.isEmpty()) 
		{
			button.setEnabled(false);
		}

		for (Long i : childIds) {
			treeNodes.get(i).setVisible(stage);
		}

	}

	public void setButtonHandler(Long id, ClickHandler handler) {
		ToggleButton button = treeNodes.get(id).getShowNode();
		button.addClickHandler(handler);

	}
	
	private void setTreeHandler(TreeHandler handler) {
		
		this.treeWidgetHandler = handler;
	}

	public void setLabelHandler(Long id, ClickHandler handler) {
		Label label = treeNodes.get(id).getNodeName();
		label.addClickHandler(handler);

	}

}
