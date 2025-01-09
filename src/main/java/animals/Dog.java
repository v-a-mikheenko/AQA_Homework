package animals;

public class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public void run(int lenght) {
        if (lenght >= 0 && lenght <= 500) {
            System.out.println(super.getName() + " пробежал " + lenght + " метров");
        } else if (lenght < 0) {
            System.out.println(super.getName() + " не может бежать отрицательное расстояние");
        } else {
            System.out.println(super.getName() + " столько не пробежит");
        }
        ;
    }

    public void swim(int lenght) {
        if (lenght >= 0 && lenght <= 10) {
            System.out.println(super.getName() + " проплыл " + lenght + " метров");
        } else if (lenght < 0) {
            System.out.println(super.getName() + " не может плыть отрицательное расстояние");
        } else {
            System.out.println(super.getName() + " столько не проплывёт");
        }
        ;
    }

    public static void getDogCount() {
        System.out.println("Количество собак: " + dogCount);
    }
}
