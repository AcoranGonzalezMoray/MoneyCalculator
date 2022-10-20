package View;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    private JLabel result = new JLabel();
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
      setBounds(10,10,500,180);
      setVisible(true);
    }
    
    public void setComponents(){
        Amount.setText("Amount: ");
        From.setText("From: ");
        To.setText("To: ");
        amount.setPreferredSize(new Dimension(50,20));
        symbol.setText(list.get(from.getSelectedIndex()).getSymbol()+"    ");
        result.setVisible(false);
        
        for (JComponent comp : allComponents) topPanel.add(comp);
        bottomPanel.add(convert);
        midPanel.add(result);
        
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
                ExchangeRate exchangeRate =  exhM.exh(
                        list.get(from.getSelectedIndex()),
                        list.get(to.getSelectedIndex()));
                
                result.setText(String.valueOf(exchangeRate.getRate()* Double.parseDouble(amount.getText())) +
                        list.get(to.getSelectedIndex()).getSymbol() );
                
                result.setFont(new Font("Serif", Font.PLAIN, 28));
                result.setVisible(true);
            }
        });
    }
}
