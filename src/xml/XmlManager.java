package xml;

import jdbc.*;
import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jdbc.*;
import pojos.*;

public class XmlManager {
	DBManager dbm;

	public XmlManager(DBManager _dbm) {
		dbm = _dbm;
	}

	public void marshalToXML(String aut, String fileName) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File(fileName);
			List <Author> autores = dbm.selectAuthor(aut);
			Author author = autores.get(0);
			marshaller.marshal(author, file);
			// Printout
			marshaller.marshal(author, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
