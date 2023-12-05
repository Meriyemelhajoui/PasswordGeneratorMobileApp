package com.GI3GL.mypasswordgenerator.models;

public class Helper {
    public static int randomVal(int size){
        double rand = Math.random(); // generate a double random beetwen 0 and <1
        return (int) (rand * size);// Scale to the desired range 0 , <8 ( fro example if the size is 8) and return the value as int
    }


    // This method return a random  number chosen by the randomVal , and return it as an integer (Asci value)
    public static int randomChar(int min, int max){
        return randomVal(max-min + 1) + min;
    } // we do the +min to ensure that we match the ASCI code for characters ..
}
