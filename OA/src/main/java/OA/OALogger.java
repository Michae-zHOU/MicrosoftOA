package OA;

import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.io.File;

public class OALogger {
    
    private Logger logger;

    public OALogger() {
        logger = null;
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void logInit() throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        File logDir = new File("./OALogs/"); 
		if( !(logDir.exists()) )
            logDir.mkdir();
            
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");  
        String timeStr = formatter.format(timestamp.getTime());  
        FileHandler handler = new FileHandler("OALogs/OA_" + timeStr + ".log", false);
        
        handler.setFormatter(new SimpleFormatter() {
  
            private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

            @Override
            public synchronized String format(final LogRecord record) {
                return "[" + new SimpleDateFormat(PATTERN).format(new Date(record.getMillis())) 
                + "] ["+ record.getLevel().getLocalizedName() + "] " 
                + record.getMessage() + "\n";
            }
        });

        Logger logger = Logger.getLogger(new Throwable().getStackTrace()[1].getMethodName());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);

        this.logger = logger;
    }


}