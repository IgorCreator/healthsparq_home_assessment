package org.demo;

import org.demo.handlers.v1.CSVProcessorImpl_v1;
import org.demo.handlers.v2.CSVProcessorImpl_v2;

import java.io.*;
import java.util.*;

/* Task:
* Imagine a CSV file called input.csv, which contains a line of comma-separated strings as column headers and
* then multiple lines of data.  Each line is terminated with a new line character. Using an appropriate language,
* write a program that reads input.csv, sorts its lines into descending alphabetical order based on the parameter
* passed in (which should match one of the headers), and writes the sorted lines in comma-separated format to a new file
* called output.csv.
* */

public class Main {

    public static String inputFile;
    public static String sortParam;
    public static String outputDir;
    public static Integer solutionVer = 1;

    private static final Scanner readIn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        readArgs(args);
        System.out.println("Program workflow: ");
        File filePath = enterInputFilePath();
        System.out.println("Step 1: File input.csv exists. Path: " + filePath);
        String paramForSorting = enterParamForSorting();
        System.out.println("Step 2: Selected sorting parameter: " + paramForSorting);

        Set<CSVObject> sortedData;
        if (solutionVer == 1)
            sortedData = CSVProcessorImpl_v1.readAndAfterSort(filePath, paramForSorting);
        else // solutionVer == 2
            sortedData = CSVProcessorImpl_v2.sortAsReadingFile(filePath, paramForSorting);
        System.out.println("Step 3: Data successfully read from input file and sorted");

        File dirToWrite = enterOutputFolder();
        System.out.println("Step 4: Saving sorted data in the output file. Path to output.csv: " + dirToWrite);
        CSVOutputWriter outputWriter = new CSVOutputWriter();
        outputWriter.writeSortedData(dirToWrite, sortedData);
    }

    private static File enterInputFilePath() throws FileNotFoundException {
        File fileToRead;
        if(inputFile == null)
            fileToRead = readInFromConsole("Please select enter absolute path of the input.csv file:");
        else
            fileToRead = new File(inputFile);

        if (!fileToRead.isFile())
            throw new FileNotFoundException("File not found: " + fileToRead.toString());
        else
            return fileToRead;
    }

    private static File enterOutputFolder() throws FileNotFoundException {
        File dirToWrite;
        if(outputDir == null)
            dirToWrite = readInFromConsole("Please enter folder to export output.csv:");
        else
            dirToWrite = new File(outputDir);

        if (!dirToWrite.isDirectory())
            throw new FileNotFoundException("Folder not found: " + dirToWrite.toString());
        else
            return dirToWrite;
    }

    private static File readInFromConsole(String s) {
        System.out.println(s);
        String dirForOutFile = readIn.nextLine();
        return new File(dirForOutFile);
    }

    private static String enterParamForSorting() {
        if(sortParam != null && !sortParam.isEmpty())
            return sortParam.toUpperCase().trim();
        else {
            System.out.println("Please enter sorting header. Can be only: \"Name\", \"State\" , \"Motto\" , \"Mayor\":");
            return readIn.nextLine().toUpperCase().trim();
        }
    }

    private static void readArgs(String[] args) {
        int argsSize = args.length;
        for(int i = 0; i < argsSize; ++i){
            if(args[i].equalsIgnoreCase("-inputFile")){
                if(++i >= argsSize) usage();

                inputFile = args[i];
            } else if(args[i].equalsIgnoreCase("-sortParam")){
                if(++i >= argsSize) usage();

                sortParam = args[i];
            } else if(args[i].equalsIgnoreCase("-outputDir")){
                if(++i >= argsSize) usage();

                outputDir = args[i];
            } else if(args[i].equalsIgnoreCase("-solutionVer")){
                if(++i >= argsSize) usage();

                solutionVer = Integer.parseInt(args[i]);
            } else if(i != 0) {
                usage();
            }
        }

        if(argsSize != 0) {
            System.out.println("----------------");
            System.out.println("Input parameters:");
            System.out.println("inputFile   : " + inputFile);
            System.out.println("sortParam   : " + sortParam);
            System.out.println("outputDir   : " + outputDir);
            System.out.println("solutionVer : " + solutionVer);
            System.out.println("----------------");
        }
    }

    private static void usage() {
        System.out.println("");
        System.out.println("java ...Main -inputFile <inputFile> -sortParam <sortParam> -outputDir <outputDir> -solutionVer <version>");
        System.out.println("Example: java ...Main -inputFile C:\\Users\\demo_assessment\\additional_resources\\input.csv -sortParam Name -outputDir C:\\Users\\demo_assessment\\additional_resources -solutionVer 1");
        System.out.println("");
        System.out.println("<inputFile>   : absolute path of the input.csv file");
        System.out.println("<sortParam>   : sorting parameter of the header from input.csv file. Can be only: \"Name\", \"State\" , \"Motto\" , \"Mayor\". ");
        System.out.println("<outputDir>   : absolute path of the folder for output.csv file");
        System.out.println("<solutionVer> : version of the program: 1) read And After Sort 2) sort As Reading File");
        System.out.println("");

        System.exit(1);
    }
}
