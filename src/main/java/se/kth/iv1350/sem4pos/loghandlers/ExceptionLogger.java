package se.kth.iv1350.sem4pos.loghandlers;

/**
 * This interface specifies the requirements of an object that logs an exception.
 * The class implementing the interface has the freedom to decide how and where the exception log
 *              should be saved or displayed.
 */
public interface ExceptionLogger {
    /**
     * Logs an exception.
     * @param exceptionToLog The exception to be logged.
     */
    void log(Exception exceptionToLog);
}
