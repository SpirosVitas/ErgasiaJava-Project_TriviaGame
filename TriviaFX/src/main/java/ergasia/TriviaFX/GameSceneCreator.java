package ergasia.TriviaFX;


import javafx.util.Duration;
import java.util.Collections;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.TriviaInfo;
import service.TriviaAPIService;

public class GameSceneCreator implements EventHandler<MouseEvent> {
	private GridPane rootGridPane;	//Αρχικοποιούμε μια μεταβλητή τύπου GridPane
	private Text questiontext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποήσουμε για να εμφανίσουμε την ερώτηση
	private Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;	//Αρχικοποιούμε τις μεταβλητές των κουμπιών των απαντήσεων
	private Button mainMenuBtn,playAgainBtn;	//Αρχικοποιούμε τις μεταβλητές δύο ακόμα κουμπιών που θα εμφανιστουν μόλις ολοκληρωθεί το παιχνίδι
	private Text scoretext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμφανίζουμε το σκορ
	private Text feedbacktext;	//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να ένα μήνυμα αν η απαντηση ειναι σωστη ή λάθος
	private Text endtext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμφανίζουμε ένα μηνυμα στο τέλος του παιχνιδιού
	
	
	
	
	
	private List<TriviaInfo> triviaQuestions;	//Δημιουργούμε την λίστα τύπου TriviaInfo που θα κρατάει το Response που παίρνουμε όταν καλούμε το API
	private int currentQuestionIndex = 0;		//Αρχικοποιούμε μία μεταβλητή που κρατάει τον αριθμό της ερώτησης 
	private int score = 0;						//Αρχικοποιούμε μία μετβλητή που κρατάει το σκορ
	private boolean isBoolean = false;			//Αρχικοποιούμε μ'ια μεταβλητή τύπου boolean που θα ελέγχει αν η ερώτησει είναι τύπου boolean
	private String correctAnswer;				//Αρχικοποιούμε μία μεταβλητή που θα κρατάει την σωστή απάντηση
	private int correctIndex;					//Αρχικοποιούμε μιί μεταβλητή που θα κρατάει τον αριθμό των σωστών απαντήσεων
	
	
	
	public GameSceneCreator () {
		rootGridPane = new GridPane();				//Δημιουργούμε ένα container τύπου GridPane
		rootGridPane.setVgap(10);					//Ορίζουμε το κενό μεταξύ των γραμμών του GridPane
		rootGridPane.setHgap(10);					//Ορίζουμε το κενό μεταξύ των στηλών του GridPane
		rootGridPane.setAlignment(Pos.CENTER);		//Ευθυγραμίζουμε τα στοιχεία στο κέντρο;
		
		questiontext = new Text();					//Δημιουργούμε την μεταβλητή για την ερώτηση
		answer1Btn = new Button();					//Δημιουργούμε τα κουμπια για τις απαντήσεις
		answer1Btn.setMinSize(120, 30);				//Ορίζουμε το μέγεθός τους
		answer2Btn = new Button();
		answer2Btn.setMinSize(120, 30);
		answer3Btn = new Button();
		answer3Btn.setMinSize(120, 30);
		answer4Btn = new Button();
		answer4Btn.setMinSize(120, 30);
		playAgainBtn = new Button("Play Again");	//Δημιουργούμε το κουμπί για να ξαναπαίξει ο χρήστης και να γράφει Play Again
		playAgainBtn.setMinSize(120, 30);
		mainMenuBtn = new Button("Main Menu");		//Δημιουργούμε το κουμπί για να γυρίσει ο χρήστης στο Main Menu και να γράφει Main Menu
		mainMenuBtn.setMinSize(120, 30);
		
		rootGridPane.add(questiontext, 0, 0, 2, 1);	//Πρσθέτουμε τα στοιχεία στο GridPane στις θέσεις που επιθυμούμε
        rootGridPane.add(answer1Btn, 0, 1);
        rootGridPane.add(answer2Btn, 1, 1);
        rootGridPane.add(answer3Btn, 0, 2);
        rootGridPane.add(answer4Btn, 1, 2);
        rootGridPane.add(playAgainBtn, 1, 1);
        rootGridPane.add(mainMenuBtn, 1, 2);
        playAgainBtn.setVisible(false);				//Το κουμπί θα είναι αρχικά κρυφό
        mainMenuBtn.setVisible(false);				//Το κουμπί θα είναι αρχικά κρυφό
        scoretext = new Text("Score ");				//Δημιουργούμε την μεταβλητή του σκορ και να γράφει Score
        scoretext.setVisible(false);				//Το κουμπί θα είναι αρχικά κρυφό
        rootGridPane.add(scoretext, 0, 4, 4, 2 );
        feedbacktext = new Text();					//Δημιουργούμε την μεταβλητή feedbacktext
        rootGridPane.add(feedbacktext, 0, 6, 2, 1);
        endtext = new Text("Game is over");			//Δημιουργούμε την μεταβλήτη που θα εμφανίζει το μηνυμα Game is over στο τέλος του παιχνιδιού
        endtext.setVisible(false);					//Αρχικά θα είναι κρυφό
        rootGridPane.add(endtext, 0, 0, 2, 2);
        
        
        answer1Btn.setOnMouseClicked(this);			//Συνδέoυμε τον Event Handler με τα κουμπιά
        answer2Btn.setOnMouseClicked(this);
        answer3Btn.setOnMouseClicked(this);
        answer4Btn.setOnMouseClicked(this);
        playAgainBtn.setOnMouseClicked(this);
        mainMenuBtn.setOnMouseClicked(this);
        
        loadQuestions();		//Καλoύμε την μέθοδο που καλέι το API και επιστρέφει τα αποτελέσματα 
	}
	
