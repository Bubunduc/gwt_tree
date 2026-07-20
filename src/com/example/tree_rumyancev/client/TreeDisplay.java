package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface TreeDisplay extends IsWidget {

	void showChildList(List<Node> child);

	void setButtonHandler(Long id, ClickHandler handler);

	void setLabelHandler(Long id, ClickHandler handler);

	void setNodeVisible(Long id, boolean stage);

	void drawRoots(List<Node> roots);

}
