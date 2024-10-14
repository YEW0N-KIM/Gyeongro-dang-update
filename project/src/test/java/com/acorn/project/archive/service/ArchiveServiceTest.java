package com.acorn.project.archive.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.archive.domain.Archive;
import com.acorn.project.archive.repository.ArchiveDAOI;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class ArchiveServiceTest {

	@Autowired
	ArchiveDAOI dao;
	
	@Autowired
	ArchiveService service;
	private Archive arch = new Archive("u0001","b0001");
	
	@Test
	public void testInsert() {
		int result = service.insert(arch);
		assertEquals(1, result);
	}

	@Test
	public void testDelete() {
		int result = service.delete(arch);
		assertEquals(1, result);
	}

	@Test
	public void testCheckArch() {
		Archive check = dao.selectOne(arch);
		Archive result = service.checkArch(arch);
		assertEquals(check,result);
	}

}
