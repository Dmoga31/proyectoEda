package Test;

import Cache.Creator;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {


    @org.junit.jupiter.api.Test
    void createFile() {
        try {
            assertTrue(Creator.createFile("File_Test"));
        } catch (Exception e) {
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void createFolder() {
        Creator creator = new Creator();
        assertTrue(creator.createFolder("FolderTest"));
    }



    @org.junit.jupiter.api.Test
    void createFileIn() {
        Creator creator = new Creator();
        creator.createFolder("Folder_Test");
        try {
            assertTrue(creator.createFileIn("File_Test","Folder_Test"));
        } catch (Exception e) {
            fail();
        }
    }



    @org.junit.jupiter.api.Test
    void readFolder() {
        try {
            Creator.readFolder("Folder_Test");
        } catch (Exception e) {
            fail();
        }
    }



    @org.junit.jupiter.api.Test
    void deleteFile() {
        try {
            Creator.deleteFile("File_Test");
        } catch (Exception e) {
            fail();
        }
    }
}