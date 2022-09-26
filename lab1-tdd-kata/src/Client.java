

public class Client {
    public static void main(String[] args) {

        StringCalculator stringCalculator = new StringCalculator();
        try {
            int test = stringCalculator.add("//[!][!!!][()][1]\n212!!2,2()2\n2");
            System.out.println(test);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }



    }
}