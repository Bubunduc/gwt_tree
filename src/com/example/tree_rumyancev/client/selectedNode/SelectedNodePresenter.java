package com.example.tree_rumyancev.client.selectedNode;

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
		Node newNode = view.getCurrentNode();

		if (hasEmptyRequeredFields(newNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}

		newNode.setParentId(null);
		return newNode;

	}

	public Node getNodeToCreate() {
		Node selectedNode = view.getCurrentNode();

		if (selectedNode.getId() == null) {
			Window.alert("Сначала выберите родительский узел");
			return null;
		}
		if (hasEmptyRequeredFields(selectedNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}

		Node newNode = new Node();
		newNode.setParentId(selectedNode.getId());
		newNode.setName(selectedNode.getName());
		newNode.setIp(selectedNode.getIp());
		newNode.setPort(selectedNode.getPort());
		return newNode;
	}

	public Node getNodeToUpdate() {
		Node newNode = view.getCurrentNode();
		if (hasEmptyRequeredFields(newNode)) {
			Window.alert("Текстовые поля являются обязательными для заполнения");
			return null;
		}
		if (newNode.getId() == null) {
			Window.alert("Id является обязательным полем");
			return null;
		}
		return newNode;
	}

	public Long getIdToDelete() {
		Node deletedNode = view.getCurrentNode();

		if (deletedNode.getId() == null) {
			Window.alert("Сначала выберите узел");
			return null;
		}

		if (deletedNode.getParentId() == null) {
			Window.alert("корень удалить нельзя");
			return null;
		}
		return deletedNode.getId();
	}

	public void clean() {
		view.cleanSelected();
	}

	public void loadNode(Node node) {
		view.showNode(node);

	}

	private boolean hasEmptyRequeredFields(Node node) {
		return (node.getName().isEmpty() || node.getIp().isEmpty() || node.getPort() == null);

	}

}
