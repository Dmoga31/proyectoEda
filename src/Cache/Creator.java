package Cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Creator {

    /**
     * Create a new file with the name passed as argument.
     *
     * @param fileName Name of the file to create.
     * @return True if file was created.
     */
    public static boolean createFile(String fileName) {
        try {
            File newFile = new File(fileName);
            return newFile.createNewFile();

        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
            return false;
        }
    }

    /**
     * Create a new folder with the name passed as argument.
     *
     * @param folderName Name of the folder to create.
     * @return True if folder was created.
     */
    public static boolean createFolder(String folderName) {
        File newFolder = new File(folderName);
            boolean folderCreated = newFolder.mkdir();
            if(folderCreated){
                System.out.println("Folder created");
                return true;
            } else if (newFolder.exists()){
                System.out.println("Folder already exists");
                return false;
            } else {
                System.out.println("Folder not created");
                return false;
            }
    }

    /**
     * Delete a file with the name passed as argument.
     *
     * @param fileName Name of the file to write.
     */
    public static void deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("File " + fileName + " deleted: " + file.delete());
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Write a string in a file.
     *
     * @param fileName Name of the file to write.
     * @param text     Content to write in the file.
     * @return
     */
    public static boolean writeInFile(String fileName, String text) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        FileWriter fileWriter = new FileWriter(fileName);
        if(file.exists()){
            fileWriter.write(text);
            fileWriter.close();
            return true;
        } else {
            System.out.println("File not found");
            return false;
        }
    }

    /**
     * Creates a file in a folder.
     *
     * @param fileName   Name of the file to look for.
     * @param folderName Name of the folder to look for.
     * @return boolean
     */
    public static boolean createFileIn(String fileName, String folderName){
        File folder = new File(folderName);
        File newFile = new File(folder + "\\" + fileName);
        try {
            boolean fileCreated = newFile.createNewFile();
            if (fileCreated) {
                System.out.println("File: " + newFile.getName() + " created in: " + newFile.getParent());
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
            return false;
        }
    }

    /**
     * Check if a file exists.
     * @param fileName Name of the file to look for.
     * @return true if file exists.
     */
    public static boolean existFile(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    /**
     * Read all files in a folder.
     *
     * @param folderName Name of the folder to look for.
     * @return Array of files names in the folder.
     */
    public static String[] readFolder(String folderName)  {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        String[] filesNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            filesNames[i] = files[i].getName().replace(".txt", "");
        }
        return filesNames;
    }

    /**
     * Read a file.
     *
     * @param fileName Name of the file to read.
     * @return String with the content of the file.
     * @throws IOException If file not found.
     */
    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String fileContent = "";
        if (file.exists()) {
            while (sc.hasNextLine()) {
                fileContent += sc.nextLine();
            }
        } else {
            System.out.println("File not found");
        }
        sc.close();
        return fileContent;
    }
}
