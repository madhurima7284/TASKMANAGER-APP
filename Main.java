import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userId = -1;

        System.out.println("=== Task Manager ===");
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                if (User.register(username, password)) System.out.println("Registration successful!");
            } else if (choice == 2) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                userId = User.login(username, password);
                if (userId != -1) {
                    System.out.println("Login successful!");
                    break;
                } else {
                    System.out.println("Invalid credentials.");
                }
            } else {
                System.exit(0);
            }
        }

        // Logged-in operations
        while (true) {
            System.out.println("\n1. Create Task\n2. View Tasks\n3. Delete Task\n4. Logout");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Description: ");
                String desc = sc.nextLine();
                Task.create(userId, title, desc);
            } else if (choice == 2) {
                Task.view(userId);
            } else if (choice == 3) {
                System.out.print("Task ID to delete: ");
                int taskId = sc.nextInt();
                Task.delete(userId, taskId);
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }

        sc.close();
    }
}