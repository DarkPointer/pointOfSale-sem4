package se.kth.iv1350.sem4pos.loghandlers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class implements the {@link ExceptionLogger} interface and provides logging functionality
 *              to notify the developer when an exception occurs. This logger logs exceptions to a text file.
 */
public class DeveloperLogger implements ExceptionLogger {

    private PrintWriter printerWriter;

    /**
     * Creates a new instance of the {@link DeveloperLogger}.
     */
    public DeveloperLogger() {
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String logFilename = String.format("log-%s.txt", dateFormat.format(currentDate));
            FileWriter fileWriter = new FileWriter(logFilename, true);
            this.printerWriter = new PrintWriter(fileWriter);
        }
        catch (IOException exception)
        {
            System.out.println("Developer logger failed:");
            exception.printStackTrace();
        }

    }

    /**
     * Logs an exception with its stack trace.
     * @param exceptionToLog The exception to be logged.
     */
    @Override
    public void log(Exception exceptionToLog) {
        String logTime = this.getCurrentTime();
        String exStackTrace = this.getStackTrace(exceptionToLog);
        String logMessage = String.format("%s : %s", logTime, exStackTrace);
        printerWriter.println(logMessage);
        printerWriter.flush();
    }

    private String getCurrentTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        return timeFormat.format(currentDate);
    }

    private String getStackTrace(Exception exception) {
        StringWriter stackTraceStringWriter = new StringWriter();
        PrintWriter stackTracePrintWriter = new PrintWriter(stackTraceStringWriter);
        exception.printStackTrace(stackTracePrintWriter);
        return stackTraceStringWriter.toString();
    }

}
