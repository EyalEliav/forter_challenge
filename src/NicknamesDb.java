import java.io.*;
import java.util.*;

public class NicknamesDb implements DataBase {
    //Map between string - the nickname, to Id - number of line, nicknames of the same name has same id
    private HashMap<String, HashSet<String>> nicks;
    public NicknamesDb(String location){
        nicks = new HashMap<String, HashSet<String>>();
        try {
            Scanner scr = new Scanner(new BufferedReader(new FileReader(location)));
            while (scr.hasNext()){
                HashSet<String> commonNicks = new HashSet<String>();
                Collections.addAll(commonNicks, scr.next().split(","));
                for (String s : commonNicks)
                    nicks.put(s.toLowerCase(), commonNicks);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean contains(String name){
        return nicks.containsKey(name);
    }
    public boolean nameEquals(String name1, String name2){
        //checking if the names are the same or nicknames of the same name
        if (name1.equals(name2) || nicks.get(name1).contains(name2))
            return true;
        //if the db contains both of the names and they are not nicknames of each other (with checked earlier), they are not equal
        if (this.contains(name1) && this.contains(name2))
            return false;
        //checking if the names are typos of each other
        HashSet<String> common = possibleNames(name1);
        common.retainAll(possibleNames(name2));
        return common.size() > 0;
    }
    public HashSet<String> possibleNames(String name){
        HashSet<String> posNames = new HashSet<String>();
        for (String s : Distance.levinshteinIsOne(name))
            if (this.contains(s)) {
                //adding all the possible names and nicknames
                for (String str : nicks.get(s))
                    posNames.add(str);
            }
        return posNames;
    }
}
