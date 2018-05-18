
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Card implements Serializable{
	
	//Name
	String name;
	
	//Cost/Colour
	int[] cost = new int[6];
	
	
	static final String[] colours = new String[]{"Colourless", "White", "Blue", "Black", "Red", "Green"};
	boolean[] colour = new boolean[] {true, false, false, false, false, false};
	
	//Type (Creature/Land/Instant/Sorcery/Enchantment/Artifact)
	ArrayList<String> types = new ArrayList<String>();
	
	//Enters Tapped?
	boolean entersTapped = false;
	
	//Image
	ImageIcon pic;
	
	//Active Ability
	ArrayList<ActiveAbility> abilities = new ArrayList<ActiveAbility>();
	
	//Passives
	
	String flavourText;
	
	Card(){
		name = "No Name";
		for(int i = 0; i < cost.length; i++) {
			cost[i] = 0;
		}
		types.add("No Type");
		flavourText = "Flavour Text";
	}
	
	Card(String nm, int[] cst, String type, String flavTxt, boolean tap, ImageIcon image){
		name = nm;
		cost = cst;
		types.add(type);
		flavourText = flavTxt;
		entersTapped = tap;
		pic = image;
	}
	
	//Set Colours
	public void setColours() {
		for(int i = 1; i <= 5; i++) {
			if(cost[i] > 0) {
				addColour(i);
			}
		}
	}
	
	//Add Colour
	public void addColour(int i) {
		if(i > 0 && i <=5) {
			colour[i] = true;
			colour[0] = false;
		}
	}
	
	//Add/Remove Type
	public void addType(String t) {
		types.add(t);
	}
	
	public void removeType(String t) {
		if(types.contains(t)) {
			types.remove(t);
		}
	}
	
	//Add passive
}
