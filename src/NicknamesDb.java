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
                for (String s : commonNicks){
                    if (nicks.containsKey(s))
                        nicks.get(s).addAll(commonNicks);
                    else
                        nicks.put(s, commonNicks);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean contains(String name){
        return nicks.containsKey(name);
    }
    public boolean nameEquals(String name1, String name2){
        if (name1.equals(name2))
            return true;
        //checking if the names are on the db
        if (this.contains(name1) && this.contains(name2)) {
            //if the names are on the db, they should be the same or nicknames of each other (otherwise they are not equal)
            if (nicks.get(name1).contains(name2))
                return true;
            else
                return false;
        }
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
