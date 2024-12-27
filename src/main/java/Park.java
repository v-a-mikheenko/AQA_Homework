import java.util.*;

public class Park {
    private final List<Attraction> attractions;

    public Park() {
        attractions = new ArrayList<>();
    }

    public void addNewAttraction(String attractionName, String workingHours, double price) {
        attractions.add(new Attraction(attractionName, workingHours, price));
    }

    public void allAttraction() {
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }

    public static class Attraction {
        private String attractionName;
        private String workingHours;
        private double price;

        public Attraction(String attractionName, String workingHours, double price) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Аттракцион: " + attractionName +
                    " | Часы работы: " + workingHours +
                    " | Стоимость: " + price;
        }
    }
}
