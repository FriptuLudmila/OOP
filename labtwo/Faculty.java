import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Faculty {
    public String name;
    private String abbreviation;
    private List<Student> students; // to hold enrolled students
    private List<Student> graduates; // to hold graduated students
    private StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
        this.graduates = new ArrayList<>();
    }
    public void graduateStudent(String email) {
        Optional<Student> studentToGraduate = students.stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();

        if (studentToGraduate.isPresent()) {
            students.remove(studentToGraduate.get());
            graduates.add(studentToGraduate.get());
            System.out.println(studentToGraduate.get().getFirstName() + " has graduated!");
        } else {
            System.out.println("Student with email " + email + " not found in current enrolled students!");
        }
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void graduateStudent(Student student) {
        this.students.remove(student);
        this.graduates.add(student);
    }

    public void displayEnrolledStudents() {
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }

    public void displayGraduates() {
        for (Student graduate : graduates) {
            System.out.println(graduate.getFirstName() + " " + graduate.getLastName());
        }
    }

    public boolean doesStudentBelong(String email) {
        return students.stream().anyMatch(student -> student.getEmail().equals(email));
    }



}