package com.example.tree_rumyancev.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.example.tree_rumyancev.shared.model.Node;
import com.example.tree_rumyancev.client.NodeViewHolder;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

public class TreeView implements TreeDisplay
{
	private NodeViewHolder mainPanel;
	
	private  FlowPanel rootPanel;
	
	private Map<Long, NodeViewHolder> treeNodes = new HashMap<>();;
	
	public TreeView(FlowPanel rootPanel)
	{
		this.rootPanel = rootPanel;
	}
	
	@Override
	public NodeViewHolder showNode(Node node) {
		Long id  = node.getId();
		
		FlowPanel panel = new FlowPanel();
		ToggleButton showNode = new ToggleButton("+");
		Label nodeName = new Label("Panel" + id.toString());
		
		
		showNode.getElement().setId("NodeButton " + id.toString());
		nodeName.getElement().setId("Node Label " + id.toString());
		panel.getElement().setId("Panel " + id.toString());
		panel.add(showNode);
		panel.add(nodeName);
		
		panel.setStyleName("nodePanel");
		showNode.setStyleName("nodeButton");
		nodeName.setStyleName("nodeLabel");
		
		NodeViewHolder holder = new NodeViewHolder(panel,  showNode,nodeName);
		
		treeNodes.put(id,holder);
		
		
		return holder;
		
			
	}

	@Override
	public void showChildList(List<Node> child) {
		Long parentId = child.get(0).getParentId();
		NodeViewHolder parentpanel;
		if (!treeNodes.get(parentId).equals(null)) 
		{
			parentpanel = treeNodes.get(parentId);
		}
		else
		{
			parentpanel = mainPanel;
		}
		Set<Long> childIds = new HashSet<Long>();
		
		
		for (Node children : child)
		{
			NodeViewHolder childPanel = showNode(children);
			
			childPanel.getPanel().addStyleName("nodeChild");
			
			treeNodes.put(children.getId(),childPanel);
			childIds.add(children.getId());
			
			parentpanel.getPanel().add(childPanel.getPanel());
			
		}
		
		parentpanel.setChildIds(childIds);
		
		
	}
	
	@Override
	public void initTree(Node node) 
	{
		mainPanel = showNode(node);
		rootPanel.add(mainPanel.getPanel());
	}

	@Override
	public void setButtonHandler(Long id, ClickHandler handler) {
		ToggleButton button = treeNodes.get(id).getShowNode();
		button.addClickHandler(handler);
		
	}

	@Override
	public void setLabelHandler(Long id, ClickHandler handler) {
		Label label = treeNodes.get(id).getNodeName();
		label.addClickHandler(handler);
		
	}

	@Override
	public void setNodeVisible(Long id, boolean stage) {
		Set<Long> childIds =  treeNodes.get(id).getChildIds();
		ToggleButton button = treeNodes.get(id).getShowNode();
		if (stage == true) 
		{
			button.setText("-");
		}
		else {
			button.setText("+");
		}
		
		for (Long i : childIds)
		{
			 treeNodes.get(i).getPanel().setVisible(stage);
		}
		
	}
	

}
