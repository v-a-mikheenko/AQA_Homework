import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FactorialTest {

    @Test
    public void testZero() {
        assertEquals(1, Main.getFactorial(0));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "2, 2", "3, 6", "4, 24", "5, 120", "11, 39916800", "12, 479001600"})
    public void testPositiveValues(int number, int result) {
        assertEquals(result, Main.getFactorial(number));
    }

    @Test
    public void testNegativeNumber() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Main.getFactorial(-1));

        String expectedMessage = "Число должно быть неотрицательным";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 50})
    public void testGrateInt(int number) {
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> Main.getFactorial(number));

        String expectedMessage = "Слишом большой результат расчета для типа данных int";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    private static Stream<Object> wrongType() {
        return Stream.of("five", true, 5.5);
    }

    @ParameterizedTest
    @MethodSource("wrongType")
    void testWrongType(Object input) {
        Assertions.assertThrows(ClassCastException.class, () -> Main.getFactorial((int) input));
    }

    @ParameterizedTest
    @NullSource
    void testNullInput(Integer input) {
        Assertions.assertThrows(NullPointerException.class, () -> Main.getFactorial(input));
    }
}
