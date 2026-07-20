package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TablePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private TableDisplay tableDisplay;

	public TablePresenter(TableDisplay tableDisplay) {
		this.tableDisplay = tableDisplay;
	}

	void go() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				tableDisplay.initTable(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}

}
