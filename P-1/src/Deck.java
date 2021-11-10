import java.util.*;

/**
 * Deck Class
 */
public class Deck {
	
	private Queue<Card> deck = new ArrayDeque<Card>();
	private Stack<Card> discard = new Stack<Card>();
	
	public Deck() {
		Fill(new Card("first", ""));
	}
	
	public Queue<Card> getDeck() {
		return deck;
	}
	
	public Stack<Card> getDiscard() {
		return discard;
	}
	
	/**
	 * Fill the deck with 36 cards (N:24(0~5), D2: 8, W:4).
	 * The card is the top card currently on the deck, so we avoid to
	 * add the same card.
	 */
	private void Fill(Card card) {
		
		// Normal
		for(int number = 0; number <= 5; number++) {
			deck.add(new Normal(Integer.toString(number), "red"));
			deck.add(new Normal(Integer.toString(number), "blue"));
			deck.add(new Normal(Integer.toString(number), "green"));
			deck.add(new Normal(Integer.toString(number), "yellow"));
		}
		
		// Draw2
		for(int i = 0; i < 2; i++) {
			deck.add(new Draw2("+2", "red"));
			deck.add(new Draw2("+2", "blue"));
			deck.add(new Draw2("+2", "green"));
			deck.add(new Draw2("+2", "yellow"));
		}
		
		// Wild
		for(int i = 0; i < 4; i++) {
			deck.add(new Wild("wild", "none"));
		}
		
		if(!card.getCard()[0].equals("first")) {
			Remove(card);
		}
		
		Shuffle();
	}
	
	/**
	 * Remove the card from deck as it's on the discard already
	 */
	private void Remove(Card card) {
		Queue<Card> deck_temp = new ArrayDeque<Card>();
		while(!deck.isEmpty()) {
			if(deck.peek() != card) {
				deck_temp.add(deck.remove());
			} else {
				deck.remove();
			}
		}
		
		deck = deck_temp;
	}
	
	/**
	 * Shuffle the deck
	 */
	private void Shuffle() {
		
		ArrayList<Card> deck_temp = new ArrayList<Card>();
		// move the cards to temporary deck
		while(!deck.isEmpty()) {
			deck_temp.add(deck.poll());
		}
		
		// shuffle
		Collections.shuffle(deck_temp);
		
		for(Card card : deck_temp) {
			deck.add(card);
		}
	}
	
	
	/**
	 * Clear the deck
	 */
	public void Clear() {
		while(!deck.isEmpty()) {
			deck.poll();
		}
	}
	
	/**
	 * Clear the discard while having the top remained
	 */
	public void clearDiscard() {
		Card top = discard.pop();
		while(!discard.isEmpty()) {
			discard.pop();
		}
		discard.push(top);
	}
	
	/**
	 * Draw a card from the deck, and return the card drawn.
	 * If the deck is empty, fill it again.
	 */
	public Card Draw() {
		if(deck.isEmpty()) {
			clearDiscard();
			Fill(discard.peek());
		}
		return deck.poll();
	}
	
	/**
	 * Draw cards from the deck and return them
	 */
	public ArrayList<Card> drawCards(int number) {
		ArrayList<Card> cards = new ArrayList<Card>();
		if(deck.size() < number) {
			Clear();
			clearDiscard();
			Fill(discard.peek());	
		}
		
		for(int i = 0; i < number; i++) {
			cards.add(deck.poll());
		}
		
		return cards;
	}
	
	/**
	 * Play a card
	 */
	public void Play(Card card) {
		discard.push(card);
	}
}
