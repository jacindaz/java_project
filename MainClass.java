
import java.lang.*;
import java.util.*;
import java.io.*;

public class MainClass {

   public static void main(String[] args) {


    SomeClass1 sc1 = new SomeClass1('q');
    SomeClass2 sc2 = new SomeClass2('q');
    ICharQ ob;

    ob = sc1;
    System.out.println("ob.get = " + ob.get() );

    ob = sc2;
    System.out.println("ob.get = " + ob.get() );



    System.out.println("ok.");




    }
}


class SomeClass1 implements ICharQ {

    private char var;

    SomeClass1(char v)
    {
        var = v;
    }

    public void addOne()
    {
        var++;
    }

    public void put(char c)
    {
        var = (char)(c + c);

    }
    public char get()
    {
        return 'a';
    }


}

class SomeClass2 implements ICharQ {

    private char var;

    SomeClass2(char v)
    {
        var = v;
    }

    public void addOne()
    {
        var++;
    }

    public void put(char c)
    {
        var = c;
    }
    public char get()
    {
        return 'b';

    }


}

