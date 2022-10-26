package Control;

import Model.Currency;
import Model.ExchangeRate;
import Model.Money;
import Persistence.CurrencyLoaderFromFile;
import Persistence.rest.ExchangeRateLoaderFromWebService;
import View.GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Controller {
    private GUI frame;
    private CurrencyLoaderFromFile currencyLoaderFromFile;
    private ExchangeRateLoaderFromWebService exhM;
    private List<Currency> currencies;
    
    public Controller(GUI gui) {
        this.frame = gui;
    }
    
    public void  ini(){
        this.exhM = new ExchangeRateLoaderFromWebService() ;
        this.currencyLoaderFromFile = new CurrencyLoaderFromFile ("Curren.txt");
        this.currencies = currencyLoaderFromFile.loadCurrencies();
        this.frame.setCurrency(this.currencies);
        setListener();
        frame.ini();
    
    }
   
    private void setListener(){
        JComboBox from = (JComboBox)frame.getDetailsComponents().get("From");
        JComboBox to = (JComboBox)frame.getDetailsComponents().get("To");
        JLabel symbol = (JLabel)frame.getDetailsComponents().get("Symbol");
        JButton convert = (JButton)frame.getDetailsComponents().get("Convert");
        JLabel result = (JLabel)frame.getDetailsComponents().get("Result");
        JLabel rate = (JLabel)frame.getDetailsComponents().get("Rate");
        JTextField amount = (JTextField)frame.getDetailsComponents().get("Amount");
        
        
        from.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                symbol.setText(currencies.get(from.getSelectedIndex()).getSymbol());
            }
        });
        convert.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                DecimalFormat df = new DecimalFormat("###.##");
               
                
                Double tempValueFrom = Double.parseDouble(amount.getText());
                Money moneyFrom = new Money(tempValueFrom,currencies.get(from.getSelectedIndex()));
                Currency to_ = currencies.get(to.getSelectedIndex());
                

                ExchangeRate exchangeRate =  exhM.exh(moneyFrom.getCurrency() , to_);
                Double tempValueTo = exchangeRate.getRate()* tempValueFrom;
                Money moneyTo = new Money(tempValueTo, currencies.get(to.getSelectedIndex()) );

                ExchangeRate exchangeRateReverse =  exhM.exh(moneyTo.getCurrency() , moneyFrom.getCurrency());
                
                result.setText(df.format(moneyTo.getAmount())+ moneyTo.getCurrency().getSymbol() );
                
                rate.setText("<html><body> "
                        + "1 "+moneyFrom.getCurrency().getName()+" = "+exchangeRate.getRate()+" "+moneyTo.getCurrency().getName()
                        + "<br> "
                        + "1 " +moneyTo.getCurrency().getName()+" = "+ exchangeRateReverse.getRate()+" "+moneyFrom.getCurrency().getName() 
                        +"</body></html>");
                
                result.setFont(new Font("Serif", Font.PLAIN, 28));
                
                result.setVisible(true);
                rate.setVisible(true);
            }
        });
    }
}
