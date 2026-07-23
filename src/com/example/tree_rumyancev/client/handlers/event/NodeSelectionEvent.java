package com.example.tree_rumyancev.client.handlers.event;
import com.example.tree_rumyancev.client.handlers.selectedNode.NodeSelectionEventHandler;
import com.example.tree_rumyancev.shared.model.Node;
import com.google.gwt.event.shared.GwtEvent;

public class NodeSelectionEvent
        extends GwtEvent<NodeSelectionEventHandler>
{
    public static final Type<NodeSelectionEventHandler> TYPE =
            new Type<NodeSelectionEventHandler>();

    private final Node node;

    public NodeSelectionEvent(Node node)
    {
        this.node = node;
    }

    public Node getNode()
    {
        return node;
    }

    @Override
    public Type<NodeSelectionEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(NodeSelectionEventHandler handler)
    {
        handler.onNodeSelected(this);
    }
}