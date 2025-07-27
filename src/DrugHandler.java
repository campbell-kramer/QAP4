import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * handles drug.txt file to store Drug entries.
 */
public class DrugHandler {
    private static final Logger logger = Logger.getLogger(DrugHandler.class.getName()); // logger
    private static final String FILE_PATH = "drugs.txt"; // path to drugs.txt file

    /**
     * adds drug entry to the text file.
     * @param drug -the Drug object to be saved.
     */
    public static void writeDrugToFile(Drug drug) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(drug.toString()); // write drug data as a single line
            writer.newLine();              // add new line after each entry
            System.out.println("Drug saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file:");
            logger.log(Level.SEVERE, "An exception occurred", e); // log error
        }
    }

    /**
     * reads all drugs from text file and prints them to console
     * if the file does not exist, exits
     */
    public static void readDrugsFromFile() {
        File file = new File(FILE_PATH);

        // check if file exists before trying to read
        if (!file.exists()) {
            System.out.println("No drugs file found. Try saving a drug first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Drugs in File:");

            while ((line = reader.readLine()) != null) {
                Drug drug = Drug.fromString(line); // convert line to Drug object
                System.out.println(drug);          // print out the drug data
            }
        } catch (IOException e) {
            System.out.println("Error reading from file:");
            logger.log(Level.SEVERE, "An exception occurred", e); // log error
        }
    }
}
