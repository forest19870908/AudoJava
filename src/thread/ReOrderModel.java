package thread;

/**
 * Created by ZSL on 2017/8/7.
 */
public class ReOrderModel {
    private static int a = 0;
    private static int b = 0;
    public ReOrderModel(){
        System.out.println(a+b);
    }
    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        ReOrderModel.a = a;
    }

    public static int getB() {
        return b;
    }

    public static void setB(int b) {
        ReOrderModel.b = b;
    }
}
