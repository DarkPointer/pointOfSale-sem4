package se.kth.iv1350.sem4pos.loghandlers;

/**
 * This class implements the {@link ExceptionLogger} interface and provides logging functionality
 *              to notify the user when an exception occurs. This logger logs exceptions to the "standard" output stream.
 */
public class UserLogger implements ExceptionLogger {

    /**
     * Logs an exception with its descriptive message.
     * @param exceptionToLog The exception to be logged.
     */
    @Override
    public void log(Exception exceptionToLog) {
        String exMessage = exceptionToLog.getMessage();
        String logMessage = String.format("An error has occurred: %s", exMessage);
        System.out.println();
        System.out.println(logMessage);
    }
}
