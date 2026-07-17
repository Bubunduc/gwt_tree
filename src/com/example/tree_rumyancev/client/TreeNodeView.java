package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeNodeView {
	
	
	private final Node node;
	
	private Long id;
	
	private Long parentId;
	
	private FlowPanel parentPanel;
	
	private ToggleButton showNodeButton;
	
	private Label nodeNameLabel;
	
	FlowPanel panel = new FlowPanel();
	
	
	public TreeNodeView(Node node , FlowPanel parentPanel)
	{
		this.node = node;
		
		this.id = node.getId();
		
		this.parentPanel = parentPanel;
		
		
	}
	
	public TreeNodeView(Node node)
	{
		this.node = node;
		
		this.id = node.getId();
		
	}
	
	
	public Long getId() 
	{
		return this.id;
	}
	
	public Long getParentId()
	{
		return this.id;
	}
	
	public ToggleButton getshowNodeButton() 
	{
		return showNodeButton;
	}
	
	public Label getNodeNameLabel()
	{
		return nodeNameLabel;
	}
	
	public void setOnAddHandler(Command command) 
	{
		showNodeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				command.execute();
				
			}
		});
	}
	
	public void createNode() 
	{
		String panelId  = id.toString();
		
		
		showNodeButton = new ToggleButton("+");
		nodeNameLabel = new Label("Panel" + panelId);
		
		
		showNodeButton.getElement().setId("NodeButton " + panelId);
		nodeNameLabel.getElement().setId("Node Label " + panelId);
		panel.getElement().setId("Panel " + panelId);
		
		panel.add(showNodeButton);
		panel.add(nodeNameLabel);
		
		if (parentPanel.equals(null)) 
		{
			RootPanel.get("NodeWithRoot" + panelId).add(panel);
		}
		
		else
		{
			panel.getElement().setId("Node_child" + parentId.toString());
		}
		
	}
	
	public boolean isButtonDowned()
	{
		return showNodeButton.isDown() == true;
	}
	
	public void upButton()
	{
		showNodeButton.setText("+");
		panel.setVisible(false);
		
	}
	
	public void downButton()
	{
		showNodeButton.setText("-");
		panel.setVisible(true);
	}
	
	
}
