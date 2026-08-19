package com.example.tree_rumyancev.client.mainPanel;

import java.util.List;

import com.example.tree_rumyancev.client.ServerStatus.ServerStatusPresenter;
import com.example.tree_rumyancev.client.dto.DeletedNodeData;
import com.example.tree_rumyancev.client.dto.ServerStatusViewData;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.PingNodeClicklHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.client.handlers.tree.TreeHandler;
import com.example.tree_rumyancev.client.request.HealthRequest;
import com.example.tree_rumyancev.client.request.PingCallback;
import com.example.tree_rumyancev.client.selectedNode.SelectedNodePresenter;
import com.example.tree_rumyancev.client.store.NodeRepository;
import com.example.tree_rumyancev.client.store.NodeStore;
import com.example.tree_rumyancev.client.table.TablePresenter;
import com.example.tree_rumyancev.client.tree.TreePresenter;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MainPanelPresenter {

	private final MainPanelDisplay view;
	private final TreePresenter treePresenter;
	private final TablePresenter tablePresenter;
	private final SelectedNodePresenter selectedNodePresenter;
	private final ServerStatusPresenter serverStatusPresenter;
	private final NodeStore nodeStore;

	public MainPanelPresenter(Builder builder) {
		this.view = builder.view;
		this.treePresenter = builder.treePresenter;
		this.tablePresenter = builder.tablePresenter;
		this.selectedNodePresenter = builder.selectedNodePresenter;
		this.serverStatusPresenter = builder.serverStatusPresenter;
		this.nodeStore = builder.nodeStore;
		loadData();
		bind();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private MainPanelDisplay view;
		private TreePresenter treePresenter;
		private TablePresenter tablePresenter;
		private SelectedNodePresenter selectedNodePresenter;
		private ServerStatusPresenter serverStatusPresenter;

		private NodeStore nodeStore;

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

		public Builder serverStatusPresenter(ServerStatusPresenter serverStatusPresenter) {
			this.serverStatusPresenter = serverStatusPresenter;
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
		NodeRepository.findAll(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				List<Node> roots = nodeStore.getRoots();
				treePresenter.loadData(roots);
				tablePresenter.loadData(result);
				updateTreeButtons(roots);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка при инициализации" + caught.getMessage());

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
		view.setPingNodeHandler(new PingNodeClicklHandler() {

			@Override
			public void onClick() {
				pingNode();
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
		serverStatusPresenter.showData(id);
	}

	private void refreshData() {
		NodeRepository.findAll(new AsyncCallback<List<Node>>() {

			@Override
			public void onSuccess(List<Node> result) {
				tablePresenter.loadData(result);
				tablePresenter.colorRow(nodeStore.getSelectedNodeId());
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка при получении всех нод " + caught.getMessage());

			}
		});

	}

	private void createNode() {

		Node newNode = selectedNodePresenter.getNodeToCreate();
		if (newNode == null) {
			return;
		}
		final Long parentId = newNode.getParentId();

		NodeRepository.create(newNode, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				treePresenter.createNode(result);
				updateTreeButton(parentId);
				Window.alert("Дочерняя ветвь создана успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка при создании ноды " + caught.getMessage());

			}
		});

	}

	private void createRoot() {
		final Node newRoot = selectedNodePresenter.getRootToCreate();

		if (newRoot == null) {
			return;
		}

		NodeRepository.create(newRoot, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				treePresenter.createRoot(result);
				updateTreeButton(result.getId());
				Window.alert("Корень создан успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка при создании корня " + caught.getMessage());

			}

		});
	}

	private void updateNode() {
		final Node updatedNode = selectedNodePresenter.getNodeToUpdate();

		if (updatedNode == null) {
			return;
		}

		NodeRepository.update(updatedNode, new AsyncCallback<Node>() {

			@Override
			public void onSuccess(Node result) {
				treePresenter.updateNode(result);
				Window.alert("Обновление прошло успешно");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ошибка при обновлении " + caught.getMessage());

			}
		});
	}

	private void deleteNode() {
		final Long deletedId = selectedNodePresenter.getIdToDelete();

		if (deletedId == null) {
			return;
		}
		NodeRepository.delete(deletedId, new AsyncCallback<DeletedNodeData>() {

			@Override
			public void onSuccess(DeletedNodeData result) {
				treePresenter.deleteNode(deletedId, result.getParentId(), result.getRemovedIds());
				selectedNodePresenter.clean();
				updateTreeButton(result.getParentId());
				Window.alert("Удаление прошло успешно");
			}

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Ошибка при удалении " + caught.getMessage());

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

	private void pingNode() {
		final Node selectedNode = nodeStore.getSelectedNode();
		HealthRequest.ping(selectedNode, new PingCallback() {

			@Override
			public void onSuccess(ServerStatusViewData data) {
				serverStatusPresenter.setData(selectedNode.getId(), data);
				treePresenter.setStatus(data.getStatus());

			}

			@Override
			public void onFailure(String message) {
				Window.alert(message);
			}
		});
	}
}
