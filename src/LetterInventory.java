
public class LetterInventory {
	
	public static final int NUM_OF_LETTERS = 26;
	public static final int LETTER_A_SML_VAL = 97;

	private int[] inventory;
	private int totalCount;
	
	public LetterInventory() {
		inventory = new int[NUM_OF_LETTERS];
		totalCount = 0;
	}
	
	
	public LetterInventory(String data) {
		this();
		for(int i = 0; i < data.length(); i++) {
			if(Character.isLetter(data.charAt(i)))
			{
				inventory[countLetterVal((data.charAt(i)))]++;
				totalCount++;
			}
		}
		
	}
	
	private int countLetterVal(char letter) {

		return ((int) Character.toLowerCase(letter)) - LETTER_A_SML_VAL;
	}
	
	private void isLetter(char letter) {
		if(!Character.isLetter(letter)) {
			throw new IllegalArgumentException("non-alphabetic character is passed");
		}
		
		
	}
	
	public int get(char letter) {
		isLetter(letter);
		return inventory[countLetterVal(letter)];
	}
	
	public void set(char letter, int value) {
		int count = 0;
		isLetter(letter);
		if(value < 0) {
			throw new IllegalArgumentException("value is negative");
		}
		
		if(value == 0) {
			count = -(inventory[countLetterVal(letter)]);
			totalCount += count;
		}
		else {
			totalCount += value;
		}

		inventory[countLetterVal(letter)] = value;
		
		
	}
	 
	public int size() {
		return totalCount;
	}
	
	public boolean isEmpty() {
		return totalCount == 0;
	}
	
	public String toString() {
		String allInventories = "";
		for(int i = 0 ; i < inventory.length; i++) {
			for(int j = 0; j < inventory[i]; j++) {
				allInventories += (char) (i + LETTER_A_SML_VAL);
			}
		}
		return "[" + allInventories + "]";
	}
	
	public LetterInventory add(LetterInventory other) {
		LetterInventory sumInventory = new LetterInventory();
		
		for(int i = 0; i < NUM_OF_LETTERS; i++) {
			sumInventory.set((char) (i + LETTER_A_SML_VAL), 
					(this.inventory[i] + other.inventory[i]));
		}
		
		return sumInventory;
	}
	
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

	public double getLetterPercentage(char letter) {
		isLetter(letter);
		double count = get(letter);
		if(count == 0.0) {
			return 0.0;
		} else {
			return (count / totalCount) * 100; 
		}
	}
	
	public static void main(String[] args) {
		
		LetterInventory k = new LetterInventory("abcbabcbabcbabacbabacbabc");
		System.out.println(k);
		System.out.println(k.getLetterPercentage('c'));
		
	}

}