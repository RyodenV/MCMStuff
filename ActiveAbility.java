import java.util.ArrayList;

public class ActiveAbility {
	//Ablility Cost
	int[] cost = new int[6];
	boolean doesTap = false;
	
	//Target Options
	final static String targetOptions[] = {"Creature", "Artifact", "Enchantement", "Land", "Planeswalker", "Instant", "Sorcery", "Permanent", "Player"};
	final static String controllerOptions[] = {"Self", "Friendly", "Opponent", "All"};
	
	//Targets Chosen
	public ArrayList<Integer> targets = new ArrayList<Integer>();
	public int controller = 0;
	
	//Effect
	String effect = "";
	
	ActiveAbility(){
		for(int i = 0; i < cost.length; i++) {
			cost[i] = 0;
		}
		targets.add(-1);
		controller = 0;
	}
	
	ActiveAbility(int[] c, boolean tap, String ctrl, String eff){
		cost = c;
		doesTap = tap;
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
