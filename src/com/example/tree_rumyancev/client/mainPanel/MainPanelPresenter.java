package com.example.tree_rumyancev.client.mainPanel;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodePresenter;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.example.tree_rumyancev.client.store.NodeStore;
import com.example.tree_rumyancev.client.table.TablePresenterImpl;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MainPanelPresenter {
	
	private final TreeServiceAsync treeService;
	private MainPanelDisplay view;
	private TreePresenter treePresenter;
	private TablePresenterImpl tablePresenter;
	private SelectedNodePresenter selectedNodePresenter;

	private NodeStore nodeStore;

	public MainPanelPresenter(MainPanelDisplay view, TreePresenter treePresenter, TablePresenterImpl tablePresenter,
			SelectedNodePresenter selectedNodePresenter, NodeStore nodeStore, TreeServiceAsync treeService) {
		this.view = view;
		this.treePresenter = treePresenter;
		this.tablePresenter = tablePresenter;
		this.selectedNodePresenter = selectedNodePresenter;
		this.nodeStore = nodeStore;
		this.treeService = treeService;
		loadData();
		bind();
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

	private void bind() {
		bindTableHandlers();
		bindActionPanelHandlers();
		bindTreeHandlers();
	}

	public void loadData() {
		treeService.getAllData(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				nodeStore.clear();
				for (Node node : result) {
					nodeStore.save(node);
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


	private void bindTreeHandlers() {
		view.setTreeHandler(new TreeHandler() {

			@Override
			public void onNodeSelected(Long nodeId) {
				selectNode(nodeId);

			}

			@Override
			public void onClick(final Long nodeId) {
				
				handleTreeButtonClicked(nodeId);

			}
		});
	}

	private void bindTableHandlers() {
		view.setRefreshButtonHandler(new RefreshButtonClickHandler() {

			@Override
			public void onClick() {
				refreshData();
				
			}
		});
		view.setSelectedRowHandler(new SelectedRowHandler() {

			@Override
			public void onSelected(Long nodeId) {
				selectNode(nodeId);

			}
		});
	}

	private void bindActionPanelHandlers() {
		view.setCreateNodeHandler(new CreateNodeClickHandler() {

			@Override
			public void onClick() {
				createNode();
			}
		});
		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {
				createRoot();
			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {

				updateNode();
				
			}
		});
		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {
				
				deleteNode();
			}
		});
	}

	private void selectNode(Long id) {
		Node node = nodeStore.get(id);

	    if (node == null) {
	        return;
	    }

	    nodeStore.setSelectedNodeId(id);
	    selectedNodePresenter.loadNode(node);
	    tablePresenter.colorRow(id);
	    treePresenter.colorLabel(id);
	}
	
	private void refreshData() {
		tablePresenter.loadData(nodeStore.getValuesList());
		tablePresenter.colorRow(nodeStore.getSelectedNodeId());
	}
	
	private void createNode() {
		Node newNode = selectedNodePresenter.getNodeToCreate();
		treeService.create(newNode, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				nodeStore.save(result);
				treePresenter.createNode(result);
				Window.alert("Дочерняя ветвь создана успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}
		});
	}
	
	private void createRoot() {
		final Node newRoot = selectedNodePresenter.getRootToCreate();
		treeService.create(newRoot, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				nodeStore.save(result);
				treePresenter.createRoot(result);
				Window.alert("Корень создан успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

		});
	}
	
	private void updateNode() {
		final Node updatedNode = selectedNodePresenter.getNodeToUpdate();

		treeService.update(updatedNode, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				nodeStore.save(updatedNode);
				treePresenter.updateNode(updatedNode);
				Window.alert("Обновление прошло успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}
		});
	}
	
	private void deleteNode() {
		final Long deletedId = selectedNodePresenter.getIdToDelete();
		if (deletedId == null) {
			return;
		}
		treeService.delete(deletedId, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				Long parentId = nodeStore.get(deletedId).getParentId();
				List<Long> removedIds = nodeStore.removeSubTrees(deletedId);
				nodeStore.remove(deletedId);
				treePresenter.deleteNode(deletedId, parentId, removedIds);
				selectedNodePresenter.clean();
				nodeStore.clearSelection();

			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ошибка при удалении");

			}
		});
	}
	
	private void handleTreeButtonClicked(Long nodeId) {
		treeService.getChildrenList(nodeId, new AsyncCallback<List<Node>>() {
			@Override
			public void onSuccess(List<Node> children) {

				treePresenter.onNodeButtonClicked(nodeStore.get(nodeId), children);
				treePresenter.colorLabel(nodeStore.getSelectedNodeId());
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ошибка загрузки дочерних узлов");

			}
		});
	}
}
