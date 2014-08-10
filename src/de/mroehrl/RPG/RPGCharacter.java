package de.mroehrl.RPG;

public class RPGCharacter {
	private String name;
	private int copperPennies = 0;
			
	public RPGCharacter() {
		this("none");
	}
	
	public RPGCharacter(String aName) {
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
