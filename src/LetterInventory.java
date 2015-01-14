/*
 * Author: Vincent Jonany
 * Date: 1/10/15
 * Versions : 1.0
 * Section: CS143 AJ 
 * Description: Class LetterInventory can be used to store inventory of the 26 letters.
 * 				It can also be used to compare letter inventory with other letters inventory.
 * 				It can also generate the percentage of letters in inventory.
 */
public class LetterInventory {
	
	public static final int NUM_OF_LETTERS = 26;
	
	//The decimal value of the alphabet 'a'.
	public static final int LETTER_A_SML_VAL = 97;

	private int[] inventory;
	private int totalCount;
	
	/*
	 * Constructs a letter inventory object based on the string passed in.
	 * The constructor will ignore any non-alphabetic character in the string.
	 * pre: parameter must be a String. 
	 * post: letter inventory object will be populated with letter counts based
	 * 		 on the string parameter passed in. 
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
	 * Constructs an empty letter inventory object.
	 * post: the second constructor constructs a letter inventory, and initialize
	 * 		 total count of the inventory.
	 */
	public LetterInventory() {
		// calls the first constructor with empty string passed in.
		this("");
	}
	
	/*
	 * A method that convert a letter to a value starting from zero. Ex: 'a' -> 0.
	 * pre: the parameter passed in must be a character.
	 * post: returns a converted value of the letter passed in.
	 */
	private int countLetterVal(char letter) {
		return ((int) Character.toLowerCase(letter)) - LETTER_A_SML_VAL;
	}
	
	/*
	 * A method to check if the letter passed in is alphabetic.
	 * pre: throws IllegalArgumentException if non-alphabetic character is passed.
	 */
	private void isLetter(char letter) {
		if(!Character.isLetter(letter)) {
			throw new IllegalArgumentException("non-alphabetic character is passed");
		}
	}
	
	/*
	 * A method to get the count of a letter in the inventory.
	 * pre: throws IllegalArgumentException if non-alphabetic character is passed.
	 * post: returns back the count of that letter in the inventory.
	 */
	public int get(char letter) {
		isLetter(letter);
		return inventory[countLetterVal(letter)];
	}
	
	/*
	 * A method to set the count of the letter by the value passed in as parameter.
	 * The total count of the inventory will be calculated as value passed in.
	 * pre: throws IllegalArgumentException if non-alphabetic character or negative values
	 * 		are passed.
	 * post: The count of the letter will be reset to the value passed in as the parameter.
	 * 		 The total count will be calculated as the value is set to the letter.
	 */
	public void set(char letter, int value) {
		isLetter(letter);
		if(value < 0) {
			throw new IllegalArgumentException("value is negative");
		}
		
		totalCount -= get(letter);
		inventory[countLetterVal(letter)] = value;
		totalCount += value;
	}
	
	/*
	 * A method to return the size of the inventory.
	 * post: returns the total count of letters in the inventory.
	 */
	public int size() {
		return totalCount;
	}
	
	/*
	 * Method to check if inventory is empty.
	 * post: returns true if inventory is empty, or otherwise. 
	 */
	public boolean isEmpty() {
		return totalCount == 0;
	}
	
	/*
	 * Returns a string as a representation of the inventory. All Strings will all 
	 * be sorted and in lower case. 
	 * post: returns a string to represent the letter inventory.
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
	 * A method that gives the result of the sum of this and other letter inventory objects.
	 * It takes in another letter inventory object to be added together. 
	 * pre:  a valid LetterInventory object must be passed in. 
	 * post: returns a new letter Inventory object which is populated with the
	 * 	     sum of the counts of the two letter inventory objects for each letters.
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
	 * A method that gives the result of the difference of this and other letter inventory objects.
	 * It takes in another letter inventory object to be subtracted.
	 * pre:  a valid LetterInventory object must be passed in. 
	 * post: returns a new letter Inventory object which is populated with the
	 * 	     difference of the counts of the two letter inventory objects. 
	 * 		 It will return null if any of the difference in counts is negative. 
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
	 * A method that gives the percentage of a letter in the inventory.
	 * It takes in a letter as a parameter to get the percentage of that letter.
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