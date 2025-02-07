package ergasia.TriviaFX;

public class GameSettings {
	
	private  int amount;
	private  String category;
	private  String difficulty;
	private  String type;
	
	public GameSettings(int amount, String category, String difficulty, String type) {
		
		this.amount = amount;
		this.category = category;
		this.difficulty = difficulty;
		this.type = type;
	}

	public GameSettings(int amount, String category) {
		
		this.amount = amount;
		this.category = category;
	}

	public GameSettings(int amount, String category, String difficulty) {
		
		this.amount = amount;
		this.category = category;
		this.difficulty = difficulty;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}	
	
	
	
	