import java.util.ArrayList;
import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args){
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

        map.put(1,new ArrayList<>());
        ArrayList<Integer> tmp = map.getOrDefault(2, new ArrayList<>());
        tmp.add(1);
        tmp.add(2);
        System.out.println(map);
    }
}
