package ergasia.TriviaFX;



import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;


public class MainSceneCreator implements EventHandler<MouseEvent> {
	
	
	FlowPane rootFlowPane;	//Αρχικοποιούμε μια μεταβλητη τύπου FlowPane
	
	Button startBtn, settingsBtn;	//Αρχικοποιούμε τις μεταβλητές των κουμπίων

	public MainSceneCreator() {
		 rootFlowPane = new FlowPane(); //Δημιουργούμε ένα container FlowPane
		 
	       startBtn = new Button("Start");	//Δημιουργούμε το κουμπί και οριζω ονομα και μέγεθος 
	       startBtn.setMinSize(120, 30);
	       
	       settingsBtn = new Button("Choose Settings");	//Δημιουργούμε το κουμπί και οριζω ονομα και μέγεθος 
	       settingsBtn.setMinSize(120, 30);
	       
	       rootFlowPane.getChildren().add(startBtn);	//Προσθέτουμε πάνω στο FlowPane τα κουμπιά
	       rootFlowPane.getChildren().add(settingsBtn);
	       
	       rootFlowPane.setAlignment(Pos.CENTER);	//Ορίζουμε που θέλουμε να εμφανίζονται τα αντικειμενα  στο FlowPane
	       rootFlowPane.setHgap(60);	//Ορίζουμε την απόσταση που θα έχουν τα κουμπιά μεταξύ τους
	       
	       startBtn.setOnMouseClicked(this); //Συνδέουμε τον Event Handler με τα κουμπιά
	       settingsBtn.setOnMouseClicked(this);
	}
	
	Scene createScene(){
		return new Scene (rootFlowPane, 650, 300);	//Φτιάχνουμε το Scene με τις διαστάσεις της επιλογής μας και την επιστρέφουμε
	}

	@Override
	public void handle(MouseEvent event) {		//Σε περίπτωση που γίνει κάποιο κλικ
		if (event.getSource() == startBtn) {	//Ο χρήστης έκανε κλικ στο κουμπί Start
			GameSceneCreator gameSceneCreator = new GameSceneCreator();	//Καλούμε τον constructor της GameSceneCreator για να αρχικοποιήσει τα πεδια
            App.primaryStage.setScene(gameSceneCreator.createScene());	//Φτιάχνεταί το GameScene και αλλάζει το Scene σε GameScene
		}
		else if (event.getSource() == settingsBtn) {	//Ο χρήστης έκανε κλικ στο κουμπί Settings
			SettingsSceneCreator settingsSceneCreator = new SettingsSceneCreator();//Καλούμε τον constructor της SettingsSceneCreator για να αρχικοποιήσει τα πεδια
            App.primaryStage.setScene(settingsSceneCreator.createScene()); //Φτιάχνεταί το SettingsScene και αλλάζει το Scene σε SettingsScene
			
		}
		
	}
}	
