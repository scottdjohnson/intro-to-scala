package IntroToScala;

public class JavaClassMain {
    public static void main(java.lang.String[] args) {

        JavaClass jc = new JavaClass(1, 2);
        System.out.println(jc.sum());

        jc.setX(5);
        jc.setY(10);
        System.out.println(jc.sum());
    }
}