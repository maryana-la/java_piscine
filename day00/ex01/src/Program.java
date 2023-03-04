import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        try {
            num = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Enter a number!");
            System.exit(-1);
        }

        boolean isPrime = true;
        short iter = 1;
        if (num < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        if (num == 2) {
            System.out.println(true + " " + iter);
        }
        else if (num % 2 == 0) {
            System.out.println(false + " " + iter);
        }
        else {
            for (int i = 3; i * i <= num; i += 2) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
                iter++;
            }
            System.out.println(isPrime + " " + iter);
        }
    }
}
