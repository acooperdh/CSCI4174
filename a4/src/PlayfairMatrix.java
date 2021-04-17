import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class PlayfairMatrix {
    private char[][] matrix = new char[5][5];
    private char[] key;
    private String ciphertext = "";
    private String plaintext = "";
    private ArrayList<Character> alphabet = new ArrayList<Character>();
    public PlayfairMatrix(String key){
        String t = key.toUpperCase();
        this.key = t.toCharArray();
    }

    public String getCiphertext(){
        return ciphertext;
    }

    public void setCiphertext(String c){
        this.ciphertext = c;
    }

    public String getPlaintext(){
        return plaintext;
    }

    public void setPlaintext(String p){
        this.plaintext = p;
    }

    public void setMatrix(char[][] m){
        this.matrix = m;
    }

    public char[][] getMatrix(){
        return this.matrix;
    }
    public void readInMatrix(String name){
        try{
            File filename = new File(name);
            Scanner mFile = new Scanner(filename);
            String temp;
            char c;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    temp = mFile.next();
                    matrix[i][j] = temp.charAt(0);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }
    public void fillAlphabet(){
        File alphabetText = new File("alphabet.txt");
        try{
            Scanner fileIn = new Scanner(alphabetText);
            while(fileIn.hasNextLine()){
                String t = fileIn.next();
                char c = t.charAt(0);
                alphabet.add(c);
            }
        }catch (FileNotFoundException e){
            System.out.println("Error has occurred");
            e.printStackTrace();
        }
    }

    public void fillMatrix(){
        ArrayList<Character> matrixContent = new ArrayList<Character>();
        int count = 0;
        //removing duplicate letters
        for(int i = 0; i<this.key.length; i++){
            if(alphabet.contains(this.key[i])){
                int index = alphabet.indexOf(this.key[i]);
                alphabet.remove(index);
                alphabet.add(i, this.key[i]);
            }
        }
        //creating the key matrix using the new order of the alphabet and printing it out for the user to see
        for(int i = 0; i < 5; i++){
            for (int j = 0; j<5; j++){
                matrix[i][j] = alphabet.get(count);
                System.out.print(matrix[i][j]+"\t");
                count++;
            }
            System.out.println("");
        }
    }

    public String formatPlaintext(String plaintext){
        char character1, character2;
        int start, end, textLen;
        String substring;
        plaintext = plaintext.toUpperCase();
        textLen = plaintext.length();
        String temp = "";
        for(start = 0, end = 2; end<textLen;start+=2, end+=2){
            substring = plaintext.substring(start, end);
            character1 = substring.charAt(0);
            character2 = substring.charAt(1);
            if(character1 == character2){
                String replacementText = character1 + "X"+ character2;
                temp = plaintext.replaceFirst(substring, replacementText);
                plaintext = temp;
                textLen++;
            }
        }
        textLen = plaintext.length();
        if(textLen%2 != 0){
            plaintext += "X";
        }
        plaintext = plaintext.replaceAll("J", "I");
        plaintext = plaintext.replaceAll("\\s", "");
        return plaintext;
    }

    public void runEncrpytion(String plaintext){
        int start, end;
        for(start = 0, end = 2; end<plaintext.length()+1; start+=2, end+=2){
            String pair = plaintext.substring(start, end);
            char startChar = pair.charAt(0);
            char endChar = pair.charAt(1);
            int[] locations = determineLocation(startChar, endChar);
            encrypt(locations);
        }
    }
    public void runDecryption(String ciphertext){
        int start, end;
        for(start = 0, end = 2; end<ciphertext.length()+1; start+=2, end+=2){
            String pair = ciphertext.substring(start, end);
            char startChar = pair.charAt(0);
            char endChar = pair.charAt(1);
            int[] locations = determineLocation(startChar, endChar);
            decrypt(locations);
        }
    }

    public int[] determineLocation(char startChar, char endChar){
        int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
        char temp;
        for(int i=0;i < 5; i++){
            for(int j=0;j<5;j++){
                temp = matrix[i][j];
                if(temp == startChar){
                    r1 = i;
                    c1 = j;
                }else if(temp == endChar){
                    r2 = i;
                    c2 = j;
                }
            }
        }
        return new int[]{r1, c1, r2, c2};
    }
    public void encrypt(int[] locations){
        String s1, s2;
        if(locations[0] == locations[2]){
            locations[3]++; locations[1]++;
            if(locations[3]>4){
                locations[3] = 0;
            }
            if(locations[1]>4){
                locations[1] = 0;
            }
            s1 = Character.toString(matrix[locations[0]][locations[1]]);
            s2 = Character.toString(matrix[locations[0]][locations[3]]);
            ciphertext += s1 + s2;
        }else if(locations[1] == locations[3]){
            locations[2]++; locations[0]++;
            if(locations[2]>4){
                locations[2] = 0;
            }
            if(locations[0]>4){
                locations[0] = 0;
            }
            s1 = Character.toString(matrix[locations[0]][locations[1]]);
            s2 = Character.toString(matrix[locations[2]][locations[1]]);
            ciphertext += s1 + s2;
        }else{
            s1 = Character.toString(matrix[locations[0]][locations[3]]);
            s2 = Character.toString(matrix[locations[2]][locations[1]]);
            ciphertext += s1 + s2;
        }
    }

    public void decrypt(int[] locations){
        String s1, s2;
        if(locations[0] == locations[2]){
            locations[3]--; locations[1]--;
            if(locations[3]<0){
                locations[3] = 4;
            }
            if(locations[1]<0){
                locations[1] = 4;
            }
            s1 = Character.toString(matrix[locations[0]][locations[1]]);
            s2 = Character.toString(matrix[locations[0]][locations[3]]);
            plaintext += s1 + s2;
        }else if(locations[1] == locations[3]){
            locations[2]--; locations[0]--;
            if(locations[2]<0){
                locations[2] = 4;
            }
            if(locations[0]<0){
                locations[0] = 4;
            }
            s1 = Character.toString(matrix[locations[0]][locations[1]]);
            s2 = Character.toString(matrix[locations[2]][locations[1]]);
            plaintext += s1 + s2;
        }else{
            s1 = Character.toString(matrix[locations[0]][locations[3]]);
            s2 = Character.toString(matrix[locations[2]][locations[1]]);
            plaintext += s1 + s2;
        }
    }


}
