import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class University {
    List<Faculty> faculties = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private Faculty selectedFaculty;

    public static void main(String[] args) {
        University university = new University();
        university.run();
    }

    public void run() {
        int choice;
        do {
            System.out.println("Choose operation category:\n1. Faculty operations\n2. General operations\n3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) facultyOperations();
            else if (choice == 2) generalOperations();
        } while (choice != 3);

        System.out.println("Exiting...");
    }

    private void facultyOperations() {
        if (faculties.isEmpty()) {
            System.out.println("No faculties available. Create a faculty first through General Operations.");
            return;
        }

        // Listing faculties and asking the user to choose one
        System.out.println("Choose a Faculty by entering its number:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }

        int facChoice = scanner.nextInt();
        scanner.nextLine();

        selectedFaculty = faculties.get(facChoice - 1);

        System.out.println("Choose Faculty Operation:\n1. Create and assign a student to a faculty\n2. Graduate a student from a faculty\n3. Display current enrolled students\n4. Display graduates\n5. Check student belongs to this faculty");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter Student's First Name: ");
                String firstName = scanner.nextLine();
                System.out.println("Enter Student's Last Name: ");
                String lastName = scanner.nextLine();
                System.out.println("Enter Student's Email: ");
                String email = scanner.nextLine();
                System.out.println("Enter Student's Enrollment Date (yyyy-MM-dd): ");
                String enrollmentDateString = scanner.nextLine();
                System.out.println("Enter Student's Date of Birth (yyyy-MM-dd): ");
                String dobString = scanner.nextLine();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date enrollmentDate = null;
                Date dob = null;
                try {
                    enrollmentDate = sdf.parse(enrollmentDateString);
                    dob = sdf.parse(dobString);
                    Student student = new Student(firstName, lastName, email, enrollmentDate, dob);
                    selectedFaculty.addStudent(student);
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format");
                }
                Student student = new Student(firstName, lastName, email, enrollmentDate, dob);
                selectedFaculty.addStudent(student);
                System.out.println("Student has been created and assigned to " + selectedFaculty.getName());
                break;
            case 2:
                System.out.println("Enter the email of the student you want to graduate:");
                String graduateEmail = scanner.nextLine(); // Changed variable name here
                selectedFaculty.graduateStudent(graduateEmail); // And here
                break;
            case 3:
                selectedFaculty.displayEnrolledStudents();
                break;
            case 4:
                selectedFaculty.displayGraduates();
                break;
            case 5:

                System.out.println("Enter the email of the student you want to check:");
                String studentEmail = scanner.nextLine(); // Changed variable name to avoid conflict
                if (selectedFaculty.doesStudentBelong(studentEmail))
                    System.out.println("Yes, the student belongs to this faculty.");
                else
                    System.out.println("No, the student does not belong to this faculty.");
                break;
        }
    }

    private void generalOperations() {
        //Keep it only for general operations like creating a faculty, displaying university faculties, etc.
        System.out.println("Choose General Operation:\n1. Create a new faculty\n2. Search what faculty a student belongs to\n3. Display University faculties\n4. Display all faculties belonging to a field");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.println("Enter Faculty Name:");
                String name = scanner.nextLine();

                System.out.println("Enter Faculty Abbreviation:");
                String abbreviation = scanner.nextLine();

                System.out.println("Enter Study Field (MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE):");
                String field = scanner.nextLine();

                faculties.add(new Faculty(name, abbreviation, StudyField.valueOf(field)));
                // Rest of the cases are similar
                break;
            case 2:
                System.out.println("Existing Faculties are: ");
                faculties.forEach(faculty -> System.out.println(faculty.getName()));
                break;
            case 3:
                System.out.println("Enter Study Field (MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE):");
                String studyFieldInput = scanner.nextLine();
                try {
                    StudyField studyField = StudyField.valueOf(studyFieldInput);
                    System.out.println("Faculties in field " + studyField + " are:");
                    faculties.stream()
                            .filter(faculty -> faculty.getStudyField() == studyField)
                            .forEach(faculty -> System.out.println(faculty.getName()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid Study Field Entered!");
                }
                break;
        }
    }
}

