package game;

public class Card {
	
	private String suit;
	private String display;
	
	//Constructor for card
	public Card(int s, int v) {
		this.suit = genSuit(s);
		this.display = genDisplay(v);
	}
	
	/**
	 * Converts a given int to the suit of the card
	 * @param s Numeric suit: 0-Clubs, 1-Spades, 2-Hearts, 3-Diamonds
	 * @return the Suit as a string
	 */
	private String genSuit(int s) {
		String r = "";
		switch (s) {
			case 0: 
				r+="Clubs";
				break;
			case 1: 
				r+="Spades";
				break;
			case 2:
				r+="Hearts";
				break;
			case 3:
				r+="Diamonds";
				break;
			default:
				System.err.print("Card generated with illegal suit\n");
				break;
			}
		return r;
	}
	
	/**
	 * Converts given int to display name for the card.
	 * @param v is a numeric value of a card.
	 * @return the card's display name.
	 */
	private String genDisplay(int v) {

		String d ="";
		switch(v) {
		case 0:
			d+="2";
			break;
		case 1:
			d+="3";
			break;
		case 2:
			d+="4";
			break;
		case 3: 
			d+="5";
			break;
		case 4:
			d+="6";
			break;
		case 5:
			d+="7";
			break;
		case 6:
			d+="8";
			break;
		case 7:
			d+="9";
			break;
		case 8:
			d+="10";
			break;
		case 9:
			d+="J";
			break;
		case 10:
			d+="K";
			break;
		case 11:
			d+="Q";
			break;
		case 12:
			d+="A";
			break;
		default:
			System.err.print("Card generated with illegal display name\n");
		    break;
		}
		return d;
	}
	
	/**
	 * Test method that displays the name and suit of the card.
	 * @return proper name for the card.
	 */
	
	public String toString() {
		return this.display+" of "+this.suit;
	}
	
	/**
	 * Gets the name of the card
	 * @return name of the card.
	 */
	public String getDisplay() {
		return this.display;
	}
	
	/**
	 * Gets the suit of the card 
	 * @return the suit of the card.
	 */
	public String getSuit() {
		return this.suit;
	}

	
}
