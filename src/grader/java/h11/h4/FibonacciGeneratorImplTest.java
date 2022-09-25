package h11.h4;

import h11.fibs.FibonacciGeneratorImpl;

public class FibonacciGeneratorImplTest extends FibonacciGeneratorTest {

    protected FibonacciGeneratorImplTest() {
        super(new FibonacciGeneratorImpl());
    }

    @Override
    public void testThat_initialIsCorrect(int numberOfFibs) {
        super.testThat_initialIsCorrect(numberOfFibs);
    }

    @Override
    public void testThat_fibsAreCorrect(int numberOfFibs) {
        super.testThat_fibsAreCorrect(numberOfFibs);
    }
}
