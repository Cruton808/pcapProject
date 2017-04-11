package IPConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static java.net.InetAddress.*;

/**
 * Created by Creighton PC on 4/8/2017.
 */
public class IPConverter {


    private static HashMap<String, String> ipHostNamePair = new HashMap<String, String>();

    //add this ip to cached map
    public static void add(String ip) {
        if(!ipHostNamePair.containsKey(ip)) {
            String hostname = "";

            try {
                hostname = InetAddress.getByName(ip).getHostName();
                System.out.println(hostname);
            } catch (UnknownHostException e) {
                hostname = ip;
                //do nothing. hostname is blank
                //e.printStackTrace();
            }

            ipHostNamePair.put(ip, hostname);
        }
        //resolve hostname

    }

    public static String getHostname(String ip) {

        return ipHostNamePair.get(ip);
    }


    public static void resetCache() {
        ipHostNamePair.clear();
    }

}
