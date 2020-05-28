import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LastNamesDb implements DataBase{
    private HashSet<String> names;
    public LastNamesDb(String location){
        names = new HashSet<String>();
        try {
            Scanner scr = new Scanner(new BufferedReader(new FileReader(location)));
            while (scr.hasNext())
                //in the db, the last name appears first in the line (along with other data)
                names.add(scr.next().split(",")[0].toLowerCase());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean contains(String s){
        return names.contains(s);
    }
    public HashSet<String> possibleNames(String name){
        HashSet<String> posNames = new HashSet<String>();
        for (String s : Distance.levinshteinIsOne(name))
            if (this.contains(s))
                //adding all the possible names
                posNames.add(s);
        return posNames;
    }
}
