package h11.fibs;

/**
 * This class represents a pair of numbers
 * used to compute Fibonacci numbers.
 *
 * @param a The first number.
 * @param b The second number.
 */
public record FibonacciPair(int a, int b) {

    FibonacciPair() {
        this(1, 2);
    }

    public FibonacciPair next() {
        return new FibonacciPair(b, a+b);
    }
}
