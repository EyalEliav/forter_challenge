import java.util.*;

public class Distance {
    //calculate all the strings that are 1 edit distance from the given string
    public static HashSet<String> levinshteinIsOne(String s){
        HashSet<String> strs = new HashSet<String>();
        //missing letter
        StringBuilder sBuilder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++)
        {
            char temp = sBuilder.charAt(i);
            strs.add(sBuilder.deleteCharAt(i).toString());
            //bring back the original string
            sBuilder.insert(i, temp);
        }
        //replace letter
        char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char c : abc){
            for (int i = 0; i < s.length(); i++){
                if (sBuilder.charAt(i) != c) {
                    char temp = sBuilder.charAt(i);
                    sBuilder.setCharAt(i, c);
                    strs.add(sBuilder.toString());
                    //bring back the original string
                    sBuilder.setCharAt(i, temp);
                }
            }
        }
        //add a letter
        for (char c : abc){
            for (int i = 0; i <= s.length(); i++){
                strs.add(sBuilder.insert(i, c).toString());
                //bring back the original string
                sBuilder.deleteCharAt(i);
            }
        }
        return strs;
    }
}
