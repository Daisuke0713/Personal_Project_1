/**
 * Subclass of Card class
 */

public class Draw2 extends Card{
	
	/**
	 * NoralCard constructor
	 */
	public Draw2(String s, String c) {
		super(s, c);
	}
	
	@Override
	public boolean playable(Card card) {
		if(card.getCard()[0].equals(number) || card.getCard()[1].equals(color)) {
			return true;
		}
		return false;
	}
}
