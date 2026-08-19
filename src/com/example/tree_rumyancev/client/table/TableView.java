package com.example.tree_rumyancev.client.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tree_rumyancev.client.dto.TableViewData;
import com.example.tree_rumyancev.client.handlers.table.RefreshButtonClickHandler;
import com.example.tree_rumyancev.client.handlers.table.SelectedRowHandler;
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
	private FlowPanel tablePanel;
	private FlexTable allDatatable;
	private Button refreshButton;

	private Integer selectedRowId;
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
		panel.addStyleName("rootTablePanel");

		tablePanel = new FlowPanel();
		tablePanel.setStyleName("tablePanel");
		allDatatable = new FlexTable();

		tablePanel.add(allDatatable);
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

		allDatatable.setStyleName("nodeTable");

		refreshButton = new Button("обновить");
		refreshButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				buttonClickHandler.onClick();

			}
		});
		refreshButton.addStyleName("updateTableButton");

		panel.add(refreshButton);
		panel.add(tablePanel);

	}

	private void initHeaders() {

		allDatatable.setWidget(HEADER_ROW, 0, new Label("name"));
		allDatatable.getCellFormatter().addStyleName(HEADER_ROW, 0, "tableHeader");
		allDatatable.setWidget(HEADER_ROW, 1, new Label("ip"));
		allDatatable.getCellFormatter().addStyleName(HEADER_ROW, 1, "tableHeader");
		allDatatable.setWidget(HEADER_ROW, 2, new Label("port"));
		allDatatable.getCellFormatter().addStyleName(HEADER_ROW, 2, "tableHeader");

	}

	private void addStyleSelectedRow(int rowIndex) {
		int rows = allDatatable.getCellCount(rowIndex);
		if (selectedRowId != null) {
			for (int i = 0; i < rows; i++) {
				allDatatable.getCellFormatter().removeStyleName(selectedRowId, i, "selectedRow");
			}
		}

		selectedRowId = rowIndex;
		for (int i = 0; i < rows; i++) {
			allDatatable.getCellFormatter().addStyleName(selectedRowId, i, "selectedRow");
		}
	}

	@Override
	public void colorSelectedRow(Long id) {

		if (id == null) {
			return;
		}

		int rowIndex = -1;
		for (Map.Entry<Integer, Long> entry : rowToNodeId.entrySet()) {
			if (id.equals(entry.getValue())) {
				rowIndex = entry.getKey();
				break;
			}
		}
		if (rowIndex == -1) {
			return;
		}

		addStyleSelectedRow(rowIndex);
	}

	@Override
	public void fillTable(List<TableViewData> nodes) {
		allDatatable.removeAllRows();
		rowToNodeId.clear();
		selectedRowId = null;

		initHeaders();
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
	public Widget asWidget() {

		return panel;
	}

}
