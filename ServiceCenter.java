public class ServiceCenter {
    private WaitingList awl;

    public ServiceCenter() {
        this.awl = new WaitingList();
    }

    /**
     * Record patient's data and add the patient into waiting list
     * 
     * @param name
     * @param phoneNumber
     * @param triageLevel
     * @param location
     */
    public void addPatientIntoList(String name, String phoneNumber, int triageLevel, String location) {
        Patient patient = new Patient(name, phoneNumber, triageLevel, location);
        if (this.awl.isInList(patient)) {
            System.out.println(patient.getName() + " is in waiting list. ");
        } else {
            this.awl.addToList(patient);
            System.out.println("Add " + patient.getName() + " into waiting list. ");
        }
    }

    /**
     * Pop out the first patient in the waiting list and assign an Ambulance for
     * him/her
     * 
     * @return the patient object
     */
    public Patient assignAmbulanceForPatient() {
        // TODO: The tester report that the system will crash when waiting list is empty
        Patient patient = this.awl.popPatient();
        if (patient != null) {
            System.out.println("Assigned an ambulance for patient: " + patient.getName());
        } else {
            System.out.println("Error! The waiting list is empty.");
        }
        return patient;
    }

    /**
     * Print out the waiting list
     */
    public void printWaitingList() {
        this.awl.printList();
    }

    // Assign an ambulance for patient by their ID
    public Patient assignAmbulanceForPatient(String patientID) {

        int id = Integer.parseInt(patientID);
        Patient patient = this.awl.popPatientByID(id);
        if (patient != null) {
            System.out.println("Success! An ambulance as assigned for patient " + id);
        } else {
            System.out.println("Invalid patient ID");
        }
        return patient;
    }

    // Check the position of a patient in the waiting list By their ID
    public int checkPosition(String patientID) {

        int id = Integer.parseInt(patientID);
        int position = this.awl.checkPosition(id);
        if (position != -1) {
            System.out.println("There are " + (position - 1) + " patients before patient " + id);
        } else {
            System.out.println("Invalid patient ID");
        }
        return position;
    }
}