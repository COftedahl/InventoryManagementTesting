public class Product
{
    private String name;
    private int ID;
    private double price;
    private int quantity;
    
    public Product(String n, int i, double p, int q)
    {
        name = n;
        ID = i;
        price = p;
        quantity = q;
    }
    
    public String getName()
    {
        return name;
    }
    public int getID()
    {
        return ID;
    }
    public double getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setName(String n)
    {
        name = n;
    }
    public void setID(int i)
    {
        ID = i;
    }
    public void setPrice(double p)
    {
        price = p;
    }
    public void setQuantity(int q)
    {
        quantity = q;
    }
    
    public String toString()
    {
        return (name + "\n  -ID: " + ID + "\n  -Unit Price: $" + price + "\n  -Quantity: " + quantity + "\n");
    }
    public boolean equals(Product otherProduct)
    {
        return ((name.equals(otherProduct.name)) && (ID == otherProduct.ID) && (price == otherProduct.price) && (quantity == otherProduct.quantity));
    }
}