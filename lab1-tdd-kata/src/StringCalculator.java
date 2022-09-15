import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringCalculator {

    ArrayList<String> getSplittedDelimiterList(String delimiters){
        int startPos = 0, endPos;
        ArrayList<String> splittedDelimiters = new ArrayList<>();
        for(int i = 0; i < delimiters.length() - 1; i++) {
            if(delimiters.charAt(i) == ']' && delimiters.charAt(i+1) == '['){
                endPos = i;
                splittedDelimiters.add(delimiters.substring(startPos, endPos));
                startPos = i + 2;
            }
        }
        splittedDelimiters.add(delimiters.substring(startPos));


        return splittedDelimiters;
    }

    ArrayList<String> customStrSplit(String str, String delimiters) throws Exception {
        ArrayList<String> splittedWords = new ArrayList<>(){{add(str);}};
        List<String> splittedDelimiterList = getSplittedDelimiterList(delimiters);

        for(String delimiter: splittedDelimiterList){
            List<String> tempSplittedWords = new ArrayList<>();
            for(String word: splittedWords){
                tempSplittedWords.addAll(singleWordSplit(word, delimiter));
            }
            splittedWords = new ArrayList<>(tempSplittedWords);

        }

        return  splittedWords;
    }

     public ArrayList<String> singleWordSplit(String word, String delimiter) throws Exception {
        ArrayList<String> singleWordSplitList = new ArrayList<>();
        List<Integer> delimiterIndexList = new ArrayList<>();
         int index = word.indexOf(delimiter);
         while (index >= 0) {
             delimiterIndexList.add(index);
             index = word.indexOf(delimiter, index + 1);
         }
         if(delimiterIndexList.isEmpty()) return new ArrayList<>(List.of(word));

         singleWordSplitList.add(word.substring(0, delimiterIndexList.get(0)));
         try{
             for (int i = 1; i < delimiterIndexList.size(); i++){
                 singleWordSplitList.add(word.substring(delimiterIndexList.get(i - 1) + delimiter.length(),
                         delimiterIndexList.get(i)));
             }
             singleWordSplitList.add(word.substring(
                     delimiterIndexList.get(delimiterIndexList.size() - 1) + delimiter.length()));
         } catch (Exception e){
            throw new Exception("Invalid input, expected format: //delimiter\nnum[delimiter]num..");
         }


        return singleWordSplitList;
    }

    int arraySum(String numbers, String delimiters, String msg) throws Exception {

        delimiters = delimiters.concat("][\n][,");

        List<String> splitted_numbers = customStrSplit(numbers, delimiters);

        if(!Character.isDigit(numbers.charAt(numbers.length() - 1))){
            throw new Exception(msg);
        }

        try {
            int res = splitted_numbers.stream().filter(item -> !Objects.equals(item, "")).
                    map(Integer::parseInt).filter(x -> x <= 1000).reduce(0, Integer::sum);
            List<Integer> negative_list = splitted_numbers.stream().filter(item -> !Objects.equals(item, "")).
                    map(Integer::parseInt).filter(x -> x <= 0).toList();
            int negative_count = (int) splitted_numbers.stream().filter(item -> !Objects.equals(item, "")).
                    map(Integer::parseInt).filter(x -> x <= 0).count();

            if(negative_count > 0)
            {
                System.out.println(negative_list);
                msg = "negative numbers found";
                throw new Exception(msg);
            }
            return res;
        } catch (Exception exception) {
            throw new Exception(msg);
        }
    }
    boolean delimiterChecker(String delimiters) {
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

        if(numbers.length() == 1 && !Character.isDigit(numbers.charAt(0)))
            throw new Exception("Invalid input, expected format: num[,][\\n]num..");

        if(numbers.charAt(0) != '/' || numbers.charAt(1) != '/'){
            return arraySum(numbers, ",][\n",
                    "Invalid input, expected format: num[,][\\n]num..");
        }
        else {
            int break_position = numbers.indexOf('\n');
            if(break_position == -1)
                throw new Exception("Invalid input: expected \\n after delimiter");
            String delimiters = numbers.substring(2, break_position);
            numbers = numbers.substring(break_position + 1);
            if (!delimiterChecker(delimiters)){
                return arraySum(numbers, delimiters,
                        "Invalid input, expected format: //delimiter\nnum[delimiter]num..");
            }
            else{
                delimiters = delimiters.substring(1, delimiters.length() - 1);
              //  delimiters = delimiters.replace("]", "");
               // delimiters = delimiters.replace("[", "");
                return arraySum(numbers, delimiters,
                        "Invalid input, expected format: //delimiter\nnum[delimiter]num..");
            }
        }

    }

}