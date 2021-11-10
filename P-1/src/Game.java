import java.util.*;

/**
 * Game class
 */
public class Game {
	
	private int turn_num = 1;
	private Deck deck;
	private Player player;
	private Player computer;
	private static Scanner input = new Scanner(System.in);
	private int computer_handsize = 0;
	//private Printer printer;
	
	public Game() {
		printer = new Printer();
	}
	
	/**
	 * Play one game
	 */
	public void playGame() {
		deck = new Deck();
		player = new Player(deck.drawCards(7));
		computer = new Player(deck.drawCards(7));
		computer_handsize = computer.handSize();
		
		// Set Normal Card as the top of discard
		while(deck.getDeck().peek().getCard()[0].equals("none") 
				|| deck.getDeck().peek().getCard()[0].equals("+2")) {
			deck.Play(deck.Draw());
		}
		deck.Play(deck.Draw());
		
		// Game starts, take turns until one of them plays all the cards
		while(player.handSize() != 0 && computer.handSize() != 0) {
			System.out.println("------------------------------------------------------");
			System.out.println("                        Round " + turn_num);
			System.out.println("------------------------------------------------------");
			
			// Player's turn
			player_turn();
			
			// Computer's turn if player still has card(s)
			if(player.handSize() != 0) {
				System.out.println("------------------------------------------------------");
				comp_Turn();
				turn_num += 1;
			}
		}
		
		if(player.handSize() == 0) {
			printer.Win();
		} else {
			printer.Lose();
		}
	}
	
	private void player_turn() {
		printer.Hand(player.getHand());
		printer.Top(deck.getDiscard().peek());
		
		// if there is a card to play
		if()
	}
	
	/**
	 * Check if player has at least one card to play
	 */
	private boolean playable(Card top, Player player) {
		for(Map.Entry<String, Card> entry : player.getHand().entrySet()) {
			Card card = entry.getValue();
			if(card.playable(top)) {
				return true;
			}
		}
		return false;
	}
	
	
}
