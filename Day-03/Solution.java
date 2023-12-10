import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    
    // List to hold all part numbers 
    public static List<String> resultList = new ArrayList<>();

    // Main method
    public static void main(String[] args) {
        
        // Input text
        String engineSchematic = 
            "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...$.*....\n" +
            ".664.598..";
       
        // Split into lines and store them as a list of strings
        List<String> linesList = Arrays.asList(engineSchematic.split("\n"));
        
        // Convert the lines into 2-D array
        char[][] twoDimArray = convertToTwoDimArray(linesList);
        
        // Find part numbers
        findAdjacentPartNumbers(twoDimArray, linesList.get(0).length(), linesList.size());

        // Calculate the sum of part numbers
        int sum = 0;
        for (String s : resultList) {
            sum += Integer.parseInt(s);
        }
        System.out.println("The sum of all part numbers = "+sum);
    }

    // Method to convert the lines list to 2-D array
    private static char[][] convertToTwoDimArray(List<String> linesList) { 
        char[][] twoDimArray = new char[linesList.get(0).length()][linesList.size()];
        for (int i = 0; i < linesList.size(); i++) { 
            for (int j = 0; j < linesList.get(i).length(); j++) {
                twoDimArray[i][j] = linesList.get(i).charAt(j);
            }
        }
        return twoDimArray;
    }

    private static void findAdjacentPartNumbers(char[][] twoDimArray, int cols, int rows) {
        // StringBuilder to construct a part number
        StringBuilder partNumber = new StringBuilder();
        // Flag to indicate if the current digit is part of a valid part number
        boolean isPartNumber = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Character.isDigit(twoDimArray[i][j])) {
                    // Append the character to the current part number
                    partNumber.append(twoDimArray[i][j]);
                    if (isDigitAdjacentToSymbol(twoDimArray,i,j)) {
                        isPartNumber = true;
                    }
                } else {
                    if (isPartNumber) {
                        // If a valid number is complete, add it to the result list
                        resultList.add(partNumber.toString());
                    }
                    isPartNumber = false;
                    // Clears the StringBuilder
                    partNumber = new StringBuilder(); 
                }
            }
        }
    }

    private static boolean isDigitAdjacentToSymbol(char[][] twoDimArray, int x, int y) {
        // Check surrounding characters
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                // Skip invalid adjacent cells 
                if (!isAdjacentCellValid(x, y, i, j, twoDimArray)) 
                    continue;
                if (isSymbol(twoDimArray[x + i][y + j])) {
                    // If a symbol is found in any of the adjacent cells
                    return true;
                }
            }
        }
        // No symbol is found in the adjacent cells
        return false;
    }

    // Method checks if the given coordinates represent a valid adjacent cell within the array
    private static boolean isAdjacentCellValid(int x, int y, int dx, int dy, char[][] data) {
        return ((x + dx >= 0) && (x + dx < data.length)  && (y + dy >= 0) && (y + dy < data[x + dx].length) && (!(dx == 0 && dy == 0)));
    }
    
    private static boolean isSymbol(char c) {
        return Character.toString(c).matches("[^a-zA-Z0-9.]");
    }
}