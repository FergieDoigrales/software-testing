public class Debug {
    public static void main(String[] args) {
        double result = Arcsin.series(1.0, 500);
        double expected = 3.141592653589793 / 2;
        System.out.println("result: " + result);
        System.out.println("PI/2: " + expected);
        System.out.println("difference: " + Math.abs(expected - result));
        System.out.println("\n");
        double x = 0.99;
        double expected2 = Math.asin(x);
        double result2 = Arcsin.series(x, 100);
        System.out.println("expected (0.99): " + expected2);
        System.out.println("result (0.99): " + result2);
        System.out.println("difference: " + Math.abs(expected2 - result2));

        double x_fisrt = 0.5;
        //x + 1/6*x^3
        double expectedN2 = x_fisrt + (1.0 / 6) * Math.pow(x_fisrt, 3);
        System.out.println("expected 2 mem (0.5): " + expectedN2);
        //x + 1/6*x^3 + 3/40*x^5
        double expectedN3 = expectedN2 + (3.0 / 40) * Math.pow(x_fisrt, 5);
        System.out.println("expected 3 mem (0.5): " + expectedN3);

    }
}