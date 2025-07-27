import java.sql.Date;

/**
 * a patient with ID, name, and date of birth
 */
public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private Date dob;  // Using java.sql.Date for compatibility with SQL databases

    /**
     * constructor to initialize a Patient object
     * @param patientId unique ID for the patient
     * @param firstName patient's first name
     * @param lastName patient's last name
     * @param dob patient's date of birth (java.sql.Date)
     */
    public Patient(int patientId, String firstName, String lastName, Date dob) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    /**
     * getters to access patient info
     */
    public int getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }
}
