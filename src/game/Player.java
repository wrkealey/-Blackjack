package game;

import java.util.ArrayList;

public class Player {

	private String pName = "";
	private int chips = 0;
	private ArrayList<Card> playerHand; 
	public boolean standing = false;
	
	public Player(String name, int bank) {
		this.pName = name;
		this.chips = bank;
		this.playerHand = new ArrayList<Card>();
		this.standing = false;
	}
	
	public Player(String name) {
		this.pName = name;
		this.chips = 10000;
		this.playerHand = new ArrayList<Card>();
		this.standing = false;
	}
	
	/**
	 * Sets the player's chip value to a number of chips. 
	 * @param t is the set value.
	 */
	public void setChips(int t) {
		this.chips = t;
	}
	
	/**
	 * Checks if the player meets a required value of chips.
	 * @param req the required chip value.
	 * @return true if requirement is met.
	 */
	public boolean hasEnough(int req) {
		return this.chips>=req;
	}
	
	/**
	 * Gets player name
	 * @return the name of the player.
	 */
	public String getName() {
		return this.pName;
	}
	
	/** 
	 * Gets player chip value.
	 * @return player chip value.
	 */
	public int getChips() {
		return this.chips;
	}
	
	/**
	 * Completely clears the player's hand.
	 */
	public void resetHand() {
		this.playerHand.clear();
	}
	
	/** 
	 * Adds given card to player's hand.
	 * @param x given card.
	 */
	public void addCard(Card x) {
		this.playerHand.add(x);
	}
	
	
	/**
	 * Shows the player hand (as proper cards with suit)
	 * @return player hand.
	 */
	public ArrayList<Card> getHand(){
		return this.playerHand;
	}
	
	
}
