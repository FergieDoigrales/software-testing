import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArcsinTest {

    private static final double DELTA = 0.0001;
    private static final double PI = 3.141592653589793;
    private static final double HALF_PI = 1.5707963267948966;
    private static final double NEAR_ONE = 1.4292568534704693;
    private static final double TAYLOR_MEM_1 = 0.5;
    private static final double TAYLOR_MEM_2 = 0.5208333333333334;
    private static final double TAYLOR_MEM_3 = 0.5231770833333333;

    @Test
    public void testSeriesForXZero() {
        double x = 0.0;
        for (int n = 1; n <= 5; n++) {
            assertEquals(0.0, Arcsin.series(x, n), DELTA);
        }
    }

    @Test
    public void testSeriesForXOneLargeN() {
        double x = 1.0;
        double result = Arcsin.series(x, 200);
        assertEquals(HALF_PI, result, 0.1);
    }

    @Test
    public void testSeriesForXMinusOneLargeN() {
        double x = -1.0;
        double result = Arcsin.series(x, 200);
        assertTrue(result > -PI/2);
        assertTrue(result < -1.5);
    }

    @Test
    public void testSeriesForXNearOne() {
        double x = 0.99;
        double result = Arcsin.series(x, 100);
        assertEquals(NEAR_ONE, result, 0.01);
    }


    @Test
    public void testSeriesForPositiveXSmallN() {
        double x = TAYLOR_MEM_1;
        assertEquals(x, Arcsin.series(x, 1), DELTA);
        //x + 1/6*x^3
        assertEquals(TAYLOR_MEM_2, Arcsin.series(x, 2), DELTA);
        //x + 1/6*x^3 + 3/40*x^5
        assertEquals(TAYLOR_MEM_3, Arcsin.series(x, 3), DELTA);
    }

    @Test
    public void testSeriesForNegativeXSmallN() {
        double x = -TAYLOR_MEM_1;
        assertEquals(x, Arcsin.series(x, 1), DELTA);
        assertEquals(-TAYLOR_MEM_2, Arcsin.series(x, 2), DELTA);
        assertEquals(-TAYLOR_MEM_3, Arcsin.series(x, 3), DELTA);
    }

    @Test
    public void testIllegalArgument() {
        assertThrows(IllegalArgumentException .class, () ->Arcsin.series(2, 1));
        assertThrows(IllegalArgumentException .class, () ->Arcsin.series(-2, 1));
    }


}