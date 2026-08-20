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
	private FlexTable allDataTable;
	private Button refreshButton;

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
		allDataTable = new FlexTable();

		tablePanel.add(allDataTable);
		allDataTable.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				HTMLTable.Cell cell = allDataTable.getCellForEvent(event);

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

		allDataTable.setStyleName("nodeTable");

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

		allDataTable.setWidget(HEADER_ROW, 0, new Label("name"));
		allDataTable.getCellFormatter().addStyleName(HEADER_ROW, 0, "tableHeader");
		allDataTable.setWidget(HEADER_ROW, 1, new Label("ip"));
		allDataTable.getCellFormatter().addStyleName(HEADER_ROW, 1, "tableHeader");
		allDataTable.setWidget(HEADER_ROW, 2, new Label("port"));
		allDataTable.getCellFormatter().addStyleName(HEADER_ROW, 2, "tableHeader");

	}

	@Override
	public void colorSelectedRow(Long id) {

		for (Map.Entry<Integer, Long> entry : rowToNodeId.entrySet()) {
			
			int rowIndex = entry.getKey();
			Long nodeId = entry.getValue();
			
			
			int cellCount = allDataTable.getCellCount(rowIndex);

			for (int column = 0; column < cellCount; column++) {

				if (id != null && id.equals(nodeId)) {

					allDataTable.getCellFormatter().addStyleName(rowIndex, column, "selectedRow");

				} else {

					allDataTable.getCellFormatter().removeStyleName(rowIndex, column, "selectedRow");
				}
			}
		}
	}

	@Override
	public void fillTable(List<TableViewData> nodes) {
		allDataTable.removeAllRows();
		rowToNodeId.clear();

		initHeaders();
		int counter = 1;
		for (TableViewData node : nodes) {

			allDataTable.setWidget(counter, 0, new Label(node.getName()));
			allDataTable.setWidget(counter, 1, new Label(node.getIp()));
			allDataTable.setWidget(counter, 2, new Label(node.getPort().toString()));

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
