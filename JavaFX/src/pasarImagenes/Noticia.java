package pasarImagenes;

import java.io.Serializable;

public class Noticia implements Serializable {
	private String title;
	private String descri;

	public Noticia() {
	}

	public Noticia(String t, String d) {
		title = t;
		descri = d;
	}

	public String getTitle() {
		return title;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String d) {
		this.descri = d;
	}

	public void setTitle(String t) {
		this.title = t;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Noticia)) {
			return false;
		}
		Noticia a = (Noticia) o;
		if (title.equals(a.getTitle()) && (descri.equals(a.getDescri()))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String s1 = "\nNoticia:\nTítulo=" + this.title + "\nDescripción=" + this.descri + "\n";
		return s1;
	}

}
