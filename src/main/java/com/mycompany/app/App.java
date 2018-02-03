package com.mycompany.app;
     
import java.util.Date;

/**
 * Hello world!
 */
public class App
{

    private final String message = "Hello World!";

    public App() {}

    public static void main(String[] args) {
       while(true){
		   System.out.println("v1.3:" + new Date());
		   try{
			Thread.sleep(3000);
		   }catch(Exception e){
		   }
	   }
    }

    private final String getMessage() {
        return message;
    }
	
    public static void hello() {
         System.out.println(new App().getMessage());
    }	


}
