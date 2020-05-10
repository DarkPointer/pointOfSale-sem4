package se.kth.iv1350.sem4pos.integration;

/**
 * This exception is thrown when a database could not be reached.
 */
public class DatabaseUnreachableException extends Exception {
    /**
     * Creates a new instance of the {@link DatabaseUnreachableException}
     *              exception containing an informative message about the exception.
     * @param itemId The item identifier of the item that could not be retrieved.
     */
     DatabaseUnreachableException(String itemId) {
        super("The product with the item identifier " + itemId +
                " could not be retrieved because the inventory database was unreachable.");
    }
}
