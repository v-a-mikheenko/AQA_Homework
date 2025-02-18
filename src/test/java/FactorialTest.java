
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @Test
    public void testZero() {
        assertEquals(1, FactorialCalculator.getFactorial(0));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "2, 2", "5, 120", "12, 479001600", "19, 121645100408832000", "20, 2432902008176640000"})
    public void testPositiveValues(int number, long result) {
        assertEquals(result, FactorialCalculator.getFactorial(number));
    }

    @Test
    public void testNegativeNumber() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.getFactorial(-1));

        String expectedMessage = "Число должно быть неотрицательным";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 22, 23, 50})
    public void testGrateInt(int number) {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> FactorialCalculator.getFactorial(number));

        String expectedMessage = "Слишком большой результат расчета для типа данных long";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @ParameterizedTest
    @NullSource
    void testNullInput(Integer input) {
        Assertions.assertThrows(NullPointerException.class, () -> FactorialCalculator.getFactorial(input));
    }
}
