//Drew Cooper B00811386 Assignment 4 - CSCI 4174 - Matrix Transposition Encryption
import java.util.Scanner;
public class MatrixDriver {
    public static void main(String[] args){
        System.out.println("Hello & Welcome to the Matrix Transpo Driver ... ");
        int[] key = {5, 4, 2, 1, 3};
        Matrix temp = new Matrix(key);
        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the phrase you'd like to encrypt");
        String input = kb.nextLine();
        String encryption = temp.encrypt(input);
        String decryption = temp.decrypt(encryption);
        System.out.println(encryption);
        System.out.println(decryption);
    }
}
