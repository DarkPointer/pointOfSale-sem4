package se.kth.iv1350.sem4pos.integration;

/**
 * This exception is thrown when an item with the specified item identifier could not be found in the inventory system.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance of the {@link ItemNotFoundException} exception
     *              with a message containing the item identifier
     *              of the item that could not be found.
     * @param itemId The item identifier of the item that could not be found.
     */
     ItemNotFoundException(String itemId) {
        super("The product with the item identifier " + itemId + " could not be found" +
                " in the inventory system.");
    }
}
