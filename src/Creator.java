import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Creator {
    public static void createFile() {
        String fileName = System.in.toString();
        File newFile = new File(fileName);
        try {
            boolean fileCreated = newFile.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e);
        }
    }

    public static void createFolder(String folderName) {
        File newFolder = new File(folderName);
        boolean folderCreated = newFolder.mkdir();
        if(folderCreated){
            System.out.println("Folder created: " + newFolder.getName());
        } else {
            System.out.println("Folder alredy exists.");
        }
    }

    public static void writeInFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(text);

    }

}
