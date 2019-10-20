package com.mycompany;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Excel save = new Excel();
            save.writeExcel();

        } catch (Exception e){
            System.out.println("Failed");
        }
    }
}
