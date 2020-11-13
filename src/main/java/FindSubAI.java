import java.util.ArrayList;

public class FindSubAI {


    public static void main(String[] args) {
        String testString = "37AB7AC40001CX9AZ0AI3XXXAB0AI9BY10BZ233CX9AZ0AI3XXX";
        String TVL = testString.substring(2);
        System.out.println(recursion(TVL, 0));
    }

    private static ArrayList<String> result = new ArrayList<String>();
    private static ArrayList<String> recursion(String testString, int startIndex) {
        //Terminator
        if ( startIndex >= testString.length() - 1) return result;

        //current logic
        int len = Character.getNumericValue(testString.charAt(startIndex + 2));
        int endIndex = startIndex + len + 3;
        String val = testString.substring(startIndex + 3, endIndex);
        System.out.println(val);
        if ( val.contains("AI")) {
            String[] subValue = val.split("AI");
            for (String s : subValue) {
                 if (Character.isDigit(s.charAt(0))) {
                     int valueLength = Character.getNumericValue(s.charAt(0));
                     String sub = s.substring(1, valueLength + 1);
                     result.add(sub);
                 }
            }
        }

        //drill down
        recursion(testString, endIndex);

       return result;

    }

    private static ArrayList<String> findSubAI(String testString) {
        ArrayList<String> res = new ArrayList<String>();

        //remove the first 2 character(length) of the payment transaction data
        String TVL = testString.substring(2);

        //find every tag, length, value in DATA
        int length = 0;
        for (int i = 2; i < TVL.length(); i = i + length +3) {
            length = Character.getNumericValue(TVL.charAt(i));
            String value = TVL.substring(i + 1, i + length + 1);

            //check the if the value contains sub-tag AI
            int subLength = 0;
            for (int j = 0; j < length; j = j + subLength + 3) {
                subLength =  Character.getNumericValue(value.charAt(j + 2));
                String subTag = value.substring(j, j + 2);

                if (subTag.equals("AI")) {
                    int subAILength = Character.getNumericValue(value.charAt(j + 2));
                    String subAIvalue = value.substring(j + 3, j + 3 + subAILength);
                    res.add(subAIvalue);
                }
            }
        }
        return res;
    }

}
