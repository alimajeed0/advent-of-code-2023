import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {

        // Input text
        String gamesRecord =
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

        // Regular expression pattern matches cube information in the format "number color"
        Pattern colorPattern = Pattern.compile("(\\d+) (\\w+)");

        // Total number of cubes of each color available
        int totalRedCubes = 12;
        int totalGreenCubes = 13;
        int totalBlueCubes = 14;

        int validGameIdsSum = 0;
        int sumOfPowers = 0;

        // Print output header
        System.out.println("----------------------------------------------------");
        System.out.println("The minimum set of cubes that must have been present");
        System.out.println("----------------------------------------------------");

        // Loop through lines
        for (String line : gamesRecord.split("\n")) {

            // Separate the game number from the list of cube sets
            String[] gameData = line.split(":");

            // Extract the game number from the first part of the game data
            int gameNumber = Integer.parseInt(gameData[0].replace("Game", "").trim());

            // Counters for highest value found in the current game record
            int highestRedCount = 0, highestGreenCount = 0, highestBlueCount = 0;

            // Loop through sets
            for (String set : gameData[1].split(";")) {
                // Loop through cubes
                for (String cube : set.split(",")) {
                    Matcher m = colorPattern.matcher(cube);
                    while (m.find()) {
                        // Get number and color
                        int cubeQuantity = Integer.parseInt(m.group(1));
                        String color = m.group(2);

                        // Switch statement that finds the highest value appears for each color
                        switch (color) {
                            case "red":
                                if (cubeQuantity > highestRedCount) {
                                    highestRedCount = cubeQuantity;
                                }
                                break;
                            case "green":
                                if (cubeQuantity > highestGreenCount) {
                                    highestGreenCount = cubeQuantity;
                                }
                                break;
                            case "blue":
                                if (cubeQuantity > highestBlueCount) {
                                    highestBlueCount = cubeQuantity;
                                }
                                break;
                        }
                    }
                }
            }
            System.out.println(String.format("Game %d:\tRed: %2d\tGreen: %2d\tBlue: %2d",
                    gameNumber, highestRedCount, highestGreenCount, highestBlueCount));

            // Check if the highest color counts are within the available limits
            if ((highestRedCount <= totalRedCubes) && (highestGreenCount <= totalGreenCubes) && (highestBlueCount <= totalBlueCubes)) {
                validGameIdsSum += gameNumber;
            }

            // Part 2
            int power = highestRedCount * highestGreenCount * highestBlueCount;
            sumOfPowers += power;
        }
        System.out.println("\n----------------------------------------------------");
        System.out.println("Sum of valid games IDs = "+validGameIdsSum);
        System.out.println("Sum of powers of all games = "+sumOfPowers);
    }
}