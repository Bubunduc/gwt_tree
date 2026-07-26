package com.example.tree_rumyancev.shared.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.example.tree_rumyancev.shared.model.Node;

public class NodeData
{
    private Node node;

    private boolean childrenLoaded;

    public NodeData(Node node)
    {
        this.node = node;
        this.childrenLoaded = false;
    }

    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public boolean isChildrenLoaded()
    {
        return childrenLoaded;
    }

    public void setChildrenLoaded(boolean childrenLoaded)
    {
        this.childrenLoaded = childrenLoaded;
    }
    
}