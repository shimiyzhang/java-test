package unitTests.useFixture;

public class Calculator {
    private long n = 0;

    public long add(long x) {
        n = n + x;
        return n;
    }

    public long sub(long y) {
        n = n - y;
        return n;
    }
}
