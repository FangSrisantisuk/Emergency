import java.util.Scanner;

public class Main {
    /**
     * initialize data to the serviceCenter, terminate the system if data is wrong
     * 
     * @param serviceCenter
     */
    public static void initializeSystem(ServiceCenter serviceCenter) {
        String[] name = { "Tod", "David", "Dave", "Roy", "Megan" };
        String[] phoneNumbers = { "0410333222", "0410333223", "0410333224", "0410333225", "0410333226" };
        int[] triageLevel = { 1, 5, 2, 4, 3 };
        String[] location = { "King William ST", "North Terrace", "Grote St", "Gouger St", "Melbourne St" };
        // Check the length for each array is equal
        if (!(name.length == phoneNumbers.length && name.length == location.length
                && name.length == triageLevel.length)) {
            System.out.println("Failed to initialize data.");
            return;
        }
        // Initialize data into service center
        for (int i = 0; i < name.length; i++) {
            serviceCenter.addPatientIntoList(name[i], phoneNumbers[i], triageLevel[i], location[i]);
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getHeader(boolean toPrint) {
        String ans = "\n\n\n";
        ans += "===============================================================================" + "\n";
        ans += ":: Emergency Service Center                                                  ::" + "\n";
        ans += "===============================================================================" + "\n";
        if (toPrint)
            System.out.println(ans);
        return ans;
    }

    public static void printSubHeader(String subHeader) {
        getHeader(true);
        String str = ":: " + subHeader + "\n";
        System.out.println(str);
    }

    public static void printScreenMessage(String message) {
        String ans = getHeader(false) + "\n\n";
        ans += "::            " + message + "\n\n";
        System.out.println(ans);

    }

    public static void exitMessage() {
        clearScreen();
        printScreenMessage("Thank you!!");
    }

    public static void displayMenu() {
        String ans = getHeader(false);
        ans += ":: Options:                                                                  ::" + "\n";
        ans += ":: 1. Add Patient                                                            ::" + "\n";
        ans += ":: 2. Assign Ambulance For Patient                                           ::" + "\n";
        ans += ":: 3. Assign Ambulance For Patient By Id                                     ::" + "\n";
        ans += ":: 4. Check Position By Id                                                   ::" + "\n";
        ans += ":: 5. Print Waiting List                                                     ::" + "\n";
        ans += ":: 0. Exit                                                                   ::" + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
    }

    public static int getOption(Scanner inputScan, int[] optionsAvailable) {
        String ans = ":: Your option: ";
        System.out.println(ans);
        String _input = inputScan.nextLine();
        int _option = Integer.parseInt(_input);
        for (int i = 0; i < optionsAvailable.length; i++)
            if (_option == optionsAvailable[i])
                return _option;
        return -1;
    }

    public static String getInput(Scanner inputScan, String question) {
        System.out.println(question);
        String _input = inputScan.nextLine();
        return _input;
    }

    public static void waitInput(Scanner scan) {
        String ans = "\n\n\n";
        ans += "                        Press any key to continue . . .      " + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
        scan.nextLine();
    }

    public static void tryAgain(Scanner scan) {
        clearScreen();
        String ans = getHeader(false);
        ans += ":: Your input is invalid, please select ::" + "\n";
        ans += ":: a valid option.                      ::" + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
        waitInput(scan);
    }

    public static boolean performAction(ServiceCenter serviceCenter, int _option, Scanner inputScan) {
        String query = "";
        String id = "";
        switch (_option) {
            case 1:
                clearScreen();
                printSubHeader("Add Patient");
                String name = getInput(inputScan, "Please enter the name of patient: ");
                String phoneNumber = getInput(inputScan, "Please enter the phoneNumber of patient: ");
                String triageLevel = getInput(inputScan, "Please enter the triage level of patient: ");

                // Test if input triage level is a number and is valid (1-5)
                while (!testValidInt(triageLevel) || !testValidTriage(triageLevel)) {

                    if (!testValidInt(triageLevel)) {

                        System.out.println("The input is not a number.");
                        triageLevel = getInput(inputScan, "Please enter the triage level of patient: ");
                    } else if (!testValidTriage(triageLevel)) {

                        System.out.println("This is an invalid triage level.");
                        triageLevel = getInput(inputScan, "Please enter the triage level of patient: ");
                    }
                }

                String location = getInput(inputScan, "Please enter the location of patient: ");
                serviceCenter.addPatientIntoList(name, phoneNumber, Integer.parseInt(triageLevel), location);
                waitInput(inputScan);
                break;
            case 2:
                clearScreen();
                printSubHeader("Assign Ambulance");
                serviceCenter.assignAmbulanceForPatient();
                waitInput(inputScan);
                break;
            case 3:
                clearScreen();
                printSubHeader("Assign Ambulance For Patient By ID");
                String patientID = getInput(inputScan, "Please enter the ID of patient: ");

                // Test if input patient ID is a number
                while (!testValidInt(patientID)) {

                    System.out.println("The input is not a number.");
                    patientID = getInput(inputScan, "Please enter the ID of patient: ");
                }

                serviceCenter.assignAmbulanceForPatient(patientID);
                waitInput(inputScan);
                break;
            case 4:
                clearScreen();
                printSubHeader("Check Patient's Position In The Waiting List By ID");
                String checkID = getInput(inputScan, "Please enter the ID of patient: ");

                // Test if input patient ID is a number
                while (!testValidInt(checkID)) {

                    System.out.println("The input is not a number.");
                    checkID = getInput(inputScan, "Please enter the ID of patient: ");
                }

                serviceCenter.checkPosition(checkID);
                waitInput(inputScan);
                break;
            case 5:
                clearScreen();
                printSubHeader("Waiting List");
                serviceCenter.printWaitingList();
                waitInput(inputScan);
                break;
            case 0:
                exitMessage();
                return false;
        }
        return true;

    }

    public static boolean testValidInt(String input) {

        boolean valid = true;

        try {

            Integer.parseInt(input);
        } catch (Exception e) {

            valid = false;
        }

        return valid;
    }

    public static boolean testValidTriage(String input) {

        int triageLevel = Integer.parseInt(input);
        if (0 < triageLevel && triageLevel < 6)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        ServiceCenter serviceCenter = new ServiceCenter();
        initializeSystem(serviceCenter);
        Scanner inputScan = new Scanner(System.in);
        int[] options = new int[] { 1, 2, 3, 4, 5, 0 };
        boolean _continue = true;
        while (_continue) {
            clearScreen();
            displayMenu();
            int _option = getOption(inputScan, options);
            if (_option != -1)
                _continue = performAction(serviceCenter, _option, inputScan);
            else
                tryAgain(inputScan);
        }
    }
}