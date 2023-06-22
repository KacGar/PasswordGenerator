package password;

import GUI.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

/**
 * This class generates password from given <code>List</code> of character strings in constructor.
 *
 * Length of password is provided in constructor upon object creation. Every time new object is created, it is guaranteed that generated password will be unique.
 * Everytime when new password is created, it is digested through SHA-256 algorithm and result in hex format is saved in a file,
 * password itself is displayed in a dialog box with editable <code>JTextField</code>.
 *
 * Before hashcode will be saved it is compared to every hashcode saved in a file to ensure uniqueness.
 */
public final class PasswordGenerator {

    private List<String> chars;
    private StringBuilder password;
    private int passwordLength;
    private static final ResourceBundle language = MainFrame.getLanguage();

    /**
     * Initializes alphabet used in password creation and length sets a limit character
     * @param alphabet List of characters strings
     * @param length Password length
     */
    public PasswordGenerator(List<String> alphabet, int length){
        Collections.shuffle(alphabet);
        chars = new ArrayList<>(alphabet);
        password = new StringBuilder();
        passwordLength = length;
        createPassword(chars);
    }

    /**
     * Creates password from a list of character strings passed as an argument.
     * @param chars List of characters strings
     */
    private void createPassword(List<String> chars){
        int index = passwordLength;
        int min = 1;
        int max = chars.size();
        while (index != 0){
            int randomIndex = (int)((Math.random() * (max - min)) + min);
            if (randomIndex >= 0 && randomIndex <= chars.size()){
                this.password.append(chars.get(randomIndex));
                index--;
            }
        }
        boolean passwordExists = exists(this.password.toString());
        while (passwordExists){
            Collections.shuffle(chars,new Random(3));
            createPassword(chars);
            passwordExists = exists(this.password.toString());
        }
    }

    /**
     * Checks whether password already was created in the past.
     *
     * Method checks if provided password was created by comparing hashcodes from a file.
     * At the same time if password was not found, it is written into a file.
     * @param password Password string
     * @return Returns true if password hashcode exists in a file, otherwise false
     */
    private static boolean exists(String password){
        if (password.length() < 1){
            return false;
        }

        String hashed = bytesToHex(
                hashPassword(password)
        );
        Path pathToFile = Paths.get("src/password/psswds.txt");

        if (Files.notExists(pathToFile)){
            try{
                Files.createFile(pathToFile);
                var bw = Files.newBufferedWriter(pathToFile);
                bw.write(hashed);
                bw.flush();
                bw.close();
                return false;
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null,language.getString("writeError"));
            }
        }
        if (Files.exists(pathToFile)){
            try {
                var lines = Files.readAllLines(pathToFile);
                int index = lines.size() - 1;
                while (index != 0){
                    if (lines.get(index).equals(hashed)) return true; //founded
                    index--;
                }
                //not found
                Files.write(
                        pathToFile,
                        hashed.getBytes(),
                        StandardOpenOption.APPEND
                );
                return false;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,language.getString("writeError"));
            }
        }
        return false;
    }

    /**
     * Hashes password by using {@link MessageDigest} object with a SHA-256 algorithm.
     * @param password Password string upon which cryptography will be performed
     * @return the array of bytes for resulting hash values (which is a result of digest() method)
     */
    private static byte[] hashPassword(String password){
        byte[] result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] input = password.getBytes(StandardCharsets.UTF_8);
            result = messageDigest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null,language.getString("hashingError"));
        }
        return result;
    }

    /**
     * Converts given array of bytes (result of digesting) into a hex values and returns string text with fixed length everytime.
     * @param hash the array of bytes for resulting hash values (which is a result of digest() method)
     * @return String with hex values (fixed length)
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        hexString.append("\r\n");
        return hexString.toString();
    }

    /**
     * Gets text of generated password
     * @return String of created password
     */
    public String getPassword(){
        chars.clear();
        return this.password.toString();
    }
}
