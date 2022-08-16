import java.util.Scanner;

public class InputReader {
    static Scanner scanner = new Scanner(System.in);

    public static String readTextInput(){
        String inputGuess = scanner.nextLine();
        return inputGuess;

    }
}