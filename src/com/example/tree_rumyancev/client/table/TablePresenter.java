package com.example.tree_rumyancev.client.table;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.user.client.ui.HasWidgets;

public interface TablePresenter {

	void loadData(List<Node> data);
	
	void loadData();
	
	void colorRow(Long id);

	void go(HasWidgets container);

}
