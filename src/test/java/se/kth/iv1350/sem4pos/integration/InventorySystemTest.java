package se.kth.iv1350.sem4pos.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InventorySystemTest {

    private InventorySystem inventorySystemInstance;

    @BeforeEach
    void setUp() {
        inventorySystemInstance = new InventorySystem();
    }

    @AfterEach
    void tearDown() {
        inventorySystemInstance = null;
    }

    @Test
    void testEqualItems() throws ItemNotFoundException, DatabaseUnreachableException {
        ItemInfoDTO firstItem = inventorySystemInstance.getItemInfo("item1");
        ItemInfoDTO secondItem = inventorySystemInstance.getItemInfo("item1");
        boolean result = firstItem.equals(secondItem);
        assertTrue(result, "Fetching two identical items resulted in two items with different information.");

    }

    @Test
    void testUnequalItems() throws ItemNotFoundException, DatabaseUnreachableException {
        ItemInfoDTO firstItem = inventorySystemInstance.getItemInfo("item1");
        ItemInfoDTO secondItem = inventorySystemInstance.getItemInfo("item4");
        boolean result = firstItem.equals(secondItem);
        assertFalse(result, "Fetching two different items resulted in two items with identical attributes.");
    }


    @Test
    void testItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> inventorySystemInstance.getItemInfo("item1337"),
                "ItemNotFoundException was expected to be thrown.");
    }

    @Test
    void testItemNotFoundExceptionMessage() {
        try
        {
            inventorySystemInstance.getItemInfo("item1337");
        }
        catch (ItemNotFoundException | DatabaseUnreachableException thrownException)
        {
            assertEquals("The product with the item identifier item1337" +
                            " could not be found in the inventory system.", thrownException.getMessage(),
                    "The exception did not contain the expected message.");
        }

    }

    @Test
    void testDatabaseUnreachableException() {
        assertThrows(DatabaseUnreachableException.class, () -> inventorySystemInstance.getItemInfo("item3"),
                "DatabaseUnreachableException was expected to be thrown.");
    }

    @Test
    void testDatabaseUnreachableExceptionMessage() {
        try
        {
            inventorySystemInstance.getItemInfo("item3");
        }
        catch (DatabaseUnreachableException | ItemNotFoundException  thrownException)
        {
            assertEquals("The product with the item identifier item3 could not be retrieved" +
                            " because the inventory database was unreachable.", thrownException.getMessage(),
                    "The exception did not contain the expected message.");
        }


    }

}