	public Scene createScene() {
		return new Scene (rootGridPane,650,300);	//Φτιάχνουμε το Scene με τις διαστάσεις της επιλογής μας και την επιστρέφουμε
	}
	
	public void loadQuestions() {		//Φτιάχνουμε την μέθοδο loadQuestions
        TriviaAPIService triviaAPIService = new TriviaAPIService("https://opentdb.com");	//Δημιουργεία του url

        try {
             triviaQuestions = triviaAPIService.getQuestions(10); 		//Καλούμε την μέθοδο που δημιουργήσαμε στην TriviaAPIService με παράμετρο 10(ερωτήσεις)
             															//Και φορτώνουμε το response στη λίστα triviaQuestions
            if (triviaQuestions == null || triviaQuestions.isEmpty()) {	//Ελέγχουμε αν η λίστα είναι κενή
                throw new Exception("No questions loaded from API.");	//Δημιουργούμε το exception
            }
            
            showQuestion(); 	//Καλούμε την μέθοδο showQuestions
        } catch (Exception e) {						//Σε περίπτωση σφάλματος
			Alert a =new Alert(AlertType.ERROR);	//Δημιουργούμε παράθυρο σφάλματος
			a.setTitle("Error calling API");		//Τίτλος παραθύρου
			a.setContentText(e.getMessage());		
			a.show();								//Εμφανίζεται το παράθυρο
		};
             
   
        }
        public void showQuestion() {	//Φτιάχνουμε την μέθοδο showQuesions
        	if (triviaQuestions != null && !triviaQuestions.isEmpty() && currentQuestionIndex < triviaQuestions.size()) {	//Αν η λίστα δεν είναι κενή και υπάρχουν ακόμα ερωτήσεις στη λίστα
 
                TriviaInfo currentQuestion = triviaQuestions.get(currentQuestionIndex);	//Δίνουμε στην currentQuestion το μέρος του response που έχει στοιχεία TriviaInfo
                questiontext.setText(currentQuestion.getQuestion());					//Εμφανίζεται στο questiontext η ερωτηση με την χρήση του getQuestion

                
                isBoolean = currentQuestion.getType().equalsIgnoreCase("boolean");		//Γίνεται έλεγχος αν η ερώτηση είναι τύπου boolean με την χρήση του getType της TriviaInfo
                correctAnswer = currentQuestion.getCorectAnswer();						//Αποθηκεύουμε την σωστή απάντηση στην μεταβλητή με την χρήση του getCorectAnswer της TriviaInfo
                List<String> answers =  currentQuestion.getIncorectAnswers();			//Αποθηκεύουμε τις λάθος απαντήσεις σε μιά λίστα answers με την χρήση του getIncorectAnswers της TriviaInfo
                answers.add(correctAnswer);												//Προσθέτουμε την σωστή απάντηση στην λίστα answers
                Collections.shuffle(answers);											//Ανακατεύουμε τις απαντήσεις (για να μην είναι η σωστή απάντησει πάντα στο ίδιο κουμπί)
                
                if (isBoolean) {														//Αν η ερώτηση είναι τύπου boolean
                   answer1Btn.setText("True");											//Το κουμπί1 εμφανίζει True
                   answer2Btn.setText("False");											//Το κουμπί2 εμφανίζει False
                   answer3Btn.setVisible(false);  										//Αποκρύπτουμε τα κουμπιά 3 και 4
                   answer4Btn.setVisible(false);  
                } else {																//Αν η ερώτηση δεν είναι τύπου boolean
                    
                	answer1Btn.setText(answers.get(0));									//Τα κουμπιά εμφανίζουν τις 4 απαντήσεις της λίστας 
                	answer2Btn.setText(answers.get(1));
                	answer3Btn.setText(answers.get(2));
                	answer4Btn.setText(answers.get(3));

                	answer3Btn.setVisible(true);										//Εμφανίζουμε τα κουμπία 3 και 4
                    answer4Btn.setVisible(true);
                }
            }
                

	
                    
	}
		
	

