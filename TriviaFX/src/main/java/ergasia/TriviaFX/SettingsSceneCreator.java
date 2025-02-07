package ergasia.TriviaFX;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class SettingsSceneCreator implements EventHandler<MouseEvent> {
	private GridPane rootGridPane;					//Αρχικοποιούμε μία μεταβλητή τύπου GridPane
	private Button saveBtn, backBtn;				//Αρχικοποιούμε τις μεταβλητές των κουμπιών 
	private ChoiceBox<String> categoryChoiceBox;	//Αρχικοποιούμε μία μεταβλητή τύπου ChoiceBox για επιλογή κατηγορίας ερώτησης
	private ChoiceBox<String> difficultyChoiceBox;	//Αρχικοποιούμε μία μεταβλητή τύπου ChoiceBox για επιλογή δυσκολίας ερώτησης
	private ChoiceBox<String> typeChoiceBox;		//Αρχικοποιούμε μία μεταβλητή τύπου ChoiceBox για επιλογή τύπου ερώτησης
	private TextField questionsField;				//Αρχικοποιούμε μία μεταβλητή τύπου TextField για επιλογή αριθμού ερωτήσεων
	private Text categoryExplain;					//Αρχικοποιούμε μία μεταβλητή τύπου Text που εξηγεί τις κατηγορίες
	
	private  int amount;							//Αρχικοποιούμε μία μεταβλητή για αποθήκευση του αριθμού των ερωτήσεων
	private  String category;						//Αρχικοποιούμε μία μεταβλητή για αποθήκευση της κατηγορίας
	private  String difficulty;						//Αρχικοποιούμε μία μεταβλητή για αποθήκευση της δυσκολίας
	private  String type;							//Αρχικοποιούμε μία μεταβλητή για του τύπου
	
	public SettingsSceneCreator() {					
        rootGridPane = new GridPane();				//Δημιουργούμε ένα container τύπου GridPane
        rootGridPane.setVgap(10);					//Ορίζουμε το κενό μεταξύ των γραμμών του GridPane
        rootGridPane.setHgap(10);					//Ορίζουμε το κενό μεταξύ των στηλών του GridPane
        rootGridPane.setAlignment(Pos.CENTER);		//Ευθυγραμίζουμε τα στοιχεία στο κέντρο
        
        questionsField = new TextField();			//Δημιουγούμε την μεταβλητή για να εισάγει ο χρήστης τον αριθμό των ερωτήσεων που θέλει να παίξει
        questionsField.setPromptText("Enter number of questions");		//Προτροπή για εισαγωγή αριθμού ερωτήσεων
        
        categoryChoiceBox = new ChoiceBox<>();		//Δημιουργούμε την μεταβλητή για να επιλέξει ο χρήστης κατηγορία και ορίζουμε τις επιλογές
        categoryChoiceBox.getItems().addAll("9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32");
        
        difficultyChoiceBox = new ChoiceBox<>();	//Δημιουργούμε την μεταβλητή για να επιλέξει ο χρήστης δυσκολία και ορίζουμε τις επιλογές
        difficultyChoiceBox.getItems().addAll("easy","medium","hard");
        
        typeChoiceBox = new ChoiceBox<>();			//Δημιουργούμε την μεταβλητή για να επιλέξει ο χρήστης τύπο και ορίζουμε τις επιλογές
        typeChoiceBox.getItems().addAll("multiple", "boolean");
        
        
        
        saveBtn = new Button("Save Settings");		//Δημιουργούμε τα κουμπία Save Settings και Back
        backBtn = new Button("Back");
        
        //Δημιουργούμε την μεταβλητή κειμένου για τις επεξηγήσεις των κατηγοριών
        categoryExplain = new Text(" 9: General Knowledge , 10: Entertainment: Books,  11: Entertainment: Film,  12: Entertainment: Music, "
        		+ "\n 13: Entertainment: Musical Theatres,  14 Entertainment: Television,  15: Entertainment: Video Games, "
        		+ "\n 16: Entertainment: Board Games,  17: Science Nature,  18: Science: Computers,  19: Science: Mathematics, "
        		+ "\n 20: Mythology, 21: Sports, 22: Geography, 23: History, 24: Politics, 25: Art, 26: Celebrities, 27: Animals, "
        		+ "\n 28: Vehicles, 29: Entertainment: Comics, 30: Science: Gadgets, 31: Entertainment: Japanese Anime Manga, "
        		+ "\n 32: Entertainment: Cartoon Animation ");
        
        
        
        rootGridPane.add(new Label("Number of Questions: "), 0, 0);		//Προσθέτουμε τα στοιχεία στο GridPane στις θέσεις που επιθυμούμε
        rootGridPane.add(questionsField, 1, 0);
        rootGridPane.add(new Label("Category: "), 0, 1);
        rootGridPane.add(categoryChoiceBox, 1, 1);
        rootGridPane.add(new Label("Difficulty: "), 0, 2);
        rootGridPane.add(difficultyChoiceBox, 1, 2);
        rootGridPane.add(new Label("Type: "), 0, 3);
        rootGridPane.add(typeChoiceBox, 1, 3);
        rootGridPane.add(saveBtn, 0, 4);
        rootGridPane.add(backBtn, 1, 4);
        rootGridPane.add(categoryExplain, 0, 5, 2, 1);
        
        saveBtn.setOnMouseClicked(this);		//Συνδέoυμε τον Event Handler με τα κουμπιά
        backBtn.setOnMouseClicked(this);
	}
        public Scene createScene() {			//Φτιάχνουμε το Scene με τις διαστάσεις της επιλογής μας και την επιστρέφουμε
            return new Scene(rootGridPane, 650, 300);
            
            
	
	
	

}
		@Override
		public void handle(MouseEvent event) {		//Σε περίπτωση που γίνει κάποιο κλικ
			if (event.getSource()== saveBtn) {		//Αν ο χρήστης κάνει κλικ στο κουμπί Save settings
				
				//Λήψη δεδομένων από τα πεδία εισόδου
				int amount = Integer.parseInt(questionsField.getText());	//Λήψη αριθμού ερωτήσεων
				String category = categoryChoiceBox.getValue();				//Λήψη κατηγορίας
				String difficulty = difficultyChoiceBox.getValue();			//Ληψη δυσκολιας
				String type = typeChoiceBox.getValue();						//λήψη τύπου
				
				//Δημιουργούμε ένα αντικείμενο GameSettings με τις επιλεγμένες ρυθμίσεις
				GameSettings gameSettings = new GameSettings(amount, category, difficulty, type);
				
				//Δημιουργούμε GameScene με τις επιλεγμένες ρυθμίσεις
				GameSceneCreatorWithParameters gameSceneCreatorWithParameters = new GameSceneCreatorWithParameters( gameSettings);
				
				
				//Φτιάχνεταί το GameScene και αλλάζει το Scene σε GameScene
	            App.primaryStage.setScene(gameSceneCreatorWithParameters.createScene());
			}
			
			else if (event.getSource()== backBtn) {			//Αν ο χρήστης κάνει κλικ στο κουμπί Back 
				App.primaryStage.setScene(App.mainScene);	//Αλλάζει το Scene σε MainScene
			
			}
			
		}
}



