package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SelectedNodeView implements SelectedNodeDisplay {
	
	private FlexTable selectedNodeTable;
	private FlowPanel buttonPanel;
	FlowPanel selectedNodePanel;
	
	private DeleteClickHandler deleteHandler;
	private CreateRootClickHandler createRootHandler;
	private CreateNodeClickHandler createNodeHandler;
	private UpdateNodeClickHandler updateNodeHandler;
	
	public SelectedNodeView() {
		
		selectedNodeTable = new FlexTable();
		buttonPanel = new FlowPanel();
		selectedNodePanel = new FlowPanel();

		initSelectedNodeTable();
	}

	@Override
	public void initSelectedNodeTable() {
		TextBox id = new TextBox();
		id.setReadOnly(true);
		TextBox parentId = new TextBox();
		parentId.setReadOnly(true);
		selectedNodeTable.setText(0, 0, "Id");
		selectedNodeTable.setWidget(0, 1, id);

		selectedNodeTable.setText(1, 0, "parentId");
		selectedNodeTable.setWidget(1, 1, parentId);

		selectedNodeTable.setText(2, 0, "name");
		selectedNodeTable.setWidget(2, 1, new TextBox());

		selectedNodeTable.setText(3, 0, "ip");
		selectedNodeTable.setWidget(3, 1, new TextBox());

		selectedNodeTable.setText(4, 0, "port");
		selectedNodeTable.setWidget(4, 1, new TextBox());

		Button addNodeButton = new Button("Add");
		addNodeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				createNodeHandler.onClick();
				
			}
		});
		
		
		Button addRootButton = new Button("Add Root");
		addRootButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				createRootHandler.onClick();
				
			}
		});
		
		
		Button editButton = new Button("Edit");
		editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				updateNodeHandler.onClick();
				
			}
		});
		
		
		Button deleteButton = new Button("Delete");
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				deleteHandler.onClick();
				
			}
		});
		

		buttonPanel.add(addNodeButton);
		buttonPanel.add(addRootButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		
		selectedNodePanel.add(selectedNodeTable);
		selectedNodePanel.add(buttonPanel);

		selectedNodePanel.setStyleName("SelectedNodePanel");
		
	}

	@Override
	public void showNode(Node node) {
		
		TextBox id = new TextBox();
		id.setReadOnly(true);
		TextBox parentId = new TextBox();
		parentId.setReadOnly(true);
		TextBox name = new TextBox();
		TextBox ip = new TextBox();

		TextBox port = new TextBox();
		id.setText(node.getId().toString());
		if (node.getParentId() != null) {
			
			parentId.setText(node.getParentId().toString());
			
		} 
		else {
			parentId.setText("Корень дерева");	
		}
		
		name.setText(node.getName());

		ip.setText(node.getIp());
		
		if (node.getPort() == null) {
			port.setText("");
		}
		else {
			port.setText(node.getPort().toString());
		}
		
		selectedNodeTable.setWidget(0, 1, id);
		selectedNodeTable.setWidget(1, 1, parentId);
		selectedNodeTable.setWidget(2, 1, name);
		selectedNodeTable.setWidget(3, 1, ip);
		selectedNodeTable.setWidget(4, 1, port);
	}
	
	@Override
	public Node getNewNode() {
		
		Long id;
		try {
			id = Long.valueOf(((TextBox)selectedNodeTable.getWidget(0, 1)).getText());
		}
		catch (Exception e){
			id = null;
		}
		Long parentId;
		try {
			parentId = Long.valueOf(((TextBox)selectedNodeTable.getWidget(1, 1)).getText());
		}
		catch (Exception e){
			parentId = null;
		}
		 
		String name =  ((TextBox)selectedNodeTable.getWidget(2, 1)).getText();
		String ip =  ((TextBox)selectedNodeTable.getWidget(3, 1)).getText();
		Short port;
		try {
			port =  Short.valueOf(((TextBox)selectedNodeTable.getWidget(4, 1)).getText());
		}
		catch (Exception e){
			port = null;
		}
		
		Node node = new Node(id, parentId, name, ip, port);
		return node;
		
		
	}
	@Override
	public Widget asWidget() {
		
		return selectedNodePanel;
	}

	@Override
	public void setDeleteButtonHandler(DeleteClickHandler handler) {
		this.deleteHandler = handler;
		
	}
	
	@Override
	public void setCreateRootHandler(CreateRootClickHandler createRootHandler) {
		this.createRootHandler = createRootHandler;
	}
	
	@Override
	public void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler) {
		this.createNodeHandler = createNodeHandler;
	}
	
	@Override
	public void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler) {
		this.updateNodeHandler = updateNodeHandler;
	}
	
	
}
