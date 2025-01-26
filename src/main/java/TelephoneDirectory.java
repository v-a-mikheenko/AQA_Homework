import java.util.*;

public class TelephoneDirectory {
    private final Map<String, Set<String>> telephoneDirectory = new HashMap<>();
    private final Set<String> set = new HashSet<>();


    public void add(String lastName, String telephoneNumber) {
        if(!set.add(telephoneNumber)){
            System.out.println(telephoneNumber + " - данный телефонный номер уже есть в справочнике");
        } else {
            telephoneDirectory.computeIfAbsent(lastName, p -> new HashSet<>()).add(telephoneNumber);
        }
    }

    public Set<String> get(String lastName) {
        return telephoneDirectory.getOrDefault(lastName, Collections.emptySet());
    }
}
