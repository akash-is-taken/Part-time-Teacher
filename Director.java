public class Director {
    private final String name;
    private final String role;
    private final String passwordHash;

    public Director(String name, String role, String passwordHash) {
        this.name = name;
        this.role = role;
        this.passwordHash = passwordHash;
    }


    public void addRequirement(TeachingRequirements teachingRequirements, Course course, int slotsRequired) {
        System.out.println(name + " is adding a new requirement...");
        teachingRequirements.addCourse(course, slotsRequired);
    }


    public void viewRequirements(TeachingRequirements teachingRequirements) {
        System.out.println(name + " is viewing teaching requirements...");
        teachingRequirements.viewRequirements();
    }


    public void saveRequirementsToFile(TeachingRequirements teachingRequirements, FileManager FileManager, String filepath) {
        System.out.println(name + " is saving teaching requirements to file...");
        teachingRequirements.saveRequirementsToFile(FileManager, filepath);
    }


    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
