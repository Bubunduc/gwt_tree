package com.example.tree_rumyancev.client.table;

import java.util.List;

import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface TableDisplay extends IsWidget {

	public void fillTable(List<TableViewData> nodes);

	public void setRefreshButtonHandler(final RefreshButtonClickHandler handler);

	void setSelectedRowHandler(final SelectedRowHandler handler);

	void showSelectedRow(TableViewData currentRow);

	Widget asWidget();

}
