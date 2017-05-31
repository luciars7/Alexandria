package xml;

import jdbc.*;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pojos.*;

public class XmlManager {
	DBManager dbm;

	public XmlManager(DBManager _dbm) {
		dbm = _dbm;
	}

	// MARSHALLERS ----------------------------------------------------------------------------------------
	public void marshalToXMLAuthor(String aut, String fileName) {
		try {
			List <Author> autores = dbm.selectAuthor(aut);
			Author author = autores.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(author, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(author, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLBodyPart(String bp, String fileName) {
		try {
			List <BodyPart> bodyparts = dbm.selectBodyPart(bp);
			BodyPart bodypart = bodyparts.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(BodyPart.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(bodypart, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(bodypart, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLDevice(String d, String fileName) {
		try {
			List <Device> devices = dbm.selectDevice(d);
			Device device = devices.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Device.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(device, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(device, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLDisease(String d, String fileName) {
		try {
			List <Disease> diseases = dbm.selectDisease(d);
			Disease disease = diseases.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Disease.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(disease, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(disease, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLImage(String i, String fileName) {
		try {
			List <Image> images = dbm.selectImage(i);
			Image image = images.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Image.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(image, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(image, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLPaper(String p, String fileName) {
		try {
			List <Paper> papers = dbm.selectPaper(p);
			Paper paper = papers.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Paper.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(paper, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(paper, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLProcedure(String p, String fileName) {
		try {
			List <Procedure> procedures = dbm.selectProcedure(p);
			Procedure procedure = procedures.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Procedure.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(procedure, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(procedure, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void marshalToXMLSymptom(String s, String fileName) {
		try {
			List <Symptom> symptoms = dbm.selectSymptom(s);
			Symptom symptom = symptoms.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Symptom.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(symptom, file);
			//Printout
			System.out.println("\nMarshalling complete.\nThe new XML file looks like this:\n");
			marshaller.marshal(symptom, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	// UNMARSHALLERS --------------------------------------------------------------------------------------
	public void unmarshalToJava(String fileName) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(fileName);
			Author autor =(Author) unmarshaller.unmarshal(XMLfile);
			// Printout
			System.out.println("\nUnmarshalling complete.\nThe recovered author looks like this:\n");
			System.out.println(autor);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
