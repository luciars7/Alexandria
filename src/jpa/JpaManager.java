package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.*;
import sample.db.pojos.*;

public class JpaManager {
	
	private static	EntityManager em;
	
	public JpaManager(){
    em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
	}
	
	public static void insertsymtomdisease(int disease_id, int symptom_id){
		em.getTransaction().begin();
		Disease disease=readDisease(disease_id);
		Symptom symptom=readSymptom(symptom_id)
		disease.setSymptom(symptom);
		symptom.setDisease(disease);
		em.getTransaction().commit();
	}
	
	public static Disease readDisease (int disease_id){
		Query q1 = em.createNativeQuery("SELECT * FROM disease WHERE id = ?", Disease.class);
		q1.setParameter(1, disease_id);
		Disease disease = (Disease) q1.getSingleResult(); 
		return disease;
	}
	public static Symptom readSymptom (int symptom_id){
		Query q1 = em.createNativeQuery("SELECT * FROM symptom WHERE id = ?", Symptom.class);
		q1.setParameter(1, symptom_id);
		Symptom symptom = (Symptom) q1.getSingleResult(); 
		return symptom;
	}
	
}
