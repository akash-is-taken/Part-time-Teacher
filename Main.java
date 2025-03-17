import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create instances of FileManager and TeachingRequirements
        FileManager fileManager = new FileManager();
        TeachingRequirements teachingRequirements = new TeachingRequirements();

        // Create default courses and add them to teaching requirements
        Course course1 = Course.createCourse(1, "math", 3);
        Course course2 = Course.createCourse(2, "science", 2);
        teachingRequirements.addCourse(course1, 5);
        teachingRequirements.addCourse(course2, 4);

        // Create default users
        Director director = new Director("dir123", "Director", "dir123");
        Teacher teacher = new Teacher(1001, "tea123", "tea123");
        Administrator admin = new Administrator("admin123", "admin123");

        // Create a UserManager and register users
        UserManager userManager = new UserManager();
        userManager.addDirector(director);
        userManager.addTeacher(teacher);
        userManager.addAdministrator(admin);

        // Simple interactive menu
        while (true) {
            System.out.println("\n==== Part-Time Teachers Management System ====");
            System.out.println("Select your role:");
            System.out.println("1. Director");
            System.out.println("2. Teacher");
            System.out.println("3. Administrator");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                System.out.println("Exiting system.");
                break;
            }

            switch (choice) {
                case 1:
                    // Director interface with password verification
                    System.out.print("Enter your name: ");
                    String dName = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String dPass = scanner.nextLine();
                    Director loggedDirector = userManager.getDirectorByName(dName);
                    if (loggedDirector == null || !loggedDirector.authenticate(dPass)) {
                        System.out.println("Director authentication failed.");
                        break;
                    }
                    System.out.println("Hello, Director " + loggedDirector.getName());
                    System.out.println("1. Add Teaching Requirement");
                    System.out.println("2. View Teaching Requirements");
                    System.out.print("Enter option: ");
                    int dOption = scanner.nextInt();
                    scanner.nextLine();
                    if (dOption == 1) {
                        System.out.print("Enter course name: ");
                        String cName = scanner.nextLine();
                        System.out.print("Enter training sessions for the course: ");
                        int ts = scanner.nextInt();
                        System.out.print("Enter slots required: ");
                        int slots = scanner.nextInt();
                        scanner.nextLine();
                        Course newCourse = Course.createCourse((int) (Math.random() * 1000), cName, ts);
                        loggedDirector.addRequirement(teachingRequirements, newCourse, slots);
                    } else if (dOption == 2) {
                        loggedDirector.viewRequirements(teachingRequirements);
                        System.out.print("Do you want to save the requirements to file? (Y/N): ");
                        String saveChoice = scanner.nextLine();
                        if (saveChoice.equalsIgnoreCase("Y")) {
                            loggedDirector.saveRequirementsToFile(teachingRequirements, fileManager, "requirements.txt");
                        }
                    }
                    break;
                case 2:
                    // Teacher interface
                    System.out.print("Enter your name: ");
                    String tName = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String tPass = scanner.nextLine();
                    Teacher loggedTeacher = userManager.getTeacherByName(tName);
                    if (loggedTeacher == null || !loggedTeacher.authenticate(tPass)) {
                        System.out.println("Teacher authentication failed.");
                        break;
                    }
                    System.out.println("Hello, Teacher " + loggedTeacher.getName());
                    System.out.println("1. Set Available Slots");
                    System.out.println("2. View Available Courses");
                    System.out.println("3. Select a Course");
                    System.out.println("4. Submit Training Application");
                    System.out.println("5. View Training Status");
                    System.out.print("Enter option: ");
                    int tOption = scanner.nextInt();
                    scanner.nextLine();
                    if (tOption == 1) {
                        System.out.print("Enter number of available slots: ");
                        int available = scanner.nextInt();
                        scanner.nextLine();
                        loggedTeacher.setAvailableSlots(available);
                        System.out.println("Available slots updated to " + loggedTeacher.getAvailableSlots());
                    } else if (tOption == 2) {
                        loggedTeacher.viewAvailableCourses();
                    } else if (tOption == 3) {
                        System.out.print("Enter course ID to select: ");
                        int courseId = scanner.nextInt();
                        scanner.nextLine();
                        loggedTeacher.selectCourse(courseId);
                    } else if (tOption == 4) {
                        System.out.print("Enter course name for training application: ");
                        String courseName = scanner.nextLine();
                        System.out.print("Enter requested training sessions: ");
                        int reqSessions = scanner.nextInt();
                        scanner.nextLine();
                        loggedTeacher.submitTrainingApplication(courseName, reqSessions);
                    } else if (tOption == 5) {
                        loggedTeacher.viewTrainingStatus();
                    }
                    break;
                case 3:
                    // Administrator interface with password verification
                    System.out.print("Enter your username: ");
                    String aName = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String aPass = scanner.nextLine();
                    Administrator loggedAdmin = userManager.getAdministratorByName(aName);
                    if (loggedAdmin == null || !loggedAdmin.authenticate(aPass)) {
                        System.out.println("Administrator authentication failed.");
                        break;
                    }
                    System.out.println("Hello, Administrator " + aName);
                    System.out.println("1. View Teaching Requirements and Teacher Info");
                    System.out.println("2. Assign Teacher to Course");
                    System.out.println("3. Organize Training for Teacher");
                    System.out.println("4. Update Training Details");
                    System.out.println("5. View Pending Training Requests");
                    System.out.print("Enter option: ");
                    int aOption = scanner.nextInt();
                    scanner.nextLine();
                    if (aOption == 1) {
                        teachingRequirements.viewRequirements();
                        System.out.println("Teacher Information:");
                        Teacher t = userManager.getTeacherByName(teacher.getName());
                        if (t != null) {
                            System.out.println("Name: " + t.getName() + ", Available Slots: " + t.getAvailableSlots());
                        }
                    } else if (aOption == 2) {
                        System.out.print("Enter teacher name to assign: ");
                        String assignTeacherName = scanner.nextLine();
                        System.out.print("Enter course name to assign: ");
                        String assignCourseName = scanner.nextLine();
                        loggedAdmin.assignTeacherToCourse(assignTeacherName, assignCourseName);
                    } else if (aOption == 3) {
                        System.out.print("Enter teacher name for training: ");
                        String trTeacher = scanner.nextLine();
                        System.out.print("Enter course name for training: ");
                        String trCourse = scanner.nextLine();
                        System.out.print("Enter required training sessions: ");
                        int reqSessions = scanner.nextInt();
                        scanner.nextLine();
                        loggedAdmin.organizeTraining(trTeacher, trCourse, reqSessions);
                    } else if (aOption == 4) {
                        System.out.print("Enter teacher name for updating training: ");
                        String upTeacher = scanner.nextLine();
                        System.out.print("Enter course name for training update: ");
                        String upCourse = scanner.nextLine();
                        System.out.print("Enter completed sessions: ");
                        int compSessions = scanner.nextInt();
                        scanner.nextLine();
                        loggedAdmin.updateTraining(upTeacher, upCourse, compSessions);
                    } else if (aOption == 5) {
                        loggedAdmin.viewPendingRequests();
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
