package app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        Database.init(); // ensure table exists

        while (true) {
            System.out.println("\n=== Student Database App ===");
            System.out.println("1) Add student");
            System.out.println("2) List students");
            System.out.println("3) Update student");
            System.out.println("4) Delete student");
            System.out.println("0) Exit");
            System.out.print("Select: ");

            int choice = readInt();
            try {
                switch (choice) {
                    case 1: add(); break;
                    case 2: list(); break;
                    case 3: update(); break;
                    case 4: deleteById(); break;
                    case 0: System.out.println("Bye!"); return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("DB error: " + e.getMessage());
            }
        }
    }

    private static void add() throws SQLException {
        System.out.print("Name  : "); String name = sc.nextLine().trim();
        System.out.print("Email : "); String email = sc.nextLine().trim();
        System.out.print("Course: "); String course = sc.nextLine().trim();
        dao.add(new Student(name, email, course));
        System.out.println("Added.");
    }

    private static void list() throws SQLException {
        List<Student> all = dao.getAll();
        System.out.println(String.format("%-4s | %-20s | %-25s | %-15s","ID","NAME","EMAIL","COURSE"));
        System.out.println("-------------------------------------------------------------------");
        for (Student s : all) System.out.println(s);
        if (all.isEmpty()) System.out.println("(no records)");
    }

    private static void update() throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = readInt();
        Student s = dao.getById(id);
        if (s == null) { System.out.println("Not found."); return; }

        System.out.print("New name (" + s.getName() + "): ");
        String name = emptyKeep(sc.nextLine(), s.getName());

        System.out.print("New email (" + s.getEmail() + "): ");
        String email = emptyKeep(sc.nextLine(), s.getEmail());

        System.out.print("New course (" + s.getCourse() + "): ");
        String course = emptyKeep(sc.nextLine(), s.getCourse());

        dao.update(new Student(id, name, email, course));
        System.out.println("Updated.");
    }

    private static void deleteById() throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = readInt();
        dao.delete(id);
        System.out.println("Deleted (if it existed).");
    }

    private static int readInt() {
        while (true) {
            try {
                int v = Integer.parseInt(sc.nextLine().trim());
                return v;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    private static String emptyKeep(String input, String keep) {
        String t = input.trim();
        return t.isEmpty() ? keep : t;
    }
}
