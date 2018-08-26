package assignment1;

//This class holds collection of decks
public class Shoe {
	private Deck[] decks;
	
	public Shoe(int num){
		decks = new Deck[num];
		for(int i = 0; i < decks.length; i++)
			decks[i] = new Deck();
	}
	
	public Deck getDeck(int ind){
		return decks[ind];
	}

}
