package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface SelectedNodeDisplay extends IsWidget {

	void cleanSelected();

	void showNode(Node node);

	Node getCurrentNode();

	Widget asWidget();
}
