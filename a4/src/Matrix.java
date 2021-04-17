//Drew Cooper B00811386 Assignment 4 - CSCI 4174 - Matrix Transposition Encryption
public class Matrix {
    private int[] key;
    private char[][] encryptedMatrix;
    private char[][] decryptedMatrix;
    public Matrix(int[] key){
        this.key = key;

    }

    public int[] getKey(){
        return this.key;
    }

    public void setKey(int[] key){
        this.key = key;
    }

    public char[][] getEncryptedMatrix(){
        return this.encryptedMatrix;
    }

    public String encrypt(String plaintext){
        double cols = this.key.length;
        double numRows = Math.ceil(plaintext.length() / cols);
        this.encryptedMatrix = new char[(int)numRows][(int)cols];
        int expectedCharacters = (int)cols * (int)numRows;
        if (expectedCharacters > plaintext.length()){
            int diff = expectedCharacters - plaintext.length();
            for(int i = 0; i<expectedCharacters;i++){
                plaintext+=".";
            }
        }
        char[] charArr = plaintext.toCharArray();
        int counter = 0;
        for(int i = 0; i<numRows;i++){
            for(int j = 0; j<cols;j++){
                this.encryptedMatrix[i][j] = charArr[counter];
                counter++;
            }
        }
//        for(int i = 0; i<numRows;i++){
//            for(int j = 0; j<cols;j++){
//                System.out.print(this.encryptedMatrix[i][j]+ " ");
//            }
//            System.out.println("");
//        }
        //this loop will compile the matrix into cipher text and return it to the user
        char[] cipher = new char[plaintext.length()];
        counter = 0;
        for(int i=0; i<cols; i++){
            for(int j=0; j<numRows; j++){
                cipher[counter]=this.encryptedMatrix[j][(this.key[i]-1)];
//                System.out.print(cipher[counter]);
                counter++;
            }
        }
        String cipherText = String.valueOf(cipher);
//        System.out.println("");
        return String.valueOf(cipherText);
    }

    public String decrypt(String ciphertext){
        double cols = this.key.length;
        double rows = Math.ceil(ciphertext.length() / cols);
        this.decryptedMatrix = new char[(int)rows][(int)cols];
        int expectedCharacters = (int)cols*(int)rows;
        char[] charArr = ciphertext.toCharArray();
        int counter = 0;
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                this.decryptedMatrix[j][(this.key[i]-1)] = ciphertext.charAt(counter);
                counter++;
            }
        }
//        for(int i=0;i<rows;i++){
//            for(int j=0;j<cols;j++){
//                System.out.print(this.decryptedMatrix[i][j]);
//            }
//            System.out.println("");
//        }
        counter = 0;
        for(int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                charArr[counter] = this.decryptedMatrix[i][j];
                counter++;
            }
        }
        return String.valueOf(charArr);
    }
}
