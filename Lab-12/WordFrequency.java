import java.util.*;

public class WordFrequency {

    public static void main(String[] args) {

        String paragraph = "java is easy java is powerful java is popular";

        // Convert paragraph to lowercase and split into words
        String[] words = paragraph.toLowerCase().split("\\s+");

        // Store word frequencies
        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        // Sort entries by frequency (highest first)
        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        list.sort(
            Comparator.comparing(
                (Map.Entry<String, Integer> entry) -> entry.getValue()
            ).reversed()
            .thenComparing(Map.Entry::getKey)
        );

        // Print sorted word frequencies
        System.out.println("Word Frequencies:");

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(
                entry.getKey() + " : " + entry.getValue()
            );
        }
    }
}
