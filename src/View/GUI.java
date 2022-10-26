package View;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
    
    private JLabel Amount = new JLabel();
    private JTextField amount = new JTextField("");
    
    private JLabel From = new JLabel();
    private JComboBox from = new JComboBox();
    
    private JLabel To = new JLabel();
    private JComboBox to = new JComboBox();
    
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel midPanel = new JPanel();
    
    private JLabel symbol = new JLabel();
    private List<Currency> list;
    private ExchangeRateLoaderFromWebService exhM;
    private JSeparator separator = new JSeparator();
    
    private JLabel result = new JLabel();
    private JLabel rate = new JLabel();
    private JButton convert = new JButton("Convert");
    private JComponent[] allComponents = {Amount,amount,symbol,From,from,To,to};
    
    public GUI(List<Currency> list, ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService) {
        super("Money Calculator");
        this.list=list;
        this.exhM=exchangeRateLoaderFromWebService;
        for(Currency item : list){
            to.addItem(item.getCode());
            from.addItem(item.getCode());
        }
        setComponents();
        setListener();
        setStructure();
    }

    public void setStructure() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().add(mainPanel);
      pack();
      setLocationByPlatform(true);
      setBounds(10,10,570,180);
      setVisible(true);
    }
    
    public void setComponents(){
        Amount.setText("Amount: ");
        From.setText("From: ");
        To.setText("To: ");
        amount.setPreferredSize(new Dimension(50,20));
        symbol.setText(list.get(from.getSelectedIndex()).getSymbol()+"    ");
        result.setVisible(false);
        rate.setVisible(false);
        separator.setOrientation(SwingConstants.VERTICAL);
        for (JComponent comp : allComponents) topPanel.add(comp);
        bottomPanel.add(convert);
        
        midPanel.add(result);
        midPanel.add(separator);
        midPanel.add(rate);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(midPanel);
        mainPanel.add(bottomPanel);
    }
   
    public void setListener(){
        from.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                symbol.setText(list.get(from.getSelectedIndex()).getSymbol());
            }
        });
        convert.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                Currency from_ = list.get(from.getSelectedIndex());
                Currency to_ = list.get(to.getSelectedIndex());
                Double tempValue = Double.parseDouble(amount.getText());
                DecimalFormat df = new DecimalFormat("###.##");
                
                ExchangeRate exchangeRate =  exhM.exh(from_ , to_);
                ExchangeRate exchangeRateReverse =  exhM.exh(to_ , from_);
                
                result.setText(df.format(exchangeRate.getRate()* tempValue)
                        + to_.getSymbol() );
                
                rate.setText("<html><body> "
                        + "1 "+from_.getName()+" = "+exchangeRate.getRate()+" "+to_.getName()
                        + "<br> "
                        + "1 " + to_.getName()+" = "+ exchangeRateReverse.getRate()+" "+from_.getName() 
                        +"</body></html>");
                
                result.setFont(new Font("Serif", Font.PLAIN, 28));
                
                result.setVisible(true);
                rate.setVisible(true);
            }
        });
    }
}
