package com.example.tree_rumyancev.client.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.handlers.event.selectedNode.NodeSelectionEvent;
import com.example.tree_rumyancev.client.handlers.event.table.SelectedFromTableNodeEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeSelectionEventHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TablePresenterImpl implements TablePresenter {

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private TableDisplay view;
	private EventBus eventBus;

	private Map<Long, Node> data;

	public TablePresenterImpl(TableDisplay view, EventBus eventBus) {

		data = new HashMap<Long, Node>();

		this.view = view;
		this.eventBus = eventBus;

		//bind();

	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());
	}
	
	//Используется, если нет данных извне
	@Override
	public void loadData() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				data.clear();
				for (Node node : result) {
					data.put(node.getId(), node);
				}
				view.fillTable(TableViewData.toViewDataList(result));

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка");

			}
		});

	}
	
	@Override
	public void loadData(List<Node> data) {
		view.fillTable(TableViewData.toViewDataList(data));
		
	}

	private void bind() {

		eventBus.addHandler(NodeSelectionEvent.TYPE, new NodeSelectionEventHandler() {

			@Override
			public void onNodeSelected(NodeSelectionEvent event) {
				view.colorSelectedRow(event.getNode().getId());

			}
		});

		view.setRefreshButtonHandler(new RefreshButtonClickHandler() {

			@Override
			public void onClick() {

				loadData();
			}
		});

		view.setSelectedRowHandler(new SelectedRowHandler() {

			@Override
			public void onSelected(final Long nodeId) {
				Node selectedNode = data.get(nodeId);
				eventBus.fireEvent(new SelectedFromTableNodeEvent(selectedNode));
			}
		});

	}
	
	@Override
	public void colorRow(Long id) {
		view.colorSelectedRow(id);
	}
	

}
