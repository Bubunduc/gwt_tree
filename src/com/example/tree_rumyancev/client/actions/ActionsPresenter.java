package com.example.tree_rumyancev.client.actions;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class ActionsPresenter {

	private ActionsDisplay view;

	private EventBus eventBus;

	public ActionsPresenter(ActionsDisplay view, EventBus eventBus) {

		this.view = view;
		this.eventBus = eventBus;

	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

}
