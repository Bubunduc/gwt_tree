package com.example.tree_rumyancev.client.table;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TablePresenterImpl implements TablePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private TableDisplay view;
	private Long SelectedNodeId;

	public TablePresenterImpl(TableDisplay view) {
		this.view = view;
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());
		loadData();

	}

	@Override
	public void loadData() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {

				List<TableViewData> data = TableViewData.toDtoList(result);
				view.fillTable(data);
				bind();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

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
				treeService.findById(nodeId, new AsyncCallback<Node>() {

					@Override
					public void onSuccess(Node result) {
						TableViewData row = TableViewData.toDto(result);
						view.showSelectedRow(row);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

	}

}
