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
        if (!nicks.containsKey(name1) || !nicks.containsKey(name2))
            return false;
        return nicks.get(name1).equals(nicks.get(name2));
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
