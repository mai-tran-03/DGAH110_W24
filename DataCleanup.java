import java.util.*;
import java.io.*;

public class DataCleanup {
    private List<String> processTextFile(String file) {
        List<String> wordList = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] splitLine = line.split(" ");
                for (String word : splitLine) {
                    if (isAlpha(word)) {
                        wordList.add(word.toLowerCase());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + file);
        }
        return wordList;
    }

    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

    private Set<String> removeStopWords(String stopWords) {
        Set<String> stopWordsSet = new HashSet<>();
        try (Scanner scan = new Scanner(new File(stopWords))) {
            while (scan.hasNextLine()) {
                stopWordsSet.add(scan.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + stopWords);
        }
        return stopWordsSet;
    }

    public List<String> clean(String textFile, String stopWordsFile) {
        List<String> wordList = processTextFile(textFile);
        Set<String> stopWordsSet = removeStopWords(stopWordsFile);
        wordList.removeAll(stopWordsSet);
        return wordList;
    }

    public void writeCleanedListToFile(List<String> cleanedList, String newFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            for (String word : cleanedList) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java DataCleanup fileName stopWordsFile");
        } else {
            DataCleanup cleanData = new DataCleanup();
            List<String> cleanedList = cleanData.clean(args[0], args[1]);
            cleanData.writeCleanedListToFile(cleanedList, "CleanedFile.txt");
        }
    }
}