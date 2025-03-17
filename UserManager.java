import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<Director> directors;
    private List<Teacher> teachers;
    private List<Administrator> administrators;

    public UserManager() {
        directors = new ArrayList<>();
        teachers = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public void addDirector(Director d) {
        directors.add(d);
    }

    public void addTeacher(Teacher t) {
        teachers.add(t);
    }

    public void addAdministrator(Administrator a) {
        administrators.add(a);
    }

    public Director getDirectorByName(String name) {
        for (Director d : directors) {
            if (d.getName().equalsIgnoreCase(name))
                return d;
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {
        for (Teacher t : teachers) {
            if (t.getName().equalsIgnoreCase(name))
                return t;
        }
        return null;
    }

    public Administrator getAdministratorByName(String username) {
        for (Administrator a : administrators) {
            if (a.getUsername().equalsIgnoreCase(username))
                return a;
        }
        return null;
    }
}
