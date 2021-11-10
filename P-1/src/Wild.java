/**
 * Subclass of Card class
 */

public class Wild extends Card{
	
	/**
	 * NoralCard constructor
	 */
	public Wild(String s, String c) {
		super(s, c);
	}
	
	@Override
	public boolean playable(Card card) {
		if(!card.getCard()[0].equals("+2")) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setColor(String new_color) {
		this.color = new_color;
	}
}
