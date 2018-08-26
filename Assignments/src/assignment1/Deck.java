package assignment1;

//This class holds collection of cards
public class Deck {
	private Card[] cards = new Card[52];
		
	public Deck(){
		int index = 0;
		for(Card.Suits s: Card.Suits.values()){
			for(Card.Ranks r: Card.Ranks.values()){
				cards[index] = new Card(s, r);
				index++;
			}
				
		}
	}
	
	public Card getCard(int ind){
		return cards[ind];
	}

}
