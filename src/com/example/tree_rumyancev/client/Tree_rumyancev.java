package com.example.tree_rumyancev.client;

import com.example.tree_rumyancev.shared.FieldVerifier;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tree_rumyancev implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	/**
	 * This is the entry point method.
	 */
	private Node rootNode;
	List<Node> allNodes;
	FlexTable AllDatatable = new FlexTable();
	FlexTable currentNodeTable = new FlexTable();
	public void onModuleLoad() {
		initTree();
		initAllNodesTable();
		initCurrentNodeTable();
		
	}					
	public void initTree() 
	{
		final FlowPanel mainPanel = new FlowPanel(); // панель для веток	
		RootPanel.get("NodesContainer").add(mainPanel);
		treeService.getRootNode(new AsyncCallback<Node>() {
			
			@Override
			public void onSuccess(Node result) {
				rootNode = result;
				FlowPanel rootPanel = createNode(rootNode);
				mainPanel.add(rootPanel);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public void initCurrentNodeTable() {
		currentNodeTable.setText(0, 0, "Id");
		currentNodeTable.setWidget(0, 1, new TextBox());

		currentNodeTable.setText(1, 0, "parentId");
		currentNodeTable.setWidget(1, 1, new TextBox());

		currentNodeTable.setText(2, 0, "name");
		currentNodeTable.setWidget(2, 1, new TextBox());

		currentNodeTable.setText(3, 0, "ip");
		currentNodeTable.setWidget(3, 1, new TextBox());

		currentNodeTable.setText(4, 0, "port");
		currentNodeTable.setWidget(4, 1, new TextBox());
	    RootPanel.get("CurrentNodeContainer").add(currentNodeTable);
	}
	public void initAllNodesTable() 
	{
			treeService.getAllData(new AsyncCallback<List<Node>>() {
			
			@Override
			public void onSuccess(List<Node> result) {
				allNodes = result;
				
				Button refreshButton = new Button("обновить");
				AllDatatable.setWidget(0, 0, refreshButton);
				int counter = 2;
				AllDatatable.setText(1, 0, "Id");
				AllDatatable.setText(1, 1, "parentId");
				AllDatatable.setText(1, 2, "name");
				AllDatatable.setText(1, 3, "ip");
				AllDatatable.setText(1, 4, "port");
				for (Node node : allNodes)
				{
					AllDatatable.setText(counter, 0, node.getId().toString());
					if (node.getParentId()!= null) 
					{
						AllDatatable.setText(counter, 1, node.getParentId().toString());
					}
					else
					{
						AllDatatable.setText(counter, 1, "корень");
					}
					AllDatatable.setText(counter, 2, node.getName());
					AllDatatable.setText(counter, 3, node.getIp());
					AllDatatable.setText(counter, 4, node.getPort().toString());
					counter++;
				}
				RootPanel.get("AllNodesTable").add(AllDatatable);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	public FlowPanel createNode(Node node) 
	{
		String id  = node.getId().toString();
		FlowPanel panel = new FlowPanel();
		
		ToggleButton showNode = new ToggleButton("+");
		Label nodeName = new Label("Panel" + id);
		showNode.getElement().setId("NodeButton " + id);
		nodeName.getElement().setId("Node Label " + id);
		panel.getElement().setId("Panel " + id);
		panel.add(showNode);
		panel.add(nodeName);
		showNode.addClickHandler(new TreeClickHandler(node.getId(),panel));
		nodeName.addClickHandler(new NodeClickHandler(node));
		
		return panel;
	}
	public class TreeClickHandler implements ClickHandler {
		private final Long id;
		FlowPanel panel;
		FlowPanel childPanel = new FlowPanel();
		boolean isLoaded = false;
		public TreeClickHandler(Long id,FlowPanel panel) {
			this.id = id;
			this.panel = panel;
	
			
		}
		   @Override
		   public void onClick(ClickEvent event) {
			   ToggleButton toggleButton = (ToggleButton) event.getSource();
			   if (toggleButton.isDown() == true) 
			   {
				   if (isLoaded == false)
				   {
				   toggleButton.setText("−");
				   treeService.getChildrenList(id,new AsyncCallback<List<Node>>() {
						
						@Override
						public void onSuccess(List<Node> result) {
							isLoaded = true;
							
							showNodes(result);


						}
						
						@Override
						public void onFailure(Throwable caught) {
							
							
						}
					});
				   }
				   else 
				   {
					   hideNodes();
				   }
				   
			   }
			   else
			   {
				   toggleButton.setText("+");
				   childPanel.setVisible(false);
			   }
		   }
		   public void showNodes(List<Node> result) 
		   {
			   childPanel.getElement().setId("Node_child" + id.toString());
			   for (Node node : result) 
			   {
				   childPanel.add(createNode(node));
			   }
			   panel.add(childPanel);
		   }
		   public void hideNodes() 
		   {
			   childPanel.setVisible(true);
		   }
		   
		}
	
	public class NodeClickHandler implements ClickHandler
	{
		Node node;
		public NodeClickHandler(Node node) 
		{
		this.node = node;	
		}
		@Override 
		public void onClick(ClickEvent event)
		{
		
			TextBox id =  new TextBox();
			
			TextBox parentId =  new TextBox();
			
			TextBox name =  new TextBox();
			
			TextBox ip =  new TextBox();
			
			TextBox port =  new TextBox();
			id.setText(node.getId().toString());
			if (node.getParentId()!=null)
			{
				parentId.setText(node.getParentId().toString());
			}
			else
			{
				parentId.setText("Корень дерева");
			}
			name.setText(node.getName());
			ip.setText(node.getIp());
			port.setText(node.getPort().toString());
			
			currentNodeTable.setWidget(0, 1,id );
			currentNodeTable.setWidget(1, 1,parentId);
			currentNodeTable.setWidget(2, 1, name);
			currentNodeTable.setWidget(3, 1, ip);
			currentNodeTable.setWidget(4, 1, port);
		}
	}
}
