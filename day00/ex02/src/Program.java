import java.util.Scanner;

public class Program {

    static boolean isPrime(int num) {
        boolean isPrime = true;
        if (num < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            isPrime = false;
        } else {
            for (int i = 3; i * i <= num; i += 2) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }

    static int sumNumbers(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int temp = 0;
        int coffeeQuery = 0;
        while (temp != 42) {
            try {
                temp = sc.nextInt();
            } catch (Exception e) {
                System.err.println("Not a number");
                System.exit(-1);
            }
            if (isPrime(sumNumbers(temp)))
                coffeeQuery++;
        }
        System.out.println("Count of coffee - request - " + coffeeQuery);
    }
}
