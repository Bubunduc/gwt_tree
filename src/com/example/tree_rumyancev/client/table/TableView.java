package com.example.tree_rumyancev.client.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TableView implements TableDisplay {

	private FlexTable AllDatatable = new FlexTable();
	Button refreshButton = new Button("обновить");
	Label selectedRowLabel = new Label();
	Map<Integer, Long> rowToNodeId = new HashMap<>();

	@Override
	public void fillTable(List<TableViewData> nodes) {
		AllDatatable.clear();
		AllDatatable.setWidget(0, 0, selectedRowLabel);
		AllDatatable.setWidget(1, 0, refreshButton);
		int counter = 3;
		AllDatatable.setText(2, 0, "name");
		AllDatatable.setText(2, 1, "ip");
		AllDatatable.setText(2, 2, "port");

		for (TableViewData node : nodes) {

			Label nameLabel = new Label(node.getName());
			Label ipLabel = new Label(node.getIp());
			Label portLabel = new Label(node.getPort().toString());

			AllDatatable.setWidget(counter, 0, nameLabel);
			AllDatatable.setWidget(counter, 1, ipLabel);
			AllDatatable.setWidget(counter, 2, portLabel);

			rowToNodeId.put(counter, node.getNodeId());
			counter++;
		}
	}

	@Override
	public void setRefreshButtonHandler(final RefreshButtonClickHandler handler) {
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				handler.onClick();
				Window.alert("Обновлено");

			}
		});

	}

	@Override
	public void setSelectedRowHandler(final SelectedRowHandler handler) {
		int rows = AllDatatable.getRowCount();

		for (int i = 3; i < rows; i++) {

			final int rowIndex = i;
			AllDatatable.getWidget(i, 0).addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					final Long nodeId = rowToNodeId.get(rowIndex);
					handler.onSelected(nodeId);

				}
			}, ClickEvent.getType());

			AllDatatable.getWidget(i, 1).addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					final Long nodeId = rowToNodeId.get(rowIndex);
					handler.onSelected(nodeId);

				}
			}, ClickEvent.getType());

			AllDatatable.getWidget(i, 2).addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					final Long nodeId = rowToNodeId.get(rowIndex);
					handler.onSelected(nodeId);

				}
			}, ClickEvent.getType());

		}
	}

	@Override
	public void showSelectedRow(TableViewData currentRow) {
		selectedRowLabel.setText("Name : " + currentRow.getName()

				+ " Ip : " + currentRow.getIp()

				+ " Port: " + currentRow.getPort().toString());

	}

	@Override
	public Widget asWidget() {

		return AllDatatable;
	}

}
