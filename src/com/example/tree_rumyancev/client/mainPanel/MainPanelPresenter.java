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
import com.example.tree_rumyancev.client.table.TablePresenter;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MainPanelPresenter {

	private final TreeServiceAsync treeService;
	private final MainPanelDisplay view;
	private final TreePresenter treePresenter;
	private final TablePresenter tablePresenter;
	private final SelectedNodePresenter selectedNodePresenter;

	private final NodeStore nodeStore;

	public MainPanelPresenter(Builder bulder) {
		this.view = bulder.view;
		this.treePresenter = bulder.treePresenter;
		this.tablePresenter = bulder.tablePresenter;
		this.selectedNodePresenter = bulder.selectedNodePresenter;
		this.nodeStore = bulder.nodeStore;
		this.treeService = bulder.treeService;
		loadData();
		bind();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private TreeServiceAsync treeService;
		private MainPanelDisplay view;
		private TreePresenter treePresenter;
		private TablePresenter tablePresenter;
		private SelectedNodePresenter selectedNodePresenter;

		private NodeStore nodeStore;

		public Builder treeService(TreeServiceAsync treeService) {
			this.treeService = treeService;
			return this;
		}

		public Builder view(MainPanelDisplay view) {
			this.view = view;
			return this;
		}

		public Builder treePresenter(TreePresenter treePresenter) {
			this.treePresenter = treePresenter;
			return this;
		}

		public Builder tablePresenter(TablePresenter tablePresenter) {
			this.tablePresenter = tablePresenter;
			return this;
		}

		public Builder selectedNodePresenter(SelectedNodePresenter selectedNodePresenter) {
			this.selectedNodePresenter = selectedNodePresenter;
			return this;
		}

		public Builder nodeStore(NodeStore nodeStore) {
			this.nodeStore = nodeStore;
			return this;
		}

		public MainPanelPresenter build() {
			return new MainPanelPresenter(this);
		}
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
		treeService.findAll(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				nodeStore.clear();
				for (Node node : result) {
					nodeStore.save(node);
				}

				tablePresenter.loadData(result);
				List<Node> roots = nodeStore.getRoots();
				treePresenter.loadData(roots);
				updateTreeButtons(roots);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка");

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

				Node node = nodeStore.get(nodeId);
				if (node.getParentId() != null) {
					List<Long> pathList = nodeStore.getHierarchyIdList(nodeId);
					if (!pathList.isEmpty()) {
						for (Long pathId : pathList) {
							treePresenter.expandNode(nodeStore.get(pathId), nodeStore.getChildrenList(pathId));
							;
						}
					}
				}
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
		if (newNode == null) {
			return;
		}
		final Long parentId = newNode.getParentId();

		treeService.create(newNode, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				nodeStore.save(result);
				treePresenter.createNode(result);
				updateTreeButton(parentId);
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

		if (newRoot == null) {
			return;
		}

		treeService.create(newRoot, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				nodeStore.save(result);
				treePresenter.createRoot(result);
				updateTreeButton(result.getId());
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

		if (updatedNode == null) {
			return;
		}

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
				updateTreeButton(parentId);

			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ошибка при удалении");

			}
		});
	}

	private void handleTreeButtonClicked(Long nodeId) {
		List<Node> children = nodeStore.getChildrenList(nodeId);
		treePresenter.onNodeButtonClicked(nodeStore.get(nodeId), children);
		updateTreeButtons(children);
		treePresenter.colorLabel(nodeStore.getSelectedNodeId());
	}

	private void updateTreeButton(Long nodeId) {
		if (nodeId == null) {
			return;
		}
		treePresenter.setButtonVisible(nodeId, nodeStore.hasChild(nodeId));
	}

	private void updateTreeButtons(List<Node> nodes) {
		if (nodes == null) {
			return;
		}

		for (Node node : nodes) {
			treePresenter.setButtonVisible(node.getId(), nodeStore.hasChild(node.getId()));
		}
	}
}
