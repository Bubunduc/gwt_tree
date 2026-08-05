package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SelectedNodeView implements SelectedNodeDisplay {

	private FlexTable selectedNodeTable;

	private FlowPanel selectedNodePanel;

	public SelectedNodeView() {

		selectedNodeTable = new FlexTable();
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

		selectedNodePanel.add(selectedNodeTable);

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
		} else {
			parentId.setText("Корень дерева");
		}

		name.setText(node.getName());

		ip.setText(node.getIp());

		if (node.getPort() == null) {
			port.setText("");
		} else {
			port.setText(node.getPort().toString());
		}

		selectedNodeTable.setWidget(0, 1, id);
		selectedNodeTable.setWidget(1, 1, parentId);
		selectedNodeTable.setWidget(2, 1, name);
		selectedNodeTable.setWidget(3, 1, ip);
		selectedNodeTable.setWidget(4, 1, port);
	}

	@Override
	public Node getCurrentNode() {

		Long id;
		try {
			id = Long.valueOf(((TextBox) selectedNodeTable.getWidget(0, 1)).getText());
		} catch (Exception e) {
			id = null;
		}
		Long parentId;
		try {
			parentId = Long.valueOf(((TextBox) selectedNodeTable.getWidget(1, 1)).getText());
		} catch (Exception e) {
			parentId = null;
		}

		String name = ((TextBox) selectedNodeTable.getWidget(2, 1)).getText();
		String ip = ((TextBox) selectedNodeTable.getWidget(3, 1)).getText();
		Short port;
		try {
			port = Short.valueOf(((TextBox) selectedNodeTable.getWidget(4, 1)).getText());
		} catch (Exception e) {
			port = null;
		}

		Node node = new Node(id, parentId, name, ip, port);
		return node;

	}

	@Override
	public void cleanSelected() {
		selectedNodeTable.removeAllRows();
		initSelectedNodeTable();
	}

	@Override
	public Widget asWidget() {

		return selectedNodePanel;
	}

}
