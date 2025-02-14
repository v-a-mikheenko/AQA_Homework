public class Main {
    public static void main(String[] args) {
        System.out.println(getFactorial(5));
    }

    public static int getFactorial(int f) {
        if (f < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        } else if (f < 13) {
            int result = 1;
            for (int i = 2; i <= f; i++) {
                result = result * i;
            }
            return result;
        } else {
            throw new ArithmeticException("Слишом большой результат расчета для типа данных int");
        }
    }
}
