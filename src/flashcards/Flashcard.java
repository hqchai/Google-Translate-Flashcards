package flashcards;

import com.googlecode.objectify.annotation.Embed;

@Embed
public class Flashcard {

	/**
     * 
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    private String phrase1;
	private String phrase2;
    private int totalScore;       
    private int correctnessRating;  // Higher score means more times correct
    private int userRating;  // 1 = easy 3 = hard
    private int timeRating;  // A high time rating means you haven't seen it in a while
	
    // no-arg constructor required for objectify
    @SuppressWarnings("unused")
    private Flashcard() {}
    
	public Flashcard(String p1, String p2) {
		
		// TODO: Phrase 2 is optional b/c use Google Translate?
	    
		phrase1 = p1;
		phrase2 = p2;
		
        //Default ratings
		correctnessRating = 3; // default (1-5)
		userRating = 2; // default (1-3)
	    timeRating = 100;   //See setTimeRating for more info on time rating
        //Calculate the total score
        updateTotalScore();
	}
	
	public String toString() {
	    
	    return "phrase 1: " + phrase1 + ", phase 2: " + phrase2;
	}

    public int getTotalScore() {
        return totalScore;
    }
    
    public String getPhrase1() {
        
        return phrase1;
    }
    
    public void setPhrase1(String s) {
        
        phrase1 = s;
    }
    
    public String getPhrase2() {
        
        return phrase2;
    }
    
    public void setPhrase2(String s) {
        
        phrase2 = s;
    }

    public boolean timeRatingIs0() {
        if (timeRating == 0)
            return true;
        else
            return false;
    }

    //////////////////////////////////////
    // Functions that update the scores //
    //////////////////////////////////////

	public void updateTotalScore() {
        // Take the time rating and add multipliers based on correctness and user rating
        int base = timeRating;
        switch (userRating) {
            case 1: break;
            case 2: base *= 1.2;
                    break;
            case 3: base *= 1.4;
                    break;
            default: break;
        }
        switch (correctnessRating) {
            case 1: base *= 1.1;
                    break;
            case 2: base *= 1.2;
                    break;
            case 3: base *= 1.3;
                    break;
            case 4: base *= 1.4;
                    break;
            case 5: base *= 1.5;
                    break;
            default: break;
        }
        totalScore = base;
    }
    
    public void updateCorrectnessRating(Boolean correct) {
        //If rating is out of bounds, default back to 3
        if (correctnessRating < 1 || correctnessRating > 5)
            correctnessRating = 3;
        //Set rating based on if user was correct or not
        if (correct) {
            if (correctnessRating < 5) 
                correctnessRating++;
        }
        else {
            if (correctnessRating > 1)
                correctnessRating--;
        }
    }

    public void updateUserRating(int newRating) {
        userRating = newRating;
    }

    public void updateTimeRating() {
        // Rating is used to know how long it has been since a card has been accessed
        // Should be called whenever a card is accessed
        // Accessing a card will decrement the time rating, so a higher rating means the card hasn't been accessed in a while
        // When a card hits a rating of 0, all of the cards ratings will be bumped up by 100
        timeRating--;
    }

    public void add100ToTimeRating() {
        timeRating += 100; 
    }
}
