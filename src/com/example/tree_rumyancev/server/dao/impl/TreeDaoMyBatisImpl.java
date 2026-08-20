package com.example.tree_rumyancev.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.tree_rumyancev.server.dao.TreeDao;
import com.example.tree_rumyancev.server.mybatis.MyBatisUtil;
import com.example.tree_rumyancev.server.mybatis.TreeMapper;
import com.example.tree_rumyancev.shared.model.Node;

public class TreeDaoMyBatisImpl implements TreeDao {

	@Override
	public List<Node> findAll() {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			return mapper.findAll();
		}
	}

	@Override
	public Node findById(Long id) {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			return mapper.findById(id);
		}
	}

	@Override
	public Node create(Node node) {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			mapper.create(node);
			session.commit();
			return node;
		}
	}

	@Override
	public void update(Node node) {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			if(findById(node.getId())== null) {
				return;
			}
			mapper.update(node);
			session.commit();
		}
	}

	@Override
	public void delete(Long id) {
		try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			TreeMapper mapper = session.getMapper(TreeMapper.class);
			if(findById(id)== null) {
				return;
			}
			mapper.delete(id);
			session.commit();
		}
	}
}
