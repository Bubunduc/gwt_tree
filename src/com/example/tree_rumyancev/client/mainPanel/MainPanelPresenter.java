package com.example.tree_rumyancev.client.mainPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.actions.ActionsPresenter;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateNodeEvent;
import com.example.tree_rumyancev.client.handlers.event.selectedNode.CreateRootEvent;
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
	private Long selectedNode;

	public MainPanelPresenter(MainPanelDisplay view, EventBus eventBus, TreePresenter treePresenter,
			ActionsPresenter actionsPresenter, TablePresenterImpl tablePresenter,
			SelectedNodePresenter selectedNodePresenter) {
		this.view = view;
		this.eventBus = eventBus;
		this.treePresenter = treePresenter;
		this.actionsPresenter = actionsPresenter;
		this.tablePresenter = tablePresenter;
		this.selectedNodePresenter = selectedNodePresenter;

		nodes = new HashMap<>();
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
				Node newNode = selectedNodePresenter.getNodeToCreate();
				treeService.create(newNode, new AsyncCallback<Node>() {

					@Override
					public void onSuccess(Node result) {
						nodes.put(result.getId(), result);
						treePresenter.createNode(result);
						Window.alert("Дочерняя ветвь создана успешно");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}
				});

			}
		});
		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {
				final Node newRoot = selectedNodePresenter.getRootToCreate();
				treeService.create(newRoot, new AsyncCallback<Node>() {

					@Override
					public void onSuccess(Node result) {
						nodes.put(result.getId(), result);
						treePresenter.createRoot(result);
						Window.alert("Корень создан успешно");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}

				});

			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {

				final Node updatedNode = selectedNodePresenter.getNodeToUpdate();

				treeService.update(updatedNode, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						nodes.put(updatedNode.getId(),updatedNode);
						treePresenter.updateNode(updatedNode);
						Window.alert("Обновление прошло успешно");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());

					}
				});

			}
		});
		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {
				treeService.delete(selectedNode, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Long parentId = nodes.get(selectedNode).getParentId();
						List<Long> removedIds = removeChild(selectedNode);
						nodes.remove(selectedNode);
						treePresenter.deleteNode(selectedNode, parentId, removedIds);
						selectedNodePresenter.clean();
						
						
					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Ошибка при удалении");

					}
				});

			}
		});
		// Таблица
		view.setRefreshButtonHandler(new RefreshButtonClickHandler() {

			@Override
			public void onClick() {
				tablePresenter.loadData(new ArrayList(nodes.values()));

			}
		});
		view.setSelectedRowHandler(new SelectedRowHandler() {

			@Override
			public void onSelected(Long nodeId) {
				selectedNode = nodeId;
				selectedNodePresenter.loadNode(nodes.get(nodeId));
				tablePresenter.colorRow(nodeId);
				treePresenter.onNodeLabelClicked(nodeId);

			}
		});

		// Дерево
		view.setTreeHandler(new TreeHandler() {

			@Override
			public void onNodeSelected(Long nodeId) {
				selectedNode = nodeId;
				selectedNodePresenter.loadNode(nodes.get(nodeId));
				tablePresenter.colorRow(nodeId);
				treePresenter.onNodeLabelClicked(nodeId);

			}

			@Override
			public void onClick(final Long nodeId) {
				treeService.getChildrenList(nodeId, new AsyncCallback<List<Node>>() {
					@Override
					public void onSuccess(List<Node> children) {

						treePresenter.onNodeButtonClicked(nodes.get(nodeId), children);
					}

					@Override
					public void onFailure(Throwable caught) {

						Window.alert("Ошибка загрузки дочерних узлов");

					}
				});

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

				tablePresenter.loadData(result);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка");

			}
		});
		treeService.getParentList(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				treePresenter.loadData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка загрузки корней");

			}
		});
	}

	private List<Long> removeChild(Long parentId) {
		List<Long> removedIds = new ArrayList<>();
		List<Long> directChildIds = new ArrayList<>();

		for (Map.Entry<Long, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();

			if (parentId.equals(node.getParentId())) {
				directChildIds.add(node.getId());
			}
		}

		for (Long childId : directChildIds) {
			removedIds.addAll(removeChild(childId));
			
			nodes.remove(childId);
			
			
			removedIds.add(childId);
		}

		return removedIds;
	}

}
