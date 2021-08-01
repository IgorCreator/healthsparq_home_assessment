package org.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVOutputWriter {

    private static final String SLASH = "/";
    private static final String FINAL_FILE_NAME = "output";
    private static final String FILE_EXTENCTION = ".csv";
    private static final String DATA_JOINER = ",";

    private static final String header = "Name,State,Motto,Mayor";

    public void writeSortedData(File dirToWrite, Set<CSVObject> sortedData) throws IOException {
        File csvOutputFile = new File(dirToWrite + SLASH + FINAL_FILE_NAME + FILE_EXTENCTION);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(header);
            sortedData.stream()
                    .map(entry -> new String[]{entry.getName(), entry.getState(), entry.getMotto(), entry.getMayor()})
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(DATA_JOINER));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
