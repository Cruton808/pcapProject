package IPConverter;

import java.util.*;

/**
 * Created by Creighton PC on 4/11/2017.
 */
public class TopDest {

    HashMap<String, Set<String>> IPMap = new HashMap<>();

    public void add(String dest, String src) {
        if (!IPMap.containsKey(dest)) {
            IPMap.put(dest, new HashSet<String>());
        }

        IPMap.get(dest).add(src);
    }


    public int getSize(String ip){
        return IPMap.get(ip).size();
    }

    public List<String> getTop(int count) {
        ArrayList<String> result = new ArrayList<>(count);

        Set<String> keys = IPMap.keySet();

        for (String key : keys) {
            int size = IPMap.get(key).size();
            for(int i = 0 ; i < count ; i++) {
                //System.out.println("key: " + key+ " size: "+result.size());
                if (i <= result.size()) {
                    result.add( key);
                    break;
                } else { // check the size
                    //if current size is less than go to the next one
                    if(getSize(key) < size) {
                        continue;
                    } else {
                        result.add(i, key);
                        break;
                    }
                }
            }
        }
        return result.subList(0, 10);

    }


}
