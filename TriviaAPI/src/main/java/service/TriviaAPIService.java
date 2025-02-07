package service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.TriviaAPIException;
import model.TriviaInfo;
import model.trivia.ErrorResponse;
import model.trivia.Result;
import model.trivia.TriviaResult;

public class TriviaAPIService {
	private final String API_URL;	//ορίζω την μεταβλητή για το URL
	

	public TriviaAPIService(String API_URL) {	//Φτιάχνω τον Constructor της κλάσης
		this.API_URL = API_URL;
		
	}


	public List<TriviaInfo> getQuestions(int number) throws TriviaAPIException {
		List<TriviaInfo> triviaInfoList = new ArrayList<TriviaInfo>();	// Φτιάχνουμε μία λιστα με στοιχεια TriviaInfo
	try {  // εντοπίζει κάποιο σφάλμα
		
		//Χτίζουμε το URI με την URIbuilder δίνουμε το base url με την μεταβλητή και δίνουμε μια λίστα με το τμήμα που ακολουθεί το base url
		URIBuilder uriBuilder = new URIBuilder(API_URL).setPathSegments("api.php")
				.addParameter("amount", (new Integer (number)).toString());	//Προσθέτουμε την παράμετρο για να ολοκληρωθεί το url 
		
		URI uri = uriBuilder.build();
		
		HttpGet getRequest = new HttpGet(uri);	//Φτιάχνουμε το request που θα στείλουμε στο Server
		CloseableHttpClient httpclient = HttpClients.createDefault();	//Αυτός είναι ο Client που θα στείλει το request
		
		CloseableHttpResponse response = httpclient.execute(getRequest);	//Ο Client στέλνει το request και αποθηκεύει το response
		
		HttpEntity entity = response.getEntity();	//Κάνουμε extract το response και το αποθηκεύουμε
		
		ObjectMapper mapper = new ObjectMapper();	
		
		if (response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK) {	//Αν το StatusCode δεν είναι ΟΚ (Δεν είναι επιτυχης η κληση του response)
			ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);	//Φτίαχνει ένα αντικείμενο ErrorResponse απο το response
			if(errorResponse.getResponseCode()!=null) {	//Αν το ResponseCode δεν ειναι null
				System.out.println(errorResponse.getResponseCode());	//Κάνουμε Print το ResponseCode
				throw new TriviaAPIException("Error occures on API call code "+ errorResponse.getResponseCode()); //Κανουμε throw το exception στο App 
			}
		}
		
		TriviaResult triviaResultObject = mapper.readValue(entity.getContent(), TriviaResult.class);	//Φτίαχνει ένα αντικείμενο TriviaResult απο το response
		
		List<Result> tResults = triviaResultObject.getResults();	// Δημιουργουμε μια λιστα με τα Results που μας επιστραφηκαν
		
		//Για κάθε στοιχείο της λιστας tResults αποθηκευουμε τα πεδία που θελουμε σε μια καινουρια λιστα TriviaInfoList
		for (Result r: tResults) { 
			TriviaInfo t = new TriviaInfo(r.getCategory(), r.getType(), r.getQuestion(), r.getCorrectAnswer(), r.getIncorrectAnswers());
			triviaInfoList.add(t);
			
			

		}
		
		
		return triviaInfoList;
		
		
		//Σε περιπτωση σφαλματος ενημερωνει με το αναλογο μηνυμα
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Unable to create request URI.", e);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Problem with Client", e);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Error requesting data from the site", e);
	}
	
	
	
	}
	
	
	public List<TriviaInfo> getQuenstionsWithParameters(int number, String category, String difficulty, String type) throws TriviaAPIException{
		List<TriviaInfo> triviaInfoList = new ArrayList<TriviaInfo>();	// Φτιάχνουμε μία λιστα με στοιχεια TriviaInfo
	try {	// εντοπίζει κάποιο σφάλμα
		
		//Χτίζουμε το URI με την URIbuilder δίνουμε το base url με την μεταβλητή και δίνουμε μια λίστα με το τμήμα που ακολουθεί το base url
		URIBuilder uriBuilder = new URIBuilder(API_URL).setPathSegments("api.php")	
				.addParameter("amount", (new Integer (number)).toString())	//Προσθέτουμε τις παραμέτρους για να ολοκληρωθεί το url 
				.addParameter("category", category)
				.addParameter("difficulty",difficulty)
				.addParameter("type",type);
		
		URI uri = uriBuilder.build();
		
		HttpGet getRequest = new HttpGet(uri);	//Φτιάχνουμε το request που θα στείλουμε στο Server
		CloseableHttpClient httpclient = HttpClients.createDefault();	//Αυτός είναι ο Client που θα στείλει το request
		
		CloseableHttpResponse response = httpclient.execute(getRequest);	//Ο Client στέλνει το request και αποθηκεύει το response
		
		HttpEntity entity = response.getEntity();	//Κάνουμε extract το response και το αποθηκεύουμε
		
		ObjectMapper mapper = new ObjectMapper();	
		
		if (response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK) {	//Αν το StatusCode δεν είναι ΟΚ (Δεν είναι επιτυχης η κληση του response)
			ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);	//Φτίαχνει ένα αντικείμενο ErrorResponse απο το response
			if(errorResponse.getResponseCode()!=null) {	//Αν το ResponseCode δεν ειναι null
				System.out.println(errorResponse.getResponseCode());	//Κάνουμε Print το ResponseCode
				throw new TriviaAPIException("Error occures on API call code "+ errorResponse.getResponseCode());	//Κανουμε throw το exception στο App 
			}
		}
		
		TriviaResult triviaResultObject = mapper.readValue(entity.getContent(), TriviaResult.class);	//Φτίαχνει ένα αντικείμενο TriviaResult απο το response
		
		List<Result> tResults = triviaResultObject.getResults();	// Δημιουργουμε μια λιστα με τα Results που μας επιστραφηκαν
		
		//Για κάθε στοιχείο της λιστας tResults αποθηκευουμε τα πεδία που θελουμε σε μια καινουρια λιστα TriviaInfoList
		for (Result r: tResults) {
			TriviaInfo t = new TriviaInfo(r.getCategory(), r.getType(), r.getQuestion(), r.getCorrectAnswer(), r.getIncorrectAnswers());
			triviaInfoList.add(t);
			

		}
		
		
		
		return triviaInfoList;
		
		//Σε περιπτωση σφαλματος ενημερωνει με το αναλογο μηνυμα
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Error requesting data from the site", e);
		
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Error requesting data from the site", e);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new TriviaAPIException("Error requesting data from the site", e);
	}
	
	
	
	}
	
	
	
}
