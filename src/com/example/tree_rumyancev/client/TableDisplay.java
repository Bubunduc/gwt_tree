package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.dom.client.ClickHandler;

public interface TableDisplay {
	
	public void fillTable(List<Node> nodes);
	
	public void setRefreshButtonClickHandler(ClickHandler handler);
	
	public void initTable(List<Node> nodes);
	
}
