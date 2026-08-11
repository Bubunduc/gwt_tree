package com.example.tree_rumyancev.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.server.mybatis.TreeMapper;
import com.example.tree_rumyancev.shared.model.Node;

public class TreeDaoMyBatisImpl implements TreeDao {

    private final SqlSessionFactory sqlSessionFactory;

    public TreeDaoMyBatisImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Node> getAllData() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TreeMapper mapper = session.getMapper(TreeMapper.class);
            return mapper.getAllData();
        }
    }

    @Override
    public Node findById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            TreeMapper mapper = session.getMapper(TreeMapper.class);
            return mapper.findById(id);
        }
    }

    @Override
    public Node create(Node node) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) { // true = autocommit
            TreeMapper mapper = session.getMapper(TreeMapper.class);
            mapper.create(node); // MyBatis сам подставит сгенерированный id в объект node
            return node;
        }
    }

    @Override
    public void update(Node node) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TreeMapper mapper = session.getMapper(TreeMapper.class);
            mapper.update(node);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            TreeMapper mapper = session.getMapper(TreeMapper.class);
            mapper.delete(id);
        }
    }
}
