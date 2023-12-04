public class SolutionP1 {

    public static void main(String[] args) {

        // Input text
        String calibrationDocument = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet";

        // Print output header
        System.out.println("\nProcessed Lines and Their Sum");
        System.out.println("-------------------------------\n");

        int totalSum = 0;
        int lineCounter = 1;

        // Loop through the lines
        for (String line : calibrationDocument.split("\n")) {
            // Process the current line

            int firstDigit = -1;
            int lastDigit = -1;

            // Loop through characters
            for (char c : line.toCharArray()) {
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

            // Print the original text, calibration value, and increment the line counter
            System.out.println("Line " + lineCounter + ":\tOriginal Text: " + line);
            System.out.println("\tCalibration Value: " + calibrationValue + "\n");
            lineCounter++;
        }

        // Print the totalSum for all calibration values
        System.out.println("-------------------------------");
        System.out.println("Total Sum = " + totalSum);
    }
}
