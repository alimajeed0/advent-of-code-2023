import java.util.HashMap;
import java.util.Map;

public class SolutionP2 {
    public static void main(String[] args) {

        // Input text
        String calibrationDocument = "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen";

        // Print output header
        System.out.println("\nProcessed Lines and Their Sum");
        System.out.println("-------------------------------\n");

        int totalSum = 0;
        int lineCounter = 1;

        // Loop through the lines
        for (String line : calibrationDocument.split("\n")) {
            // Process the current line

            // replace written numbers with integers
            String replacedNumbers = replaceNumbers(line);

            int firstDigit = -1;
            int lastDigit = -1;

            // Loop through characters
            for (char c : replacedNumbers.toCharArray()) {
                // Check if the character is a digit
                if (Character.isDigit(c)) {
                    // Process the digit

                    // If firstDigit is not set, set it
                    if (firstDigit == -1) {
                        firstDigit = Character.getNumericValue(c);
                    }
                    // Always update lastDigit
                    lastDigit = Character.getNumericValue(c);
                }
            }

            // Calculate the calibration value and update the total sum
            int calibrationValue = firstDigit * 10 + lastDigit;
            totalSum += calibrationValue;

            // Print the original text, processed text, calibration value, and increment the
            // line counter
            System.out.println("Line " + lineCounter + ":\tOriginal Text: " + line);
            System.out.println("\tProcessed text: " + replacedNumbers);
            System.out.println("\tCalibration Value: " + calibrationValue + "\n");
            lineCounter++;
        }

        // Print the totalSum for all calibration values
        System.out.println("-------------------------------");
        System.out.println("Total Sum = " + totalSum);
    }

    // Method to replace written numbers with corresponding values
    private static String replaceNumbers(String line) {
        Map<String, String> numbersMap = new HashMap<>();
        numbersMap.put("one", "o1e");
        numbersMap.put("two", "t2o");
        numbersMap.put("three", "th3ee");
        numbersMap.put("four", "f4ur");
        numbersMap.put("five", "f5ve");
        numbersMap.put("six", "s6x");
        numbersMap.put("seven", "se7en");
        numbersMap.put("eight", "ei8ht");
        numbersMap.put("nine", "n9ne");

        // Set the initial index to a large value
        int index = 10000;
        while (index == 10000) {
            // variable to store the written number currently being processed.
            String currentNumber = "";

            // loop through each key-value pair in numbersMap object
            for (Map.Entry<String, String> entry : numbersMap.entrySet()) {
                String key = entry.getKey();
                // Get the index of the first occurrence of the key in the input string line
                int position = line.indexOf(key);

                // If the key is found and its index is less than the current index, update the
                // index and firstNumber
                if (position != -1 && position < index) {
                    index = position;
                    currentNumber = key;
                }
            }
            // Reset the index and replace the firstNumber with its corresponding value
            index = -1;
            if (numbersMap.containsKey(currentNumber)) {
                line = line.replace(currentNumber, numbersMap.get(currentNumber));
                index = 10000;
            }
        }
        return line;
    }
}