package OA;

import java.sql.Timestamp;
import OA.OALogger;
import OA.OAUtility;

public class OABaseClass extends OAUtility {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public OALogger logger;

    public OABaseClass() {
        logger = new OALogger();
        try{
            logger.logInit();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Logging Not Supported.");
        }
    }

    public <T> void printDebug(T s) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("\t"+timestamp + " Debug Message: \u001B[41m " + s + " \u001B[0m");
        
        try{
            logger.info("[Debug] " + s);
        } catch(Exception e) {}
    }

    public <T> void printOA(T t, T s, T ans) {
        if(t instanceof String)
            System.out.println("Test Case:\t" + t);
        else
            System.out.println("Test Case:\t" + t.toString());
            
        System.out.println("Test Result:\t" + s.toString());
        
        if(ans != null) {
            if(s.getClass() == ans.getClass() && s.toString().equals(ans.toString()))
                System.out.println("Test Answer:\t" + ANSI_GREEN + ans.toString() + " " + ANSI_RESET);
            else
                System.out.println("Test Answer:\t" + ANSI_RED + ans.toString() + " " + ANSI_RESET);
        }
        System.out.print("\n");

        try{
            logger.info("Test Case: " + t);
            logger.info("Test Result: " + s.toString());
            if(ans != null) {
                if(s.getClass() == ans.getClass() && s.toString().equals(ans.toString()))
                    logger.info("Test Answer: " + ans.toString() +" Passed");
                else
                    logger.info("Test Answer: " + ans.toString() +" Failed");
            }
        } catch(Exception e) {}
    }
    
    public void printFunction() {
        String method = new Throwable().getStackTrace()[1].getMethodName(); 
        System.out.println("\u001B[44mFunction Name:\t"+ method + " \u001B[0m"); 
        try{
            logger.info("Test Name: " + method);
        } catch(Exception e) {}
    }

}