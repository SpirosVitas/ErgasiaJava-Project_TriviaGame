package ergasia.TriviaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	static Stage primaryStage;	//ορίζουμε το Stage
	static Scene mainScene;	// ορίζουμε το παράθυρο
	
	

    @Override
    public void start(Stage stage) {
        
    	this.primaryStage = stage;	//εκχωρούμε στο primaryStage το stage 
    	

       
       MainSceneCreator mainSceneCreator = new MainSceneCreator(); //Καλούμε τον constructor της MainSceneCreator για να αρχικοποιήσει τα πεδια
       mainScene = mainSceneCreator.createScene();	//Δημιουργούμε το MainScene
       
       stage.setTitle("My Trivia");	//Δίνουμε όνομα στο παράθυρο 
       stage.setScene(mainScene);	//Το primaryStage δειχνει το mainScene
 
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    

}