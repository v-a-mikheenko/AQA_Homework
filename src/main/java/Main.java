import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] words = {"one", "two", "three",
                "two", "three", "four", "three",
                "four", "five", "four", "five",
                "six", "five", "six", "seven",
                "six", "seven", "eight"};

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("Слова повторяются : " + wordCount);

        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();
        {
            telephoneDirectory.add("Иванов", "+79998887766");
            telephoneDirectory.add("Петров", "+79998887755");
            telephoneDirectory.add("Сидоров", "+79998887744");
            telephoneDirectory.add("Петров", "+79998887733");
            telephoneDirectory.add("Сидоров", "+79998887722");
            telephoneDirectory.add("Леонов", "+79998887711");
            telephoneDirectory.add("Сидоров", "+79998887700");

            System.out.println("Номера Иванова: " + telephoneDirectory.get("Иванов"));
            System.out.println("Номера Петрова: " + telephoneDirectory.get("Петров"));
            System.out.println("Номера Сидорова: " + telephoneDirectory.get("Сидоров"));
            System.out.println("Номера Леонова: " + telephoneDirectory.get("Леонов"));

        }
    }
}