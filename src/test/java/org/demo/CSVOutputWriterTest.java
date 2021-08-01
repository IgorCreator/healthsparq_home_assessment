package org.demo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CSVOutputWriterTest {

    private static final String fileName = "./test.csv";

    @Test
    public void testReader(){
        Set<CSVObject> data = initDataToWrite();
        CSVOutputWriter writer = new CSVOutputWriter();

        try {
            writer.writeSortedData(new File("."), data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File expectedFile = new File("./output.csv");
        assertTrue(expectedFile.isFile());
        expectedFile.delete();
    }

    private Set<CSVObject> initDataToWrite() {
        Set<CSVObject> data = new HashSet<>();
        data.add(new CSVObject("Portland,Oregon,The City That Works,Ted Wheeler"));
        data.add(new CSVObject("Boston,Massachusetts,It’s All Here,Marty Walsh"));
        data.add(new CSVObject("New Orleans,Louisiana,City of Yes,LaToya Cantrell"));
        data.add(new CSVObject("New York,New York,Big Apple,Bloomberg"));
        return data;
    }

}