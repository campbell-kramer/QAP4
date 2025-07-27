import java.sql.Date;
import java.util.Scanner;

/**
 * a simple menu for the user to save or retrieve data from the drug file or patient database
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // scanner for reading user input
        boolean running = true;              // controls menu loop

        // main menu loop
        while (running) {
            // menu options
            System.out.println("\nMenu:");
            System.out.println("1. Save Drug to File");
            System.out.println("2. Read Drugs from File");
            System.out.println("3. Save Patient to Database");
            System.out.println("4. Read Patients from Database");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt(); // get user's menu choice
            sc.nextLine();

            switch (choice) {
                case 1:
                    // save a new drug to .txt file
                    System.out.print("Drug ID: ");
                    int drugId = sc.nextInt();
                    sc.nextLine(); // Clear newline

                    System.out.print("Drug Name: ");
                    String name = sc.nextLine();

                    System.out.print("Drug Cost: ");
                    double cost = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Dosage: ");
                    String dosage = sc.nextLine();

                    Drug drug = new Drug(drugId, name, cost, dosage);
                    DrugHandler.writeDrugToFile(drug);
                    break;

                case 2:
                    // read all drugs from the file
                    DrugHandler.readDrugsFromFile();
                    break;

                case 3:
                    // save a new patient to the database
                    System.out.print("Patient ID: ");
                    int patientId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("First Name: ");
                    String first = sc.nextLine();

                    System.out.print("Last Name: ");
                    String last = sc.nextLine();

                    System.out.print("DOB (YYYY-MM-DD): ");
                    String dobInput = sc.nextLine();

                    try {
                        // convert the string into java.sql.Date format
                        Date dob = Date.valueOf(dobInput);
                        Patient patient = new Patient(patientId, first, last, dob);
                        PatientHandler.insertPatient(patient);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }

                    break;

                case 4:
                    // read all patients from the database
                    PatientHandler.readPatientsFromDatabase();
                    break;

                case 5:
                    // exit the program
                    running = false;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close(); // close the scanner
    }
}