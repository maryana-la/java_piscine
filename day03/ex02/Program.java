import java.util.Random;

public class Program {
    private static final int MAX_ARRAY_SIZE = 2000000;
    private static final int MAX_MODULE_ARRAY_VALUE = 1000;
    private static final String ERROR = "Please provide valid arguments. Usage: java Program --arraySize=NUM1 --threadsCount=NUM2";

    public static void main(String[] args) {
        checkArgsValidity(args);
        int arrSize = getNumber(args[0]);
        int threadCount = getNumber(args[1]);

        if (threadCount > arrSize || arrSize > MAX_ARRAY_SIZE || threadCount < 0) {
            System.err.println("Error. Number of threads must not exceed array size. Maximum array size is " + MAX_ARRAY_SIZE);
            System.exit(-1);
        }

        int segmentSize = arrSize / threadCount;
        long[] partialSums = new long[threadCount];
        int[] arr = fillArray(arrSize);
        calculateSum (arr, threadCount, segmentSize, partialSums);
        sumArray(partialSums, "Partial sum");
    }

    private static void checkArgsValidity(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.err.println(ERROR);
            System.exit(-1);
        }
    }

    private static int getNumber (String num) {
        int number = 0;
        try {
            number = Integer.parseInt(num.substring(num.indexOf('=') + 1));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println(ERROR);
            System.exit(-1);
        }
        return number;
    }

    private static int[] fillArray(int arrSize) {
        int[] arr = new int[arrSize];
        Random rand = new Random();
        for (int i = 0; i< arrSize; i++) {
            arr[i] = MAX_MODULE_ARRAY_VALUE - rand.nextInt(MAX_MODULE_ARRAY_VALUE * 2);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        long sumTmp = 0;
        for (int x : arr) {
            sumTmp += x;
        }
        System.out.println("Array sum = " + sumTmp);
        return arr;
    }

    private static void calculateSum(int[] arr, int threadCount, int segmentSize, long[] partialSums) {
        int j = 0;
        for (int i = 0; i < threadCount; i++) {
            Calculate thread;
            if (i + 1 == threadCount) {
                thread = new Calculate(arr, j, arr.length, partialSums, i);
            } else {
                thread = new Calculate(arr, j, j + segmentSize, partialSums, i);
            }
            thread.start();
            j += segmentSize;
        }
    }

    static void sumArray(long[] arr, String name) {
        long sumTmp = 0;
        for (long x : arr) {
            sumTmp += x;
        }
        System.out.println(name + " = " + sumTmp);
    }

}
