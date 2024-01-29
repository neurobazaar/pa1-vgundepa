package csc435.app;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class SortWords {


    /**
     * Main method which takes the input from the arguments as inputDirectory and
     * outputDirectory.
     * Call the method processDirectory by passing them and measure the time.
     * 
     * @param args User arguments that will take user input from command line
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SortWords <inputDir> <outputDir>");
            return;
        }

        String inputDir = args[0];
        String outputDir = args[1];

        long startTime = System.currentTimeMillis();
        processDirectory(inputDir, outputDir);
        long endTime = System.currentTimeMillis();

        System.out.println("Processing completed in " + (endTime - startTime) + " ms");
    }

    /**
     *  This method will process Directiries which walks through the given user
     * directory and when it finds a .txt file in the hierarchy it will simply
     * process all the .txt format files.
     * 
     * @param inputDir The input directory name.
     * @param outputDir The output directory name.
     */
    private static void processDirectory(String inputDir, String outputDir) {
        try {
            Files.walk(Paths.get(inputDir))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> processFile(path, inputDir, outputDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * This method is to process the file structure and then create the same folder
     * structure in the output directory.
     * After we will create valid file directory structure created. Then we will
     * call processAndWriteFile to clean and write data to output file.
     * 
     * @param filePath path of the input file.
     * @param inputDir name of hte input directory
     * @param outputDir name of the output directory.
     */
    private static void processFile(Path filePath, String inputDir, String outputDir) {
        Path relativePath = Paths.get(inputDir).relativize(filePath);
        Path outputPath = Paths.get(outputDir, relativePath.toString());

        try {
            if (!Files.exists(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }
            processAndSortFile(filePath, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for process hte input file and and read all the words add to list of map. The list is sorted based on the count values of words
     * 
     * @param inputPath The input files path
     * @param outputPath The output file path
     * @throws IOException This exception is when the file doesnt found or unable to open the file.
     */
    private static void processAndSortFile(Path inputPath, Path outputPath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            List<Map.Entry<String, Integer>> wordList = readWords(reader);
            wordList.sort((a, b) -> b.getValue().compareTo(a.getValue())); 

            for (Map.Entry<String, Integer> entry : wordList) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }

    /**
     * The method is to read words from the buffered reader of the input file and insert in the map that has word as key and count as value.
     * 
     * @param reader Buffered reader of the input file
     * @return This will return the ArrayList of the Map values.
     * @throws IOException This exception is when the file doesnt found or unable to open the file.
     */
    private static List<Map.Entry<String, Integer>> readWords(BufferedReader reader) throws IOException {
        Map<String, Integer> wordMap = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                wordMap.put(parts[0], Integer.parseInt(parts[1]));
            }
        }
        return new ArrayList<>(wordMap.entrySet());
    }
}
