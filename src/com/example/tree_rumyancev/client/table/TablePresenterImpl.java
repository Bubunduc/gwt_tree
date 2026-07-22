package com.example.tree_rumyancev.client.table;

import java.util.ArrayList;
import java.util.List;

import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TablePresenterImpl implements TablePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private TableDisplay view;

	List<Node> data;

	public TablePresenterImpl(TableDisplay view) {
		data = new ArrayList<Node>();
		this.view = view;
		bind();

	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());
	}

	@Override
	public void loadData() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {

				data = result;
				view.fillTable(TableViewData.toViewDataList(data));

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка");

			}
		});

	}

	@Override
	public void bind() {
		view.setRefreshButtonHandler(new RefreshButtonClickHandler() {

			@Override
			public void onClick() {

				loadData();
			}
		});

		view.setSelectedRowHandler(new SelectedRowHandler() {

			@Override
			public void onSelected(final Long nodeId) {
				for (Node node : data) {

					if (node.getId().equals(nodeId)) {
						TableViewData row = TableViewData.toViewData(node);
						view.showSelectedRow(row);

					}
				}
			}
		});

	}

}
