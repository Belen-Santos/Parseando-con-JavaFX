package pasarImagenes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	private List<Noticia> noticias = new ArrayList<Noticia>();
	private int i = -1;
	// modifico a 6 el número de elementos del array, ya que meteré 6 imágenes
	private Image[] aImg = new Image[6];

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		// inserto padding para separar los elemntos del borde de la ventana
		root.setPadding(new Insets(10));
		ImageView selectedImage = new ImageView();
		// meto 6 imágenes, una por cada noticia
		Image imagen1 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\Perro.jpg"));
		Image imagen2 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\gato.jpeg"));
		Image imagen3 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\orangutan.jpg"));
		Image imagen4 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\tortuga.jpg"));
		Image imagen5 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\panda.jpg"));
		Image imagen6 = new Image(new FileInputStream(
				"C:\\Users\\te_l\\Desktop\\WORKSPACE\\javaFX\\src\\pasarImagenes\\imagenes\\otra.png"));
		// establezco un tamaño definido para todas las fotos
		selectedImage.setFitHeight(350);
		selectedImage.setFitWidth(750);
		aImg[0] = imagen1;
		aImg[1] = imagen2;
		aImg[2] = imagen3;
		aImg[3] = imagen4;
		aImg[4] = imagen5;
		aImg[5] = imagen6;
		Button boton = new Button();
		boton.setText("Siguiente");
		// modifico el texto a salir
		Text t = new Text(10, 20, "Noticias de la vanguardia acompañado de imágenes lindas");
		// reduzco la letra de los titulares para una visualización nítida y que cupiera
		// dentro del tamaño deventana que he considerado adecuado
		t.setFont(Font.font("Verdana", 12));
		t.setFill(Color.RED);
		boton.setOnAction(event -> {
			i++;
			if (i < 6) {
				// paso a mayúsculas los títulos
				String st = noticias.get(i).getTitle().toUpperCase();
				t.setText(st);
				System.out.println("Title " + i + ": " + st);
				if (i < aImg.length && aImg[i] != null) {
					selectedImage.setImage(aImg[i]);
					/*
					 * Comento esta parte de tu código ya que en mi programa, una vez que incluye la
					 * imágen, no me interesa que en la siguiente noticia lo elimine --------------
					 * if (!root.getChildren().contains(selectedImage)) {
					 * root.getChildren().add(selectedImage); } else {
					 * root.getChildren().remove(selectedImage); }
					 */
				}
			} else {
				System.out.println("R E C O M E N Z A M O S ");
				i = -1;
			}
		});
		// incluyo en el VBox el elemento imagen
		root.getChildren().addAll(t, boton, selectedImage);
		// establezco separación entre elementos para mejor visualización
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 450);
		primaryStage.setTitle("NOTICIAS LA VANGUARDIA");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Has cerrado la última ventana");
			}
		});
	}

	public void stop() {
		System.out.println("\nYa se que has cerrado la última ventana\ntambién pasas por aquí");
	}

	@Override
	public void init() {
		System.out.println("Iniciando ....");
		String urlText = "https://www.lavanguardia.com/mvc/feed/rss/home/";
		BufferedReader in = null;
		StringBuilder texto = new StringBuilder();
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			OtroHandler handler = new OtroHandler();
			URL url = new URL(urlText);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				texto.append(inputLine);
			}
			saxParser.parse(new InputSource(new StringReader(texto.toString())), handler);
			noticias = handler.getLnoticias();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}