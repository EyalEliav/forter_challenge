import java.io.*;
import java.util.*;

public class NicknamesDb {
    //Map between string - the nickname to Id - number of line, nicknames of the same name has same id
    private HashMap<String, Integer> nicks;
    public NicknamesDb(String location){
        nicks = new HashMap<String, Integer>();
        try {
            Scanner scr = new Scanner(new BufferedReader(new FileReader(location)));
            int i = 0;
            String[] lineNicks;
            while (scr.hasNext()){
                lineNicks = scr.next().split(",");
                for (String s : lineNicks)
                    nicks.put(s, i);
                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean exists(String name){
        return nicks.containsKey(name);
    }
    public boolean nameEquals(String name1, String name2){
        if (!nicks.containsKey(name1) || !nicks.containsKey(name2))
            return false;
        return nicks.get(name1).equals(nicks.get(name2));
    }
}
