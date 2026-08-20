package com.example.tree_rumyancev.client.actions;

import com.google.gwt.user.client.ui.HasWidgets;

public class ActionsPresenter {

	private final ActionsDisplay view;

	public ActionsPresenter(ActionsDisplay view) {

		this.view = view;
	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());

	}

}
