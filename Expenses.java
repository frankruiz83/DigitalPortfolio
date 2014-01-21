
import java.awt.Font;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Francisco Ruiz
 */
public class Expenses extends JFrame{
    
    //Variable Declaration/Initialization
    PITI new_calc;
    String[] choices = {"Monthly","Semi-Annually","Annually"};
    private int index = 0;
    double expense_val;
    private double estate_value1;
    private double hazard_value1;
    private double assoc_value1;
    private double PMI_value1;
    private JLabel Label;
    private JLabel estate_value2;
    private JLabel hazard_value2;
    private JLabel assoc_value2;
    private JLabel PMI_value2;
    private JTextField estate_value3;
    private JTextField hazard_value3;
    private JTextField assoc_value3;
    private JTextField PMI_value3;
    private JButton OK;
    private JComboBox estate_value4;
    private JComboBox hazard_value4;
    private JComboBox assoc_value4;
    private JComboBox PMI_value4;
    
    
    public Expenses()
    {
        //Code that sets the size of the pane.
        super();
        setSize(700,400);
        
        //Code that creates the action and document listeners
        MyActionListener listener = new MyActionListener(); 
        MyDocumentListener listener2 = new MyDocumentListener();
        
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        //Sets all initial monetary values to 0.00
        expense_val = 0.00;
        estate_value1 = 0.00;
        hazard_value1 = 0.00;
        assoc_value1 = 0.00;
        PMI_value1 = 0.00;
        
        //This code sets the values to their corresponding text fields
        //labels and combo boxes.
        Label = new JLabel(String.format("Total Monthly Housing Expenses: $" + "%.2f",expense_val));
        estate_value2 = new JLabel("Real Estate Taxes:");
        hazard_value2 = new JLabel("Hazard Insurance:");
        assoc_value2 = new JLabel("Association Dues or Fees:");
        PMI_value2 = new JLabel("Mortgage Insurance(PMI):");
        estate_value3 = new JTextField(10);
        hazard_value3 = new JTextField(10);
        assoc_value3 = new JTextField(10);
        PMI_value3 = new JTextField(10);
        OK = new JButton("OK");
        OK.addActionListener(listener);
        estate_value4 = new JComboBox(choices);
        hazard_value4 = new JComboBox(choices);
        assoc_value4 = new JComboBox(choices);
        PMI_value4 = new JComboBox(choices);
        estate_value4.setSelectedIndex(index);
        hazard_value4.setSelectedIndex(index);
        assoc_value4.setSelectedIndex(index);
        PMI_value4.setSelectedIndex(index);
        estate_value3.setText("0.00");
        hazard_value3.setText("0.00");
        assoc_value3.setText("0.00");
        PMI_value3.setText("0.00");
        estate_value3.getDocument().addDocumentListener(listener2);
        estate_value4.addActionListener(listener);
        hazard_value3.getDocument().addDocumentListener(listener2);
        hazard_value4.addActionListener(listener);
        assoc_value3.getDocument().addDocumentListener(listener2);
        assoc_value4.addActionListener(listener);
        PMI_value3.getDocument().addDocumentListener(listener2);
        PMI_value4.addActionListener(listener);
        
        //Code that sets the pane's font type.
        Font font = new Font("Serif",Font.BOLD,25);
        Label.setFont(font);
        Font font2 = new Font("Serif",Font.BOLD,15);
        estate_value2.setFont(font2);
        hazard_value2.setFont(font2);
        assoc_value2.setFont(font2);
        PMI_value2.setFont(font2);
  
        //Sets the placements of the buttons, fields, and text 
        add(Label, new GBC(0,0).setSpan(3,1).setFill(GBC.NORTH));
        add(estate_value2, new GBC(1,1).setSpan(1,1).setFill(GBC.CENTER).setInsets(50, 0, 0, 0));
        add(hazard_value2, new GBC(1,2).setSpan(1,1).setFill(GBC.CENTER));
        add(assoc_value2, new GBC(1,3).setSpan(1,1).setFill(GBC.CENTER));
        add(PMI_value2, new GBC(1,4).setSpan(1,1).setFill(GBC.CENTER));
        add(estate_value3, new GBC(2,1).setSpan(1,1).setFill(GBC.CENTER).setInsets(50, 0, 0, 0));
        add(hazard_value3, new GBC(2,2).setSpan(1,1).setFill(GBC.CENTER));
        add(assoc_value3, new GBC(2,3).setSpan(1,1).setFill(GBC.CENTER));
        add(PMI_value3, new GBC(2,4).setSpan(1,1).setFill(GBC.CENTER));
        add(estate_value4, new GBC(3,1).setSpan(1,1).setFill(GBC.CENTER).setInsets(50, 50, 0, 0));
        add(hazard_value4, new GBC(3,2).setSpan(1,1).setFill(GBC.CENTER).setInsets(0, 50, 0, 0));
        add(assoc_value4, new GBC(3,3).setSpan(1,1).setFill(GBC.CENTER).setInsets(0, 50, 0, 0));
        add(PMI_value4, new GBC(3,4).setSpan(1,1).setFill(GBC.CENTER).setInsets(0, 50, 0, 0));
        add(OK, new GBC(3,5).setSpan(3,1).setFill(GBC.SOUTH).setInsets(50, 0, 0, 0));
        
        setVisible(false);
    }

    
    public double Calculate(String payment, double value)
    {
        if(payment.equalsIgnoreCase("Semi-Annually"))
        {
            value = value/6;
            return value;
        }
        else if(payment.equalsIgnoreCase("Annually"))
        {
            value = value/12;
            return value;
        }
        else
        {
            return value;
        }
    }
    
