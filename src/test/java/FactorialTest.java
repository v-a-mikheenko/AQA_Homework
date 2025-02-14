import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactorialTest {

    @Test
    public void testZero() {
        assertEquals(Main.getFactorial(0), 1);
    }

    @DataProvider(name = "positiveValues")
    public Object[][] positiveValues() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120},
                {11, 39916800},
                {12, 479001600}
        };
    }

    @Test(dataProvider = "positiveValues")
    public void testPositiveValues(int input, int expected) {
        int result = Main.getFactorial(input);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Число должно быть неотрицательным")
    public void testNegativeNumber() {
        int input = -1;
        Main.getFactorial(input);
    }

    @DataProvider(name = "GrateIntValues")
    public Object[][] GrateIntValues() {
        return new Object[][]{
                {13},
                {14},
                {15}
        };
    }

    @Test(dataProvider = "GrateIntValues",
            expectedExceptions = ArithmeticException.class,
            expectedExceptionsMessageRegExp = "Слишом большой результат расчета для типа данных int")
    public void GrateInt(int input) {
        Main.getFactorial(input);
    }

    @DataProvider(name = "wrongType")
    public Object[] wrongType() {
        return new Object[]{"five", true, 5.5};
    }

    @Test(dataProvider = "wrongType", expectedExceptions = ClassCastException.class)
    public void testWrongType(Object input) {
        Main.getFactorial((int) input);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullInput() {
        Main.getFactorial((Integer) null);
    }
}
