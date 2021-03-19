import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PlayfairMatrix {
    public PlayfairMatrix(){
        int row = 0;
        int col = 0;
        int[][] arr = new int[5][5];
        ArrayList<Character> alphabet = fillAlphabet();
     }
     /*
     fillMatrix -> set the letters within the key to be the first in the matrix
                -> fill the rest of the matrix with the letters that have not yet been used
                -> need to determine which letters have been and haven't been used
     Edge cases:
        -> repeating letters
        -> odd number of letters / does not fill the matrix completely.
     */
    public ArrayList<Character> fillAlphabet(){
        ArrayList<Character> temp = new ArrayList<Character>();
        try{
            File tempFile = new File("alphabet.txt");
            Scanner fileScanner = new Scanner(tempFile);
            while(fileScanner.hasNextLine()){
                String t = fileScanner.nextLine();
                char c = t.charAt(0);
                temp.add(c);
            }
            fileScanner.close();
        }catch (FileNotFoundException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return temp;
    }
     public void fillMatrix(String key){
        ArrayList<Character> keyLetters = new ArrayList<Character>();
        for(int i = 0; i<key.length(); i++){
            keyLetters.add(key.charAt(i));
        }
        System.out.println(keyLetters);
     }
}
