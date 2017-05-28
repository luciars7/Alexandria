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

	
	public void marshalToXML(String aut, String fileName) {
		try {
			List <Author> autores = dbm.selectAuthor(aut);
			Author author = autores.get(0);
			JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File (fileName);
			marshaller.marshal(author, file);			
			marshaller.marshal(author, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void unmarshalToJava(String aut, String fileName) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(fileName);
			Author autor =(Author) unmarshaller.unmarshal(XMLfile);
			// Printout
			System.out.println(autor);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
