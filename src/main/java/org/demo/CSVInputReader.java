package org.demo;

import java.io.*;
import java.util.*;

public class CSVInputReader {
    private final Set<CSVObject> data;

    public CSVInputReader(File fileToParse, Comparator<CSVObject> sortBy) {
        data = new TreeSet<>(sortBy);
        csvParsing(fileToParse);
    }

    public CSVInputReader(File fileToParse) {
        data = new HashSet<>();
        csvParsing(fileToParse);
    }

    private void csvParsing(File fileToParse) {
        boolean properFileFormat = false;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToParse))) {
            while ((line = reader.readLine()) != null) {
                if (isHeaderLine(line)){
                    properFileFormat = true;
                    continue;
                } else if(!properFileFormat)
                    throw new IllegalArgumentException("Incorrect file format. Please check header.");

                data.add(new CSVObject(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isHeaderLine(String line) {
        return line.contains("Name") && line.contains("State") && line.contains("Motto") && line.contains("Mayor");
    }

    public Set<CSVObject> getData() {
        return data;
    }
}
