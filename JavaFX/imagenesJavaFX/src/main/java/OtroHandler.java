
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OtroHandler extends DefaultHandler {

	// List to hold Noticia object
	private List<Noticia> lnoticias = null;
	private Noticia noticia = null;
	private StringBuilder data = null;

	// getter method for noticias list
	public List<Noticia> getLnoticias() {
		return lnoticias;
	}

	boolean uitem = false;
	boolean utitle = false;
	boolean udescri = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("item")) {
			// create a new Noticia and put it in Map
			noticia = new Noticia();
			// initialize list
			if (lnoticias == null) {
				lnoticias = new ArrayList<Noticia>();
			}
			uitem = true;
			// System.out.println("- primer item-");
		}
		if (qName.equalsIgnoreCase("title") && uitem) {
			utitle = true;
			data = new StringBuilder();
			// System.out.println("- title-");

		}
		if (qName.equalsIgnoreCase("description") && uitem) {
			udescri = true;
			data = new StringBuilder();
			// System.out.println("- descri-");
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (utitle && uitem) {
			noticia.setTitle(data.toString());
			utitle = false;
		}
		if (udescri && uitem) {
			noticia.setDescri(data.toString());
			udescri = false;
		}
		if (qName.equalsIgnoreCase("item")) {
			// add Noticia object to list
			lnoticias.add(noticia);
			uitem = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (utitle || udescri)
			data.append(new String(ch, start, length));
	}
}
