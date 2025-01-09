package animals;

public class Cat extends Animal {
    private static int catCount = 0;
    public boolean satiety;

    public Cat(String name) {
        super(name);
        catCount++;
        satiety = false;
    }

    public void run(int lenght) {
        if (lenght >= 0 && lenght <= 200) {
            System.out.println(super.getName() + " пробежал " + lenght + " метров");
        } else if (lenght < 0) {
            System.out.println(super.getName() + " не может бежать отрицательное расстояние");
        } else {
            System.out.println(super.getName() + " столько не пробежит");
        }
        ;
    }

    public void swim(int lenght) {
        if (lenght == 0) {
            System.out.println(super.getName() + " остался на берегу");
        } else if (lenght > 0) {
            System.out.println(super.getName() + " не умеет плавать");
        } else {
            System.out.println(super.getName() + " не понимает отрицательные числа");
        }
        ;
    }

    public static void getCatCount() {
        System.out.println("Количество котов: " + catCount);
    }

    public void eat(Bowl bowl, int amount) {
        if (!satiety && amount <= bowl.getFood()) {
            bowl.feeding(amount);
            satiety = true;
            System.out.println(super.getName() + " наелся");
        } else {
            System.out.println(super.getName() + " не хватило еды");
        }
    }
}
