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
    public boolean nameEquals(String name1, String name2){
        //checking if the names are the same or nicknames of the same name
        if (name1.equals(name2))
            return true;
        //if the db contains both of the names and they are not equal, they are not the same name
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
            if (this.contains(s))
                //adding all the possible names
                posNames.add(s);
        return posNames;
    }
}
