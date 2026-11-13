import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        Scanner Input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("    JAVA OOP CRUD + SP MENU   ");
            System.out.println("==============================");
            System.out.println("[1] Magdagdag ng User (Create)");
            System.out.println("[2] Tingnan ang Lahat ng Users (Read)");
            System.out.println("[3] I-update ang User (Update)");
            System.out.println("[4] Burahin ang User (Delete)");
            System.out.println("[5] Lumabas (Exit)");
            System.out.print("Pumili ng numero (1-5): ");

            if (Input.hasNextInt()) {
                choice = Input.nextInt();
                Input.nextLine(); // Consume the newline character

                switch (choice) {

                    case 1:
                        System.out.println("Input your name please: ");
                        String name = Input.nextLine();
                        System.out.println("Input your email please: ");
                        String email = Input.nextLine();
                        System.out.println("Input your age please: ");
                        int age = Input.nextInt();

                        User newUser = new User(name, email, age);
                        userDao.createUser(newUser);
                        break;

                    case 2:
                        System.out.println("\n---- list of the the USERS ---- ");
                        List<User> users = userDao.getAllUsers();
                        if (users.isEmpty()) {
                            System.out.println("We can't see any users right now duane");
                        } else {
                            for (User user : users) {
                                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: "
                                        + user.getEmail() + ", Age: " + user.getAge());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Input the ID of the user you want to update: ");
                        int UpdateId = Input.nextInt();
                        Input.nextLine();

                        System.out.println("Input the new name of the User: ");
                        String newName = Input.nextLine();

                        System.out.println("Input the new email of the User: ");
                        String newEmail = Input.nextLine();

                        System.out.println("Input the new age of the User: ");
                        int newAge = Input.nextInt();

                        User updateUser = new User(UpdateId, newName, newEmail, newAge);
                        userDao.updateUser(updateUser);
                        break;

                    case 4:
                        System.out.println("Put the User Id para madelete ko na pagkatao niya");
                        int deleteId = Input.nextInt();
                        userDao.deleteUser(deleteId);
                        break;

                    case 5:
                        System.out.println("Thank you for using this system bwakanang sheems ka");
                        break;

                    default:
                        System.out.println(
                                "Click lang kasi 1-5 sinabi ko bang 1-100? bakit click ka nang click kung ano-ano!");

                }

            } else {
                System.out.println("Hays julet");
                Input.next();
            }

        } while (choice != 5);

        Input.close();
    }
}