package javas;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;
import java.security.*;

/**
 * Created by andreas on 2018-08-18.
 */
public class CanYouAccess {


        public static void main(String[] args) throws Exception {
            DoNotTerminate.forbidExit();

            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int num = Integer.parseInt(br.readLine().trim());
                Object o;// Must be used to hold the reference of the instance of the class Solution.Inner.Private

                Object inner = new CanYouAccess.Inner();
                Class<?> innerClazz = Class.forName("javas.CanYouAccess$Inner$Private");

                Constructor<?> constructor = innerClazz.getDeclaredConstructor(Inner.class);
                constructor.setAccessible(true);

                o = constructor.newInstance(inner);

                Method method = o.getClass().getDeclaredMethod("powerof2", int.class);
                method.setAccessible(true);
                Object r = method.invoke(o, num);

                String res = (String)r;

                System.out.println(num + " is " + res);


                System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");


            }//end of try

            catch (DoNotTerminate.ExitTrappedException e) {
                System.out.println("Unsuccessful Termination!!");
            }
        }//end of main

    static class Inner{
        private class Private{
            private String powerof2(int num){
                return ((num&num-1)==0)?"power of 2":"not a power of 2";
            }
        }
    }//end of Inner

}//end of Solution

class DoNotTerminate { //This class prevents exit(0)

    public static class ExitTrappedException extends SecurityException {

        private static final long serialVersionUID = 1L;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}
