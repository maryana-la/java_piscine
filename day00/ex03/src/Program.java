import java.util.Scanner;

public class Program {
    private static final String END_OF_INPUT = "42";
    private static final String WEEK = "Week";
    private static final int MAX_NUM_OF_WEEKS = 18;
    private static final int NUM_OF_TESTS = 5;
    private static long GRADES;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        int weekCount = 0;

        while (!END_OF_INPUT.equals(input) && weekCount < MAX_NUM_OF_WEEKS) {
            try {
                input = sc.next();
            } catch (Exception e) {
                errorExit(-3);
            }
            if (input.matches(WEEK) || input.matches("week")) {
                int num = 0;
                try {
                    num = sc.nextInt();
                } catch (Exception e) {
                    errorExit(-2);
                }
                if (num != weekCount + 1)
                    errorExit(-1);
                saveMinGrade(sc, weekCount);
                weekCount++;
            }
        }
//        System.out.println(GRADES);
        printGraph(weekCount);
    }

    private static void errorExit(int exitCode) {
        System.err.println("IllegalArgument");
        System.exit(exitCode);
    }

    private static void saveMinGrade(Scanner sc, int weekCount) {
        long min = 10;
        int grade = 0;
        for (int i = 0; i < NUM_OF_TESTS; i++) {
            try {
                grade = sc.nextInt();
            } catch (Exception e) {
                errorExit(-2);
            }
            if (grade > 9 || grade < 1)
                errorExit(-1);
            min = grade < min ? grade : min;
        }
        for (int i = 0; i < weekCount; i++)
            min *= 10;
        GRADES = min + GRADES;
    }

    private static void printGraph(int weekCount) {
        for (int i = 1; i <= weekCount; i++) {
            System.out.print(WEEK + " " + i + " ");
            for (int j = 0; j < GRADES % 10; j++)
                System.out.print("=");
            System.out.println(">");
            GRADES = GRADES / 10;
        }
    }
}

/*
        Week 1
        3 3 3 3 3
        Week 2
        4 4 4 4 4
        Week 3
        5 5 5 5 5
        Week 4
        9 9 9 9 9
        Week 5
        4 4 4 4 4
        week 6
        5 5 5 5 5
        week 7
        9 9 9 9 9
        week 8
        1 1 1 1 1
        week 9
        2 2 2 2 2
        week 10
        5 5 5 5 5
        week 11
        3 3 3 3 3
        week 12
        7 7 7 7 7
        week 13
        3 3 3 3 3
        week 14
        6 6 6 6 6
        week 15
        7 7 7 7 7
        week 16
        5 5 5 5 5
        week 17
        8 8 8 8 8
        week 18
        9 9 9 9 9
*/
