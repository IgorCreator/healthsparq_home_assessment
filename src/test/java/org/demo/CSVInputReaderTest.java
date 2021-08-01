package org.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

public class CSVInputReaderTest {
    private static final String fileName = "./test.csv";

    @Test
    public void testReader(){
        initCorrectMockFile();
        File testFile = new File(fileName);

        CSVInputReader reader = new CSVInputReader(testFile);
        try {
            Set<CSVObject> data = reader.getData();
            assertTrue(data.contains(new CSVObject("Portland,Oregon,The City That Works,Ted Wheeler")));
            assertTrue(data.contains(new CSVObject("Boston,Massachusetts,It’s All Here,Marty Walsh")));
            assertTrue(data.contains(new CSVObject("New Orleans,Louisiana,City of Yes,LaToya Cantrell")));
            assertFalse(data.contains(new CSVObject("New York,New York,Big Apple,Bloomberg")));
        } finally {
            testFile.delete();
        }
    }

    private void initCorrectMockFile(){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Name,State,Motto,Mayor\n");
            myWriter.write("Portland,Oregon,The City That Works,Ted Wheeler\n");
            myWriter.write("Boston,Massachusetts,It’s All Here,Marty Walsh\n");
            myWriter.write("New Orleans,Louisiana,City of Yes,LaToya Cantrell\n");
            myWriter.close();
            System.out.println("Successfully created test file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReaderWithException(){
        initIncorrectMockFile();
        File testFile = new File(fileName);

        try {
            CSVInputReader reader = new CSVInputReader(testFile);
        } finally {
            testFile.delete();
        }
    }

    private void initIncorrectMockFile(){
        try {

            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Portland,Oregon,The City That Works,Ted Wheeler");
            myWriter.close();
            System.out.println("Successfully created test file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}