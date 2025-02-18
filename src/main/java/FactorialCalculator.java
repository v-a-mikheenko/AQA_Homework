public class FactorialCalculator {

    public static long getFactorial(int f) {
        if (f < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        } else if (f < 21) {
            long result = 1;
            for (int i = 2; i <= f; i++) {
                result = result * i;
            }
            return result;
        } else {
            throw new ArithmeticException("Слишком большой результат расчета для типа данных long");
        }
    }
}
