package com.example.tree_rumyancev.client.selectedNode;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedNodePresenter {

	private SelectedNodeDisplay view;

	public SelectedNodePresenter(SelectedNodeDisplay view) {

		this.view = view;
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

	public Node getRootToCreate() {
		Node newNode = view.getCurrentNode();

		if (newNode.getId() == null || newNode.getName().isEmpty() || newNode.getIp().isEmpty()
				|| newNode.getPort() == null) {
			Window.alert("Использование пустых полей не допускается");
			return null;
		}

		newNode.setParentId(null);
		return newNode;

	}

	public Node getNodeToCreate() {
		Node selectedNode = view.getCurrentNode();

		if ((selectedNode.getId() == null && selectedNode.getParentId() == null) || selectedNode.getName().isEmpty()
				|| selectedNode.getIp().isEmpty() || selectedNode.getPort() == null) {
			Window.alert("Использование пустых полей не допускается");
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
		if (newNode.getId() == null || newNode.getName().isEmpty() || newNode.getIp().isEmpty()
				|| newNode.getPort() == null) {
			Window.alert("Использование пустых полей не допускается");
			return null;
		}
		return newNode;
	}

	public Long getIdToDelete() {
		Node deletedNode = view.getCurrentNode();
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

}
