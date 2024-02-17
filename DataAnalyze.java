import java.util.*;
import java.io.*;

public class DataAnalyze {
    public Map<String, Integer> calculateWordFrequency(String cleanedFile) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        try (Scanner scan = new Scanner(new File(cleanedFile))) {
            while (scan.hasNextLine()) {
                String word = scan.nextLine();
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + cleanedFile);
        }
        return wordFrequencyMap;
    }

    public void writeWordFrequencyToFile(Map<String, Integer> wordMap, String newFile, int topN, int bottomN) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordMap.entrySet());
            sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            writeTopWords(writer, sortedList, topN);
            writeBottomWords(writer, sortedList, bottomN);
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    private void writeTopWords(BufferedWriter writer, List<Map.Entry<String, Integer>> sortedList, int topN) throws IOException {
        writer.write("Top " + topN + " most frequent words:");
        writer.newLine();
        for (int i = 0; i < Math.min(topN, sortedList.size()); i++) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        }
    }

    private void writeBottomWords(BufferedWriter writer, List<Map.Entry<String, Integer>> sortedList, int bottomN) throws IOException {
        writer.write("Top " + bottomN + " least frequent words:");
        writer.newLine();
        for (int i = sortedList.size() - 1; i >= Math.max(0, sortedList.size() - bottomN); i--) {
            Map.Entry<String, Integer> entry = sortedList.get(i);
            writer.write(entry.getKey() + ": " + entry.getValue());
            writer.newLine();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DataAnalyze fileName");
        } else {
            DataAnalyze analyzedData = new DataAnalyze();
            Map<String, Integer> wordFrequencyMap = analyzedData.calculateWordFrequency(args[0]);
            analyzedData.writeWordFrequencyToFile(wordFrequencyMap, "WordFrequency.txt", 10, 10);
        }
    }
}
