package com.skilldistillery.atlas.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisitCommentTest {
	
	public static EntityManagerFactory emf;
	private EntityManager em;
	private VisitComment vc;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("AtlasJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		vc = em.find(VisitComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vc = null;
	}

	@Test
	void test_VisitComment_mapping() {
		assertNotNull(vc);
		assertEquals(1, vc.getVisit().getId());
	}

}
