package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SelectedNodeView implements SelectedNodeDisplay {
	private FlexTable selectedNodeTable = new FlexTable();

	public SelectedNodeView() {

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

		Button addButton = new Button("Add");
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");

		selectedNodeTable.setWidget(5, 0, addButton);
		selectedNodeTable.setWidget(5, 1, editButton);
		selectedNodeTable.setWidget(5, 2, deleteButton);

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
		
		return selectedNodeTable;
	}
}
