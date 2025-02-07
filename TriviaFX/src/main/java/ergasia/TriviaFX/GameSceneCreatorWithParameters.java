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
import model.TriviaInfo;
import service.TriviaAPIService;

public class GameSceneCreatorWithParameters implements EventHandler<MouseEvent> {
	private GridPane rootGridPane;	//Αρχικοποιούμε μια μεταβλητή τύπου GridPane
	private Text questiontext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποήσουμε για να εμφανίσουμε την ερώτηση
	private Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;	//Αρχικοποιούμε τις μεταβλητές των κουμπιών των απαντήσεων
	private Button mainMenuBtn,playAgainBtn;	//Αρχικοποιούμε τις μεταβλητές δύο ακόμα κουμπιών που θα εμφανιστουν μόλις ολοκληρωθεί το παιχνίδι
	private Text scoretext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμφανίζουμε το σκορ
	private Text feedbacktext;	//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να ένα μήνυμα αν η απαντηση ειναι σωστη ή λάθος
	private Text endtext;		//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμφανίζουμε ένα μηνυμα στο τέλος του παιχνιδιού
	private Text highscoretext;	//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμφανίζουμε το highscore
	private Text correcttext;	//Αρχικοποιούμε την μεταβλητή τύπου Text που θα το χρησιμοποιήσουμε για να εμαφανίζουμε πόσες ήταν οι σωστές απαντήσεις του χρήστη
	
	
	
	
	
	private List<TriviaInfo> triviaQuestionsWithParameters;	//Δημιουργούμε την λίστα τύπου TriviaInfo που θα κρατάει το Response που παίρνουμε όταν καλούμε το API
	private int currentQuestionIndex = 0;					//Αρχικοποιούμε μία μεταβλητή που κρατάει τον αριθμό της ερώτησης
	private int score = 0;									//Αρχικοποιούμε μία μετβλητή που κρατάει το σκορ
	private boolean isBoolean = false;						//Αρχικοποιούμε μία μεταβλητή τύπου boolean που θα ελέγχει αν η ερώτησει είναι τύπου boolean
	private String correctAnswer;							//Αρχικοποιούμε μία μεταβλητή που θα κρατάει την σωστή απάντηση
	private GameSettings gameSettings;						//Αρχικοποιούμε την μεταβλητή με τις ρυθμίσεις του παιχνιδιού
	private  int highscore ;								//Αρχικοποιούμε μία μεταβλητή που θα κρατάει το highscore
	private static int lasthighscore;						//Αρχικοποιούμε μία static μεταβλητή που κραταέι το highscore για αυτές τις ρυθμίσεις
	private int correctIndex;								//Αρχικοποιούμε μία μεταβλητή που θα κρατάει τον αριθμό των σωστών απαντήσεων
	
	
	
	
	
	
	
	public GameSceneCreatorWithParameters (GameSettings gameSettings) {	//ο constructor της κλάσης
		this.gameSettings = gameSettings;			//Αποθηκεύουμε τις ρυθμίσεις του παιχνιδιού
		rootGridPane = new GridPane();				//Δημιουργούμε ένα container τύπου GridPane
		rootGridPane.setVgap(10);					//Ορίζουμε το κενό μεταξύ των γραμμών του GridPane
		rootGridPane.setHgap(10);					//Ορίζουμε το κενό μεταξύ των στηλών του GridPane
		rootGridPane.setAlignment(Pos.CENTER);		//Ευθυγραμίζουμε τα στοιχεία στο κέντρο
		
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
        rootGridPane.add(scoretext, 0, 4, 4, 2 );
        scoretext.setVisible(false);				//Το κουμπί θα είναι αρχικά κρυφό
        feedbacktext = new Text();					//Δημιουργούμε την μεταβλητή feedbacktext
        rootGridPane.add(feedbacktext, 0, 6, 2, 1);
        endtext = new Text("Game is over");			//Δημιουργούμε την μεταβλήτη που θα εμφανίζει το μηνυμα Game is over στο τέλος του παιχνιδιού
        endtext.setVisible(false);					//Το κουμπί θα είναι αρχικά κρυφό
        rootGridPane.add(endtext, 0, 0, 2, 2);
        highscoretext = new Text("New Highscore ");	//Δημιουργούμε την μεταβλήτη που θα εμφανίζει το μηνυμα New Highscore στο τέλος του παιχνιδιού
        rootGridPane.add(highscoretext,2,2 );
        highscoretext.setVisible(false);			//Το κουμπί θα είναι αρχικά κρυφό
        correcttext = new Text();					//Δημιουργούμε την μεταβλήτη που θα εμφανίζει πόσες σωστές απαντήσεις έχει ο χρήστης στο τέλος του παιχνιδιού
        correcttext.setVisible(false);				//Το κουμπί θα είναι αρχικά κρυφό
        rootGridPane.add(correcttext, 0, 0, 3, 3);
        
        
        
        answer1Btn.setOnMouseClicked(this);			//Συνδέoυμε τον Event Handler με τα κουμπιά
        answer2Btn.setOnMouseClicked(this);
        answer3Btn.setOnMouseClicked(this);
        answer4Btn.setOnMouseClicked(this);
        playAgainBtn.setOnMouseClicked(this);
        mainMenuBtn.setOnMouseClicked(this);
        
        loadQuestionsWithParameters();				//Καλoύμε την μέθοδο που καλέι το API(με τις παραμέτρους) και επιστρέφει τα αποτελέσματα
	}
	
