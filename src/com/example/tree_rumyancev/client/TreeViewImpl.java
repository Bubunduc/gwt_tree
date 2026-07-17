package com.example.tree_rumyancev.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.tree_rumyancev.client.TreeWidget.NodeClickHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.example.tree_rumyancev.shared.model.TreeNode;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeViewImpl implements TreeView
{
	final FlowPanel mainPanel = new FlowPanel();
	
	private Map<Long, ToggleButton> buttons = new HashMap();
	
	private Map<Long, Label> labels = new HashMap<>();
	
	private Map<Long, FlowPanel> panels = new HashMap<>();
	
	public TreeViewImpl(Node rootNode)
	{
		initTree(rootNode);
	}
	
	@Override
	public FlowPanel showNode(Node node) {
		Long id  = node.getId();
		
		FlowPanel panel = new FlowPanel();
		ToggleButton showNode = new ToggleButton("+");
		Label nodeName = new Label("Panel" + id.toString());
		
		
		showNode.getElement().setId("NodeButton " + id.toString());
		nodeName.getElement().setId("Node Label " + id.toString());
		panel.getElement().setId("Panel " + id.toString());
		panel.add(showNode);
		panel.add(nodeName);
		
		
		buttons.put(id, showNode);
		labels.put(id, nodeName);
		panels.put(id, panel);
		
		return panel;
		
		//showNode.addClickHandler(new TreeClickHandler(node.getId(),panel));
		//nodeName.addClickHandler(new NodeClickHandler(node,selectedNodePanel));
		
		
		
	}

	@Override
	public void showChildList(List<Node> child) {
		Long parentId = child.get(0).getParentId();
		FlowPanel parentpanel;
		if (!panels.get(parentId).equals(null)) 
		{
			parentpanel = panels.get(parentId);
		}
		else
		{
			parentpanel = mainPanel;
		}
		for (Node children : child)
		{
			parentpanel.add(showNode(children));
		}
		
	}
	
	@Override
	public void initTree(Node node) 
	{
		mainPanel.add(showNode(node));
		RootPanel.get("treeRootPanel").add(mainPanel);
	}
	

}
