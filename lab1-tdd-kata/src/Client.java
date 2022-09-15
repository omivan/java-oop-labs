

public class Client {
    public static void main(String[] args) {

        StringCalculator stringCalculator = new StringCalculator();
        try {
            int test = stringCalculator.add("//&&\n1&&2&&3&&&&4");
            System.out.println(test);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}