    //Checks to make sure a double is inputted.
    //Also checks to see if the current text box is empty.  
    //If so, it sets the values to 0.00
    public void check2()
    {
        if(estate_value3.getText().isEmpty())
        {
            estate_value1 = 0.00;
        }
        else
        {
            estate_value1 = new Double(estate_value3.getText());
            estate_value1 = Calculate(choices[estate_value4.getSelectedIndex()], estate_value1);
        }
        if(hazard_value3.getText().isEmpty())
        {
            estate_value1 = 0.00;
        }
        else
        {
            hazard_value1 = new Double(hazard_value3.getText());
            hazard_value1 = Calculate(choices[hazard_value4.getSelectedIndex()], hazard_value1);
        }
        if(assoc_value3.getText().isEmpty())
        {
            assoc_value1 = 0.00;
        }
        else
        {
            assoc_value1 = new Double(assoc_value3.getText());
            assoc_value1 = Calculate(choices[assoc_value4.getSelectedIndex()], assoc_value1);
        }
        if(PMI_value3.getText().isEmpty())
        {
            PMI_value1 = 0.00;
        }
        else
        {
            PMI_value1 = new Double(PMI_value3.getText());
            PMI_value1 = Calculate(choices[PMI_value4.getSelectedIndex()], PMI_value1);
        }
    }
    
    //Checks to make sure the text boxes are set to 0.00 initially.
    public void check()
    {
        if(estate_value3.getText().isEmpty())
        {
            estate_value3.setText("0.00");
        }
        if(hazard_value3.getText().isEmpty())
        {
            hazard_value3.setText("0.00");
        }
        if(assoc_value3.getText().isEmpty())
        {
            assoc_value3.setText("0.00");
        }
        if(PMI_value3.getText().isEmpty())
        {
            PMI_value3.setText("0.00");
        }
    }
    class MyDocumentListener implements DocumentListener 
    {
        
        public void changedUpdate(DocumentEvent de) 
        {
            try {
                    check2();
                    expense_val = estate_value1 + hazard_value1 + assoc_value1 + PMI_value1;
                    
                    Label.setText(String.format("Total Monthly Housing " + "Expenses: $" + "%.2f",expense_val));
                    
                } catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, 
                            "Invalid entry kind sir.  Please enter an integer.",
                    "", JOptionPane.ERROR_MESSAGE);  
                }
        }

        @Override
        public void insertUpdate(DocumentEvent de) 
        {
            try {   
                    check2();
                    expense_val = estate_value1 + hazard_value1 + assoc_value1 + PMI_value1;
                    
                    Label.setText(String.format("Total Monthly Housing " + "Expenses: $" + "%.2f",expense_val));
                    
                } catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, 
                            "Invalid entry kind sir.  Please enter a double.",
                    "", JOptionPane.ERROR_MESSAGE);  
                }
        }

        @Override
        public void removeUpdate(DocumentEvent de) 
        {
            try {
                    check2();
                    
                    expense_val = estate_value1 + hazard_value1 + assoc_value1 + PMI_value1;
                    
                    Label.setText(String.format("Total Monthly Housing " + "Expenses: $" + "%.2f",expense_val));
                    
                } catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, 
                            "Invalid Entry. Entry must be a double",
                    "", JOptionPane.ERROR_MESSAGE);  
                }
        }
    
    }
    class MyActionListener implements ActionListener 
    {
        
        public void actionPerformed(ActionEvent e) 
        {
            
            if(e.getSource() == OK)
            {
                setVisible(false);
            }
            else
            {
                    try {
                        check();
                        estate_value1 = new Double(estate_value3.getText());
                        estate_value1 = Calculate(choices[estate_value4.getSelectedIndex()], estate_value1);
                        hazard_value1 = new Double(hazard_value3.getText());
                        hazard_value1 = Calculate(choices[hazard_value4.getSelectedIndex()], hazard_value1);
                        assoc_value1 = new Double(assoc_value3.getText());
                        assoc_value1 = Calculate(choices[assoc_value4.getSelectedIndex()], assoc_value1);
                        PMI_value1 = new Double(PMI_value3.getText());
                        PMI_value1 = Calculate(choices[PMI_value4.getSelectedIndex()], PMI_value1);

                        expense_val = estate_value1 + hazard_value1 + assoc_value1 + PMI_value1;

                        Label.setText(String.format("Total Monthly Housing " + "Expenses: $" + "%.2f",expense_val));

                    } catch (NumberFormatException ex) 
                    {
                        JOptionPane.showMessageDialog(null, 
                                "Invalid entry sir.  Please enter a double.",
                        "", JOptionPane.ERROR_MESSAGE);  
                    }
            }
            
        }

    }
}
