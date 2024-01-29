package csc435.app;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class CountWords {


    /**
     * Main method which takes the input from the arguments as inputDirectory and
     * outputDirectory.
     * Call the method processDirectory by passing them and measure the time.
     * 
     * @param args User arguments that will take user input from command line
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CountWords <inputDir> <outputDir>");
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
            processAndWriteFile(filePath, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will go line by line and clean as per requirement. Then it will be written to the outputPath
     * 
     * @param inputPath The input files path
     * @param outputPath The output file path
     * @throws IOException This exception is when the file doesnt found or unable to open the file.
     */
    private static void processAndWriteFile(Path inputPath, Path outputPath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            Map<String, Integer> wordCounts = countWords(reader);
            writeWordCounts(wordCounts, writer);
        }
    }

    /**
     * This method will count the words in line by line and add the word and count in the hash map.
     * 
     * @param reader The buffer reader object created for the input file.
     * @return The word count hashmap which contains key as word and count of the key value in the value pair.
     * @throws IOException This exeception is when file is not found.
     */
    private static Map<String, Integer> countWords(BufferedReader reader) throws IOException {
        Map<String, Integer> wordCounts = new HashMap<>();
        String line;
        Pattern pattern = Pattern.compile("[\\w]+");

        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        return wordCounts;
    }

    /**
     * This nethod is for writing in to the buffer writer.
     * 
     * @param wordCounts The word count in the format of HashMap
     * @param writer The Bufferwriter object of the output file
     * @throws IOException This exeception is when file is not found.
     */
    private static void writeWordCounts(Map<String, Integer> wordCounts, BufferedWriter writer) throws IOException {
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue());
            writer.newLine();
        }
    }
}
