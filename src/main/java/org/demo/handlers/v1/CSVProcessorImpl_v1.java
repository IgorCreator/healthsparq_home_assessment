package org.demo.handlers.v1;

import org.demo.CSVInputReader;
import org.demo.CSVObject;
import org.demo.handlers.CSVProcessorAPI;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CSVProcessorImpl_v1 implements CSVProcessorAPI {

    @Override
    public Set<CSVObject> process(File filePath, String paramForSorting) {
        return readAndAfterSort(filePath, paramForSorting);
    }

    public static Set<CSVObject> readAndAfterSort(File filePath, String paramForSorting) {
        CSVInputReader reader = new CSVInputReader(filePath);
        Set<CSVObject> csvObjects = reader.getData();
        if (csvObjects.isEmpty())
            throw new IllegalStateException("There is no data in: " + filePath.toString());

        return sortDataByParameter(csvObjects, paramForSorting);
    }

    public static Set<CSVObject> sortDataByParameter(Set<CSVObject> csvObjects, String paramForSorting) {
        switch (paramForSorting) {
            case "NAME":
                return csvObjects.stream().sorted(Comparator.comparing(CSVObject::getName).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
            case "STATE":
                return csvObjects.stream().sorted(Comparator.comparing(CSVObject::getState).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
            case "MOTTO":
                return csvObjects.stream().sorted(Comparator.comparing(CSVObject::getMotto).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
            case "MAYOR":
                return csvObjects.stream().sorted(Comparator.comparing(CSVObject::getMayor).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
            default:
                throw new IllegalStateException("Incorrect sorting parameter: " + paramForSorting);
        }
    }

}
