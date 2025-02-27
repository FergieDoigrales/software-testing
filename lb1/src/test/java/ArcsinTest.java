import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArcsinTest {

    private static final double DELTA = 0.0001;
    private static final double PI = 3.141592653589793;
    private static final double HALF_PI = 1.5707963267948966;
    private static final double NEAR_ONE = 1.4292568534704693;
    private static final double TAYLOR_MEM_1 = 0.5;
    private static final double TAYLOR_MEM_2 = 0.5208333333333334;
    private static final double TAYLOR_MEM_3 = 0.5231770833333333;

    @ParameterizedTest
    @MethodSource("xValuesForZeroTest")
    public void testForXZero(double x, int n) {
        assertEquals(0.0, Arcsin.series(x, n), DELTA);
    }

    private static Stream<Arguments> xValuesForZeroTest() {
        return Stream.of(
                Arguments.of(0.0, 1),
                Arguments.of(0.0, 3),
                Arguments.of(0.0, 5),
                Arguments.of(0.0, 100)
        );
    }

    @Test
    public void testForXOneLargeN() {
        double x = 1.0;
        double result = Arcsin.series(x, 200);
        assertEquals(HALF_PI, result, 0.1);
    }

    @Test
    public void testForXMinusOneLargeN() {
        double x = -1.0;
        double result = Arcsin.series(x, 200);
        assertTrue(result > -PI/2);
        assertTrue(result < -1.5);
    }

    @Test
    public void testForXNearOne() {
        double x = 0.99;
        double result = Arcsin.series(x, 100);
        assertEquals(NEAR_ONE, result, 0.01);
    }


    @Test
    public void testForPositiveXSmallN() {
        double x = TAYLOR_MEM_1;
        assertEquals(x, Arcsin.series(x, 1), DELTA);
        //x + 1/6*x^3
        assertEquals(TAYLOR_MEM_2, Arcsin.series(x, 2), DELTA);
        //x + 1/6*x^3 + 3/40*x^5
        assertEquals(TAYLOR_MEM_3, Arcsin.series(x, 3), DELTA);
    }

    @ParameterizedTest
    @MethodSource("xValuesForNegativeXSmallN")
    public void testsForNegativeXSmallN(double x, int n, double expected) {
        assertEquals(expected, Arcsin.series(x, n), DELTA);
    }

    private static Stream<Arguments> xValuesForNegativeXSmallN() {
        return Stream.of(
                Arguments.of(-TAYLOR_MEM_1, 1, -TAYLOR_MEM_1),
                Arguments.of(-TAYLOR_MEM_1, 2, -TAYLOR_MEM_2),
                Arguments.of(-TAYLOR_MEM_1, 3, -TAYLOR_MEM_3)
        );
    }

    @ParameterizedTest
    @MethodSource("illegalArguments")
    public void testIllegalArgument(double x, int n) {
        assertThrows(IllegalArgumentException.class, () -> Arcsin.series(x, n));
    }

    private static Stream<Arguments> illegalArguments() {
        return Stream.of(
                Arguments.of(2.0, 1),
                Arguments.of(-2.0, 1)
        );
    }


}