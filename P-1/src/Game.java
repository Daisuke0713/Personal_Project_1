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
	private Printer printer;
	private int draw_count = 0; 
	
	public Game() {
		printer = new Printer();
	}
	
	/**
	 * Repeat games
	 */
	public void playGames() {
		String proceed = "";
		printer.INTRO();
		
		while(!proceed.equals("n")) {
			playGame();
			turn_num = 1;
			System.out.print("Play again? - Y/n ");
			while(!proceed.equals("Y") && !proceed.equals("n")) {
				proceed = input.nextLine();
				if(!proceed.equals("Y") && !proceed.equals("n")) {
					System.out.print("Invalid input - enter Y/n ");
				}
			}
	
		}
		
		System.out.println("THANK YOU FOR PLAYING!");
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
		while(deck.getDeck().peek().getCard()[0].equals("wild") 
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
				comp_turn();
				turn_num += 1;
			}
		}
		
		if(player.handSize() == 0) {
			printer.WIN();
		} else {
			printer.LOSE();
		}
	}
	
	/**
	 * Simulate Player's turn
	 */
	private void player_turn() {
		printer.HAND(player.getHand());
		Card top = deck.getDiscard().peek();
		printer.TOP(top);
		
		// if there is a card to play
		if(playable(top, player)) {
			// I can add a loop here until Player inputs a valid indicator
			System.out.print("Pick a card to play: ");
			Card card = null;
			while(card == null) {
				card = check_valid(top);
			}
				
			if(card.getCard()[0].equals("wild")) {		
				System.out.print("Choose a color: ");	
				String color = "";	
				while(!color.equals("red") && !color.equals("blue") 
						&& !color.equals("green") && !color.equals("yellow")) {
					color = input.nextLine();
					if(!color.equals("red") && !color.equals("blue") 
						&& !color.equals("green") && !color.equals("yellow")) {
						System.out.print("Invalid color - choose again: ");
					}
				}
				card.setColor(color);
			} else if (card.getCard()[0].equals("+2")) {
				draw_count += 2;
			}
			
			deck.Play(card);
			player.play(card);
			
			if(player.handSize() == 1) {
				printer.UNO();
			}
			
		} else { // Player had no cards to play
			System.out.println("No cards to play");
			
			if(top.getCard()[0].equals("+2")) {
				System.out.println("You draw " + draw_count +  " cards");
				player.drawCards(deck.drawCards(draw_count));
				draw_count = 0;
				if(turn_num != 1) { // redraw the top from deck
					while(top.getCard()[0].equals("wild") 
							|| top.getCard()[0].equals("+2")) {
						deck.Play(deck.Draw());
						top = deck.getDiscard().peek();
					}
					deck.Play(deck.Draw());
				}
			} else {
				System.out.println("You draw one card");
				player.drawCard(deck.Draw());
			}
		}
		System.out.print("Press \"return\" to proceed");
		String next = input.nextLine();
	}
	
	/**
	 * check if Player gives a valid input
	 */
	private Card check_valid(Card top) {
		String indicator = input.nextLine();
		Card card = null;
		
		if(player.match(indicator)) {
			card = player.getHand().get(indicator);
			if(!playableCards(top, player.getHand()).contains(card)) {
				System.out.println("Invalid indicator - card cannot be played");
				card = null;
			}
		} else {
			System.out.println("Invalid indicator - chose again");
		}	
		return card;
	}
	
	/**
	 * Simulate Computer's turn
	 */
	private void comp_turn() {
		printer.C_HAND(computer_handsize);
		Card top = deck.getDiscard().peek();
		printer.TOP(top);
		
		if(playable(top, computer)) {
			Card card = playableCards(top, computer.getHand()).get(0);
			
			if(card.getCard()[0].equals("wild")) {
				String color = colorAutoSelect(card);
				System.out.println("Computer plays [" + card + "] card - the new color is " + color);
			} else if (card.getCard()[0].equals("+2")){
				draw_count += 2;
				System.out.println("Computer plays [" + card + "] card");
			} else {
				System.out.println("Computer plays [" + card + "] card");
			}
			deck.Play(card);
			computer.play(card);
			computer_handsize -= 1;
		} else {
			if(top.getCard()[0].equals("+2")) {
				System.out.println("Computer draws " + draw_count + " cards");
				computer.drawCards(deck.drawCards(draw_count));
				computer_handsize += computer_handsize;
				draw_count = 0;
				while(top.getCard()[0].equals("wild") 
						|| top.getCard()[0].equals("+2")) {
					deck.Play(deck.Draw());
					top = deck.getDiscard().peek();
				}
				deck.Play(deck.Draw());
			} else {
				System.out.println("Computer draws one card");
				computer.drawCard(deck.Draw());
				computer_handsize += 1;
			}
		}
		System.out.print("Press \"return\" to proceed");
		String next = input.nextLine();
	}
	
	/**
	 * Return a list of cards that can be played - only
	 * called when there are such a list
	 */
	private ArrayList<Card> playableCards(Card top, Map<String, Card> hand) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for(Map.Entry<String, Card> entry : hand.entrySet()) {
			Card card = entry.getValue();
			if(card.playable(top)) {
				cards.add(card);
			}	
		}
		return cards;
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
	
	/**
	 * Change color randomly for computer
	 */
	private String colorAutoSelect(Card card) {
		int random = (int)Math.floor(Math.random() * 4);
		String color = "";
		if(random == 0) {
			color = "red";
		} else if(random == 1) {
			color = "blue";
		} else if(random == 1) {
			color = "green";
		} else {
			color = "yellow";
		}
		
		card.setColor(color);
		return color;
	}
}
