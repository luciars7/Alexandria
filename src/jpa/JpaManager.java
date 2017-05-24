package jpa;

import java.util.List;
import javax.persistence.*;
import pojos.*;

public class JpaManager {

	private static EntityManager em;

	public JpaManager() {
		em = null;
	}

	public void connect() {
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	public void disconnect() {
		em.close();
	}

	// INSERTIONS INTO N-N TABLES
	// ------------------------------------------------------------------------------------------------

	public void insertsymtomdisease(int disease_id, int symptom_id) {
		if (disease_id != 0 && symptom_id != 0) {
			em.getTransaction().begin();
			Disease disease = readDisease(disease_id);
			Symptom symptom = readSymptom(symptom_id);
			disease.addSymptom(symptom);
			symptom.addDisease(disease);
			em.getTransaction().commit();
		}
	}

	public void insertpaperauthor(int paper_id, int author_id) {
		if (paper_id != 0 && author_id != 0) {
			em.getTransaction().begin();
			Paper paper = readPaper(paper_id);
			Author author = readAuthor(author_id);
			paper.addAuthor(author);
			author.addPaper(paper);
			em.getTransaction().commit();
		}
	}

	public void insertpaperdisease(int paper_id, int disease_id) {
		if (disease_id != 0 && paper_id != 0) {
			em.getTransaction().begin();
			Paper paper = readPaper(paper_id);
			Disease disease = readDisease(disease_id);
			paper.addDisease(disease);
			disease.addPaper(paper);
			em.getTransaction().commit();
		}
	}

	public void insertimagedisease(int image_id, int disease_id) {
		if (disease_id != 0 && image_id != 0) {
			em.getTransaction().begin();
			Disease disease = readDisease(disease_id);
			Image image = readImage(image_id);
			disease.addImage(image);
			image.addDisease(disease);
			em.getTransaction().commit();
		}
	}

	public void insertproceduredisease(int procedure_id, int disease_id) {
		if (disease_id != 0 && procedure_id != 0) {
			em.getTransaction().begin();
			Disease disease = readDisease(disease_id);
			Procedure procedure = readProcedure(procedure_id);
			disease.addProcedure(procedure);
			procedure.addDisease(disease);
			em.getTransaction().commit();
		}
	}

	// INSERTIONS INTO 1-N TABLES
	// ------------------------------------------------------------------------------------------------

	// READS
	// ------------------------------------------------------------------------------------------------
	public static Disease readDisease(int disease_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM disease WHERE id = ?", Disease.class);
		q1.setParameter(1, disease_id);
		Disease disease = (Disease) q1.getSingleResult();
		return disease;
	}
	
	public static Disease readDisease(String disease_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM disease WHERE name = ?", Disease.class);
		q1.setParameter(1, disease_name);
		Disease disease = (Disease) q1.getSingleResult();
		return disease;
	}

	public static Symptom readSymptom(int symptom_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM symptom WHERE id = ?", Symptom.class);
		q1.setParameter(1, symptom_id);
		Symptom symptom = (Symptom) q1.getSingleResult();
		return symptom;
	}
	
	public static Symptom readSymptom(String symptom_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM symptom WHERE name = ?", Symptom.class);
		q1.setParameter(1, symptom_name);
		Symptom symptom = (Symptom) q1.getSingleResult();
		return symptom;
	}

	public static Device readDevice(int device_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM device WHERE id = ?", Device.class);
		q1.setParameter(1, device_id);
		Device device = (Device) q1.getSingleResult();
		return device;
	}
	
	public static Device readDevice(String device_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM device WHERE name = ?", Device.class);
		q1.setParameter(1, device_name);
		Device device = (Device) q1.getSingleResult();
		return device;
	}
	
	public static Paper readPaper(int paper_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM paper WHERE id = ?", Paper.class);
		q1.setParameter(1, paper_id);
		Paper paper = (Paper) q1.getSingleResult();
		return paper;
	}
	
	public static Paper readPaper(String paper_title) {
		Query q1 = em.createNativeQuery("SELECT * FROM paper WHERE title = ?", Paper.class);
		q1.setParameter(1, paper_title);
		Paper paper = (Paper) q1.getSingleResult();
		return paper;
	}

	public static BodyPart readBodyPart(int bodyPart_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM bodypart WHERE id = ?", BodyPart.class);
		q1.setParameter(1, bodyPart_id);
		BodyPart bodyPart = (BodyPart) q1.getSingleResult();
		return bodyPart;
	}
	
	public static BodyPart readBodyPart(String bodyPart_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM bodypart WHERE name = ?", BodyPart.class);
		q1.setParameter(1, bodyPart_name);
		BodyPart bodyPart = (BodyPart) q1.getSingleResult();
		return bodyPart;
	}

	public static Author readAuthor(int author_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM author WHERE id = ?", Author.class);
		q1.setParameter(1, author_id);
		Author author = (Author) q1.getSingleResult();
		return author;
	}
	
	public static Author readAuthor(String author_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM author WHERE name = ?", Author.class);
		q1.setParameter(1, author_name);
		Author author = (Author) q1.getSingleResult();
		return author;
	}

	public static Image readImage(int image_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM image WHERE id = ?", Image.class);
		q1.setParameter(1, image_id);
		Image image = (Image) q1.getSingleResult();
		return image;
	}
	
	public static Image readImage(String image_description) {
		Query q1 = em.createNativeQuery("SELECT * FROM image WHERE description = ?", Image.class);
		q1.setParameter(1, image_description);
		Image image = (Image) q1.getSingleResult();
		return image;
	}

	public static Procedure readProcedure(int procedure_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM procedure WHERE id = ?", Procedure.class);
		q1.setParameter(1, procedure_id);
		Procedure procedure = (Procedure) q1.getSingleResult();
		return procedure;
	}
	
	public static Procedure readProcedure(String procedure_name) {
		Query q1 = em.createNativeQuery("SELECT * FROM procedure WHERE name = ?", Procedure.class);
		q1.setParameter(1, procedure_name);
		Procedure procedure = (Procedure) q1.getSingleResult();
		return procedure;
	}

	public static List<Paper> readPaperAuthor(int author_id) {
		Query q1 = em.createNativeQuery("SELECT * FROM paperauthor WHERE author = ?", Paper.class);
		q1.setParameter(1, author_id);
		List<Paper> papers = (List<Paper>) q1.getResultList();
		return papers;
	}

	// CREATES
	// ------------------------------------------------------------------------------------------------
	public void createSymptomJPA(Symptom symptom) {
		em.getTransaction().begin();
		em.persist(symptom);
		em.getTransaction().commit();
	}

	// DELETES
	// ------------------------------------------------------------------------------------------------

	public void deleteProcedureJPA(int procedure_id) {
		em.getTransaction().begin();
		Procedure procedure = readProcedure(procedure_id);
		em.remove(procedure);
		em.getTransaction().commit();
	}

	// UPDATES
	// ------------------------------------------------------------------------------------------------

	public void updateProcedureJPA(Integer procedure_id, String newDescription) {
		em.getTransaction().begin();
		Procedure procedure = readProcedure(procedure_id);
		procedure.setDescription(newDescription);
		em.getTransaction().commit();
	}
}