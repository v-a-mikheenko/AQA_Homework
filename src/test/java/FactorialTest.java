import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FactorialTest {

    @Test
    public void testZero() {
        assertEquals(FactorialCalculator.getFactorial(0), 1);
    }

    @DataProvider(name = "positiveValues")
    public Object[][] positiveValues() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {5, 120},
                {12, 479001600},
                {19, 121645100408832000L},
                {20, 2432902008176640000L}
        };
    }

    @Test(dataProvider = "positiveValues")
    public void testPositiveValues(int input, long expected) {
        long result = FactorialCalculator.getFactorial(input);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Число должно быть неотрицательным")
    public void testNegativeNumber() {
        int input = -1;
        FactorialCalculator.getFactorial(input);
    }

    @DataProvider(name = "GrateIntValues")
    public Object[][] GrateIntValues() {
        return new Object[][]{
                {21},
                {22},
                {23}
        };
    }

    @Test(dataProvider = "GrateIntValues",
            expectedExceptions = ArithmeticException.class,
            expectedExceptionsMessageRegExp = "Слишком большой результат расчета для типа данных long")
    public void GrateInt(int input) {
        FactorialCalculator.getFactorial(input);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullInput() {
        FactorialCalculator.getFactorial((Integer) null);
    }
}
