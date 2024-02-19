import java.util.ArrayList;

public class StoreInventory
{
    private ArrayList<Product> inventory;
    public StoreInventory()
    {
        inventory = new ArrayList<Product>(10);
    }
    //addProduct will not enter items with an already existing product ID in the inventory
    public void addProduct(Product newProd)
    {
        if (newProd == null) {  return;  }
        for (Product prod : inventory)
        {
            if (prod.getID() == newProd.getID())
            {
                return;
            }
        }
        inventory.add(newProd);
    }
    public void addProduct(String name, int ID, double price, int quantity)
    {
        addProduct(new Product(name, ID, price, quantity));
    }
    public Product removeProduct(int ID)
    {
        for (Product prod : inventory)
        {
            if (prod.getID() == ID)
            {
                inventory.remove(prod);
                return prod;
            }
        }
        return null;
    }
    public void updateProductQty(int productID, int newQty)
    {
        for (Product prod : inventory)
        {
            if (prod.getID() == productID)
            {
                prod.setQuantity(newQty);
                return;
            }
        }
    }
    public void viewInventory()
    {
        System.out.println(toString());
    }
    public double getInventoryValue()
    {
        double totalPrice = 0.0;
        for (Product prod : inventory)
        {
            totalPrice += (prod.getPrice() * prod.getQuantity());
        }
        return totalPrice;
    }
    public void displayInventoryValue()
    {
        System.out.printf("Total Inventory Value: $%.2f\n", getInventoryValue());
    }
    public String toString()
    {
        String inventoryString = "CURRENT INVENTORY: \n";
        for (Product prod : inventory)
        {
            inventoryString += prod.toString() + "\n";
        }
        return inventoryString;
    }
}