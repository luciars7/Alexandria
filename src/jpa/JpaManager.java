package jpa;

import java.util.List;

import javax.persistence.*;

import pojos.*;

public class JpaManager {

	private static EntityManager em;

	public JpaManager() {
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

	public static Symptom readSymptom(int symptom_id) {

		Query q1 = em.createNativeQuery("SELECT * FROM symptom WHERE id = ?", Symptom.class);

		q1.setParameter(1, symptom_id);

		Symptom symptom = (Symptom) q1.getSingleResult();

		return symptom;

	}

	public static Device readDevice(int device_id) {

		Query q1 = em.createNativeQuery("SELECT * FROM device WHERE id = ?", Device.class);

		q1.setParameter(1, device_id);

		Device device = (Device) q1.getSingleResult();

		return device;

	}

	public static Paper readPaper(int paper_id) {

		Query q1 = em.createNativeQuery("SELECT * FROM paper WHERE id = ?", Paper.class);

		q1.setParameter(1, paper_id);

		Paper paper = (Paper) q1.getSingleResult();

		return paper;

	}

	public static BodyPart readBodyPart(int bodyPart_id) {

		Query q1 = em.createNativeQuery("SELECT * FROM bodypart WHERE id = ?", BodyPart.class);

		q1.setParameter(1, bodyPart_id);

		BodyPart bodyPart = (BodyPart) q1.getSingleResult();

		return bodyPart;

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

	// DELETES

	// ------------------------------------------------------------------------------------------------

	public void deleteSymptomJPA(int symptom_id) {

		em.getTransaction().begin();

		Symptom symptom = readSymptom(symptom_id);

		em.remove(symptom);

		em.getTransaction().commit();

	}

	// UPDATES

	// ------------------------------------------------------------------------------------------------

	public void updateProcedureJPA(Integer procedure_id, String newDescription) {
		// Begin transaction
		em.getTransaction().begin();
		System.out.println("1");
		// Make changes
		Procedure procedure = readProcedure(procedure_id);
		procedure.setDescription(newDescription);
		System.out.println("2");
		// End transaction
		em.getTransaction().commit();
		System.out.println("3");
	}
}