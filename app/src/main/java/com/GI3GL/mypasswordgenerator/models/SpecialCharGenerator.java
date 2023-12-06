package com.GI3GL.mypasswordgenerator.models;

public class SpecialCharGenerator extends PasswordGenerator {
    public  static final char[] SPECIAL_CHAR_ARRAY = "?./!%*$^+-)]@(['{}#<>".toCharArray();



    @Override
    public String getChar() {
        return String.valueOf(SPECIAL_CHAR_ARRAY[Helper.randomChar(0, SPECIAL_CHAR_ARRAY.length - 1)]);
    }
}
