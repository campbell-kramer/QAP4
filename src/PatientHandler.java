import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * handles PostgreSQL database for Patient entries using JDBC
 */
public class PatientHandler {
    private static final Logger logger = Logger.getLogger(PatientHandler.class.getName());
    // access details for PostgreSQL database
    private static final String URL = "jdbc:postgresql://localhost:5433/Patients";
    private static final String USER = "postgres";
    private static final String PASSWORD = ":^(((";

    /**
     * Inserts a Patient record into the 'patients' table.
     * @param patient The Patient object to insert.
     */
    public static void insertPatient(Patient patient) {
        String query = "INSERT INTO patients (patient_id, first_name, last_name, dob) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = createPreparedStatement(conn, query, patient)) {

            stmt.executeUpdate();
            System.out.println("Patient saved to database.");
        } catch (SQLException e) {
            System.err.println("Database insert failed: " + e.getMessage());
        }
    }

    // helper function to prepare the statement
    private static PreparedStatement createPreparedStatement(Connection conn, String query, Patient p) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, p.getPatientId());
        stmt.setString(2, p.getFirstName());
        stmt.setString(3, p.getLastName());
        stmt.setDate(4, p.getDob());
        return stmt;
    }

    /**
     * gets and prints all patients from the Patients table.
     */
    public static void readPatientsFromDatabase() {
        String query = "SELECT * FROM patients";

        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // open DB connection
                Statement stmt = conn.createStatement();                             // create SQL statement
                ResultSet rs = stmt.executeQuery(query)                              // execute SELECT query
        ) {
            System.out.println("Patients in Database:");
            while (rs.next()) {
                // retrieve and print each patient's information
                System.out.printf("%d - %s %s - DOB: %s\n",
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading patients from database:");
            logger.log(Level.SEVERE, "Database insert failed", e);
        }
    }
}
