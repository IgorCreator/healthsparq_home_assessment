package org.demo.handlers;

import org.demo.CSVObject;

import java.io.File;
import java.util.Set;

public interface CSVProcessorAPI {
    Set<CSVObject> process(File filePath, String paramForSorting);
}
