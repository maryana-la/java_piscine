public class Program {

    static int sumNumbers(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int num = 303420000;
        System.out.println(sumNumbers(num));
    }
}
