package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class TableView implements TableDisplay
{
	
	private FlexTable AllDatatable = new FlexTable();
	Button refreshButton = new Button("обновить");
	ClickHandler refreshHandler;
	
	@Override
	public void setRefreshButtonClickHandler(ClickHandler handler)
	{
		refreshHandler = handler;
	}
	
	@Override
	public void fillTable(List<Node> nodes) 
	{
		AllDatatable.clear();
		AllDatatable.setWidget(0, 0, refreshButton);
		//refreshButton.addClickHandler(refreshHandler); //Раскоментировать как только появится хендлер
		int counter = 2;
		AllDatatable.setText(1, 0, "Id");
		AllDatatable.setText(1, 1, "parentId");
		AllDatatable.setText(1, 2, "name");
		AllDatatable.setText(1, 3, "ip");
		AllDatatable.setText(1, 4, "port");
		for (Node node : nodes)
		{
			AllDatatable.setText(counter, 0, node.getId().toString());
			if (node.getParentId()!= null) 
			{
				AllDatatable.setText(counter, 1, node.getParentId().toString());
			}
			else
			{
				AllDatatable.setText(counter, 1, "корень");
			}
			AllDatatable.setText(counter, 2, node.getName());
			AllDatatable.setText(counter, 3, node.getIp());
			AllDatatable.setText(counter, 4, node.getPort().toString());
			counter++;
		}
	}
	
	@Override
	public void initTable(List<Node> nodes)
	{
		RootPanel.get("AllNodesTable").add(AllDatatable);
		fillTable(nodes);
	}
	
	
}
