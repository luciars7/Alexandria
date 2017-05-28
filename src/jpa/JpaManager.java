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

	/*
	 * public static List<Object> readPersonalizedString(String selection,
	 * String table, String attribute, String value) { Query q1 =
	 * em.createNativeQuery("SELECT ? FROM ? WHERE ? = ?", Procedure.class);
	 * q1.setParameter(1, "%" + selection + "%"); q1.setParameter(2, "%" + table
	 * + "%"); q1.setParameter(3, "%" + attribute + "%"); q1.setParameter(4, "%"
	 * + value + "%"); List<Object> objects = (List<Object>) q1.getResultList();
	 * return objects; }
	 */

	// READS N-N
	// ------------------------------------------------------------------------------------------------
	public static List<Paper> readPaperFromPaperAuthor(Integer author_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM paper as p INNER JOIN paperauthor as pa ON p.ID = pa.paper INNER JOIN author as a ON a.ID = pa.author WHERE a.ID = ?",
				Paper.class);
		q1.setParameter(1, author_id);
		List<Paper> ids = (List<Paper>) q1.getResultList();
		return ids;
	}

	public static List<Author> readAuthorFromPaperAuthor(Integer paper_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM author as a INNER JOIN paperauthor as pa ON a.ID = pa.author INNER JOIN paper as p ON p.ID = pa.paper WHERE p.ID = ?",
				Author.class);
		q1.setParameter(1, paper_id);
		List<Author> ids = (List<Author>) q1.getResultList();
		return ids;
	}

	public static List<Image> readImageFromImageDisease(Integer disease_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM image as i INNER JOIN imagedisease as id ON i.ID = id.image INNER JOIN disease as d ON d.ID = id.disease WHERE d.ID = ?",
				Image.class);
		q1.setParameter(1, disease_id);
		List<Image> ids = (List<Image>) q1.getResultList();
		return ids;
	}

	public static List<Disease> readDiseaseFromImageDisease(Integer image_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM disease as d INNER JOIN imagedisease as id ON d.ID = id.disease INNER JOIN image as i ON i.ID = id.image WHERE i.ID = ?",
				Disease.class);
		q1.setParameter(1, image_id);
		List<Disease> ids = (List<Disease>) q1.getResultList();
		return ids;
	}

	public static List<Disease> readDiseaseFromPaperDisease(Integer paper_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM disease as d INNER JOIN paperdisease as pd ON d.ID = pd.disease INNER JOIN paper as p ON p.ID = pd.paper WHERE p.ID = ?",
				Disease.class);
		q1.setParameter(1, paper_id);
		List<Disease> ids = (List<Disease>) q1.getResultList();
		return ids;
	}

	public static List<Paper> readPaperFromPaperDisease(Integer disease_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM paper as p INNER JOIN paperdisease as pd ON p.ID = pd.paper INNER JOIN disease as d ON d.ID = pd.disease WHERE d.ID = ?",
				Paper.class);
		q1.setParameter(1, disease_id);
		List<Paper> ids = (List<Paper>) q1.getResultList();
		return ids;
	}

	public static List<Disease> readDiseaseFromProcedureDisease(Integer procedure_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM disease as d INNER JOIN proceduredisease as pd ON d.ID = pd.disease INNER JOIN procedure as p ON p.ID = pd.procedure WHERE p.ID = ?",
				Disease.class);
		q1.setParameter(1, procedure_id);
		List<Disease> ids = (List<Disease>) q1.getResultList();
		return ids;
	}

	public static List<Procedure> readProcedureFromProcedureDisease(Integer disease_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM procedure as p INNER JOIN proceduredisease as pd ON p.ID = pd.procedure INNER JOIN disease as d ON d.ID = pd.disease WHERE d.ID = ?",
				Procedure.class);
		q1.setParameter(1, disease_id);
		List<Procedure> ids = (List<Procedure>) q1.getResultList();
		return ids;
	}

	public static List<Disease> readDiseaseFromSymptomDisease(Integer symptom_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM disease as d INNER JOIN symptomdisease as sd ON d.ID = sd.disease INNER JOIN symptom as s ON s.ID = sd.symptom WHERE s.ID = ?",
				Disease.class);
		q1.setParameter(1, symptom_id);
		List<Disease> ids = (List<Disease>) q1.getResultList();
		return ids;
	}

	public static List<Symptom> readSymptomFromSymptomDisease(Integer disease_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM symptom as s INNER JOIN symptomdisease as sd ON s.ID = sd.symptom INNER JOIN disease as d ON d.ID = sd.disease WHERE d.ID = ?",
				Symptom.class);
		q1.setParameter(1, disease_id);
		List<Symptom> ids = (List<Symptom>) q1.getResultList();
		return ids;
	}
	
	// READS 1-N
	// ------------------------------------------------------------------------------------------------
	public static List<Disease> readDiseaseRelatedToBodyPart(Integer bodyPart_id) {
		Query q1 = em.createNativeQuery(
				"SELECT * FROM disease WHERE bodypart = ?",
				Disease.class);
		q1.setParameter(1, bodyPart_id);
		List<Disease> ids = (List<Disease>) q1.getResultList();
		return ids;
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