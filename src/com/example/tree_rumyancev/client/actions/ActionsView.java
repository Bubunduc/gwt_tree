package com.example.tree_rumyancev.client.actions;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ActionsView implements ActionsDisplay {

	private FlowPanel buttonPanel;

	private DeleteClickHandler deleteHandler;
	private CreateRootClickHandler createRootHandler;
	private CreateNodeClickHandler createNodeHandler;
	private UpdateNodeClickHandler updateNodeHandler;

	public ActionsView() {
		initButtonsPanel();
	}

	
	private void initButtonsPanel() {
		buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("buttonPanel");
		Button addNodeButton = new Button("Add Node");
		addNodeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createNodeHandler.onClick();

			}
		});
		addNodeButton.addStyleName("addNodeButton");

		Button addRootButton = new Button("Add Root");
		addRootButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createRootHandler.onClick();

			}
		});
		addRootButton.addStyleName("addRootButton");

		Button editButton = new Button("Edit");
		editButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateNodeHandler.onClick();

			}
		});
		editButton.addStyleName("editButton");

		Button deleteButton = new Button("Delete");
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteHandler.onClick();

			}
		});
		deleteButton.addStyleName("deleteButton");

		buttonPanel.add(addNodeButton);
		buttonPanel.add(addRootButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);

	}

	@Override
	public void setDeleteButtonHandler(DeleteClickHandler handler) {
		deleteHandler = handler;

	}

	@Override
	public void setCreateRootHandler(CreateRootClickHandler createRootHandler) {

		this.createRootHandler = createRootHandler;

	}

	@Override
	public void setCreateNodeHandler(CreateNodeClickHandler createNodeHandler) {

		this.createNodeHandler = createNodeHandler;

	}

	@Override
	public void setUpdateNodeHandler(UpdateNodeClickHandler updateNodeHandler) {

		this.updateNodeHandler = updateNodeHandler;

	}

	@Override
	public Widget asWidget() {

		return buttonPanel.asWidget();
	}

}
