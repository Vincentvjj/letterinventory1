/*
 * Author: Vincent Jonany
 * Date: 1/10/15
 * Versions : 1.0
 * Section: CS 143AJ
 * Description: Class LetterInventory can be used to store inventory of the 26 letters.
 * 				It can also be used to compare letters inventory with other letters inventory.
 * 				It can also generate the percentage of letters in inventory.
 */
public class LetterInventory {
	
	public static final int NUM_OF_LETTERS = 26;
	
	//The decimal value of the alphabet 'a'.
	public static final int LETTER_A_SML_VAL = 97;

	private int[] inventory;
	private int totalCount;
	
	/*
	 * pre: parameter must be a String. 
	 * post: constructs a letter inventory object on the String passed in as parameter.
	 * 		 The constructor will ignore any non-alphabetic character in the string.
	 */
	public LetterInventory(String data) {
		inventory = new int[NUM_OF_LETTERS];
		totalCount = 0;
		for(int i = 0; i < data.length(); i++) {
			if(Character.isLetter(data.charAt(i)))
			{
				inventory[countLetterVal((data.charAt(i)))]++;
				totalCount++;
			}
		}	
	}
	
	/*
	 * post: the second constructor constructs a letter inventory. 
	 */
	public LetterInventory() {
		// calls the first constructor with empty string passed in.
		this("");
	}
	
	/*
	 * pre: the parameter passed in must be a character.
	 * post: a private method to convert the letter passed in to a value that matches
	 * 		 with the array indexes values. For example, 'a' will become 0. 
	 */
	private int countLetterVal(char letter) {
		return ((int) Character.toLowerCase(letter)) - LETTER_A_SML_VAL;
	}
	
	/*
	 * pre: throws IllegalArgumentException if non-alphabetic character is passed.
	 * post: a private method to check if the letter passed in is alphabetic.
	 */
	private void isLetter(char letter) {
		if(!Character.isLetter(letter)) {
			throw new IllegalArgumentException("non-alphabetic character is passed");
		}
	}
	
	/*
	 * pre: throws IllegalArgumentException if non-alphabetic character is passed.
	 * post: returns back the number of that letter which is passed in as parameter in inventory.
	 */
	public int get(char letter) {
		isLetter(letter);
		return inventory[countLetterVal(letter)];
	}
	
	/*
	 * pre: throws IllegalArgumentException if non-alphabetic character or negative values
	 * 		are passed.
	 * post: sets the count of the letter for the value passed in. 
	 * 		 Total count of the letters are calculated. 
	 */
	public void set(char letter, int value) {
		int tempCount = 0; 
		int count = 0;
		isLetter(letter);
		if(value < 0) {
			throw new IllegalArgumentException("value is negative");
		}
		
		count =	get(letter);			
		inventory[countLetterVal(letter)] = value;
		
		// if the value is 0, it will need to subtract the previous value of that letter from 
		// the totalCount of the inventory.
		if(value == 0) {
			System.out.println("this is the count : " + count);
			totalCount -= count;
		}
		
		for(int counter : inventory) {
			tempCount += counter;
		}
		totalCount = tempCount;
	}
	
	/*
	 * post: returns the total count of letters in the inventory.
	 */
	public int size() {
		return totalCount;
	}
	
	/*
	 * post: returns true if inventory is empty, or otherwise. 
	 */
	public boolean isEmpty() {
		return totalCount == 0;
	}
	
	/*
	 * post: returns a string as a representation of the inventory, all sorted and lower case.
	 * 		 String returned will be surrounded by square brackets.
	 */
	public String toString() {
		String allInventories = "";
		for(int i = 0 ; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i]; j++) {
				allInventories += (char) (i + LETTER_A_SML_VAL);
			}
		}
		return "[" + allInventories + "]";
	}
	
	/*
	 * pre:  a valid LetterInventory object must be passed in. 
	 * post: returns a new LetterInventory object which is the result of the sum 
	 * 	  	 of two LetterInventory objects. The counts of each letters from both inventories
	 * 	     will be added together, and produced in the new LetterInventory object. 
	 */
	public LetterInventory add(LetterInventory other) {
		LetterInventory sumInventory = new LetterInventory();
		for(int i = 0; i < NUM_OF_LETTERS; i++) {
			sumInventory.set((char) (i + LETTER_A_SML_VAL), 
					(this.inventory[i] + other.inventory[i]));
		}
		return sumInventory;
	}
	
	/*
	 * pre:  a valid LetterInventory object must be passed in. 
	 * post: returns a new LetterInventory object which is the result of the difference 
	 * 	  	 of two LetterInventory objects. The counts of each letters from both inventories
	 * 	     will be subtracted, and produced in the new LetterInventory object. 
	 * 		 Returns null if any of the resulting subtracted value is negative.
	 */
	public LetterInventory subtract(LetterInventory other) {
		LetterInventory remainingInventory = new LetterInventory();
		int value = 0;
		for(int i = 0; i < NUM_OF_LETTERS; i++) {
			value = this.inventory[i] - other.inventory[i];
			if(value < 0) {
				return null;
			} else {
				remainingInventory.set((char) (i + LETTER_A_SML_VAL), value);
			}
		}
		return remainingInventory;
	}

	/*
	 * pre: throws IllegalArgumentException if non-alphabetic character is passed.
	 * post: returns a double type value of the percentage of that letter in the inventory.
	 */
	public double getLetterPercentage(char letter) {
		isLetter(letter);
		double count = get(letter);
		if(count == 0.0) {
			return 0.0;
		} else {
			return count / totalCount; 
		}
	}
}