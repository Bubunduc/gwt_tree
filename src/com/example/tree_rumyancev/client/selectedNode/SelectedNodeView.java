package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.handlers.selectedNode.NodeDeleteEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SelectedNodeView implements SelectedNodeDisplay {
	
	private FlexTable selectedNodeTable;
	private HorizontalPanel buttonPanel;
	FlowPanel selectedNodePanel;
	
	DeleteButtonClickHandler deleteHandler;
	
	public SelectedNodeView() {
		
		selectedNodeTable = new FlexTable();
		buttonPanel = new HorizontalPanel();
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
		
		
		Button addRootButton = new Button("Add Root");
		
		
		Button editButton = new Button("Edit");
		
		
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
		} else {
			parentId.setText("Корень дерева");
		}
		name.setText(node.getName());
		ip.setText(node.getIp());
		port.setText(node.getPort().toString());
		selectedNodeTable.setWidget(0, 1, id);
		selectedNodeTable.setWidget(1, 1, parentId);
		selectedNodeTable.setWidget(2, 1, name);
		selectedNodeTable.setWidget(3, 1, ip);
		selectedNodeTable.setWidget(4, 1, port);
	}

	@Override
	public Widget asWidget() {
		
		return selectedNodePanel;
	}

	@Override
	public void setDeleteButtonHandler(DeleteButtonClickHandler handler) {
		this.deleteHandler = handler;
		
	}
}
