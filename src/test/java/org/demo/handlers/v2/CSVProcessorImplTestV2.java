package org.demo.handlers.v2;

import org.demo.CSVObject;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CSVProcessorImplTestV2 {
    private static final String fileName = "./test.csv";

    @Test
    public void testStateComparator(){
        initCorrectMockFile();
        File testFile = new File(fileName);

        try {
            Set<CSVObject> actualData = CSVProcessorImpl_v2.sortAsReadingFile(testFile, "STATE");

            assertTrue(actualData.contains(new CSVObject("Portland,Oregon,The City That Works,Ted Wheeler")));
            assertTrue(actualData.contains(new CSVObject("Boston,Massachusetts,It’s All Here,Marty Walsh")));
            assertTrue(actualData.contains(new CSVObject("New Orleans,Louisiana,City of Yes,LaToya Cantrell")));
            assertFalse(actualData.contains(new CSVObject("New York,New York,Big Apple,Bloomberg")));
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
            System.out.println("Successfully created and wrote data in test file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testSortingWithException(){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.close();
            System.out.println("Successfully create test file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File testFile = new File(fileName);
        try {
            Set<CSVObject> actualData = CSVProcessorImpl_v2.sortAsReadingFile(testFile, "STATE");
        } finally {
            testFile.delete();
        }
    }

}