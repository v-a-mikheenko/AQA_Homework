import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(sumRange(5, 7));
        numberSignCheck(-5);
        System.out.println(numberSignCheckBoolean(10));
        repeatLines("Строка", 4);
        System.out.println(leapYear(2007));
        changeValues();
        emptyArray();
        doublingLessThanSixArray();
        diagonalOneArray();
        System.out.println(Arrays.toString(lenInitialValueArray(7, 6)));
    }

    //Задача 1
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    //Задача 2
    public static void checkSumSign() {
        int a = 5;
        int b = -10;

        if (a + b > 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    //Задача 3
    public static void printColor() {
        int value = 100;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    //Задача 4
    public static void compareNumbers() {
        int a = 4;
        int b = 5;

        System.out.println((a >= b) ? "a >= b" : "a < b");
    }

    //Задача 5
    public static boolean sumRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    //Задача 6
    public static void numberSignCheck(int a) {
        if (a >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }

    //Задача 7
    public static boolean numberSignCheckBoolean(int a) {
        return a < 0 ? true : false;
    }

    //Задача 8
    public static void repeatLines(String line, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(line);
        }
    }

    //Задача 9
    public static boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //Задача 10
    public static void changeValues() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(array));
    }

    //Задача 11
    public static void emptyArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
    }

    //Задача 12
    public static void doublingLessThanSixArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //Задача 13
    public static void diagonalOneArray() {
        int a = 5;
        int[][] array = new int[a][a];
        for (int i = 0; i < a; i++) {
            array[i][i] = 1;
            array[i][array.length - i - 1] = 1;
        }
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n"));
    }

    //Задача 14
    public static int[] lenInitialValueArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }
}