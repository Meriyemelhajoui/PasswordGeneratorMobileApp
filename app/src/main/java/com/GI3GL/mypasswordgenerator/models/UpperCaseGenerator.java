package com.GI3GL.mypasswordgenerator.models;

public class UpperCaseGenerator extends PasswordGenerator{

    // Defining the range of the Characters
    private static final char CHAR_A = 'A';
    private static final char CHAR_Z = 'Z';



    // Overriding of the Method getChar
    @Override
    public String getChar() {
        return String.valueOf((char) (Helper.randomChar(CHAR_A,CHAR_Z)));
    } // since the method randomChar returns a random integer , we need to return the character so we do a cast to char
}
