package uhack.contractor.model;

/**
 * Created by nimit on 14/10/17.
 */

public class Inventory {
    private String InventoryId, InventoryName;
    private int InventoryQty;

    public Inventory(){

    }

    public Inventory(String inventoryId, String inventoryName, int inventoryQty) {

        InventoryId = inventoryId;
        InventoryName = inventoryName;
        InventoryQty = inventoryQty;
    }

    public String getInventoryId() {
        return InventoryId;
    }

    public String getInventoryName() {
        return InventoryName;
    }

    public int getInventoryQty() {
        return InventoryQty;
    }

    public void setInventoryId(String inventoryId) {
        InventoryId = inventoryId;
    }

    public void setInventoryName(String inventoryName) {
        InventoryName = inventoryName;
    }

    public void setInventoryQty(int inventoryQty) {
        InventoryQty = inventoryQty;
    }
}
