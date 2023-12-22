public class WaitingList {
	protected Node head;
	protected int numNodes;

	public WaitingList() {
		this.head = null;
		this.numNodes = 0;
	}

	/**
	 * This method will pop the first patient from the waiting list
	 * 
	 * @return the patient popped from the waiting list
	 */
	public Patient popPatient() {
		if (head == null)
			return null;
		else {

			numNodes--;
			Patient patient = head.getPatient();
			head = head.getNext();
			return patient;
		}
	}

	public Patient popPatientByID(int patientID) {

		if (head == null)
			return null;
		else {

			Node temp = head;
			Node previous = null;

			// Search patients in the waiting list by their ID
			while (temp != null) {

				Patient tempPatient = temp.getPatient();
				if (tempPatient.getId() != patientID) {

					previous = temp;
					temp = temp.getNext();
				} else
					break;
			}

			// Return this patient and delete it from the waiting list
			if (temp == null)
				return null;
			else {

				previous.setNext(temp.getNext());
				Patient tempPatient = temp.getPatient();
				return tempPatient;
			}
		}
	}

	public int checkPosition(int patientID) {

		if (head == null)
			return -1;
		else {

			Node temp = head;
			int position = 0;

			// Search this patient in the waiting list by their ID.
			while (temp != null) {

				position++;
				Patient tempPatient = temp.getPatient();

				if (tempPatient.getId() == patientID)
					break;
				else
					temp = temp.getNext();
			}

			// Return their position in the waiting list.
			if (temp == null)
				return -1;
			else
				return position;
		}
	}

	/**
	 * This method will add patient into the waiting list according to the triage
	 * level
	 * 
	 * @param patient
	 *            patient's data
	 */
	public void addToList(Patient patient) {
		if (head == null)
			head = new Node(patient);
		else {

			numNodes++;
			Node temp = head;

			// Compare triage levels between this new patient and the patients in the
			// waiting list.
			while (temp.getNext() != null) {

				Patient tempPatient = temp.getPatient();
				if (tempPatient.getTriageLevel() > patient.getTriageLevel())
					temp = temp.getNext();
				else
					break;
			}

			// Add new patient in the waiting list
			if (temp.getNext() == null) {

				Patient tempPatient = temp.getPatient();
				if (tempPatient.getTriageLevel() >= patient.getTriageLevel())
					temp.setNext(new Node(patient));
				else {

					temp.setPatient(patient);
					temp.setNext(new Node(tempPatient));
				}
			} else {

				while (temp.getNext() != null)
					temp = temp.getNext();
				temp.setNext(new Node(patient));
			}
		}
	}

	/**
	 * print out the information for each patient in waiting list
	 */
	public void printList() {
		if (head == null)
			System.out.println("There is not a patient in the waiting list.");
		else {

			Node temp = head;
			while (temp != null) {

				temp.printNode();
				temp = temp.getNext();
			}
		}
	}

	/**
	 * Check whether the patient is in this list or not
	 * 
	 * @return
	 */
	public boolean isInList(Patient patient) {
		if (this.head == null) {
			return false;
		}
		Node temp = this.head;
		while (temp != null) {
			if (temp.getPatient().getName().equals(patient.getName())
					&& temp.getPatient().getPhoneNumber().equals(patient.getPhoneNumber())) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
}
