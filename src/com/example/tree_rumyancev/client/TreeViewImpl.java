package com.example.tree_rumyancev.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.tree_rumyancev.shared.model.Node;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

public class TreeViewImpl implements TreeView
{
	final private FlowPanel mainPanel = new FlowPanel();
	
	private FlowPanel rootPanel;
	
	private Map<Long, ToggleButton> buttons = new HashMap();
	
	private Map<Long, Label> labels = new HashMap<>();
	
	private Map<Long, FlowPanel> panels = new HashMap<>();
	
	public TreeViewImpl(FlowPanel rootPanel)
	{
		this.rootPanel = rootPanel;
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
		
		panel.setStyleName("nodePanel");
		showNode.setStyleName("nodeButton");
		nodeName.setStyleName("nodeLabel");
		
		buttons.put(id, showNode);
		labels.put(id, nodeName);
		panels.put(id, panel);
		
		return panel;
		
			
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
			FlowPanel childPanel = showNode(children);
			childPanel.addStyleName("nodeChild");
			parentpanel.add(childPanel);
			
		}
		
	}
	
	@Override
	public void initTree(Node node) 
	{
		mainPanel.add(showNode(node));
		rootPanel.add(mainPanel);
	}

	@Override
	public void setButtonHandler(Long id, ClickHandler handler) {
		ToggleButton button = buttons.get(id);
		button.addClickHandler(handler);
		
	}

	@Override
	public void setLabelHandler(Long id, ClickHandler handler) {
		Label label = labels.get(id);
		label.addClickHandler(handler);
		
	}

	@Override
	public void setNodeVisible(Long id, boolean stage) {
		FlowPanel panel = panels.get(id);
		ToggleButton button = buttons.get(id);
		if (stage == true) 
		{
			button.setText("-");
		}
		else {
			button.setText("+");
		}
		for (int i = 2; i < panel.getWidgetCount(); i++) { //0 кнопка 1 табличка
		    Widget child = panel.getWidget(i);
		    child.setVisible(stage); 
		}
		
	}
	

}
