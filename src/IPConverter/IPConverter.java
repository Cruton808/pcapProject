package IPConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static java.net.InetAddress.*;
import org.xbill.DNS.*;

import java.io.IOException;
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
                hostname = reverseDns(ip);
                System.out.println(hostname);
            } catch (UnknownHostException e) {
                hostname = ip;
                //do nothing. hostname is blank
                //e.printStackTrace();
            } catch (IOException e) {
                hostname = ip;
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

    private static String reverseDns(String hostIp) throws IOException {

        Resolver res = new ExtendedResolver();

        Name name = ReverseMap.fromAddress(hostIp);

        int type = Type.PTR;

        int dclass = DClass.IN;

        Record rec = Record.newRecord(name, type, dclass);

        Message query = Message.newQuery(rec);

        Message response = res.send(query);

        Record[] answers = response.getSectionArray(Section.ANSWER);

        if (answers.length == 0)

            return hostIp;

        else

            return answers[0].rdataToString();

    }


}
