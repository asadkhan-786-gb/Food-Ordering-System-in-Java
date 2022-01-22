public class Beverage {
    private String size;
    private int amount;
    private double price;



    public Beverage(String size, int amount){
        super();
        this.size = size;
        this.amount = amount;
        price = 0.0;
    }


    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size = size;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

    @Override
    public String toString(){
        return String.format("%d %s glass(es) of ", this.getAmount(), this.getSize());
    }
}
