package de.mroehrl.RPG;

public class Character {
	private String name;
	private int copperPennies = 0;
			
	public Character() {
		this("none");
	}
	
	public Character(String aName) {
		name = aName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getCopperPennies() {
		return copperPennies;
	}

	public void setCopperPennies(int copperPennies) {
		this.copperPennies = copperPennies;
	}
}
