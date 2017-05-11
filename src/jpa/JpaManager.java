package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pojos.*;

public class JpaManager {

	private static EntityManager em;

	public JpaManager() {
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	// INSERTIONS INTO N-N TABLES
	// ------------------------------------------------------------------------------------------------
	public static void insertsymtomdisease(int disease_id, int symptom_id) {
		em.getTransaction().begin();
		Disease disease = readDisease(disease_id);
		Symptom symptom = readSymptom(symptom_id);
		disease.setSymptom(symptom);
		symptom.setDisease(disease);
		em.getTransaction().commit();
	}

	public static void insertpaperauthor(int paper_id, int author_id) {
		em.getTransaction().begin();
		Paper paper = readPaper(paper_id);
		Author author = readAuthor(author_id);
		paper.setAuthor(author);
		author.setPaper(paper);
		em.getTransaction().commit();
	}

	public static void insertpaperdisease(int paper_id, int disease_id) {
		em.getTransaction().begin();
		Paper paper = readPaper(paper_id);
		Disease disease = readDisease(disease_id);
		paper.setDisease(disease);
		disease.setPaper(paper);
		em.getTransaction().commit();
	}

	public static void insertimagedisease(int image_id, int disease_id) {
		em.getTransaction().begin();
		Disease disease = readDisease(disease_id);
		Image image = readImage(image_id);
		disease.setImage(image);
		image.setDisease(disease);
		em.getTransaction().commit();
	}

	public static void insertproceduredisease(int procedure_id, int disease_id) {
		em.getTransaction().begin();
		Disease disease = readDisease(disease_id);
		Procedure procedure = readProcedure(procedure_id);
		disease.setProcedure(procedure);
		procedure.setDisease(disease);
		em.getTransaction().commit();
	}

	// READS
	// ------------------------------------------------------------------------------------------------
	public static Disease readDisease(int disease_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM disease WHERE id = ?", Disease.class);
		q1.setParameter(1, disease_id);
		Disease disease = (Disease) q1.getSingleResult();
		return disease;
	}

	public static Symptom readSymptom(int symptom_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM symptom WHERE id = ?", Symptom.class);
		q1.setParameter(1, symptom_id);
		Symptom symptom = (Symptom) q1.getSingleResult();
		return symptom;
	}

	public static Paper readPaper(int paper_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM paper WHERE id = ?", Paper.class);
		q1.setParameter(1, paper_id);
		Paper paper = (Paper) q1.getSingleResult();
		return paper;
	}

	public static Author readAuthor(int author_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM author WHERE id = ?", Author.class);
		q1.setParameter(1, author_id);
		Author author = (Author) q1.getSingleResult();
		return author;
	}

	public static Image readImage(int image_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM image WHERE id = ?", Image.class);
		q1.setParameter(1, image_id);
		Image image = (Image) q1.getSingleResult();
		return image;
	}

	public static Procedure readProcedure(int procedure_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM procedure WHERE id = ?", Procedure.class);
		q1.setParameter(1, procedure_id);
		Procedure procedure = (Procedure) q1.getSingleResult();
		return procedure;
	}

}
