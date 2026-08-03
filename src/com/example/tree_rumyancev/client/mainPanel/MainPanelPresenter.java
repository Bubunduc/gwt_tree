package com.example.tree_rumyancev.client.mainPanel;

import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.actions.ActionsPresenter;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodePresenter;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.client.table.TablePresenterImpl;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MainPanelPresenter {
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	private MainPanelDisplay view;
	private EventBus eventBus;
	private TreePresenter treePresenter;
	private ActionsPresenter actionsPresenter;
	private TablePresenterImpl tablePresenter;
	private SelectedNodePresenter selectedNodePresenter;
	private Map<Long, Node> nodes;

	public MainPanelPresenter(MainPanelDisplay view, EventBus eventBus, TreePresenter treePresenter,
			ActionsPresenter actionsPresenter, TablePresenterImpl tablePresenter,
			SelectedNodePresenter selectedNodePresenter) {
		this.view = view;
		this.eventBus = eventBus;
		this.treePresenter = treePresenter;
		this.actionsPresenter = actionsPresenter;
		this.tablePresenter = tablePresenter;
		this.selectedNodePresenter = selectedNodePresenter;
	}

	public MainPanelPresenter(MainPanelDisplay mainView) {
		this.view = mainView;
		loadData();
		bind();
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());
	}

	private void bind() {

		// Панель действий
		view.setCreateNodeHandler(new CreateNodeClickHandler() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		// Таблица
		view.setRefreshButtonHandler(new RefreshButtonClickHandler() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub

			}
		});
		view.setSelectedRowHandler(new SelectedRowHandler() {

			@Override
			public void onSelected(Long nodeId) {
				// TODO Auto-generated method stub

			}
		});

		// Дерево
		view.setTreeHandler(new TreeHandler() {

			@Override
			public void onNodeSelected(Long nodeId) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onClick(Long nodeId) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void loadData() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				nodes.clear();
				for (Node node : result) {
					nodes.put(node.getId(), node);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка");

			}
		});
	}

}
