package testing;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.TriviaAPIException;
import model.TriviaInfo;
import service.TriviaAPIService;

public class TriviaAPITest {
	@Before
    public void setUp() throws InterruptedException {
       
        Thread.sleep(5000); 
    }

	@Test
	public void testgetQuestions() throws TriviaAPIException {
		final TriviaAPIService tas = new TriviaAPIService("https://opentdb.com");
		final List<TriviaInfo> results=tas.getQuestions(10);
		Assert.assertFalse(results.isEmpty());
		for(TriviaInfo t: results) {
			System.out.println("Category "+ t.getCategory()+"\nType "+t.getType()+"\n"+t.getQuestion()+ "\n" +t.getCorectAnswer()+t.getIncorectAnswers());
		}
	}
	@Test
	public void testgetQuestionsWithAllParameters() throws TriviaAPIException {
		
		final TriviaAPIService tas = new TriviaAPIService("https://opentdb.com");
		final List<TriviaInfo> results=tas.getQuenstionsWithParameters(10,"15","medium","multiple");
		Assert.assertFalse(results.isEmpty());
		for(TriviaInfo t: results) {
			System.out.println("Category "+ t.getCategory()+"\nType "+t.getType()+"\n"+t.getQuestion()+ "\n" +t.getCorectAnswer()+t.getIncorectAnswers());
		}
	}
	
	
}


