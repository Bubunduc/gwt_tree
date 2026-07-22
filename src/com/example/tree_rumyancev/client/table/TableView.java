package com.example.tree_rumyancev.client.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
import com.example.tree_rumyancev.shared.dto.TableViewData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TableView implements TableDisplay {

	private static final Integer HEADER_ROW = 0;

	private FlowPanel panel;
	private FlexTable allDatatable;
	private Button refreshButton;
	private Label selectedRowLabel;
	private Map<Integer, Long> rowToNodeId;

	private RefreshButtonClickHandler buttonClickHandler;
	private SelectedRowHandler selectedRowHandler;

	public TableView() {
		this.rowToNodeId = new HashMap<>();
		initWidget();
		initHeaders();

	}

	private void initWidget() {

		panel = new FlowPanel();

		allDatatable = new FlexTable();
		allDatatable.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				HTMLTable.Cell cell = allDatatable.getCellForEvent(event);

				if (cell == null) {
					return;
				}

				int rowIndex = cell.getRowIndex();

				Long nodeId = rowToNodeId.get(rowIndex);

				if (nodeId == null) {
					return;
				}

				selectedRowHandler.onSelected(nodeId);
			}
		});

		refreshButton = new Button("обновить");
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				buttonClickHandler.onClick();

			}
		});

		selectedRowLabel = new Label();

		panel.add(selectedRowLabel);
		panel.add(refreshButton);
		panel.add(allDatatable);

	}

	private void initHeaders() {

		allDatatable.setText(HEADER_ROW, 0, "name");
		allDatatable.setText(HEADER_ROW, 1, "ip");
		allDatatable.setText(HEADER_ROW, 2, "port");

	}

	@Override
	public void fillTable(List<TableViewData> nodes) {
		allDatatable.clear();
		int counter = 1;
		for (TableViewData node : nodes) {

			allDatatable.setWidget(counter, 0, new Label(node.getName()));
			allDatatable.setWidget(counter, 1, new Label(node.getIp()));
			allDatatable.setWidget(counter, 2, new Label(node.getPort().toString()));

			rowToNodeId.put(counter, node.getNodeId());

			counter++;
		}
	}

	@Override
	public void setRefreshButtonHandler(final RefreshButtonClickHandler handler) {
		this.buttonClickHandler = handler;
	}

	@Override
	public void setSelectedRowHandler(final SelectedRowHandler handler) {

		this.selectedRowHandler = handler;
	}

	@Override
	public void showSelectedRow(TableViewData currentRow) {
		selectedRowLabel.setText("Name : " + currentRow.getName()

				+ " Ip : " + currentRow.getIp()

				+ " Port: " + currentRow.getPort().toString());

	}

	@Override
	public Widget asWidget() {

		return panel;
	}

}
