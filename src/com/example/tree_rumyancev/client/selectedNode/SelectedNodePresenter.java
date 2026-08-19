package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.client.dto.SelectedNodeData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedNodePresenter {

	private final SelectedNodeDisplay view;

	public SelectedNodePresenter(SelectedNodeDisplay view) {

		this.view = view;
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

	public Node getRootToCreate() {

		SelectedNodeData selectedNode = view.getData();
		if (hasEmptyRequeredFields(selectedNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}
		Short port = checkPort(selectedNode.getPort());
		if (port == null) {
			return null;
		}
		Node newNode = getSelectedNode(selectedNode, port);
		newNode.setParentId(null);
		return newNode;

	}

	public Node getNodeToCreate() {
		SelectedNodeData selectedNode = view.getData();

		if (selectedNode.getId() == null) {
			Window.alert("Сначала выберите родительский узел");
			return null;
		}
		if (hasEmptyRequeredFields(selectedNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}
		Short port = checkPort(selectedNode.getPort());
		if (port == null) {
			return null;
		}
		Node newNode = new Node();
		newNode.setParentId(selectedNode.getId());
		newNode.setName(selectedNode.getName());
		newNode.setIp(selectedNode.getIp());
		newNode.setPort(port);
		return newNode;
	}

	public Node getNodeToUpdate() {

		SelectedNodeData selectedNode = view.getData();
		if (hasEmptyRequeredFields(selectedNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}
		Short port = checkPort(selectedNode.getPort());
		if (port == null) {
			return null;
		}
		if (selectedNode.getId() == null) {
			Window.alert("Id является обязательным полем");
			return null;
		}
		return getSelectedNode(selectedNode, port);
	}

	public Long getIdToDelete() {
		SelectedNodeData selectedNode = view.getData();
		if (selectedNode.getId() == null) {
			Window.alert("Сначала выберите узел");
			return null;
		}

		return selectedNode.getId();
	}

	public void clean() {
		view.cleanSelected();
	}

	public void loadNode(Node node) {
		view.showNode(node);

	}

	private boolean hasEmptyRequeredFields(SelectedNodeData node) {
		return (node.getName().isEmpty() || node.getIp().isEmpty() || node.getPort().isEmpty());

	}

	private Node getSelectedNode(SelectedNodeData nodedata, Short port) {
		Node node = new Node(nodedata.getId(), nodedata.getParentId(), nodedata.getName(), nodedata.getIp(), port);
		return node;
	}

	private Short checkPort(String stringPort) {
		Short port;
		try {
			port = Short.valueOf(stringPort);
		} catch (NumberFormatException e) {
			Window.alert("Некорректный порт");
			return null;
		}
		return port;
	}
}
