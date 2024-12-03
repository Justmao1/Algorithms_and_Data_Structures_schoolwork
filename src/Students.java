import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Students {
    private List<Student> studentList;

    // Constructor
    public Students() {
        studentList = new ArrayList<>();
    }

    // Add a student to the list
    public void addStudent(Student student) {
        studentList.add(student);
    }

    // Sort students by average score, then by name alphabetically
    public void sortStudents() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int scoreComparison = Double.compare(s1.averageScore(), s2.averageScore());
                if (scoreComparison != 0) {
                    return scoreComparison;
                }
                return s1.getName().compareTo(s2.getName());
            }
        });
    }
    public void sortStudentsByName() {
        Collections.sort(studentList, (s1, s2) -> s1.getName().compareTo(s2.getName()));
    }

    // Display all students' information
    public void displayStudents() {
        for (Student student : studentList) {
            student.display();
        }
    }
    public String generateRandomName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Ivy", "Jack", "Kelly", "Leo", "Mia", "Nick", "Olivia", "Peter", "Queen", "Ryan", "Sara", "Tom"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName; // Combine first and last name
    }
    // Generate random birthday
    public String generateRandomBirthday() {
        Random random = new Random();

        // Generate a random year (between 2000 and 2005)
        int year = 2000 + random.nextInt(6);

        // Generate a random month (1 to 12)
        int month = 1 + random.nextInt(12);

        // Generate a random day based on the month
        int day = 1 + random.nextInt(getDaysInMonth(month, year));

        // Format the birthday as YYYY-MM-DD
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    // Helper method to get the number of days in a month (considering leap years for February)
    public int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:  // January
            case 3:  // March
            case 5:  // May
            case 7:  // July
            case 8:  // August
            case 10: // October
            case 12: // December
                return 31;
            case 4:  // April
            case 6:  // June
            case 9:  // September
            case 11: // November
                return 30;
            case 2:  // February
                return (isLeapYear(year)) ? 29 : 28;
            default:
                return 0;
        }
    }

    // Helper method to check if a year is a leap year
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static void main(String[] args) {
        Random random = new Random();
        Students students = new Students();

        // Generate N students with random scores (let's generate N = 120)
        int N = 120;

        for (int i = 1; i <= N; i++) {
            double chineseScore = 50 + random.nextInt(51); // Random score between 50 and 100
            double englishScore = 50 + random.nextInt(51); // Random score between 50 and 100
            double mathScore = 50 + random.nextInt(51); // Random score between 50 and 100

            String studentNumber = String.format("%03d", i); // Generate student number with leading zeros
            String name = students.generateRandomName(); // Generate a simple name for the student
            String birthday = students.generateRandomBirthday(); // Set a placeholder birthday for simplicity

            Student student = new Student(chineseScore, englishScore, mathScore, studentNumber, name, birthday);
            students.addStudent(student);
        }

        // Sort and display students
        students.sortStudents();
        students.displayStudents();

        // Sort students by name and display again
        students.sortStudentsByName();
        System.out.println("\nAfter sorting by name:");
        students.displayStudents();
    }
}
