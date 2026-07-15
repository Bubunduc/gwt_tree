package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class TableWidget extends Composite
{
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private List<Node> allNodes;
	private FlexTable AllDatatable = new FlexTable();
	
	public TableWidget()
	{
		initWidget(AllDatatable);
		initAllNodesTable();
	}
	
	public void initAllNodesTable() 
	{
			treeService.getAllData(new AsyncCallback<List<Node>>() {
			
			@Override
			public void onSuccess(List<Node> result) {
				allNodes = result;
				
				Button refreshButton = new Button("обновить");
				AllDatatable.setWidget(0, 0, refreshButton);
				fillTable();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	public void fillTable() 
	{
		int counter = 2;
		AllDatatable.setText(1, 0, "Id");
		AllDatatable.setText(1, 1, "parentId");
		AllDatatable.setText(1, 2, "name");
		AllDatatable.setText(1, 3, "ip");
		AllDatatable.setText(1, 4, "port");
		for (Node node : allNodes)
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
}
