import java.util.Scanner;

public class Program {
    private static final short MAX_CLASSES_WEEK = 10;
    private static final short MAX_NUMBER_STUDENTS = 10;
    private static final short WEEKDAYS = 7;
    private static final String HERE = "HERE";
    private static final String DELIMITER = ".";
    private static final String[] WEEK = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] students = new String[MAX_NUMBER_STUDENTS];
        getStudents(students, sc);

        boolean[] dayOfClass = new boolean[WEEKDAYS];
        String[] timeOfClass = new String[WEEKDAYS];
        getSchedule(sc, dayOfClass, timeOfClass);

        String[][] attend = new String[MAX_NUMBER_STUDENTS + 1][MAX_CLASSES_WEEK * 5 + 1];
        getAttendance(sc, attend, students);

        //printSchedule
        System.out.printf("%10c", ' ');
        for (int i = 0; i < 30; i++) {
            int dayOfTheWeek = (i + 1) % 7;
            if (dayOfClass[dayOfTheWeek]) {
                char[] arr = sort(timeOfClass[dayOfTheWeek]);
                for (int j = 0; j < timeOfClass[dayOfTheWeek].length(); j++)
                    System.out.printf("%11s", arr[j] + ":00 " + WEEK[dayOfTheWeek] + " " + (i + 1) + "|");
            }
        }
        System.out.println();

        for (int i = 0; students[i] != null; i++) { //iter by student
            System.out.printf("%10s", students[i]);
            for (int k = 0; k < 30; k++) {
                int dayOfTheWeek = (k + 1) % 7;
                if (dayOfClass[dayOfTheWeek]) {
                    char[] arr = sort(timeOfClass[dayOfTheWeek]);
                    for (int j = 0; j < timeOfClass[dayOfTheWeek].length(); j++) {
                        String isAttended = checkAttendance(attend, students[i], arr[j] + " " + (k + 1));
                        if (isAttended == null)
                            System.out.printf("%11s", "|");
                        else
                            System.out.printf("%11s", isAttended + "|");
                    }

                }
            }
            System.out.println();
        }
    }

    private static void getStudents(String[] students, Scanner sc) {
        for (int i = 0; i < MAX_NUMBER_STUDENTS; i++) {
            String temp = sc.next();
            if (temp.equals(DELIMITER))
                break;
            if (temp.length() > 10) {
                System.err.println("Maximum name length is 10 symbols!");
                System.exit(-1);
            }
            students[i] = temp;
        }
    }

    private static void getSchedule(Scanner sc, boolean[] dayOfClass, String[] timeOfClass) {
        for(int i = 0; i < MAX_CLASSES_WEEK; i++) {
            String tempTime = sc.next();
            if (tempTime.equals("."))
                break;
            String tempDay = sc.next();
            if (!(tempTime.equals("1") || tempTime.equals("2") || tempTime.equals("3") ||
                    tempTime.equals("4") || tempTime.equals("5") || tempTime.equals("6"))) {
                System.err.println("Incorrect time of the class!");
                System.exit(-1);
            }
            switch (tempDay) {
                case "MO":
                    dayOfClass[0] = true;
                    timeOfClass[0] = timeOfClass[0] == null ? tempTime : timeOfClass[0].concat(tempTime);
                    break;
                case "TU":
                    dayOfClass[1] = true;
                    timeOfClass[1] = timeOfClass[1] == null ? tempTime : timeOfClass[1].concat(tempTime);
                    break;
                case "WE":
                    dayOfClass[2] = true;
                    timeOfClass[2] = timeOfClass[2] == null ? tempTime : timeOfClass[2].concat(tempTime);
                    break;
                case "TH":
                    dayOfClass[3] = true;
                    timeOfClass[3] = timeOfClass[3] == null ? tempTime : timeOfClass[3].concat(tempTime);
                    break;
                case "FR":
                    dayOfClass[4] = true;
                    timeOfClass[4] = timeOfClass[4] == null ? tempTime : timeOfClass[4].concat(tempTime);
                    break;
                case "SA":
                    dayOfClass[5] = true;
                    timeOfClass[5] = timeOfClass[5] == null ? tempTime : timeOfClass[5].concat(tempTime);
                    break;
                case "SU":
                    dayOfClass[6] = true;
                    timeOfClass[6] = timeOfClass[6] == null ? tempTime : timeOfClass[6].concat(tempTime);
                    break;
            }
        }
    }

    private static void getAttendance(Scanner sc, String[][] attend, String[] students) {

        while (true) {
            String name = sc.next();
            if (name.equals("."))
                break;
            String time = sc.next();
            String day = sc.next();
            String status = sc.next();

            if (!checkIfStudentExists(students, name))
                continue;

            int k;
            for (k = 1; k < MAX_NUMBER_STUDENTS + 1 && attend[k][0] != null; k++) {
                if (name.equals(attend[k][0])) {
                    int column = findTime(attend, time + " " + day);
                    attend[0][column] = time + " " + day;
                    attend[k][column] = status.equals(HERE) ? "1" : "-1";
                    break;
                }
            }
            //if student is not added to attend table yet
            if (attend[k][0] == null) {
                attend[k][0] = name;
                int column = findTime(attend, time + " " + day);
                attend[0][column] = time + " " + day;
                attend[k][column] = status.equals(HERE) ? "1" : "-1";
            }
        }
    }

    private static boolean checkIfStudentExists(String[] students, String tmp1) {
        int i;
        for (i = 0; i < MAX_NUMBER_STUDENTS; i++) {
            if (tmp1.equals(students[i]))
                return true;
        }
        return false;
    }

    private static int findTime(String[][] attend, String time) {
        int i = 1;
        for (; i < MAX_CLASSES_WEEK * 5 + 1 && attend[0][i] != null; i++) {
            if (attend[0][i].equals(time))
                break;
        }
        return i;
    }

    private static String checkAttendance(String[][] attend, String studentName, String time) {
        for (int i = 1; i < MAX_NUMBER_STUDENTS + 1; i++) {
            if (studentName.equals(attend[i][0])) {
                int column = findTime(attend, time);
                return attend[i][column];
            }
        }
        return null;
    }

    private static char[] sort(String times) {
        char[] ret = times.toCharArray();
        for (int i = 0; i < times.length(); i++) {
            for (int j = times.length() - 1; j > i; j--) {
                if (ret[j - 1] > ret[j]) {
                    char tmp = ret[j -1];
                    ret[j - 1] = ret[j];
                    ret[j] = tmp;
                }
            }
        }
        return ret;
    }
}


/*
john
ma
er
.
2 MO
3 FR
5 FR
2 FR
1 MO
.
ma 2 21 HERE
er 4 18 NOT_HERE
weew 3 19 HERE
john 2 20 NOT_HERE
.
*/
