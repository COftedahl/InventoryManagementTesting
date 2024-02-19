import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StoreInventoryTest
{
    public StoreInventoryTest() {}
    
    @BeforeEach
    public void setUp() {}
    
    @Test
    public void addProductTest()
    {
        StoreInventory storeInventory = new StoreInventory();
        storeInventory.addProduct(null);
        assertFalse(storeInventory.toString().contains("ID"));
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        //check there is exactly 1 Laptop entry in inventory
        String storeInventoryString = storeInventory.toString();
        assertTrue(storeInventoryString.contains("Laptop"));
        assertEquals(storeInventoryString.indexOf("Laptop"), storeInventoryString.lastIndexOf("Laptop"));
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        //check there is stil exactly 1 Laptop entry
        assertTrue(storeInventoryString.contains("Laptop"));
        assertEquals(storeInventoryString.indexOf("Laptop"), storeInventoryString.lastIndexOf("Laptop"));
        
        storeInventory.addProduct("Laptop", 142, 479.99, 85);
        storeInventoryString = storeInventory.toString();
        assertNotEquals(storeInventoryString.indexOf("Laptop"), storeInventoryString.lastIndexOf("Laptop"));
        storeInventory.addProduct("PC", 110, 500.00, 5);
        storeInventory.addProduct("Calculator", 121, 80.49, 12);
        storeInventory.addProduct("Monitor", 970, 149.99, 10);
        storeInventory.addProduct("Watch", 44, 75.49, 40);
        storeInventory.addProduct("Tablet", 155, 200.00, 3);
        storeInventory.addProduct("Smart TV", 535, 439.99, 70);
        storeInventory.addProduct("Headphones", 780, 129.99, 30);
        storeInventory.addProduct("Microphone", 203, 40.00, 15);
        storeInventory.addProduct("Phone", 816, 750.00, 50);
        //check that products continue to be added correctly after ArrayList resizes
        storeInventoryString = storeInventory.toString();
        assertTrue(storeInventoryString.contains("PC"));
        assertTrue(storeInventoryString.contains("Calculator"));
        assertTrue(storeInventoryString.contains("Monitor"));
        assertTrue(storeInventoryString.contains("Watch"));
        assertTrue(storeInventoryString.contains("Smart TV"));
        assertTrue(storeInventoryString.contains("Headphones"));
        assertTrue(storeInventoryString.contains("Microphone"));
        assertTrue(storeInventoryString.contains("Phone"));
    }
    
    @Test
    public void removeProductTest()
    {
        StoreInventory storeInventory = new StoreInventory();
        assertNull(storeInventory.removeProduct(101));
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        assertTrue(storeInventory.removeProduct(111).equals(new Product("Laptop", 111, 259.99, 150)));
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        storeInventory.addProduct("Laptop", 142, 479.99, 85);
        assertNull(storeInventory.removeProduct(143));//non-existant product
        assertNotEquals(storeInventory.toString().indexOf("Laptop"), storeInventory.toString().lastIndexOf("Laptop"));
        assertNotNull(storeInventory.removeProduct(142));
        assertEquals(storeInventory.toString().indexOf("Laptop"), storeInventory.toString().lastIndexOf("Laptop"));
        assertTrue(storeInventory.toString().contains("$259.99"));
        storeInventory.removeProduct(111);
        assertFalse(storeInventory.toString().contains("Laptop"));
    }

    @Test
    public void updateQuantityTest()
    {
        StoreInventory storeInventory = new StoreInventory();
        assertEquals(storeInventory.getInventoryValue(), 0);
        storeInventory.updateProductQty(123, 15);//no changes caused to inventory - empty
        assertEquals(storeInventory.getInventoryValue(), 0);
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 150));
        storeInventory.updateProductQty(111, 400);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 400));
        storeInventory.addProduct("PC", 110, 500.00, 5);
        storeInventory.addProduct("Calculator", 121, 80.49, 12);
        storeInventory.addProduct("Monitor", 970, 149.99, 10);
        storeInventory.addProduct("Watch", 44, 75.49, 40);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 400) + (500 * 5) + (80.49 * 12) + (149.99 * 10) + (75.49 * 40));
        assertFalse(storeInventory.toString().contains("Quantity: 43"));
        storeInventory.updateProductQty(110, 43);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 400) + (500 * 43) + (80.49 * 12) + (149.99 * 10) + (75.49 * 40));
        assertTrue(storeInventory.toString().contains("Quantity: 43"));
    }
    
    @Test
    public void viewInventoryTest()
    {
        StoreInventory storeInventory = new StoreInventory();
        //testing toString() method since that is what viewInventory prints
        assertFalse(storeInventory.toString().contains("ID"));//should be empty inventory
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        String storeInventoryString = storeInventory.toString();
        assertTrue(storeInventoryString.contains("Laptop"));
        assertTrue(storeInventoryString.contains("ID: 111"));
        assertTrue(storeInventoryString.contains("Price: $259.99"));
        assertTrue(storeInventoryString.contains("Quantity: 150"));
        assertTrue(storeInventoryString.indexOf("Quantity: 150") > storeInventoryString.indexOf("Laptop"));
        storeInventory.addProduct("Watch", 44, 75.49, 40);
        storeInventoryString = storeInventory.toString();
        assertTrue(storeInventoryString.contains("Watch"));
        assertTrue(storeInventoryString.indexOf("Watch") > storeInventoryString.indexOf("Quantity: 150"));
        assertTrue(storeInventoryString.contains("ID: 44"));
        assertTrue(storeInventoryString.indexOf("ID: 44") > storeInventoryString.indexOf("Watch"));
        assertTrue(storeInventoryString.contains("Price: $75.49"));
        assertTrue(storeInventoryString.indexOf("Price: $75.49") > storeInventoryString.indexOf("ID: 44"));
        assertTrue(storeInventoryString.contains("Quantity: 40"));
        assertTrue(storeInventoryString.indexOf("Quantity: 40") > storeInventoryString.indexOf("Price: $75.49"));
    }
    
    @Test
    public void getInventoryValueTest()
    {
        StoreInventory storeInventory = new StoreInventory();
        assertEquals(storeInventory.getInventoryValue(), 0);
        storeInventory.addProduct("Laptop", 111, 259.99, 150);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 150));
        storeInventory.addProduct("PC", 110, 500.00, 5);
        storeInventory.addProduct("Calculator", 121, 80.49, 12);
        storeInventory.addProduct("Monitor", 970, 149.99, 10);
        storeInventory.addProduct("Watch", 44, 75.49, 40);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 150) + (500 * 5) + (80.49 * 12) + (149.99 * 10) + (75.49 * 40));
        storeInventory.removeProduct(970);
        assertEquals(storeInventory.getInventoryValue(), (259.99 * 150) + (500 * 5) + (80.49 * 12) + (75.49 * 40));
    }
        
    @AfterEach
    public void tearDown() {}
}

