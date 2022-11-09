package com.skilldistillery.atlas.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhotoTest {

	public static EntityManagerFactory emf;
	private EntityManager em;
	private Photo photo;

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
		photo = em.find(Photo.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		photo= null;
	}

	@Test
	void test_Visit_mapping() {
		assertNotNull(photo);
		assertEquals("picture of motorbike", photo.getDescription());
	}
		
		@Test
		void test_visit_to_location_mapping() {
		assertEquals(1, photo.getVisit().getId());
	}


}
