import java.math.BigDecimal;
import java.math.MathContext;

public class Arcsin {
    public static double series(double x, int iterations) {
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException("x must be in [-1, 1]");
        }

        BigDecimal xBD = BigDecimal.valueOf(x);
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = xBD;
        int n = 0;

        while (iterations > n) {
            sum = sum.add(term);
            //(2n)!/((2^2n)*(n!)^2) * (x^(2n+1))/(2n+1)
            n++;
            BigDecimal part1 = factorial(2 * n).divide(
                    BigDecimal.valueOf(Math.pow(2, 2 * n)).multiply(
                            factorial(n).pow(2)),
                    MathContext.DECIMAL128);
            BigDecimal part2 = xBD.pow(2 * n + 1)
                    .divide(new BigDecimal(2 * n + 1), MathContext.DECIMAL128);
            term = part1.multiply(part2, MathContext.DECIMAL128);
        }

        return sum.doubleValue();
    }

    private static BigDecimal factorial(int n) {
        if (n <= 1) return BigDecimal.ONE;
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

}