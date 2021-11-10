import java.util.*;

/**
 * Player Class
 */
public class Player {
	
	private Map<String, Card> hand = new HashMap<String, Card>();
	// unique char assigned to each card in hand
	private static String indicator = "abcdefghijklmnopqrstuvwxyz";  
	
	public Player(ArrayList<Card> cards) {
		drawCards(cards); // draw cards first
	}
	
	public Map<String, Card> getHand() {
		return this.hand;
	}
	
	/**
	 * Pair the card up with a char from indicator that is not used
	 * and add it to Player's hand 
	 */
	public void drawCard(Card card) {
		String s = "";
		for(int i = 0; i <= indicator.length(); i++) {
			if(!hand.containsKey(indicator.substring(i, i+1))) {
				s = indicator.substring(i, i+1);
				break;
			}
		}
				
		if(s.equals("")) { System.out.println("ERROR1-1: Too many hands"); System.exit(0); }
		hand.put(s, card);
	}
	
	/**
	 * Add multiple cards to Player's hand
	 */
	public void drawCards(ArrayList<Card> cards) {
		for(Card card : cards) {
			drawCard(card);
		}
	}
	
	/**
	 * Remove the card from Player's hand (and return it)
	 */
	public Card play(Card card) {
		String s = ""; // indicator of the card to be removed
		for(Map.Entry<String, Card> item : hand.entrySet()) {
			if(card == item.getValue()) {
				s = item.getKey();
				break;
			}
		}
		
		if(s.equals("")) { System.out.println("ERROR1-2: Too many hands"); System.exit(0); }
		hand.remove(s);
		return card;
	}
	
	/**
	 * Check if the given indicator has a match
	 */
	public boolean match(String indicator) {
		for(Map.Entry<String, Card> entry : hand.entrySet()) {
			if(indicator.equals(entry.getKey())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the size of hand
	 */
	public int handSize() {
		return hand.size();
	}
}
