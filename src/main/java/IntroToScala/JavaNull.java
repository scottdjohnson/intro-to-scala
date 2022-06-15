package IntroToScala;

import java.util.Random;

public class JavaNull {

    // Function returns an int, but sometimes it returns a null
    //  eg reads a file that has a value (but maybe the file is not there or it is empty)
    public static Integer getIntFromSomewhere() {
        Random rd = new Random();
        if(rd.nextBoolean())
            return null;
        else
            return rd.nextInt(1000);
    }

    public static void main(java.lang.String[] args) {

        Integer myInt = getIntFromSomewhere();

        System.out.println("Hello user, you have $" + myInt);

        if (myInt == null)
            System.out.println("Hello user, it looks like you are not in the system");
        else
            System.out.println("Hello user, you have $" + myInt);

    }
}