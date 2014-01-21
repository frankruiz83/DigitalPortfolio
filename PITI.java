
import java.awt.Font;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Francisco Ruiz
 */
public class PITI extends JApplet{

    //Long list of Variable Declaration/Initialization
    String[] choices = {"30 years", "15 years"};
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private ArrayList<JTextField> fields = new ArrayList<JTextField>();
    private int index = 0;
    private double mortgageV;
    private double PIV;
    private double liabilitiesV;
    private double expensesV;
    private double rateV;
    private double rateV2;
    private double t_value;
    private double termV;
    private double PITIV;
    private Expenses new_e;
    private Liabilities new_l;
    private JLabel Label;
    private JLabel Label2;
    private JLabel total_mort;
    private JLabel total_lia;
    private JLabel total_exp;
    private JLabel PITI;
    private JLabel PI;
    private JLabel RAI;
    private JLabel int_rate;
    private JLabel loan_term;
    private JLabel percentageA;
    private JLabel percentageB;
    private JLabel percentageC;
    private JLabel percentageD;
    private JLabel percentageE;
    private JLabel percentageF;
    private JLabel percentageG;
    private JLabel percentageH;
    private JTextField mortgageF;
    private JTextField liabilitiesF;
    private JTextField expensesF;
    private JTextField PITIF;
    private JTextField PIF;
    private JTextField rateF;
    private JTextField percentageAA;
    private JTextField percentageBB;
    private JTextField percentageCC;
    private JTextField percentageDD;
    private JTextField percentageEE;
    private JTextField percentageFF;
    private JTextField percentageGG;
    private JTextField percentageHH;
    private JComboBox termBox;
    private JButton calculate;
    private JButton liab_calc;
    private JButton expenses_calc;
    
