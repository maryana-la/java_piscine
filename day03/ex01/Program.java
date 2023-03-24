
public class Program {
    private static final String ERROR = "Please provide number. Usage: java Program --count=NUM";

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println(ERROR);
            System.exit(-1);
        }
        int num = getNumber(args[0]);

        Thread hen = new myThread("HEN", num, 0);
        Thread egg = new myThread("EGG", num, 1);

        hen.start();
        egg.start();
        try {
            hen.join();
            egg.join();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
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

}






