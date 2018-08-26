package assignment1;

public class Blackjack {

	public static void main(String[] args){
		boolean showRounds = false;
		boolean endGame = false; 
		int rounds = Integer.parseInt(args[0]);
		int numdeck = Integer.parseInt(args[1]);
		System.out.println("Starting Blackjack with " + rounds + " rounds and " + numdeck + " decks in the shoe");
		System.out.println();
		
		int dealer = 0;
		int player = 0;
		int push = 0;
		
		if(rounds <= 10)
			showRounds = true; //When this is true, each round will be shown with all of the cards known
		
		RandIndexQueue<Card> shoe = new RandIndexQueue<Card>(52 * numdeck); 
		RandIndexQueue<Card> discard = new RandIndexQueue<Card>(2);
		RandIndexQueue<Card> playercard = new RandIndexQueue<Card>(2);
		RandIndexQueue<Card> dealercard = new RandIndexQueue<Card>(2);
		Shoe allcards = new Shoe(numdeck);
		
		for(int i = 0; i < numdeck; i++){ //Make array of Decks with each Deck an array of Cards. Then put in Queue
			Deck d = allcards.getDeck(i);
			for(int j = 0; j < 52; j++){
				Card c = d.getCard(j);
				shoe.offer(c);
			}
		}
		shoe.shuffle();
		
		for(int i = 0; i < rounds; i++){
			
			if(shoe.size() <= 52 * numdeck / 4){ //When shoe gets to 1/4 of original size, discarded cards added
				for(int j = 0; j < discard.size(); j++)
					shoe.offer( discard.get(j) );
				discard.clear();
				shoe.shuffle();
				if(!showRounds){
					System.out.println("Reshuffling in Round " + i);
					System.out.println();
				}
			}
			
			Card one = shoe.poll();
			Card two =  shoe.poll();
			Card three =  shoe.poll();
			Card four =  shoe.poll();
			
			int playersum = one.value() + three.value();
			int dealersum = two.value() + four.value();
			
			playercard.offer(one);
			dealercard.offer(two);
			playercard.offer(three);
			dealercard.offer(four);
			playersum = adjustAces(playercard, playersum); //Adjust sum if two aces given
			dealersum = adjustAces(dealercard, dealersum);
			
			if(showRounds){ //Show the initial two cards being dealt
				System.out.println("Round " + i + " beginning");
				System.out.print("Player: Contents: " + one.toString() + " " + three.toString());
				System.out.println("  : " + playersum );
				System.out.print("Dealer: Contents: " + two.toString() + " " + four.toString());
				System.out.println("  : " + dealersum );
			}
			
			//For cases when sum adds to 21, result immediately known, go straight to discarding cards
			if(playersum == 21 && dealersum == 21){
				if(showRounds)
					System.out.println("Result: Push");
				push++;
				endGame = true;
			}
			
			else{
				if(playersum == 21){
					if(showRounds)
						System.out.println("Result: Player Blackjack wins");
					player++;
					endGame = true;
				}

				if(dealersum == 21){
					if(showRounds)
						System.out.println("Result: Dealer Blackjack wins");
					dealer++;
					endGame = true;
				}
			}
			
			while(playersum < 17 && !endGame){ //Player hits until greater than 17
				if(playersum >= dealersum + 5) //Only 'player strategy'; don't hit if dealer's cards smaller by 5
					break;
				Card hit = shoe.poll();
				playercard.offer(hit);
				if(showRounds)
					System.out.println("Player hits: " + hit.toString());
				playersum += hit.value();
				if(hit.value() == 11)
					playersum = adjustAces(playercard, playersum); //Adjust for if ace value of 11 causes bust
			}
			
			if(playersum > 21 && !endGame){ //Going over 21 means bust, player wins. Go straight to discarding
				if(showRounds){
					System.out.print("Player BUSTS: Contents: ");
					for(int j = 0; j < playercard.size(); j++)
						System.out.print(playercard.get(j).toString() + " ");
					System.out.println(" : " + playersum);
					System.out.println("Result: Dealer wins!");
				}
				dealer++;
				endGame = true;
			}
			else if(!endGame){ //Commands for player standing
				if(showRounds){
					System.out.print("Player STANDS: Contents: ");
					for(int j = 0; j < playercard.size(); j++)
						System.out.print(playercard.get(j).toString() + " ");
					System.out.println(" : " + playersum);
				}
			}
			
			while(dealersum < 17 && !endGame){ //Commands for dealer hitting
				Card hit =  shoe.poll();
				dealercard.offer(hit);
				if(showRounds)
					System.out.println("Dealer hits: " + hit.toString());
				dealersum += hit.value();
				if(hit.value() == 11)
					dealersum = adjustAces(dealercard, dealersum);
			}
			
			if(dealersum > 21 && !endGame){ //Print statements for dealer bust
				if(showRounds){
					System.out.print("Dealer BUSTS: Contents: ");
					for(int j = 0; j < dealercard.size(); j++)
						System.out.print(dealercard.get(j).toString() + " ");
					System.out.println(" : " + dealersum);
					System.out.println("Result: Player wins!");
				}
				player++;
				endGame = true;
			}
			else if(!endGame){ //Print statements for dealer stands
				if(showRounds){
					System.out.print("Dealer STANDS: Contents: ");
					for(int j = 0; j < dealercard.size(); j++)
						System.out.print(dealercard.get(j).toString() + " ");
					System.out.println(" : " + dealersum);
				}
			}
			
			if(!endGame){ //Comparing totals of cards
				if(playersum > dealersum){
					player++;
					if(showRounds)
						System.out.println("Result: Player wins!");
				}
				else if(playersum < dealersum){
					dealer++;
					if(showRounds)
						System.out.println("Result: Dealer wins!");
				}
				else{
					push++;
					if(showRounds)
						System.out.println("Result: Push!");
				}
			}
			
			//Removes cards from player and dealer's hands and into discard.
			for(int j = 0; j < playercard.size(); j++){ 
				Card remove = playercard.get(j);
				discard.offer(remove);
			}
			for(int j = 0; j < dealercard.size(); j++){
				Card remove = dealercard.get(j);
				discard.offer(remove);
			}
			playercard.clear();
			dealercard.clear();
			endGame = false;
			
			if(showRounds) //If rounds are 1000, there will be 1000 needless linebreaks without this if-statement
				System.out.println();
		}
		
		System.out.println("After " + rounds + " rounds, here are the results");
		System.out.println("\t Dealer Wins: " + dealer );
		System.out.println("\t Player Wins: " + player );
		System.out.println("\t Pushes: " + push );
	}
	
	//This method lowers sum by 10 if there's an ace and value of 11 causes a bust
	private static int adjustAces(RandIndexQueue<Card> queue, int sum){ 
		int i = 0;
		while(sum > 21 && i < queue.size()){ //Break out of loop if adjusted sum less than 21, or end of queue reached
			Card c = queue.get(i);
			if(c.value() == 11)
				sum = sum - 10;
			i++;
		}
		return sum;	
	}
}
