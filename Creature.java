import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Creature extends Card implements Serializable{
	
	//Creature Types
	ArrayList<String> cType = new ArrayList<String>();
	
	//Perm and temp Pow+Tgh. Current HP and +1/+1 counters 
	//Note: Temp Pow/Tgh, current HP, and one one counters don't get used except in the game, but they are added here in case we ever develope a functioning version of the game based on this code
	int power, toughness, tempP, tempT, hp, oneones;
	
	//Keywords
	static final String[] keywords = {"Vigilance","Haste", "LifeLink", "First Strike", "Double Strike", "Hexproof", "Flying", "Reach", "Deathtouch", "Unblockable", "Menace"};
	ArrayList<Integer> activeKeywords = new ArrayList<Integer>(); 
	
	//Auras/Equipment
	
	Creature(){
		super();
		power = 0;
		toughness = 0;
		tempP = 0;
		tempT = 0;
		hp = 0;
		oneones = 0;
	}
	
	Creature(String nm, int[] cst, String type, String flavTxt, boolean tap, ImageIcon image, int pow, int tgh, int oo){
		super(nm, cst, "Creature", flavTxt, tap, image);
		cType.add(type);
		power = pow;
		toughness = tgh;
		tempP = 0;
		tempT = 0;
		hp = tgh;
		oneones = oo;
	}
	
	public boolean takeDamage(int dmg) {
		//take damage
		hp -= dmg;
		
		//return if creature is alive
		return hp>0;
	}
	
	public void healDamage() {
		//reset to max health
		hp = toughness + oneones;
	}
	
	//Add/Remove Aura+Enchment
	
	//Add/Remove one ones
	public void addOneOnes(int counters) {
		oneones += counters;
	}
	
	public void removeOneOnes(int counters) {
		oneones -= counters;
	}
	
	//Add Temp P/T
	public void addTempPT(int pow, int tgh) {
		tempP += pow;
		tempT += tgh;
		hp += tgh;
	}
	
	//Clear Temp P/T
	public void clearTempPT() {
		tempP = 0;
		tempT = 0;
		healDamage();
	}
	
	//Add creature type
	public void addType(String type) {
		cType.add(type);
	}
	
	//Remove creature type
	public void removeType(String type) {
		cType.remove(type);
	}
	
	//Add keyword
	public void addKeyword(int keyword) {
		activeKeywords.add(keyword);
	}
	
	//Remove keyword
	public void removeKeyword(String keyword) {
		for(int i = 0; i<keywords.length; i++) {
			if(keywords[i].equals(keyword) && activeKeywords.contains(i)) {
				activeKeywords.remove(i);
			}
		}
	}
	
	public String toString() {
		String output = this.name + " is a ";
		String cost = "";
		String kwrds = "";
		
		output += this.power + "/" + this.toughness + " ";
		
		for(int i = 0; i < this.colour.length; i++) {
			if(this.colour[i]) {
				output += this.colours[i] + ", ";
			}
			if(this.cost[i] > 0) {
				cost += this.cost[i] + " " + this.colours[i] + ", ";
			}
		}
		
		for(int i = 0; i < this.types.size(); i++) {
			output += this.cType.get(i) + ", ";
		}
		
		output += "that costs " + cost;
		output = output.substring(0, output.length()-2);
		output += ". It has: ";
		
		
		for(int i = 0; i < this.activeKeywords.size(); i++) {
			if(!kwrds.contains(Creature.keywords[this.activeKeywords.get(i)])) {
				kwrds += Creature.keywords[this.activeKeywords.get(i)] + ", ";
			}
		}
		if(kwrds.length() > 3) {
			kwrds = kwrds.substring(0, kwrds.length()-2);
			output += kwrds;
		} else {
			output = output.substring(0, output.length()-9);
		}
		
		
		return output;
	}
}
