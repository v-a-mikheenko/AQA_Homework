import java.util.*;

public class TelephoneDirectory {
    private final Map<String, List<String>> telephoneDirectory = new HashMap<>();

    public void add(String lastName, String telephoneNumber) {
        telephoneDirectory.computeIfAbsent(lastName, k -> new ArrayList<>()).add(telephoneNumber);
    }

    public List<String> get(String lastName) {
        return telephoneDirectory.getOrDefault(lastName, Collections.emptyList());
    }
}
