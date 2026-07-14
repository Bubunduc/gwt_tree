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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
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
	public void onModuleLoad() {
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
		showNode.addClickHandler(new MyClickHandler(node.getId(),panel));
		System.out.print(node.getId());
		return panel;
	}
	public class MyClickHandler implements ClickHandler {
		private final Long id;
		FlowPanel panel;
		public MyClickHandler(Long id,FlowPanel panel) {
			this.id = id;
			this.panel = panel;
		}
		   @Override
		   public void onClick(ClickEvent event) {
			   ToggleButton toggleButton = (ToggleButton) event.getSource();
			   if (toggleButton.isDown() == true) 
			   {
				   toggleButton.setText("−");
				   treeService.getChildrenList(id,new AsyncCallback<List<Node>>() {
						
						@Override
						public void onSuccess(List<Node> result) {
							showNodes(result);


						}
						
						@Override
						public void onFailure(Throwable caught) {
							
							
						}
					});
			   }
			   else
			   {
				   toggleButton.setText("+");
			   }
		   }
		   public void showNodes(List<Node> result) 
		   {
			   for (Node node : result) 
			   {
				   panel.add(createNode(node));
			   }
		   }
		   public void hideNodes() 
		   {
			   
		   }
		   
		}
	
	
}
