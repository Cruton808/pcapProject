package IPConverter;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.*;

/**
 * Created by Creighton PC on 4/8/2017.
 */
public class IPConverter {
    public static void main(String[] args) {
        String hostName = "8.8.8.8";
        try{
            InetAddress host = getByName(hostName);
            System.out.println(host.getHostName());
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