    public void init()
    {
//        super();
//        setSize(1000,900);
        
        new_e = new Expenses();
        new_l = new Liabilities();
        
        MyComponentListener listener = new MyComponentListener();
        MyActionListener listener2 = new MyActionListener();
        
        new_e.addComponentListener(listener);
        new_l.addComponentListener(listener);
        
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        mortgageV = 0.00;
        liabilitiesV = 0.00;
        expensesV = 0.00;
        rateV = 0.00;
        rateV2 = 0.00;
        t_value = 0.00;
        termV = 0;
        PIV = 0;
        PITIV = 0;
        
        //Sets the font type
        Font font = new Font("Serif",Font.BOLD,25);
        Font font2 = new Font("Serif",Font.BOLD,15);
        
        Label = new JLabel("At " + String.format("%.3f",rateV) + "% your required"
                + " annual income is: $" + String.format("%.2f",t_value));
        Label.setFont(font);
        Label2 = new JLabel("Required Annual Income for "
                + "a Variety of Interest Rates");
        Label2.setFont(font);
        total_mort = new JLabel("Desired Mortgage Amount:");
        total_mort.setFont(font2);
        total_lia = new JLabel("Montly Liabilities:");
        total_lia.setFont(font2);
        total_exp = new JLabel("Monthly Housing Expenses:");
        total_exp.setFont(font2);
        int_rate = new JLabel("Start Interest Rates At:");
        int_rate.setFont(font2);
        loan_term = new JLabel("Loan Term:");
        loan_term.setFont(font2);
        PITI = new JLabel("Monthly Housing Payment(PITI):");
        PITI.setFont(font2);
        PI = new JLabel("Principal & Interest(PI):");
        PI.setFont(font2);
        
        //Calculates the JButton values and adds the action listener value
        calculate = new JButton("Calculate");
        calculate.addActionListener(listener2);
        liab_calc = new JButton("Liability Calculator");
        liab_calc.addActionListener(listener2);
        expenses_calc = new JButton("Housing Cost Calculator");
        expenses_calc.addActionListener(listener2);
        
        //Sets the different JTextFiels to 0.00 and sets the length
        mortgageF = new JTextField(10);
        mortgageF.setText("0.00");
        rateF = new JTextField(10);
        rateF.setText("0.00");
        PITIF = new JTextField(10);
        PITIF.setText("$0.00");
        PITIF.setEditable(false);
        PIF = new JTextField(10);
        PIF.setText("$0.00");
        PIF.setEditable(false);
        liabilitiesF = new JTextField(10);
        liabilitiesF.setText("$0.00");
        liabilitiesF.setEditable(false);
        expensesF = new JTextField(10);
        expensesF.setText("$0.00");
        expensesF.setEditable(false);
        
        termBox = new JComboBox(choices);
        termBox.setSelectedIndex(index);
        
        //Adds the different buttons, text boxes
        add(Label, new GBC(0,0).setSpan(5,1).setFill(GBC.NORTH).setInsets(0, 0, 50, 0));
        add(total_mort, new GBC(0,1).setSpan(1,1).setFill(GBC.CENTER));
        add(mortgageF, new GBC(1,1).setSpan(1,1).setFill(GBC.CENTER));
        add(int_rate, new GBC(0,2).setSpan(1,1).setFill(GBC.CENTER));
        add(rateF, new GBC(1,2).setSpan(1,1).setFill(GBC.CENTER));
        add(loan_term, new GBC(0,3).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(termBox, new GBC(1,3).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(total_lia, new GBC(0,4).setSpan(1,1).setFill(GBC.CENTER));
        add(liabilitiesF, new GBC(1,4).setSpan(1,1).setFill(GBC.CENTER));
        add(liab_calc, new GBC(2,4).setSpan(1,1).setFill(GBC.CENTER));
        add(total_exp, new GBC(0,5).setSpan(1,1).setFill(GBC.CENTER));
        add(expensesF, new GBC(1,5).setSpan(1,1).setFill(GBC.CENTER));
        add(expenses_calc, new GBC(2,5).setSpan(1,1).setFill(GBC.CENTER));
        add(PITI, new GBC(0,6).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(PITIF, new GBC(1,6).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(PI, new GBC(2,6).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(PIF, new GBC(3,6).setSpan(1,1).setFill(GBC.CENTER).setInsets(20, 0, 0, 0));
        add(Label2, new GBC(0,7).setSpan(3,1).setFill(GBC.HORIZONTAL).setInsets(50, 70, 20, 0));
        add(calculate, new GBC(4,15).setSpan(3,1).setFill(GBC.SOUTH).setInsets(50, 70, 0, 0));
        
        //Sets the different JLabel percentages
        labels.add(percentageA = new JLabel());
        labels.add(percentageB = new JLabel());
        labels.add(percentageC = new JLabel());
        labels.add(percentageD = new JLabel());
        labels.add(percentageE = new JLabel());
        labels.add(percentageF = new JLabel());
        labels.add(percentageG = new JLabel());
        labels.add(percentageH = new JLabel());
        
        double i = rateV;
        
        for(JLabel l: labels)
        {
            l.setText(String.format("%.3f",i) + ":");
            l.setFont(font2);
            i += .25;
        }
        
        //Add a percentage amount to the text fields.
        fields.add(percentageAA = new JTextField(10));
        fields.add(percentageBB = new JTextField(10));
        fields.add(percentageCC = new JTextField(10));
        fields.add(percentageDD = new JTextField(10));
        fields.add(percentageEE = new JTextField(10));
        fields.add(percentageFF = new JTextField(10));
        fields.add(percentageGG = new JTextField(10));
        fields.add(percentageHH = new JTextField(10));
        
        for(JTextField f: fields)
        {
            f.setText("0.00");
            f.setEditable(false);
        }
        
        //Adds the different percentage values
        add(percentageA, new GBC(0,8).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageB, new GBC(0,9).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageC, new GBC(0,10).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageD, new GBC(0,11).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageAA, new GBC(1,8).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageBB, new GBC(1,9).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageCC, new GBC(1,10).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageDD, new GBC(1,11).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageE, new GBC(2,8).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageF, new GBC(2,9).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageG, new GBC(2,10).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageH, new GBC(2,11).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageEE, new GBC(3,8).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageFF, new GBC(3,9).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageGG, new GBC(3,10).setSpan(1,1).setFill(GBC.CENTER));
        add(percentageHH, new GBC(3,11).setSpan(1,1).setFill(GBC.CENTER));
        
//        setVisible(true);
        
    }
    
    public double Calculate()
    {
        //Variable Declaration/Initialization
        double temporary = 0;
        
        temporary = 1 + rateV2;
        System.out.println(temporary);
        temporary = Math.pow(temporary, termV);
        System.out.println(temporary);
        temporary = temporary - 1;
         System.out.println(temporary);
        temporary = rateV2/temporary;
         System.out.println(temporary);
        temporary = temporary + rateV2;
         System.out.println(temporary);
        temporary = temporary * mortgageV;
         System.out.println(temporary);
        PIV = temporary;
        PITIV = PIV + expensesV;
        temporary = PITIV + liabilitiesV;
        temporary = temporary * 12;
        temporary = temporary /.36;
        
        return temporary;
    }
    
    //Checks values to see if they are doubles initially set to 0.00
    public void Check()
    {
        if(mortgageF.getText().isEmpty())
        {
            mortgageF.setText("0.00");
            mortgageV = 0;
        }
        else
        {
            mortgageV = new Double(mortgageF.getText());
        }
        
        if(rateF.getText().isEmpty())
        {
            rateF.setText("0.00");
            rateV2 = 0;
        }
        else
        {
            rateV = new Double(rateF.getText());
            rateV2 = rateV/1200;
        }
        
        String choice = choices[termBox.getSelectedIndex()];
        
        if(choice.equalsIgnoreCase("30 years"))
        {
            termV = 30*12;
        }
        
        else
        {
            termV = 15*12;
        }
        
    }
    
    //Updates the calculate values
    public void Update()
    {
        Label.setText("At " + String.format("%.3f",rateV) + "% your required" + " annual income must be: $" + String.format("%.2f",t_value));
        PITIF.setText("$" + String.format("%.2f",PITIV));
        PIF.setText("$" + String.format("%.2f",PIV));
        
        double i = rateV;
        
        for(JLabel l: labels)
        {
            l.setText(String.format("%.3f",i) + ":");
            i += .25;
        }
        
        for(JTextField f: fields)
        {
            double result = Calculate();
            f.setText("$" + String.format("%.2f",result));
            rateV += .25;
            rateV2 = rateV/1200;
        }
        
        rateV = new Double(rateF.getText());
        rateV2 = rateV/1200;
    }
//    /*public static void main(String[] args) 
//    {
//        PITICalculator new_calc = new PITICalculator();
//        new_calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }*/
    
    class MyComponentListener implements ComponentListener
    {

        @Override
        public void componentHidden(ComponentEvent ce) {
            if(ce.getSource() == new_l)
            {
                liabilitiesV = new_l.lia_val;
                liabilitiesF.setText("$"+ String.format("%.2f",liabilitiesV));
            }
            
            else
            {
                expensesV = new_e.expense_val;
                expensesF.setText("$"+ String.format("%.2f",expensesV));
            }
                
        }

        @Override
        public void componentResized(ComponentEvent ce) {
        }

        @Override
        public void componentMoved(ComponentEvent ce) {
        }

        @Override
        public void componentShown(ComponentEvent ce) {
        }
        
    }
 
    class MyActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource() == liab_calc)
            {
                new_l.setVisible(true);
            }
            
            else if(e.getSource() == expenses_calc)
            {
                new_e.setVisible(true);
            }
            
            else if(e.getSource() == calculate)
            {
                try {
                    Check();
                    t_value = Calculate();
                    Update();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Invalid entry sir. Please enter a double.",
                    "", JOptionPane.ERROR_MESSAGE); 
            }
            
        }
    
        }
    }
}

