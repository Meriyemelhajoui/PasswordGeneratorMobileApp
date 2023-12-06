package com.GI3GL.mypasswordgenerator.models;

import static com.GI3GL.mypasswordgenerator.models.SpecialCharGenerator.SPECIAL_CHAR_ARRAY;

import java.util.ArrayList;

public abstract class PasswordGenerator {
    private static ArrayList<PasswordGenerator> generators;


    // This method is used to clear the arraylist is clear if we want to generate a new password
    // and if the list is already null , we Instantiate it
    public static void clear(){
        if(generators != null) generators.clear();
        else generators = new ArrayList<>();
    }

    public static boolean isEmpty(){
        return generators.isEmpty();
    }


    // this method add the Choice of the user to the arraylist
    public static void add(PasswordGenerator pwdg){
        generators.add(pwdg);
    }


    // this method is abstract that s mean that on every type of generator will be handled (Lowercase / NumericValue / ... )
    public abstract String getChar();

    private static PasswordGenerator getRandomPassGen(){
        // When this method is called for the first time , we initialize the list with an empty list , an we add the LowercaseGenerator
        // so by default when a user enter to the app for the first time , we have the Lowercase generator that is added to the list

        if(generators == null) {
            generators = new ArrayList<>();
            add(new LowerCaseGenerator());
        }

        // check if the user didnt add a another Generator we return it (Lowercase)
        if(generators.size() == 1) return generators.get(0);
        // The else , that s mean that the user added a new types of generators: Lowercase and Numeric
        // Each time , there is a random call to an index , that s mean for a first time
        // we gonna get the LowerCase character , and second time LowerCase Character , and third time Numeric Value ... in a random way

        int randomIndex = Helper.randomVal(generators.size());
        return generators.get(randomIndex);
    }

    public static String generatePassword(int sizeOfPassword){
        // This method is responsable for generating password , we use the String Builder because its mutable and
        // Thread safe , so each time we add a character to the password
        StringBuilder password = new StringBuilder();

        // Until the size of the password is not equal to 0 , we do generate a character based on a Random index , and we return it s value as a char
        while (sizeOfPassword !=0){
            password.append(getRandomPassGen().getChar());
            sizeOfPassword--; // and we decrement the size of it
        }

        return password.toString(); // and finally we return his value as a String

    }



    public static String checkPasswordStrength(String password) {
        int score = 0;

        // Check length
        int length = password.length();

        if (length >= 8 && length <= 12) {
            score++;
        }

        if (length >12 && length <= 15) {
            score++;
        }


        // Check for uppercase and lowercase characters
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());

        if (hasUpperCase && hasLowerCase) {
            score++;
        }

        // Check for special characters
        if (containsSpecialCharacter(password)) {
            score++;
        }

        // Determine strength based on score
        if (score >= 3) {
            return "Strong";
        } else if (score >= 2) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }

    public static boolean containsSpecialCharacter(String password) {
        for (char ch : password.toCharArray()) {
            for (char specialChar : SPECIAL_CHAR_ARRAY) {
                if (ch == specialChar) {
                    return true;
                }
            }
        }
        return false;

} }
