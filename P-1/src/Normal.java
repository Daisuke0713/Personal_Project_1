/**
 * Subclass of Card class
 */

/**
 * Note: extend vs implement
 * 
 * 		extends - you are creating a subclass of the super class you are extending
 *		implements - you are using the elements of a Java Interface in your class
 */
public class Normal extends Card{
	
	/**
	 * NoralCard constructor
	 */
	public Normal(String s, String c) {
		super(s, c);
	}
	
	@Override
	public boolean playable(Card card) {
		if(card.getCard()[0].equals("+2") 
				|| (!card.getCard()[0].equals(number) && !card.getCard()[1].equals(color))) {
			return false;
		}
		return true;
	}
	
}
