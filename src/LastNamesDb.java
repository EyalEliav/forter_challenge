import java.io.*;
import java.util.*;

public class LastNamesDb {
    private HashSet<String> names;
    public LastNamesDb(String location){
        names = new HashSet<String>();
        try {
            Scanner scr = new Scanner(new BufferedReader(new FileReader(location)));
            while (scr.hasNext())
                //in the db, the last name appears first in the line (along with other data)
                names.add(scr.next().split(",")[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean exists(String s){
        return names.contains(s);
    }
}
