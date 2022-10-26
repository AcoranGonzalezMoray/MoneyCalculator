package View;

import Model.Currency;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
    
    private final JLabel Amount = new JLabel();
    private final JLabel From = new JLabel();
    private final JLabel To = new JLabel();
    private final JPanel mainPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private  final JLabel symbol = new JLabel();
    private List<Currency> list;
    private JComponent[] allComponents;
    private final Map<String,JComponent> components = new LinkedHashMap<>();
    
    public GUI() {
        super("Money Calculator");
        setDetailsComponents();
    }
    
    public void ini(){
        setComponents();
        setStructure();
    }

    private void setStructure() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().add(mainPanel);
      pack();
      setLocationByPlatform(true);
      setBounds(10,10,570,180);
      setVisible(true);
    }
    
    private void setComponents(){
        Amount.setText("Amount: ");
        From.setText("From: ");
        To.setText("To: ");
        components.get("Amount").setPreferredSize(new Dimension(50,20));
        symbol.setText(list.get(((JComboBox)components.get("From")).getSelectedIndex()).getSymbol()+"    ");
        components.get("Result").setVisible(false);
        components.get("Rate").setVisible(false);
        for (JComponent comp : allComponents) topPanel.add(comp);
        bottomPanel.add(((JButton)components.get("Convert")));
        
        midPanel.add(components.get("Result"));
        midPanel.add(components.get("Rate"));
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(midPanel);
        mainPanel.add(bottomPanel);
    }
    
    public void setCurrency (List<Currency> loadCurrencies) {
        this.list = loadCurrencies;
        for(Currency item : list){
            ((JComboBox)components.get("To")).addItem(item.getCode());
            ((JComboBox)components.get("From")).addItem(item.getCode());
        }
    }
   
    public Map<String, JComponent>  getDetailsComponents(){
        return components;
    }
    
    private void setDetailsComponents() {

        JTextField amount = new JTextField("");
        JComboBox from = new JComboBox();
        JComboBox to = new JComboBox();
        JLabel symbolVariable = new JLabel();
        JLabel result = new JLabel();
        JLabel rate = new JLabel();
        JButton convert = new JButton("Convert");
        
        JComponent[] Components = {Amount,amount,symbolVariable,From,from,To,to};
        this.allComponents = Components;
        
        components.put("From", from);
        components.put("To", to);
        components.put("Symbol", symbolVariable);
        components.put("Convert", convert);
        components.put("Result", result);
        components.put("Rate", rate);
        components.put("Amount", amount);
    }
}
