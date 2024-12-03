public class Student {
    private final double chineseScore;
    private final double englishScore;
    private final double mathScore;
    private final String studentNumber;
    private final String name;
    private final String birthday;

    // Constructor
    public Student(double chineseScore, double englishScore, double mathScore,
                   String studentNumber, String name, String birthday) {
        this.chineseScore = chineseScore;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.studentNumber = studentNumber;
        this.name = name;
        this.birthday = birthday;
    }

    // Calculate average score
    public double averageScore() {
        return (chineseScore + englishScore + mathScore) / 3;
    }

    // Getters for name and average score for sorting
    public String getName() {
        return name;
    }

    public double getAverageScore() {
        return averageScore();
    }

    // Display student info
    public void display() {
        System.out.printf("Name: %s, Student Number: %s, Chinese: %.2f, English: %.2f, Math: %.2f, Birthday: %s, Average Score: %.2f\n",
                name, studentNumber, chineseScore, englishScore, mathScore, birthday, averageScore());
    }
}