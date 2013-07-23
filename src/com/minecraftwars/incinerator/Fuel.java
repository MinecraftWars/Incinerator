package com.minecraftwars.incinerator;

public class Fuel {
	private int fuelId;
	private int value;
	
	public Fuel(int fuelId, int value)
	{
		this.fuelId = fuelId;
		this.value = value;
	}
	
	public int getFuelId()
	{
		return this.fuelId;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
