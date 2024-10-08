import java.util.Scanner;

public class Orchard{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputAshok = scanner.nextLine();
        String inputAnand = scanner.nextLine();
        char[] ashokChars = inputAshok.toCharArray();
        char[] anandChars = inputAnand.toCharArray();
        
        int ashokCombinations = calculateCombinations(ashokChars, ashokChars.length);
        int anandCombinations = calculateCombinations(anandChars, anandChars.length);
        
        if (ashokCombinations == 0 && anandCombinations == 0) {
            System.out.print("Invalid input");
        } else if (ashokCombinations > anandCombinations) {
            System.out.print("Ashok");
        } else if (anandCombinations > ashokCombinations) {
            System.out.print("Anand");
        } else {
            System.out.print("Draw");
        }
    }

    static int calculateCombinations(char[] arr, int length) {
        int totalCombinations = 0;
        String combinationString = "";
        
        for (int i = 0; i < length; i++) {
            combinationString = "" + arr[i];
            for (int j = i + 1; j < length; j++) {
                combinationString += arr[j];
                if (combinationString.charAt(0) != arr[j]) {
                    for (int k = j + 1; k < length; k++) {
                        if (combinationString.charAt(1) != arr[k]) {
                            totalCombinations++;
                        }
                    }
                }
                combinationString = "" + arr[i];
            }
        }
        return totalCombinations;
    }
}
