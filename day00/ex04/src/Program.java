import java.util.Scanner;

public class Program {
    private static final int MAX_CHAR = 65535;
    private static final int TOP_MAX = 10;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        try {
            str = sc.nextLine();
        } catch (Exception e) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        char[] chArray = str.toCharArray();
        int[] numArray = new int[MAX_CHAR];

        //считаем количество каждого символа
        if ("".equals(str)) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        for (int i = 0; i < str.length(); i++) {
            int tmp = chArray[i];
            numArray[tmp] += 1;
        }


        // находим используемые и сортируем
        int[] topUsed = new int[TOP_MAX];
        int minValueIndex = 0;
        for (int i = 0; i < MAX_CHAR; i++) {
            if (numArray[i] == 0)
                continue;
            // for a first non zero letter
            if (minValueIndex == 0 && topUsed[minValueIndex] == 0) {
                topUsed[0] = i;
                continue;
            }

            //если меньше минимального - добавляем просто в конец
            if (numArray[i] < numArray[topUsed[minValueIndex]] && minValueIndex < TOP_MAX - 1) {
                topUsed[++minValueIndex] = i;
            } else {
                for (int j = 0; j < TOP_MAX; j++) {
                    if (numArray[i] > numArray[topUsed[j]]) {
                        insertNumberInArray(topUsed, i, j, minValueIndex);
                        break;
                    }
                    else if (numArray[i] == numArray[topUsed[j]]) {
                        if (j + 1 < TOP_MAX) {
                            insertNumberInArray(topUsed, i, j + 1, minValueIndex);
                            break;
                        }
                    }
                }
                if (minValueIndex < TOP_MAX - 1)
                    minValueIndex++;
            }
        }
//        System.out.println(Arrays.toString(topUsed));
        printGraph(topUsed, numArray);
    }

    private static void insertNumberInArray(int[] topUsed, int toInsert, int j, int minValueIndex) {
        for (int i = minValueIndex; i >= j; i--) {
            if (i < TOP_MAX - 1) {
                topUsed[i + 1] = topUsed[i];
            }
        }
        topUsed[j] = toInsert;
    }

    private static void printGraph(int[] topUsed, int[] numArray) {
        int[] height = new int[TOP_MAX];
        for (int i = 0; i < TOP_MAX; i++) {
            height[i] = TOP_MAX * numArray[topUsed[i]] / numArray[topUsed[0]];
        }

//        print vertical
        /*
        System.out.println(Arrays.toString(height));
        for (int i = 0; i < TOP_MAX; i++) {
            System.out.print((char)topUsed[i] + " ");
            for (int j = 0; j < height[i]; j++)
                System.out.print("#");
            System.out.println(" " + numArray[topUsed[i]]);
        } */

        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (height[j] == 0)
                    continue;
                if (i - height[j] == 1)
                    System.out.printf("%3d", numArray[topUsed[j]]);
                else if (i == 0)
                    System.out.printf("%3c", topUsed[j]);
                else if (i <= height[j])
                    System.out.printf("%3c", '#');
            }
            System.out.println();
        }
    }
}

//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWOOOOOOO42

// Implementation using 2 TOP arrays: for char and for number of chars

/*
public class Program {
    private static final int MAX_CHAR = 65535;
    private static final int TOP_MAX = 10;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chArray = str.toCharArray();
        int[] numArray = new int[MAX_CHAR];

        //считаем количество каждого символа
        for (int i = 0; i < str.length(); i++) {
            int tmp = chArray[i];
            numArray[tmp] += 1;
        }

        // находим используемые и сортируем
        int[] topUsedChars = new int[TOP_MAX];
        int[] topUsedQuantity = new int[TOP_MAX];

        for (int i = 0; i < TOP_MAX; i++) {
            int maxNum = 0;
            int charMax = 0;
            for (int j = 0; j < MAX_CHAR; j++) {
                if (numArray[j] > maxNum) {
                    maxNum = numArray[j];
                    charMax = j;
                }
            }
            topUsedChars[i] = charMax;
            topUsedQuantity[i] = maxNum;
            numArray[charMax] = -1;
        }
        System.out.println(Arrays.toString(topUsedChars));
        printGraph(topUsedChars, topUsedQuantity);
    }

    private static void printGraph(int[] topUsedChars, int[] topUsedQuantity) {
        int[] height = new int[TOP_MAX];
        for (int i = 0; i < TOP_MAX; i++) {
            height[i] = TOP_MAX * topUsedQuantity[i] / topUsedQuantity[0];
        }
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (i - height[j] == 1)
                    System.out.printf("%3d", topUsedQuantity[j]);
                else if (i == 0)
                    System.out.printf("%3c", topUsedChars[j]);
                else if (i <= height[j])
                    System.out.printf("%3c", '#');
            }
            System.out.println();
        }
    }
}
*/
