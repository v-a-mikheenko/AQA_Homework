package animals;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void feeding(int amount) {
        if (amount <= food) {
            food -= amount;
        } else {
            System.out.println("В миске недостаточно еды");
        }
    }

    public void addFood(int amount) {
        if (amount <= 0) {
            System.out.println("Некорректное значение");
        } else {
            food += amount;
        }
    }

    public void foodInBowl() {
        System.out.println("В миске осталось " + food + " еды");
    }
}
