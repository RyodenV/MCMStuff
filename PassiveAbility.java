import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PassiveAbility implements Serializable {

	
	//Target Options
	final static String targetOptions[] = {"Creature", "Artifact", "Enchantement", "Land", "Planeswalker", "Instant", "Sorcery", "Permanent", "Player"};
	final static String controllerOptions[] = {"Self", "Friendly", "Opponent", "All"};
	
	//Targets Chosen
	public ArrayList<Integer> targets = new ArrayList<Integer>();
	public int controller = 0;
	
	//Effect
	String effect = "";
	
	PassiveAbility(){
		targets.add(-1);
		controller = 0;
	}
	
	PassiveAbility(String ctrl, String eff){
		for(int i = 0; i < controllerOptions.length; i++) {
			if(controllerOptions[i].equals(ctrl)) {
				controller = i;
			}
		}
		effect = eff;
	}
	
	public void addTarget(String s1) {
		for(int i = 0; i < targetOptions.length; i++) {
			if(targetOptions[i].equals(s1)) {
				targets.add(i);
			}
		}
	}
}
