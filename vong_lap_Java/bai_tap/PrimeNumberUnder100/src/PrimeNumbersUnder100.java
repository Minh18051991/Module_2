public class PrimeNumbersUnder100 {
    public static void main(String[] args) {
        printPrimeNumbersUnder100();
    }
    public static void printPrimeNumbersUnder100() {
        for (int N = 2; N < 100; N++) {
            if (isPrime(N)) {
                System.out.println(N);
            }
        }
    }


    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
