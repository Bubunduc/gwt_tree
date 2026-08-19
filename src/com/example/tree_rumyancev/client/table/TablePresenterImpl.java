package com.example.tree_rumyancev.client.table;

import java.util.List;

import com.example.tree_rumyancev.client.dto.TableViewData;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.HasWidgets;

public class TablePresenterImpl implements TablePresenter {

	private final TableDisplay view;

	public TablePresenterImpl(TableDisplay view) {

		this.view = view;

	}

	public void go(HasWidgets container) {

		container.add(view.asWidget());
	}

	@Override
	public void loadData(List<Node> data) {
		view.fillTable(TableViewData.toViewDataList(data));

	}

	@Override
	public void colorRow(Long id) {
		view.colorSelectedRow(id);
	}

}
