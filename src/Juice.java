import javax.swing.JOptionPane;

public class Juice extends Beverage {
    private String fruit;
    String[] fruits = { "Apple", "Orange", "Pineapple" };

    public Juice(String size, int amount, Label lbl) {
        super(size, amount);
        try {
            fruit = (String) JOptionPane.showInputDialog(lbl, "Select a fruit.", "Select a fruit",
                    JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
            double price;
            if (size.equals("Small"))
                price = 5.0;
            else if (size.equals("Medium"))
                price = 7.0;
            else
                price = 9.0;
            if (fruit.equals("Orange"))
                price *= 1.25;
            if (fruit.equals("Pineapple"))
                price *= 1.5;
            setPrice(price);
        } catch (NullPointerException e) {
        }
    }

    @Override
	public String toString() {
		return super.toString()+fruit+" juice";
	}
}