	public Scene createScene() {					//Φτιάχνουμε το Scene με τις διαστάσεις της επιλογής μας και την επιστρέφουμε
		return new Scene (rootGridPane,650,300);
	}
	
	public void loadQuestionsWithParameters() {		//Φτιάχνουμε την μέθοδο loadQuestionsWithParameters
        TriviaAPIService triviaAPIService = new TriviaAPIService("https://opentdb.com");	//Δημιουργεία του url
        

        try {
        		//Χρησιμοποιούμε τους getters από την GameSettings για να κάνουμε λήψη των παραμέτρων από τις ρυθμίσεις του παιχνιδιού
        	int amount = gameSettings.getAmount();
        	String category = gameSettings.getCategory();
        	String difficulty = gameSettings.getDifficulty();
        	String type = gameSettings.getType();
        	
             triviaQuestionsWithParameters = triviaAPIService.getQuenstionsWithParameters(	//Καλούμε την μέθοδο που δημιουργήσαμε στην TriviaAPIService
            		 amount, category, difficulty, type );									//Και φορτώνουμε το response στη λίστα triviaQuestions
            
            
             
            if (triviaQuestionsWithParameters == null || triviaQuestionsWithParameters.isEmpty()) {	//Ελέγχουμε αν η λίστα είναι κενή
            
                throw new Exception("No questions loaded from API.");								//Δημιουργούμε το exception
            }
           
            
            showQuestionWithParameters();			//Καλούμε την μέθοδο showQuestionsWithParameters
        } catch (Exception e) {						//Σε περίπτωση σφάλματος
			Alert a =new Alert(AlertType.ERROR);	//Δημιουργούμε παράθυρο σφάλματος
			a.setTitle("Error calling API");		//Τίτλος παραθύρου
			a.setContentText(e.getMessage());		
			a.show();								//Εμφανίζει το μήνυμα
		};
             
   
        }
        public void showQuestionWithParameters() {	//Φτιάχνουμε την μέθοδο showQuesionsWithParameters
        	if (triviaQuestionsWithParameters != null && !triviaQuestionsWithParameters.isEmpty() && currentQuestionIndex < triviaQuestionsWithParameters.size()) {	//Αν η λίστα δεν είναι κενή και υπάρχουν ακόμα ερωτήσεις στη λίστα
 
                TriviaInfo currentQuestion = triviaQuestionsWithParameters.get(currentQuestionIndex);	//Δίνουμε στην currentQuestion το μέρος του response που έχει στοιχεία TriviaInfo
                questiontext.setText(currentQuestion.getQuestion());		//Εμφανίζεται στο questiontext η ερωτηση με την χρήση του getQuestion

                
                isBoolean = currentQuestion.getType().equalsIgnoreCase("boolean");	//Γίνεται έλεγχος αν η ερώτηση είναι τύπου boolean με την χρήση του getType της TriviaInfo
                correctAnswer = currentQuestion.getCorectAnswer();					//Αποθηκεύουμε την σωστή απάντηση στην μεταβλητή με την χρήση του getCorectAnswer της TriviaInfo
                List<String> answers =  currentQuestion.getIncorectAnswers();		//Αποθηκεύουμε τις λάθος απαντήσεις σε μιά λίστα answers με την χρήση του getIncorectAnswers της TriviaInfo
                answers.add(correctAnswer);											//Προσθέτουμε την σωστή απάντηση στην λίστα answers
                Collections.shuffle(answers);										//Ανακατεύουμε τις απαντήσεις (για να μην είναι η σωστή απάντησει πάντα στο ίδιο κουμπί)
                
                if (isBoolean) {													//Αν η ερώτηση είναι τύπου boolean
                   answer1Btn.setText("True");										//Το κουμπί1 εμφανίζει True
                   answer2Btn.setText("False");										//Το κουμπί2 εμφανίζει False
                   answer3Btn.setVisible(false);  									//Αποκρύπτουμε τα κουμπιά 3 και 4
                   answer4Btn.setVisible(false);  
                } else {															//Αν η ερώτηση δεν είναι τύπου boolean
                    
                	answer1Btn.setText(answers.get(0));								//Τα κουμπιά εμφανίζουν τις 4 απαντήσεις της λίστας
                	answer2Btn.setText(answers.get(1));
                	answer3Btn.setText(answers.get(2));
                	answer4Btn.setText(answers.get(3));

                	answer3Btn.setVisible(true);									//Εμφανίζουμε τα κουμπία 3 και 4
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
            	lasthighscore = 0;									//Μηδενίζεται το lasthighscore
            	highscore = 0;										//Μηδενίζεται το highscore
            	score = 0;											//Μηδενίζεται το score
            	App.primaryStage.setScene(App.mainScene);			//Αλλάζει το Scene σε MainScene
            }
            else if(event.getSource() == playAgainBtn) {			//Αν ο χρήστης πατήσει το κουμπί Play Again
            	
            	score = 0;											//Μηδενίζεται το σκορ
            	
            	GameSceneCreatorWithParameters gameSceneCreatorWithParameters = new GameSceneCreatorWithParameters(gameSettings);	//Δημιουργούμε νέο GameScene
                App.primaryStage.setScene(gameSceneCreatorWithParameters.createScene());											//Φτιάχνεταί το GameScene και αλλάζει το Scene σε GameScene
            }
            
            else {													//Αν ο χρήστης πατήσει το κουμπί λάθος απάντησης
                score -= 5; 										//Μειώνεται το σκορ κατα 5
                feedbacktext.setText("Wrong");						//Το feedbactext εμφανίζει Wrong
                feedbacktext.setFill(Color.RED);					//Το feedbacktext γίνεται κόκκινο
            }
			
		scoretext.setText("Score " + score);						//Ενημερώνουμε το scoretext και εμφανίζει το σκορ
		
		correcttext.setText("Correct Answers:  " + correctIndex +"/" + (currentQuestionIndex+1));	//Ενημερώνουμε το correcttext και εμφανίζει πόσες ερωτήσεις απαντήθηκαν σωστά
		    
		
		PauseTransition pause = new PauseTransition(Duration.seconds(2));	//Παυση για 2 δευτερόλεπτα πριν σβηστεί το feedbacktext
        pause.setOnFinished(e -> feedbacktext.setText(""));  				//Καθαρίζουμε το feedbacktext μετά την παύση
        pause.play();														//Εκτελείται η παύση
        
		currentQuestionIndex++;												//Αυξάνουμε το μετρητή του response
        if (currentQuestionIndex < triviaQuestionsWithParameters.size()) {	//Αν ο μετρητής είναι μικρότερος από το μέγεθος της λίστας
            showQuestionWithParameters();									//Εμφανίζει την επόμενη "ερώτηση"
        } else {
        	if (score > lasthighscore) {									//Αν το score είναι μεγαλύτερο από το lasthighscore
                highscore = score;  										//Κάνουμε το highscore ίσο με το score
                highscoretext.setText("New Highscore! " + highscore);		//Το highscoretext εμφανίζει μηνυμα New Highscore και τον highscore
                highscoretext.setVisible(true);								//Εμφάνιση του highscoretext
                lasthighscore = highscore;									//Κάνουμε το lasthighscore ίσο με το highscore
    		}    
        	else {
        		highscoretext.setVisible(false);							//Απόκρυψη του highscoretext
        	}
        	endtext.setVisible(true);										//Εμφάνιση του μηνύματος τέλος παιχνιδιού
        	scoretext.setVisible(true);										//Εμφάνιση του scoretext
        	answer1Btn.setVisible(false);									//Απόκρυψη των κουμπιών απάντησης
        	answer2Btn.setVisible(false);
        	answer3Btn.setVisible(false);
        	answer4Btn.setVisible(false);
        	questiontext.setVisible(false);									//Απόκρυψη του questiontext
        	playAgainBtn.setVisible(true);									//Εμφάνιση του κουμπιού Play Again
        	mainMenuBtn.setVisible(true);									//Εμφάνιση του κουμπιού Main Menu
        	correcttext.setVisible(true);									//Εμφάνιση του correctext
        	
        	
        	
        	
        	
        	
        	
        	
        }
		}
		
		
}


        	