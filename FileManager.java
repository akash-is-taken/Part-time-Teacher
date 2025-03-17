import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public void createFile(String filepath) {
        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    public void updateFile(String filepath, String content) {
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            fileWriter.write(content + "\n");
            System.out.println("File updated: " + filepath);
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }

    public void readFile(String filepath) {
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            System.out.println("Reading file: " + filepath);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    // Static methods used by other classes
    public static void writeToFile(String filepath, String content) {
        try (FileWriter writer = new FileWriter(filepath, true)) {
            writer.write(content + "\n");
            System.out.println("Data written to " + filepath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filepath);
            e.printStackTrace();
        }
    }

    public static void writeToFileOverwrite(String filepath, List<String> lines) {
        try (FileWriter writer = new FileWriter(filepath, false)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("File overwritten: " + filepath);
        } catch (IOException e) {
            System.out.println("Error overwriting file: " + filepath);
            e.printStackTrace();
        }
    }

    public static List<String> readFromFile(String filepath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filepath);
            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filepath);
            e.printStackTrace();
        }
        return lines;
    }
    
}
