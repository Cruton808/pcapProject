package IPConverter;

import sun.rmi.runtime.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static java.net.InetAddress.*;

/**
 * Created by Creighton PC on 4/8/2017.
 */
public class IPConverter {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String hostName;

        System.out.println("Enter an ip address:");
        hostName = scan.nextLine();

//        for(String host : hosts){
//
//        }
        try{
            InetAddress host = getByName(hostName);
            System.out.println(host.getHostName());
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
