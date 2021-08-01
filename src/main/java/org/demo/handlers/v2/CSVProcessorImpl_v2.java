package org.demo.handlers.v2;

import org.demo.CSVInputReader;
import org.demo.CSVObject;
import org.demo.handlers.CSVProcessorAPI;

import java.io.File;
import java.util.Comparator;
import java.util.Set;

public class CSVProcessorImpl_v2 implements CSVProcessorAPI {

    @Override
    public Set<CSVObject> process(File filePath, String paramForSorting) {
        return sortAsReadingFile(filePath, paramForSorting);
    }

    public static Set<CSVObject> sortAsReadingFile(File filePath, String paramForSorting) {
        Comparator<CSVObject> comparator = getComparatorByParam(paramForSorting);
        CSVInputReader reader = new CSVInputReader(filePath, comparator);

        Set<CSVObject> csvObjects = reader.getData();
        if (csvObjects.isEmpty())
            throw new IllegalStateException("There is no data in: " + filePath.toString());
        return csvObjects;
    }

    public static Comparator<CSVObject> getComparatorByParam(String paramForSorting) {
        switch (paramForSorting) {
            case "NAME":
                return Comparator.comparing(CSVObject::getName).reversed();
            case "STATE":
                return Comparator.comparing(CSVObject::getState).reversed();
            case "MOTTO":
                return Comparator.comparing(CSVObject::getMotto).reversed();
            case "MAYOR":
                return Comparator.comparing(CSVObject::getMayor).reversed();
            default:
                throw new IllegalStateException("Incorrect sorting parameter: " + paramForSorting);
        }
    }
}
