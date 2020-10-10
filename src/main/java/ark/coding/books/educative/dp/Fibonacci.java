package ark.coding.books.educative.dp;

public class Fibonacci {

    public int fibonacci(int n) {
        if (n == 0 || n == 1) return 1;

        return fibonacci(n-1) + fibonacci(n-2);
    }
}
