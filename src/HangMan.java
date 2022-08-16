import java.util.ArrayList;

public class HangMan {
    static boolean isGameRunning = true;
    static int lives = 10;

    public static void cls(){
        for(int i = 0 ;i<100;i++){
            System.out.println(" ");
        }
    }
    private static String takeInputFirstChar(String str) {
        if (str == null || str.equals("")){
            System.out.println("That is an invalid character");
            return null;
        }
        char[] arrInputChars = str.toCharArray();
        return String.valueOf(arrInputChars[0]);
    }
    private static String revealChar(String strToGuess, String strHidden, String charGuess) {

        char[] arrHiddenChars = strHidden.toCharArray();

        if (charGuess == null || charGuess.equals("")){
            return String.valueOf(arrHiddenChars);
        }

        char stg = charGuess.charAt(0);
        for (int i = 0; i < strToGuess.length(); i++) {
            char c = strToGuess.charAt(i);
            if (stg == c){
                arrHiddenChars[i] = stg;
            }
        }
        return String.valueOf(arrHiddenChars);
    }

    public static String createHiddenWord(String str){
        String strHidden = "";
        for(int i = 0;i < str.length(); i++){
            strHidden += "_";
        }
        return strHidden;
    }

    public static void main(String[] args) {
        String wordToGuess = WordSelector.getRandomWord();
        String hiddenWord = createHiddenWord(wordToGuess);
        ArrayList<String> guessedChars = new ArrayList<String>();


        while (isGameRunning) {
            System.out.println(hiddenWord);
            System.out.println("Guess a character");
            String strGuess = takeInputFirstChar(InputReader.readTextInput().toLowerCase().replaceAll(" ",""));
            cls();

            if (strGuess == null){
                System.out.println("That is an invalid character");
                System.out.println("Your guesses: " + guessedChars.toString());
                System.out.println("Lives left:" + lives);
                continue;
            }


            if (guessedChars.contains(strGuess)) {
                System.out.println("You have already guessed that char");
            }else{
                guessedChars.add(strGuess);
                if (wordToGuess.contains(strGuess)) {
                    System.out.println("You Guessed a char");
                }else{

                    lives -= 1;
                    System.out.println("You guessed wrong.");
                }
            }

            if (lives < 0){
                System.out.println("Lives left: 0");
            }else{
                System.out.println("Lives left:" + lives);
            }

            System.out.println("Your guesses: " + guessedChars.toString());

            hiddenWord = revealChar(wordToGuess, hiddenWord, strGuess);


            if (lives < 0){
                isGameRunning = false;
                System.out.println("You lost");
                System.out.println("The word was: " + wordToGuess);
            }
            if (wordToGuess.equals(hiddenWord)){
                isGameRunning = false;
                System.out.println("You won");
                System.out.println("You Guessed it, the word was: " + wordToGuess);

            }

        }
    }
}