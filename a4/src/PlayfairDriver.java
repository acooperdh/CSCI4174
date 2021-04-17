//Drew Cooper B00811386 Assignment 4 - CSCI 4174 - Playfair Encryption
/*
This is a program that uses the class PlayfairMatrix to taken in various different arguments in order to encrypt or decrypt messages
using a playfair encryption.
Method 1: Accepts a secret key (String of characters) as argument and generates and returns a key matrix (2D
array).
Method 2: Accepts plaintext (String of characters) and the key matrix as arguments, and generates and returns
the ciphertext.
Method 3: Accepts ciphertext (String of characters) and the key matrix as arguments, and generates and returns
the plaintext.
 */
import java.util.Scanner;
public class PlayfairDriver {

    public static void main(String[] args) {
        boolean run = true;
        Scanner kb = new Scanner(System.in);
        while(run){
            System.out.println("How would you like to use our encryption today: ");
            System.out.println("1. generate key matrix");
            System.out.println("2. enter matrix and plaintext to find ciphertext");
            System.out.println("3. enter matrix and ciphertext to find plaintext");
            System.out.println("4. exit");
            int ans = kb.nextInt();
            if(ans == 4){
                run = false;
            }else if (ans == 1){
                System.out.println("Please enter the key, remember only use letters: ");
                String key = kb.next();
                PlayfairMatrix temp = new PlayfairMatrix(key);
                temp.fillAlphabet();
                temp.fillMatrix();
                char[][] matrix = temp.getMatrix();
            }else if(ans == 2){
                System.out.println("Please enter the file name in which the matrix is stored, remember only use letters: ");
                String name = kb.next();
                System.out.println("please enter the plaintext you wish to encrypt: ");
                String plaintext = kb.next();
                PlayfairMatrix temp = new PlayfairMatrix("whatever");
                temp.readInMatrix(name);
                temp.runEncrpytion(temp.formatPlaintext(plaintext));
                System.out.println(temp.getCiphertext());
            }else if (ans == 3){
                System.out.println("Please enter the file name in which the matrix is stored, remember only use letters: ");
                String name = kb.next();
                System.out.println("please enter the ciphertext you wish to decrypt: ");
                String ciphertext = kb.next();
                PlayfairMatrix temp = new PlayfairMatrix("whatever");
                temp.readInMatrix(name);
                temp.runDecryption(ciphertext);
                System.out.println(temp.getPlaintext());
            }
        }
    }
}
