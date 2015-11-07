package distribute.algtest;

import java.math.BigInteger;

/**
 * Created by Administrator on 2015/11/5.
 */
public class RoundRobinWeight {
    public static int[] server = new int[10];
    public static int cw = 0;
    public static int number = -1;
    public static int max;//最大权重
    public static int gcd;//最大公约数

    public static void init(){
        server[0]=5;
        server[1]=15;
        server[2]=10;
        server[3]=5;
        server[4]=25;
        server[5]=5;
        server[6]=5;
        server[7]=10;
        server[8]=11;
        server[9]=9;


    }

    /**
     * 求最大值 权重
     */
    public static int getMaxWeight(int[] arrayweight) {
        int max=0;
        for (int i = 0; i < arrayweight.length; i++) {
            if (arrayweight[i] > max) {
                max = arrayweight[i];
            }
        }
        return max;
    }

    /**
     * 求最大公约数
     *
     * @param ary
     * @return
     */
    public static int gcd(int[] ary) {

        int min = ary[0];

        for (int i = 0; i < ary.length; i++) {
            if (ary[i] < min) {
                min = ary[i];
            }
        }
        while (min >= 1) {
            boolean isCommon = true;
            for (int i = 0; i < ary.length; i++) {
                if (ary[i] % min != 0) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon) {
                break;
            }
            min--;
        }
        // System.out.println("gcd=" + min);
        return min;
    }

    public int gcd1(int[] server) {
        int comDivisor = 0;
        for (int i = 0; i < server.length-1; i++) {
            if (comDivisor == 0) {
                comDivisor = gcd(server[i], server[i + 1]);
            } else {
                comDivisor = gcd(comDivisor, server[i + 1]);
            }
        }
        return comDivisor;
    }

    public int gcd(int num1, int num2) {
        BigInteger i1 = new BigInteger(String.valueOf(num1));
        BigInteger i2 = new BigInteger(String.valueOf(num2));
        return i1.gcd(i2).intValue();
    }
}
