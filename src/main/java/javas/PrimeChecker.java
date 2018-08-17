package javas;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import static java.lang.System.in;

/**
* Created by andreas on 2018-08-17.
*/


public class PrimeChecker {

    public static void main(String[] args) {
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            int n1=Integer.parseInt(br.readLine());
            int n2=Integer.parseInt(br.readLine());
            int n3=Integer.parseInt(br.readLine());
            int n4=Integer.parseInt(br.readLine());
            int n5=Integer.parseInt(br.readLine());
            Prime ob=new Prime();
            ob.checkPrime(n1);
            ob.checkPrime(n1,n2);
            ob.checkPrime(n1,n2,n3);
            ob.checkPrime(n1,n2,n3,n4,n5);
            Method[] methods=Prime.class.getDeclaredMethods();
            Set<String> set=new HashSet<>();
            boolean overload=false;
            for(int i=0;i<methods.length;i++)
            {
                if(set.contains(methods[i].getName()))
                {
                    overload=true;
                    break;
                }
                set.add(methods[i].getName());

            }
            if(overload)
            {
                throw new Exception("Overloading not allowed");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

class Prime {

    protected void checkPrime(int... primes) {

        for(int i : primes) {
            if(isPrime(i)) {
                System.out.print(i + " ");
            }
        }

        System.out.println("");
    }

    private boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n == 2 || n == 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        long sqrtN = (long)Math.sqrt(n)+1;

        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i-1) == 0 || n % (i+1) == 0) {
                return false;
            }
        }

        return true;

    }
}
