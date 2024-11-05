package program;

import java.math.BigInteger;
import java.security.SecureRandom;

/*
  допоміжний клас
 */
public class Helper {


    //метод буде генерувати випадкову строку
    public static String generateRandomString(){
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(1, secureRandom).toString(32);
    }

    // метод повинен виводити переданий текст у консоль
    public static void printMessage(String message){
        System.out.println(message);
    }
}
