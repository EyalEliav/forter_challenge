import java.util.*;

public interface DataBase {
    public boolean contains(String s);
    public HashSet<String> possibleNames(String name);
    public boolean nameEquals(String name1, String name2);
}
