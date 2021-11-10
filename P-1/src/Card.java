/**
 * Superclass of all Card objects (subclasses)
 */
public class Card {
	
	/**
	 * Note: public - accessed everywhere
	 * 		 protected - within the class AND by inheriting child classes
	 * 		 private - only within the class that defines it
	 */
	protected String number; // numbers of Cards
	protected String color;  // colors of Cards
	
	/**
	 * Card class constructor
	 */
	public Card(String n, String c) {
		this.number = n;
		this.color = c;
	}
	
	public String[] getCard() {
		String[] card = {this.number, this.color};
		return card;
	}
	
	public void setColor(String new_color) {}
	
	/**
	 * Takes in Card and returns if it can be played
	 * To be modified in each subclass
	 */
	public boolean playable(Card card) {
		return true;
	}
	
	public String toString() {
		return number + ": " + color;
	}
	
}
