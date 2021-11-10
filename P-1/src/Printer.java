import java.util.*;

/**
 * Printer Class
 */
public class Printer {
	
	private static Scanner input = new Scanner(System.in);
	
	public Printer() {}
	
	/**
	 * Display INTRO
	 */
	public void INTRO() {
		System.out.print("Welcome to UNO! Enter 1 to play a game, 2 to read the instructions: ");
	    String choise = "";

	    while(!choise.equals("1") && !choise.equals("2")) {
	    	choise = input.nextLine();
	    	if(!choise.equals("1") && !choise.equals("2")) {
	    		System.out.print("Enter 1 or 2: ");
	    	}
	    	if (choise.equals("2")) {
	    		System.out.println();
	    		System.out.println();
	    		System.out.println("+------------------------Rules-----------------------+");
	    		System.out.println("| (1) You play against computer.                     |");
	        	System.out.println("| (2) There are three kinds of cards: 24 Normal      |");
	        	System.out.println("|     cards, 8 Draw+2 cards, and 4 Wild cards.       |");
	        	System.out.println("| (3) You can play a Draw+2 card if the top card is  |");
	        	System.out.println("|     Draw+2 card, but the number of cards to be     |");
	        	System.out.println("|     drawn will not accumulate. (Not become +4, +6) |");
	        	System.out.println("| (4) If you (or computer) play a Draw+2 card and    |");
	        	System.out.println("|     the opponent passes turn, the top card (still  |");
	        	System.out.println("|     be Draw+2 card you played) will be changed     |");
	        	System.out.println("| (5) If the \"first\" new top card is Wild card or    |");
	        	System.out.println("|     Draw+2 card, a new card is drawn from the deck |");
	        	System.out.println("|     as a new top card.(Unless you/computer play it)|");
	        	System.out.println("+----------------------------------------------------+");
	    	}
	    }
	    
	    System.out.println(); System.out.println();
	    System.out.print("Press \"return\" to proceed");  
	    String next = input.nextLine();
	    System.out.println(); System.out.println();
	}
	
	/**
	 * Display Player's hand
	 */
	public void HAND(Map<String, Card> hand) {
		
	    int number_Of_Maps = (int)(hand.size()-1)/8 + 1;
	    Map<String, Card> map1 = new HashMap<String, Card>();
	    Map<String, Card> map2 = new HashMap<String, Card>(); //may not be used
	    Map<String, Card> map3 = new HashMap<String, Card>(); //may not be used
	    Map<String, Card> map4 = new HashMap<String, Card>(); //may not be used

	    //move 0 ~ 7 elements to map1
	    int count = 0;
	    for(Map.Entry<String, Card> entry: hand.entrySet()) {
	    	String k = entry.getKey();
	    	Card c = entry.getValue();
	    	map1.put(k, c);
	    	count++;
	    	if(count == 8) {
	    		break;
	    	}
	    }
	    count = 0;

	    //move 8 ~ 15 elements to map2
	    for(Map.Entry<String, Card> entry: hand.entrySet()) {
	    	if(count == 16) {
	    		break;
	    	}
	    	if(7 < count) {
	    		String k = entry.getKey();
	    		Card c = entry.getValue();
	    		map2.put(k, c);
	    		count++;
	    	} else {
	        count++;
	    	}
	    }
	    count = 0;

	    //move 16 ~ 23 elements to map3
	    for(Map.Entry<String, Card> entry: hand.entrySet()) {
	    	if(count == 24) {
	    		break;
	    	}
	    	if(15 < count) {
	    		String k = entry.getKey();
	    		Card c = entry.getValue();
	    		map3.put(k, c);
	    		count++;
	    	} else {
	    		count++;
	    	}
	    }
	    count = 0;

	    //move 24 ~ 31 elements to map4
	    for(Map.Entry<String, Card> entry: hand.entrySet()) {
	    	if(count == 32) {
	    		break;
	    	}
	    	if(23 < count) {
	    		String k = entry.getKey();
	    		Card c = entry.getValue();
	    		map4.put(k, c);
	    		count++;
	    	} else {
	    		count++;
	    	}
	    }

	    System.out.println();
	    System.out.println("                     --Your turn--");
	    System.out.println();
	    HAND_Helper(map1);
	    
	    if(number_Of_Maps == 2) {
	    	HAND_Helper(map2);
	    } 
	    if(number_Of_Maps == 3) {
	    	HAND_Helper(map2);
	    	HAND_Helper(map3);
	    } 
	    if(number_Of_Maps == 4) {
	    	HAND_Helper(map2);
	    	HAND_Helper(map3);
	    	HAND_Helper(map4); 
	    }
	    System.out.println();
	  }

