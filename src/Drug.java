/**
 * a drug with ID, name, cost, and dosage information.
 */
public class Drug {
    private int drugId;
    private String drugName;
    private double drugCost;
    private String dosage;

    /**
     * constructor to initialize a Drug object
     * @param drugId ID of the drug
     * @param drugName name of the drug
     * @param drugCost cost of the drug
     * @param dosage dosage description
     */
    public Drug(int drugId, String drugName, double drugCost, String dosage) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCost = drugCost;
        this.dosage = dosage;
    }

    /**
     * converts the Drug object to a comma-separated string.
     */
    @Override
    public String toString() {
        return drugId + "," + drugName + "," + drugCost + "," + dosage;
    }

    /**
     * creates a Drug object from a comma-separated string
     * used when reading drug data from a file
     * @param line - a line from the file representing a drug
     * @return - a Drug object parsed from the line
     */
    public static Drug fromString(String line) {
        String[] parts = line.split(",");
        return new Drug(
                Integer.parseInt(parts[0]),  // parse drug ID
                parts[1],                    // drug name
                Double.parseDouble(parts[2]),// drug cost
                parts[3]                     // dosage
        );
    }
}