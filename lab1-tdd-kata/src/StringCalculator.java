import java.util.Arrays;
import java.util.Objects;

public class StringCalculator {

    int arraySum(String numbers, String delimiters, String msg) throws Exception {
        String[] splitted_numbers = numbers.split(delimiters);
        if(!Character.isDigit(numbers.charAt(numbers.length() - 1))){
            throw new Exception(msg);
        }

        try {
            return Arrays.stream(splitted_numbers).filter(item -> !Objects.equals(item, "")).
                    map(Integer::parseInt).filter(x -> x <= 1000).reduce(0, Integer::sum);
        } catch (Exception exception) {
            throw new Exception(msg);
        }
    }
    boolean delimiter_checker(String delimiters) {
        for (int i = 0; i < delimiters.length(); i++) {
            if(delimiters.charAt(0) != '[')
                return false;
            if(delimiters.charAt(i) == ']' && i == delimiters.length() - 1)
                return true;
            if(delimiters.charAt(i) == ']' && delimiters.charAt(i + 1) != '[')
                return false;

        }
        return false;
    }
    public int add(String numbers) throws Exception{

        if(numbers.length() == 0) return 0;

        if(numbers.charAt(0) != '/' || numbers.charAt(1) != '/'){
            return arraySum(numbers, "[,\n]",
                    "Invalid input, expected format: num[,][\\n]num..");
        }
        else {
            int break_position = numbers.indexOf('\n');
            if(break_position == -1)
                throw new Exception("Invalid input: expected \\n after delimiter");
            String delimiters = numbers.substring(2, break_position);
            numbers = numbers.substring(break_position + 1);
            if(!delimiter_checker(delimiters)){
                return arraySum(numbers, delimiters,
                        "Invalid input, expected format: //delimiter\nnum[delimiter]num..");
            }
            else{
                delimiters = delimiters.replace("][", "");
                return arraySum(numbers, delimiters,
                        "Invalid input, expected format: //delimiter\nnum[delimiter]num..");
            }
        }


    }

}