	  /**
	  * Helper function for HAND()
	  */
	  private void HAND_Helper(Map<String, Card> hand) {
	    String[] indicators = new String[hand.size()];
	    Card[] cards = new Card[hand.size()];
	    int pos = 0;
	    for(Map.Entry<String, Card> entry: hand.entrySet()) {
	    	indicators[pos] = entry.getKey();
	    	cards[pos] = entry.getValue();
	    	pos++;
	    }

	    for(int i=0; i<hand.size(); i++) {
	    	System.out.print(" +---+ ");
	    }
	    System.out.print("\n");
	    for(int i=0; i<hand.size(); i++) {
	    	if(cards[i].getCard()[0].equals("wild")) {
	    		System.out.print(" |   | ");
	    	} else {
	    		System.out.print(" | " + cards[i].getCard()[1].substring(0, 1) + " | ");
	    	}
	    }
	    System.out.print("\n");
	    for(int i=0; i<hand.size(); i++) {
	    	if(cards[i].getCard()[0].equals("+2")) {
	    		System.out.print(" |" + cards[i].getCard()[0].substring(0, 2) + " | ");
	    	} else {
	    		System.out.print(" | " + cards[i].getCard()[0].substring(0, 1) + " | ");
	    	}
	    }
	    System.out.print("\n");
	    for(int i=0; i<hand.size(); i++) {
	    	System.out.print(" +---+ ");
	    }
	    	System.out.print("\n");
	    for(int i=0; i<hand.size(); i++) {
	    	System.out.print("  (" + indicators[i] + ")  ");
	    }
	    System.out.print("\n");
	  }
	  
	  /**
	   * Display the top
	   */
	  public void TOP(Card card) {
		  System.out.print("                      +---+ ");
		  System.out.print("\n");
		    
		  if(card.getCard()[1].equals("none")) {
		      System.out.print("                      |   | ");
		  } else {
		      System.out.print("                      | " + card.getCard()[1].substring(0, 1) + " | ");
		  }
		  
		  System.out.print("\n");
		  if(card.getCard()[0].equals("+2")) {
		        System.out.print("                      |" + card.getCard()[0].substring(0, 2) + " | ");
		  } else {
		        System.out.print("                      | " + card.getCard()[0].substring(0, 1) + " | ");
		  }
		  System.out.print("\n");
		  System.out.print("                      +---+ ");
		  System.out.print("\n");
		  System.out.print("                      (Top)  ");
		  System.out.print("\n");
		  System.out.println();
	  }
	  
	  /**
	   * Helper function for C_HAND
	   */
	   private void Helper_C_HAND(int number) {
	     for(int i=0; i<number; i++) {
	    	 System.out.print(" +---+ ");
	     }
	     System.out.print("\n");
	     for(int i=0; i<number; i++) {
	    	 System.out.print(" |   | ");
	     }
	     System.out.print("\n");
	     for(int i=0; i<number; i++) {
	    	 System.out.print(" |   | ");
	     }
	     System.out.print("\n");
	     for(int i=0; i<number; i++) {
	    	 System.out.print(" +---+ ");
	     }
	     System.out.print("\n");
	   }

	   /**
	   * Display Computer's hand
	   */
	   public void C_HAND(int number) {
	     int row = (int)(number-1)/8 + 1;
	     
	     System.out.println();
	     System.out.println("                 --Computer's turn--");
	     System.out.println();
	     
	     if(row == 1) {
	    	 Helper_C_HAND(number);
	     } else if(row == 2) {
	    	 Helper_C_HAND(8);
	    	 Helper_C_HAND(number-8);
	     } else if(row == 3) {
	    	 Helper_C_HAND(8);
	    	 Helper_C_HAND(8);
	    	 Helper_C_HAND(number-16);
	     }
	     System.out.println();
	   }
	   
	   /**
	    * Display "UNO"!!!
	    */
	    public void UNO(){
	      System.out.println();
	      System.out.println("           $$\\   $$\\  $$\\   $$\\   $$$$$\\");
	      System.out.println("           $$ |  $$ | $$$\\  $$ | $$\\ __$$\\");
	      System.out.println("           $$ |  $$ | $$$$\\ $$ | $$ /  $$ |");   
	      System.out.println("           $$ |  $$ | $$ $$\\$$ | $$ |  $$ |");   
	      System.out.println("           $$ |  $$ | $$ \\$$$$ | $$ |  $$ |");   
	      System.out.println("           $$ |  $$ | $$ |\\$$$ | $$ |  $$ |");
	      System.out.println("           \\$$$$$$\\ | $$ | \\$$ |  $$$$$$\\ |"); 
	      System.out.println("            \\______/  \\__|  \\__|   \\_____/");    
	      System.out.println();
	    }

	    /**
	    * Display "win!"
	    */
	    public void WIN() {
	      System.out.println("******************************************************");
	      System.out.println("                                     .--.             ");
	      System.out.println("                       ,--.          |  |             ");
	      System.out.println("           .--.   .--. `--'  .----.  |  |             ");
	      System.out.println("           |  |.'.|  | .--. |  ..  | `--'             ");
	      System.out.println("           |   .'.   | |  | |  ||  | .--.             ");
	      System.out.println("           '--'   '--' '--' '--''--' `--'             ");
	      System.out.println("******************************************************");
	    }

	    /**
	    * Display "lose..."
	    */
	    public void LOSE() {
	      System.out.println("******************************************************");
	      System.out.println("           .--.                                       ");
	      System.out.println("           |  |  .---.   ,---.   .---.                 ");
	      System.out.println("           |  | | .-. | (  .-'  |  _. )                ");
	      System.out.println("           |  | ' '-' ' .-'  `) \\ '--.  .-. .-.        ");
	      System.out.println("           '--'  `---'  `----'   `----' '-' '-'        ");
	      System.out.println("******************************************************");
	    }
}
