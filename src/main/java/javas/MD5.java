package javas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by andreas on 2018-08-19.
 */
public class MD5 {

    private final static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String data = scan.nextLine();

        //System.out.println("Got data " + data);

        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        StringBuffer hashed = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hashed.append('0');
            hashed.append(hex);
        }

        System.out.println(hashed);

        scan.close();

    }

}
