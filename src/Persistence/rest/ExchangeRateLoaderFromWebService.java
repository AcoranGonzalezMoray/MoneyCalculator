package Persistence.rest;

import Model.Currency;
import Model.ExchangeRate;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class ExchangeRateLoaderFromWebService implements ExchangeRateLoader{

    @Override
    public ExchangeRate exh(Currency from, Currency to) {
        return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
    }

    private double read(String codeFrom, String codeTo) {
        try{
            String line = read(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+codeFrom+"/"+codeTo+".json"));
            return Double.parseDouble(getStringRateFromJSONLine(line));
            //more code goes here
        }catch(MalformedURLException ex){
        }catch( IOException ex){}
        
        
        return 0.0;
    }

    private String getStringRateFromJSONLine(String line) {
        String[] split = line.split(",");
        return split[1].substring(split[1].indexOf(":")+1,split[1].indexOf(")")-1 );
     }
    
    private String read(URL url) throws IOException{
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024] ;
        int lenght = inputStream.read(buffer);
        return new String(buffer,1,lenght);
    }
    
}
