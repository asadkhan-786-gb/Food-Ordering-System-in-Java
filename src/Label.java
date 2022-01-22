import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Label extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblSize,lblBev,lblGlass,lblReport;
	private JComboBox<String> size;
	private JRadioButton rdJuice,rdWater,rdTea,rdCoffee;
	private ButtonGroup btnGroup;
	private JTextField txtGlass;
	private JButton btnAdd,btnOrder;
	int amount_of;
	ArrayList<Beverage> list_of_bvr = new ArrayList<Beverage>();
	
	public Label() {
		setLayout(null);
		setSize(600,400);
		setLocationRelativeTo(null);
		setTitle("Order");
		init();
		btnAdd.addActionListener(this); 
		btnOrder.addActionListener(this);
		setVisible(true);
	}
	
	public void init() {
		lblSize = new JLabel("Select size:");// a JLabel that labels the comboBox
		lblSize.setSize(250, 50);
		lblSize.setLocation(100, 10);
		add(lblSize);
		
		String[] sizes = {"Small","Medium","Large"};
		size = new JComboBox<String>(sizes);//
		size.setSelectedIndex(0);//default selection is small
		size.setSize(100, 25);
		size.setLocation(100, 50);
		add(size);
		
		lblBev = new JLabel("Select which type of beverage you want to order:");//a JLabel that labels the radioButtons 
		lblBev.setSize(500, 50);
		lblBev.setLocation(100, 75);
		add(lblBev);
		
		btnGroup = new ButtonGroup();
		
		rdJuice = new JRadioButton("Juice");
		rdJuice.setSize(75, 50);
		rdJuice.setLocation(100, 110);
		add(rdJuice);
		
		rdWater = new JRadioButton("Water");
		rdWater.setSize(75, 50);
		rdWater.setLocation(175, 110);
		add(rdWater);
		
		rdTea = new JRadioButton("Tea");
		rdTea.setSize(75, 50);
		rdTea.setLocation(250, 110);
		add(rdTea);
		
		rdCoffee = new JRadioButton("Coffee");
		rdCoffee.setSize(75, 50);
		rdCoffee.setLocation(325, 110);
		add(rdCoffee);
		
		btnGroup.add(rdJuice);
		btnGroup.add(rdWater);
		btnGroup.add(rdTea);
		btnGroup.add(rdCoffee);
		
		lblGlass = new JLabel("Type how many glasses you want to order:");//a JLabel that labels the Text Field
		lblGlass.setSize(500, 50);
		lblGlass.setLocation(100, 145);
		add(lblGlass);
		
		txtGlass = new JTextField();//a JTextField to get how many glasses of beverage is the user want
		txtGlass.setSize(300, 25);
		txtGlass.setLocation(100, 185);
		add(txtGlass);
		
		btnAdd = new JButton("Add");
		btnAdd.setSize(120, 40);
		btnAdd.setLocation(100, 230);
		add(btnAdd);
		
		btnOrder = new JButton("Order");
		btnOrder.setSize(120, 40);
		btnOrder.setLocation(280, 230);
		btnOrder.setEnabled(false);
		add(btnOrder);
		
		lblReport = new JLabel();//will be showing report of the beverages that added.
		lblReport.setSize(500, 50);
		lblReport.setLocation(100, 270);
		add(lblReport);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String size_of = (String)size.getSelectedItem();
		if(e.getSource().equals(btnAdd)) {
			if( rdJuice.isSelected() || rdTea.isSelected() || rdCoffee.isSelected() || rdWater.isSelected() && !(txtGlass.getText().isEmpty())) {
				try {
					amount_of  = Integer.parseInt(txtGlass.getText().trim());
					Beverage bvg;
					if(rdJuice.isSelected())  { bvg = new Juice(size_of,amount_of,this); }
					else if(rdWater.isSelected())  { bvg = new Water(size_of,amount_of,this); }
					else if(rdTea.isSelected())    { bvg = new Tea(size_of,amount_of,this); }
					else { bvg = new Coffee(size_of,amount_of,this); }
					txtGlass.setText(null);
					list_of_bvr.add(bvg);
					lblReport.setText(bvg.toString()+" added");
					btnGroup.clearSelection();
					btnOrder.setEnabled(true);
				}
				catch(NumberFormatException e1) {//if written data in TextField can't be converted to an integer[String,char,double etc...]
					JOptionPane.showMessageDialog(this, "Enter an integer as amount");
				}
			}	
			else { JOptionPane.showMessageDialog(this, "Choose a beverage type and enter an amount"); 
			//if none of the radio buttons are selected or the textField is empty
			}
		}
		if(e.getSource().equals(btnOrder)) {
			String report = "";
			double pay=0.0;
			for(int i=0;i<list_of_bvr.size();i++) {
				Beverage bvgi = list_of_bvr.get(i);
				report += bvgi.toString();
				double totalprice_of_bvg = bvgi.getAmount() * bvgi.getPrice();
				pay += totalprice_of_bvg; // add the total amount to be paid
				report = report + " - "+totalprice_of_bvg+" $\n";
			}
			JOptionPane.showMessageDialog(this, report);
			JOptionPane.showMessageDialog(this,	 "You should pay "+pay+" $");
			lblReport.setText(null);
			btnOrder.setEnabled(false);
			list_of_bvr.clear();
		}	
	}

	public static void main(String[] args) {
		new Label();
	}
}
