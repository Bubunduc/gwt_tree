package com.example.tree_rumyancev.server.mybatis;

import java.util.List;
import com.example.tree_rumyancev.shared.model.Node;

public interface TreeMapper {
    List<Node> findAll();
    Node findById(Long id);
    void create(Node node);
    void update(Node node);
    void delete(Long id);
}