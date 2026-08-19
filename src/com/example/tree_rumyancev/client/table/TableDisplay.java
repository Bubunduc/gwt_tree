package com.example.tree_rumyancev.client.table;

import java.util.List;

import com.example.tree_rumyancev.client.dto.TableViewData;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface TableDisplay extends IsWidget {

	void fillTable(List<TableViewData> nodes);

	void setRefreshButtonHandler(final RefreshButtonClickHandler handler);

	void setSelectedRowHandler(final SelectedRowHandler handler);

	void colorSelectedRow(Long id);

	Widget asWidget();

}
