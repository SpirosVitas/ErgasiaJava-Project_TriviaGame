package model;

import java.util.List;

public class TriviaInfo {
	private String category;
	private String type;
	private String question;
	private String corectAnswer;
	private List<String> incorectAnswers;
	
	
	
	
	public TriviaInfo(String category, String type, String question, String corectAnswer,
			List<String> incorectAnswers) {
		
		this.category = category;
		this.type = type;
		this.question = question;
		this.corectAnswer = corectAnswer;
		this.incorectAnswers = incorectAnswers;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorectAnswer() {
		return corectAnswer;
	}
	public void setCorectAnswer(String corectAnswer) {
		this.corectAnswer = corectAnswer;
	}
	public List<String> getIncorectAnswers() {
		return incorectAnswers;
	}
	public void setIncorectAnswers(List<String> incorectAnswers) {
		this.incorectAnswers = incorectAnswers;
	}
}
