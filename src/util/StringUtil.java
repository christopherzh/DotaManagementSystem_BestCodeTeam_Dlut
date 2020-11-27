package util;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;
public class StringUtil {
    public static boolean isEmpty(String str){
        //judge whether str is empty
        if ("".equals(str)||str==null)
            return true;
        return false;
    }
    public static boolean isNumeric(String str){
        //judge whether str can be parsed into integer
        Pattern pattern = Pattern.compile("^?[0-9]+");//using regex to express integer
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static boolean isLettersAndNumbers(String str){
        // judge whether str is consisted of letters and numbers
        Pattern pattern = Pattern.compile("[0-9|a-z|A-Z]+");
        Matcher isLegal = pattern.matcher(str);
        if (!isLegal.matches())
            return false;
        return true;
    }
    public static boolean isLetters(String str){
        // judge whether str is only consisted of letters
        char[] chars = str.toCharArray();
        for (char c : chars)
            if (!((c>='a'&& c<='z')||(c>='A')&&c<='Z'))
                return false;
        return true;
    }
    public static List<String> seperateString(String str, char separator){
        List<String> list = new LinkedList<>();
        char[] chars = str.toCharArray();
        int len = str.length();
        int left = 0,right = 0;// [left, right)
        for(;right<len;++right){
            if (chars[right] == separator) {
                list.add(str.substring(left,right));
                left = right+1;
            }
        }
        list.add(str.substring(left,right));

        return list;
    }
    public static Time getTimeFromSql(String time){
        // Time is stored as string
        List<String> t = seperateString(time,':');
        return new Time(Integer.parseInt(t.get(0)),Integer.parseInt(t.get(1)),Integer.parseInt(t.get(2)));
    }
    public static String mergeString(List<String> stringList,char separator){
        String rtn = "";
        for (String string:stringList){
            rtn += (string + separator);
        }
        return rtn;
    }
}
