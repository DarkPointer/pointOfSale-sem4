package se.kth.iv1350.sem4pos.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.sem4pos.model.SaleDTO;


/**
 * Provides access to the external inventory system.
 */
public class InventorySystem {
    private ItemInfoDTO[] itemStorage;

    /**
     * Creates a new instance of the {@link InventorySystem}.
     */
    InventorySystem() {
        List<ItemInfoDTO> storageList = new ArrayList<ItemInfoDTO>();
        ItemInfoDTO milkItemInfo = new ItemInfoDTO("item1","Milk",10.0d, 0.06f);
        ItemInfoDTO breadItemInfo = new ItemInfoDTO("item2","Bread",5.0d, 0.06f);
        ItemInfoDTO wheatFlourItemInfo = new ItemInfoDTO("item3","Wheat flour",15.0d, 0.12f);
        ItemInfoDTO energyDrinkItemInfo = new ItemInfoDTO("item4","Energy drink",12.0d, 0.25f);
        storageList.add(milkItemInfo);
        storageList.add(breadItemInfo);
        storageList.add(wheatFlourItemInfo);
        storageList.add(energyDrinkItemInfo);
        this.itemStorage = storageList.toArray(new ItemInfoDTO[0]);
    }

    /**
     * Provides information about an item existing in the inventory.
     * @param itemId The identifier of the item to be fetched.
     * @return An {@link ItemInfoDTO} that provides information about
     *              the item description, prices excluding VAT and the VAT rate of the item.
     * @throws ItemNotFoundException If the requested item with the specified itemId,
     *              could not be found in the inventory system.
     * @throws DatabaseUnreachableException If the inventory database was unreachable.
     */
    public ItemInfoDTO getItemInfo(String itemId) throws ItemNotFoundException, DatabaseUnreachableException {
        for (ItemInfoDTO item: this.itemStorage) {
            if(itemId.equals("item3")){
                throw new DatabaseUnreachableException(itemId);
            }
            else if(item.getItemId().equals(itemId)) {
                return item;
            }
        }
        throw new ItemNotFoundException(itemId);
    }

    /**
     * Updates the inventory with a {@link SaleDTO} containing all available information about a sale.
     * @param saleInfo A sale data transfer object {@link SaleDTO} containing all available information about a sale.
     */
    public void updateInventory(SaleDTO saleInfo){
    }
}
