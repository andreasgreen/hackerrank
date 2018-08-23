package javas;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by andreas on 2018-08-21.
 */
public class JavaBigDecimal {
    public static void main(String []args) {
        //Input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n + 2];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        sc.close();

        //Output
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

        System.out.print("====");

        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 == null && o2 == null) {
                    return 0;
                } else if(o1 == null) {
                    return 1;
                } else if(o2 == null) {
                    return -1;
                } else {
                    //System.out.println("string o1 " + o1);
                    BigDecimal b1 = new BigDecimal(o1);
                    //System.out.println("string o2 " + o2);
                    BigDecimal b2 = new BigDecimal(o2);
                    return b2.compareTo(b1);
                }
            }
        });

        //Output
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
