import java.util.Scanner;
abstract class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public abstract void displayInfo();
}
interface Gradeable {
    void addGrade(int grade);
    int calculateQuarterGrade();
}
class Subject implements Gradeable {
    private String name;
    private int[] grades;
    private int gradeCount;
    public Subject(String name, int gradeCount) {
        this.name = name;
        this.grades = new int[gradeCount];
        this.gradeCount = gradeCount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void addGrade(int grade) {
        if (grade >= 1 && grade <= 5 && gradeCount > 0) {
            grades[grades.length - gradeCount] = grade;
            gradeCount--;
        } else {
            System.out.println("Неверная оценка!");
        }
    }
    @Override
    public int calculateQuarterGrade() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = (double) sum / grades.length;
        return (int) Math.round(average);
    }
}
class Student extends Person implements Gradeable {
    private Subject[] subjects;
    public Student(String name, Subject[] subjects) {
        super(name);
        this.subjects = subjects;
    }
    public Subject[] getSubjects() {
        return subjects;
    }
    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }
    @Override
    public void addGrade(int grade) {
        System.out.println("Нельзя добавить оценку без указания предмета!");
    }
    public void addGrade(int subjectIndex, int grade) {
        if (subjectIndex >= 0 && subjectIndex < subjects.length) {
            subjects[subjectIndex].addGrade(grade);
        } else {
            System.out.println("Неверный индекс предмета!");
        }
    }
    @Override
    public int calculateQuarterGrade() {
        int totalGrade = 0;
        int count = 0;
        for (Subject subject : subjects) {
            int subjectGrade = subject.calculateQuarterGrade();
            if (subjectGrade > 0) {
                totalGrade += subjectGrade;
                count++;
            }
        }
        if (count > 0) {
            return totalGrade / count;
        } else {
            return 0;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Ученик: " + getName());
        for (int i = 0; i < subjects.length; i++) {
            System.out.println("Предмет " + (i + 1) + ": " + subjects[i].getName());
            System.out.println("Оценка за четверть: " + arrayToString(subjects[i].calculateQuarterGrade()));
        }
        System.out.println("Средний балл за четверть: " + calculateQuarterGrade());
    }

    private String arrayToString(int grade) {
        return grade == 0 ? "Оценок нет" : String.valueOf(grade);
    }
}

public class Admin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя ученика: ");
        String studentName = scanner.nextLine();

        System.out.print("Введите количество предметов: ");
        int numberOfSubjects = scanner.nextInt();
        scanner.nextLine();

        Subject[] subjects = new Subject[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Введите название предмета " + (i + 1) + ": ");
            String subjectName = scanner.nextLine();
            System.out.print("Введите количество оценок для " + subjectName + ": ");
            int numberOfGrades = scanner.nextInt();
            scanner.nextLine();

            subjects[i] = new Subject(subjectName, numberOfGrades);
        }

        Student student = new Student(studentName, subjects);

        for (int i = 0; i < subjects.length; i++) {
            System.out.print("Введите оценки для " + subjects[i].getName() + " через пробел: ");
            String[] gradeInputs = scanner.nextLine().split(" ");
            for (String gradeInput : gradeInputs) {
                int grade = Integer.parseInt(gradeInput);
                student.addGrade(i, grade);
            }
        }

        student.displayInfo();

        scanner.close();
    }
}