		@Override
		public void handle(MouseEvent event) {						//Σε περίπτωση που γίνει κάποιο κλικ
			Button clickedButton = (Button) event.getSource();		//Λήψη του κουμπιού που πατήθηκε

            
            if (clickedButton.getText().equals(correctAnswer)) {	//Αν ο χρήστης κάνει κλικ στο κουμπί με την σωστή απάντηση
                score += 10; 										//Αυξάνεται το σκορ κατά 10
                feedbacktext.setText("Correct");					//Το feedbacktext εμφανίζει Correct
                feedbacktext.setFill(Color.GREEN);					//Το feedbacktext γίνεται πράσινο
                correctIndex ++ ;									//Αυξάνεται ο μετρητής των σωστών απαντήσεων
            }
            else if (event.getSource()== mainMenuBtn) {				//Αν ο χρήστης πατήσει το κουμπι Main Menu
            	
            	App.primaryStage.setScene(App.mainScene);			//Αλλάζει το Scene σε MainScene
            }
            else if(event.getSource() == playAgainBtn) {			//Αν ο χρήστης πατήσει το κουμπί Play Again
            	
            	score = 0;											//Μηδενίζεται το σκορ
            	GameSceneCreator gameSceneCreator = new GameSceneCreator();	//Δημιουργούμε νέο GameScene
                App.primaryStage.setScene(gameSceneCreator.createScene());	//Φτιάχνεταί το GameScene και αλλάζει το Scene σε GameScene
            }
            
            else {								 //Αν ο χρήστης πατήσει το κουμπί λάθος απάντησης
                score -= 5; 					 //Μειώνεται το σκορ κατα 5
                feedbacktext.setText("Wrong");	 //Το feedbactext εμφανίζει Wrong
                feedbacktext.setFill(Color.RED); //Το feedbacktext γίνεται κόκκινο
            }
			//Ενημερώνουμε το scoretext και εμφανίζει το σκορ και σε πόσες ερωτήσεις απάντησε σωστά 
		scoretext.setText("Score " + score + "    Correct Answers:  " + correctIndex +"/" + (currentQuestionIndex+1));
		
		PauseTransition pause = new PauseTransition(Duration.seconds(2));	//Παυση για 2 δευτερόλεπτα πριν σβηστεί το feedbacktext
        pause.setOnFinished(e -> feedbacktext.setText(""));  				//Καθαρίζουμε το feedbacktext μετά την παύση
        pause.play();														//Εκτελείται η παύση
        
		currentQuestionIndex++;									//Αυξάνουμε το μετρητή του response
        if (currentQuestionIndex < triviaQuestions.size()) {	//Αν ο μετρητής είναι μικρότερος από το μέγεθος της λίστας
            showQuestion();										//Εμφανίζει την επόμενη "ερώτηση"
        } else {												//Αν τελείωσαν οι ερωτήσεις
        	endtext.setVisible(true);							//Εμφάνιση του μηνύματος τέλος παιχνιδιού
        	scoretext.setVisible(true);							//Εμφάνιση του scoretext
        	answer1Btn.setVisible(false);						//Απόκρυψη των κουμπιών απάντησης
        	answer2Btn.setVisible(false);
        	answer3Btn.setVisible(false);
        	answer4Btn.setVisible(false);
        	questiontext.setVisible(false);						//Απόκρυψη του questiontext
        	playAgainBtn.setVisible(true);						//Εμφάνιση του κουμπιού Play Again
        	mainMenuBtn.setVisible(true);						//Εμφάνιση του κουμπιού Main Menu
        	
        	
        	
        }
		}
		
		
}


        	