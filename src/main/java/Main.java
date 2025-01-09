import animals.Animal;
import animals.Dog;
import animals.Cat;
import animals.Bowl;
import figures.Rectangle;
import figures.Triangle;
import figures.Circle;


public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Шарик");
        Dog dog2 = new Dog("Тузик");
        Dog dog3 = new Dog("Бим");
        Dog dog4 = new Dog("Рэкс");

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Том");
        Cat cat3 = new Cat("Сильвестр");

        dog1.run(150);
        dog1.swim(3);
        System.out.println();

        dog2.run(20);
        dog2.swim(11);
        System.out.println();

        dog3.run(501);
        dog3.swim(2);
        System.out.println();

        dog4.run(-1);
        dog4.swim(-1);
        System.out.println();

        cat1.run(100);
        cat1.swim(0);
        System.out.println();

        cat2.run(201);
        cat2.swim(1);
        System.out.println();

        cat3.run(-1);
        cat3.swim(-1);
        System.out.println();

        Animal.getAnimalCount();
        Cat.getCatCount();
        Dog.getDogCount();
        System.out.println();

        Bowl bowl = new Bowl(2);
        bowl.addFood(6);

        Cat[] catsArray = {cat1, cat2, cat3};

        for (Cat cat : catsArray) {
            cat.eat(bowl, 3);
        }

        System.out.println("Остаток еды в миске " + bowl.getFood());
        System.out.println();

        Circle circle = new Circle("Оранжевый", "Белый", 9);
        Rectangle rectangle = new Rectangle("Зелёный", "Жёлтый", 10, 13);
        Triangle triangle = new Triangle("Синий", "Красный", 6, 12, 7);

        circle.printFigureInfo();
        rectangle.printFigureInfo();
        triangle.printFigureInfo();
    }
}