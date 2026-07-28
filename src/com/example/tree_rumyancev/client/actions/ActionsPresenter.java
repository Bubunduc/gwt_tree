package com.example.tree_rumyancev.client.actions;

import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.example.tree_rumyancev.client.service.TreeService;
import com.example.tree_rumyancev.client.service.TreeServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;

public class ActionsPresenter {

	private ActionsDisplay view;

	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	private EventBus eventBus;

	public ActionsPresenter(ActionsDisplay view, EventBus eventBus) {

		this.view = view;
		this.eventBus = eventBus;

	}

	void bind() {

		view.setCreateNodeHandler(new CreateNodeClickHandler() {

			@Override
			public void onClick() {

			}
		});

		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {

			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {
			}
		});

		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {

			}
		});

	}

}
