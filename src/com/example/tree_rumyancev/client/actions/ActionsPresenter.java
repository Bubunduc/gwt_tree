package com.example.tree_rumyancev.client.actions;

import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.CreateRootRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.DeleteNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.event.actionRequest.UpdateNodeRequestedEvent;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateNodeClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.CreateRootClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.DeleteClickHandler;
import com.example.tree_rumyancev.client.handlers.selectedNode.click.UpdateNodeClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class ActionsPresenter {

	private ActionsDisplay view;

	private EventBus eventBus;

	public ActionsPresenter(ActionsDisplay view, EventBus eventBus) {

		this.view = view;
		this.eventBus = eventBus;
		bind();

	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

	void bind() {

		view.setCreateNodeHandler(new CreateNodeClickHandler() {

			@Override
			public void onClick() {
				eventBus.fireEvent(new CreateNodeRequestedEvent());
			}
		});

		view.setCreateRootHandler(new CreateRootClickHandler() {

			@Override
			public void onClick() {
				eventBus.fireEvent(new CreateRootRequestedEvent());
			}
		});
		view.setUpdateNodeHandler(new UpdateNodeClickHandler() {

			@Override
			public void onClick() {
				eventBus.fireEvent(new UpdateNodeRequestedEvent());
			}
		});

		view.setDeleteButtonHandler(new DeleteClickHandler() {

			@Override
			public void onClick() {
				eventBus.fireEvent(new DeleteNodeRequestedEvent());
			}
		});

	}

}
