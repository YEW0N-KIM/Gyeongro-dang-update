package com.acorn.project.archive.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acorn.project.archive.domain.Archive;

@RunWith(MockitoJUnitRunner.class)
public class ArchiveDAOTest {

	@Mock
	private SqlSession session;
	
	@InjectMocks
	private ArchiveDAO dao;
	
	private String namespace = "com.acorn.archiveMapper.";
	private Archive archive = new Archive();
	
	@Test
	public void testInsert() {
		when(session.insert(namespace+"insert",archive)).thenReturn(1);
		int result = dao.insert(archive);
		assertEquals(1, result);
	}

	@Test
	public void testDelete() {
		when(session.delete(namespace+"delete", archive)).thenReturn(1);
		int result = dao.delete(archive);
		assertEquals(1,result);
	}

	@Test
	public void testSelectOne() {
		when(session.selectOne(namespace+"selectOne", archive)).thenReturn(archive);
		Archive result = dao.selectOne(archive);
		assertEquals(archive,result);
	}

}
