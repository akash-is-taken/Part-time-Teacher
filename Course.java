public class Course {
    private int id;
    private String name;
    private int trainingSessions;

    public Course(int id, String name, int trainingSessions) {
        this.id = id;
        this.name = name;
        this.trainingSessions = trainingSessions;
    }

    public static Course createCourse(int id, String name, int trainingSessions) {
        return new Course(id, name, trainingSessions);
    }

    public void updateCourse(String name, int trainingSessions) {
        this.name = name;
        this.trainingSessions = trainingSessions;
        System.out.println("Course updated: ID " + id + ", Name: " + name + ", Training Sessions: " + trainingSessions);
    }

    public void deleteCourse() {
        System.out.println("Course deleted: ID " + id + ", Name: " + name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTrainingSessions() {
        return trainingSessions;
    }

    @Override
    public String toString() {
        return "Course: ID " + id + ", Name: " + name + ", Training Sessions: " + trainingSessions;
    }
}
