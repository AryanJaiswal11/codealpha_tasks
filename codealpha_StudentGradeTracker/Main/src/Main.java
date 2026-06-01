import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class Student {

        private String name;
        private int rollNo;
        private float subject1;
        private float subject2;
        private float subject3;

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setRollNo(int rollNo) {
            this.rollNo = rollNo;
        }

        public void setSubject1(float subject1) {
            this.subject1 = subject1;
        }

        public void setSubject2(float subject2) {
            this.subject2 = subject2;
        }

        public void setSubject3(float subject3) {
            this.subject3 = subject3;
        }

        // Getters
        public String getName() {
            return name;
        }

        public int getRollNo() {
            return rollNo;
        }

        // Methods
        public float average() {
            return (subject1 + subject2 + subject3) / 3;
        }

        public float highest() {
            return Math.max(subject1, Math.max(subject2, subject3));
        }

        public float lowest() {
            return Math.min(subject1, Math.min(subject2, subject3));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        HashMap<Integer, Boolean> RollMap = new HashMap<>();

        while (true) {

            System.out.println("\n===== Student Grade Tracker =====");
            System.out.println("1. Add Student\n" +
                    "2. View All Students\n" +
                    "3. View Specific Student\n" +
                    "4. Show Topper\n" +
                    "5. Show Class Average\n" +
                    "6. Exit\n"+
                    "Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                Student s = new Student();

                System.out.print("Enter Name: ");
                s.setName(sc.nextLine());

                while(true) {
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    if(!RollMap.containsKey(roll)){
                        RollMap.put(roll, true);
                        s.setRollNo(roll);
                        break;
                    }
                    System.out.println("Roll Number already exists!");
                }

                int n = 1;

                while (n <= 3) {

                    System.out.print("Enter Subject " + n + " Marks: ");
                    float marks = sc.nextFloat();

                    if (marks >= 0 && marks <= 100) {

                        if (n == 1) {
                            s.setSubject1(marks);
                        } else if (n == 2) {
                            s.setSubject2(marks);
                        } else {
                            s.setSubject3(marks);
                        }

                        n++;

                    } else {
                        System.out.println("Invalid Marks! Enter between 0 and 100.");
                    }
                }

                students.add(s);

                System.out.println("Student Added Successfully!");

            } else if (choice == 2) {

                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {

                    for (Student s : students) {

                        System.out.println("\n----------------------");
                        System.out.println("Name: " + s.getName()+"\n"+
                                "Roll No: " + s.getRollNo()+"\n"+
                                "Average: " + s.average()+"\n"+
                                "Highest: " + s.highest()+"\n"+
                                "Lowest: " + s.lowest());
                    }
                }

            }
            else if (choice == 3) {

                System.out.print("Enter Student Name: ");
                String searchName = sc.nextLine();

                boolean found = false;

                for (Student s : students) {

                    if (s.getName().equalsIgnoreCase(searchName)) {

                        System.out.println("\n----------------------");
                        System.out.println("Name: " + s.getName() + "\n" +
                                "Roll No: " + s.getRollNo() + "\n" +
                                "Average: " + s.average() + "\n" +
                                "Highest: " + s.highest() + "\n" +
                                "Lowest: " + s.lowest());

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Student not found.");
                }
            }
            else if (choice == 4) {

                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {

                    Student topper = students.get(0);

                    for (Student s : students) {

                        if (s.average() > topper.average()) {
                            topper = s;
                        }
                    }

                    System.out.println("\n===== Topper =====");
                    System.out.println("Name: " + topper.getName() + "\n" +
                            "Roll No: " + topper.getRollNo() + "\n" +
                            "Average: " + topper.average() + "\n" +
                            "Highest: " + topper.highest() + "\n" +
                            "Lowest: " + topper.lowest());
                }
            }
            else if (choice == 5) {

                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {

                    float total = 0;

                    for (Student s : students) {
                        total += s.average();
                    }

                    float classAverage = total / students.size();

                    System.out.println("Class Average: " + classAverage);
                }
            }

            else if (choice == 6) {

                System.out.println("Exiting Program...");
                break;

            } else {

                System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}