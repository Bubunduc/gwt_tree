package com.example.tree_rumyancev.client;

import java.util.List;

import com.example.tree_rumyancev.shared.model.Node;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;

public class TreeWidget extends Composite{
	/*private final TreeServiceAsync treeService = GWT.create(TreeService.class);
	
	final FlowPanel mainPanel = new FlowPanel();
	private SelectedNodeWidget selectedNodePanel;
	public TreeWidget(SelectedNodeWidget selectedNodePanel)
	{
		this.selectedNodePanel = selectedNodePanel;
		initTree();
		initWidget(mainPanel);
	}
	public TreeWidget()
	{
		initTree();
		initWidget(mainPanel);
	}
	public void initTree() 
	{
		 // панель для веток	
		
		treeService.getRootNode(new AsyncCallback<Node>() {
			private FlowPanel rootPanel = new FlowPanel();
			@Override
			public void onSuccess(Node result) {
				Node rootNode = result;
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
		//showNode.addClickHandler(new TreeClickHandler(node.getId(),panel));
		nodeName.addClickHandler(new NodeClickHandler(node,selectedNodePanel));
		
		return panel;
	}
	
	
	
	public Command getOnAddHandler ()
	{
		return new Command() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public class TreeClickHandler implements ClickHandler {
		boolean isLoaded = false;	
		   @Override
		   public void onClick(ClickEvent event) {
			   if (parentNode.isButtonDowned()) 
			   {
				   if (isLoaded == false)
				   {
				   parentNode.downButton();
				   treeService.getChildrenList(id,new AsyncCallback<List<Long>>() {
						
						@Override
						public void onSuccess(List<Long> result) {
							isLoaded = true;
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							
							
						}
					});
				   }
				   else 
				   {
					   parentNode.downButton();
				   }
				   
			   }
			   else
			   {
				  parentNode.upButton();
			   }
		   }	   
		}
	
	public class NodeClickHandler implements ClickHandler
	{
		private Long node;
		private SelectedNodeWidget selectedNodePanel;
		public NodeClickHandler(Long node, SelectedNodeWidget selectedNodePanel) 
		{
		this.node = node;	
		
		this.selectedNodePanel = selectedNodePanel ;
		}
		
		@Override 
		public void onClick(ClickEvent event)
		{
			selectedNodePanel.showNode(node);
		}
	}*/
	
}
