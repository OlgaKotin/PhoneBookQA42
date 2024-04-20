package helpers;

import java.util.Random;

public class PasswordStringGenerator {

    public static void main(String[] args) {
        System.out.println("Password: " +generateRandomPassword());
    }
    public static String generateRandomPassword(){
        StringBuilder password = new StringBuilder();
        for(int i=0; i<=4;i++){
            char charUpperCase = (char) ('A' +Math.random()*('Z'-'A')+1);
            password.append(charUpperCase);
        }
        for(int i=0; i<=4;i++){
            char charLowerCase = (char) ('a' +Math.random()*('z'-'a')+1);
            password.append(charLowerCase);
        }
        Random random = new Random();
        for(int i=0; i<3; i++){
            int digit = random.nextInt(10);
            password.append(digit);
        }
        String specialChar = "[]!$_-";
        int specialCharecterCount = 1 + random.nextInt(3);
        for(int i=0; i<specialCharecterCount; i++) {
            int index = random.nextInt(specialChar.length());
            char specChar = specialChar.charAt(index);
            password.append(specChar);
        }
        return password.toString();
    }
}
