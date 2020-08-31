package game;

import java.util.Random;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BlackJack {
	private Card[] deck = new Card[52];
	private Queue<Card> deckFinal = new LinkedList<>();
	private int pot = 0;
	private int deckPos = 0; //Keeps track of current location in the deck.
	
	
	/**
	 * Generates a full deck of 52 cards.
	 */
	private void makeDeck() {
		int s = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				Card t = new Card(i, j);
				deck[s] = t;
				s++;
			}
		}
	}
	
	
	/**
	 * Swaps two positions in the deck array.
	 * @param p1 first position
	 * @param p2 second position
	 */
	private void swap(int p1, int p2) {
		Card temp = deck[p2];
		deck[p2] = deck[p1];
		deck[p1] = temp;
	}
	
	/**
	 * Swaps positions in the deck array to shuffle the deck
	 * @param x is a number of swaps. Around 50 should be plenty.
	 */
	private void shuffle(int x) {
		Random r1 = new Random();
		Random r2 = new Random();
		for(int i = x; i > 0; i--) {
			int p1 = r1.nextInt(52);
			int p2 = r2.nextInt(52);
			swap(p1, p2);
		}
	}
	
	private void generateDeck() {
		makeDeck();
		shuffle(52);
		for(Card c:deck) {
			deckFinal.add(c);
		}
	}
	
	/**
	 * Gets the hand score for the given hand..
	 * @param a target hand.
	 * @return the score for the players hand.
	 */
	public int getHandValue(ArrayList<Card> hand) {
		int val = 0;
		for(Card c : hand) {
			switch(c.getDisplay()) {
			case "2": 
				val+=2;
				break;
			case "3":
				val+=3;
				break;
			case "4":
				val+=4;
				break;
			case "5":
				val+=5;
				break;
			case "6":
				val+=6;
				break;
			case "7":
				val+=7;
				break;
			case "8":
				val+=8;
				break;
			case "9":
				val+=9;
				break;
			case "A":
				if(val >= 11) {
					val+=1;
				} else {
					val+=11;
				}
				break;
			default:
				val+=10;
			} 
		}
		return val;
	}
	
	/**
	 * Prints player options (and current pot amount) to console.
	 */
	public void printOptions() {
		System.out.println("1-Hit 2-Stay 3-Doubledown");
		System.out.println("Current pot: "+pot);
	}
	
	
	/**
	 * Sets the pot to given value
	 * @param val given value.
	 */
	private void setPot(int val) {
		this.pot = val;
	}
	
	private void doubleDown() {
		setPot(this.pot*2);
	}
	
	private void resetPot() {
		this.pot = 0;
	}
	
	
	/**
	 * Resets deck by making a new one and shuffling it
	 */
	private void resetDeck() {
		makeDeck();
		shuffle(104);
		deckPos=0;
	}
	
	/**
	 * Draws the first two cards for both player and dealer. Moves deckPos to account for four cards.
	 * @param playerHand is the player's hand (ArrayList).
	 * @param dealerHand is the dealer's hand (ArrayList).
	 */
	private void drawInitial(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		deckPos = 0;
		playerHand.add(deck[deckPos]);
		deckPos++;
		dealerHand.add(deck[deckPos]);
		deckPos++;
		playerHand.add(deck[deckPos]);
		deckPos++;
		dealerHand.add(deck[deckPos]);
		deckPos++;
	}
	
	/**
	 * Converts given ArrayList (hand) as a String.
	 * @param hand the hand to be converted
	 * @return String representation of hand.
	 */
	private String getHandDisplay(ArrayList<Card> hand) {
		String out = "";
		for(Card c : hand) {
			out+="["+c.getDisplay()+"]";
		}
		return out;
	}
	
	/**
	 * Checks if a hand has gone over 21
	 * @param hand is the hand to check
	 * @return true if the hand is a bust.
	 */
	private boolean checkBust(ArrayList<Card> hand) {
		return (getHandValue(hand)>21);
	}
	
	/**
	 * Checks if a hand is at the winning value of 21.
	 * @param hand to check
	 * @return true if the value is 21
	 */
	private boolean check21(ArrayList<Card> hand) {
		return(getHandValue(hand)==21);
	}
	
	
	/**
	 * Compares player hand to dealer hand
	 * @param playerHand is the player hand
	 * @param dealerHand is the dealer hand
	 * @return 0 if the hands are equal, 1 if the player wins, -1 if the dealer wins.
	 */
	public int compareHands(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		if (getHandValue(playerHand)==getHandValue(dealerHand)) {
			return 0;
		} else if (getHandValue(playerHand)>getHandValue(dealerHand)) {
			return 1;
		} else {
			return -1;
		}
	}
	
	private void doubleDown(Player p) {
		if(p.hasEnough(pot)) {
			pot += pot/2; //pot is divided in half to only include player's chips in the pot.
			p.setChips(p.getChips()-(pot/2));
		} else {
			System.err.print("Player does not have enough chips to double down.");
		}
	}
	
	public void hit(Player p) {
		if(deckPos<52) {
			p.getHand().add(deck[deckPos]);
			deckPos++;
		}
	}
	
	/**
	 * Takes an "AI" turn
	 * @param dealer is the "AI"
	 * Yes, I'm aware this is extremely basic AI.
	 */
	private void aITurn(Player dealer) {
		while(!dealer.standing&&getHandValue(dealer.getHand())<16) {
			hit(dealer);
			System.out.println("Dealer: "+getHandDisplay(dealer.getHand())+" Val: "+getHandValue(dealer.getHand()));
		} 
		if(!dealer.standing&&getHandValue(dealer.getHand())==16) {
			Random r = new Random();
			int sel = r.nextInt(10);
			if(sel%2==0) {
				hit(dealer);
				System.out.println("Decided to hit on 16!");
				System.out.println("Dealer: "+getHandDisplay(dealer.getHand())+" Val: "+getHandValue(dealer.getHand()));
			} else { 
				dealer.standing = true;
				System.out.println("Done! Decided to stay on 16!");
			}
		}
		dealer.standing = true;
		System.out.println("Done!");
	}
	
	//Main game logic
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.makeDeck();
		game.shuffle(100);
		
		Player dealer = new Player("Dealer");
		Player y = new Player("You", 500);
		Card c1 = new Card(0,11);
		Card c2 = new Card(1, 4);
	    dealer.getHand().add(c1);
	    dealer.getHand().add(c2);
		System.out.println("Dealer start: "+game.getHandDisplay(dealer.getHand())+" Val: "+game.getHandValue(dealer.getHand()));
		while(!dealer.standing) {
			game.aITurn(dealer);
		}
		
	}

}
 