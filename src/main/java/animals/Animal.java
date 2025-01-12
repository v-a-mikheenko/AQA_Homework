package animals;

public abstract class Animal {
    private String name;
    private static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void run(int lenght);

    public abstract void swim(int lenght);

    public static void getAnimalCount() {
        System.out.println("Количество животных: " + animalCount);
    }
}
