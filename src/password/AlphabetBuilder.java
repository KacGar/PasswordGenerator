package password;

import java.util.*;

/**
 * This class handles building list of character strings (called alphabet) by adding sets of characters like small letters, big letters, numbers or special characters by using object methods.
 *
 * Class follows build pattern. build method creates new {@link PasswordGenerator} object by passing alphabet and length of password (int value) in constructor parameters.
 */
public final class AlphabetBuilder {

    private static final String[] smallLetters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","w","v","x","y","z"};
    private static final String[] bigLetters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U","W","V","X","Y","Z"};
    private static final String[] numbers = {"0","1","2","3","4","5","6","7","8","9","0","1","2","3","4","5","6","7","8","9"};
    //most likely accepted everywhere, not every site/application allows parenthesis,colons, semicolons or arithmetic symbols
    private static final String[] specialChars = {"!","?","#","$","%","&","!","?","#","$","%","&","!","?","#","$","%","&","!","?","#","$","%","&"};
    private ArrayList<String> alphabet;
    private int passwordLength = 0;

    /**
     * Default constructor
     */
    public AlphabetBuilder(){
        alphabet = new ArrayList<>();
    }

    /**
     * Sets if small / big letters or both types should be added into alphabet list.
     *
     * index 0 -> adds only small letters
     * index 1 -> adds only big letters
     * index 2 -> adds small and big letters
     * @param type index number of selected comboBox
     * @return Reference to this class
     */
    public AlphabetBuilder letterSize(int type){
        switch (type){
            case 0 -> {
                addToAlphabet(smallLetters);
                addToAlphabet(bigLetters);
            }
            case 1 -> addToAlphabet(bigLetters);
            case 2 -> addToAlphabet(smallLetters);
        }
        return this;
    }

    /**
     * Sets if numbers / special characters or both should be added into alphabet list.
     *
     * index 0 -> does nothing
     * index 1 -> adds only numbers
     * index 2 -> adds only special characters
     * index 3 -> adds numbers and special characters
     * @param type index number of selected combo box
     * @return Reference to this class
     */
    public AlphabetBuilder addNumsAndSpecialChars(int type){
        switch (type){
            case 1 -> addToAlphabet(numbers);
            case 2 -> addToAlphabet(specialChars);
            case 3 -> {
                addToAlphabet(numbers);
                addToAlphabet(specialChars);
            }
        }
        return this;
    }

    /**
     * Sets length limit which new created password will have.
     * @param length int length limit of password
     * @return Reference to this class
     */
    public AlphabetBuilder passwordLength(int length){
        passwordLength = length;
        return this;
    }

    /**
     * Returns new object of {@link PasswordGenerator} class with set alphabet and password length.
     * @return <code>PasswordGenerator</code> object
     */
    public PasswordGenerator build(){
        return new PasswordGenerator(alphabet, passwordLength);
    }

    /**
     * Add characters to an alphabet list from given array string.
     *
     * Before characters will be added, array is shuffled.
     * @param array the array of string characters
     */
    private void addToAlphabet(String[] array){
        List<String> chars = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(chars, new Random((int)(Math.random()*10)));
        this.alphabet.addAll(chars);
    }


}
