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

class VisitTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private Visit vis;

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
		vis = em.find(Visit.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vis= null;
	}

	@Test
	void test_Visit_mapping() {
		assertNotNull(vis);
		assertEquals("motorbike trip", vis.getNote());
	}
		
		@Test
		void test_visit_to_location_mapping() {
		assertEquals(1, vis.getLocation().getId());
	}

}
