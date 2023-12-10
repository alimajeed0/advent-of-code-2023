import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // List to store all extracted part numbers
    public static List<String> partNumbersList = new ArrayList<>();

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
        List<String> schematicList = Arrays.asList(engineSchematic.split("\n"));
        
        // Convert the list to a 2D array representation
        char[][] schematicArray = convertListToTwoDimArray(schematicList);
        
        // Find part numbers
        findAdjacentPartNumbers(schematicArray, schematicList.get(0).length(), schematicList.size());

        // Calculate the sum of all part numbers
        int sum = 0;
        for (String s : partNumbersList) {
            sum += Integer.parseInt(s);
        }
        System.out.println("The sum of all part numbers = "+sum);
    }

    // Method to convert the list of lines into a 2D character array
    private static char[][] convertListToTwoDimArray(List<String> list) { 
        char[][] twoDimArray = new char[list.get(0).length()][list.size()];
        for (int i = 0; i < list.size(); i++) { 
            for (int j = 0; j < list.get(i).length(); j++) {
                twoDimArray[i][j] = list.get(i).charAt(j);
            }
        }
        return twoDimArray;
    }

    // Method to identify and extract all part numbers within the schematic array
    private static void findAdjacentPartNumbers(char[][] twoDimArray, int cols, int rows) {
        StringBuilder builder = new StringBuilder();
        // Flag to indicate if the current digit is part of a valid part number
        boolean isPartNumber = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Character.isDigit(twoDimArray[i][j])) {
                    // Append the character to the current part number
                    builder.append(twoDimArray[i][j]);
                    if (hasAdjacentSymbol(twoDimArray,i,j)) {
                        isPartNumber = true;
                    }
                } else {
                    if (isPartNumber) {
                        // If a valid number is complete, add it to the result list
                        partNumbersList.add(builder.toString());
                    }
                    isPartNumber = false;
                    // Clears the StringBuilder
                    builder = new StringBuilder(); 
                }
            }
        }
    }

    // Checks if a digit has a symbol adjacent to it
    private static boolean hasAdjacentSymbol(char[][] twoDimArray, int x, int y) {
        // Check surrounding characters
        for(int dx = -1; dx <= 1; dx++) {
            for(int dy = -1; dy <= 1; dy++) {
                // Skip invalid adjacent cells 
                if (!isCellWithinBounds(x, y, dx, dy, twoDimArray)) 
                    continue;
                if (isSymbol(twoDimArray[x + dx][y + dy])) {
                    // If a symbol is found in any of the adjacent cells
                    return true;
                }
            }
        }
        // No symbol is found in the adjacent cells
        return false;
    }

    // Method checks if the given coordinates represent a valid adjacent cell within the array
    private static boolean isCellWithinBounds(int x, int y, int dx, int dy, char[][] data) {
        return ((x + dx >= 0) && (x + dx < data.length)  && (y + dy >= 0) && (y + dy < data[x + dx].length) && (!(dx == 0 && dy == 0)));
    }
    
    // Checks if a character is not alphanumeric or dot
    private static boolean isSymbol(char c) {
        return Character.toString(c).matches("[^a-zA-Z0-9.]");
    }
}