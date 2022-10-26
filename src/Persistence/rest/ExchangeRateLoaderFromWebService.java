package Persistence.rest;

import Model.Currency;
import Model.ExchangeRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class ExchangeRateLoaderFromWebService implements ExchangeRateLoader{

    @Override
    public ExchangeRate exh(Currency from, Currency to) {
        return new ExchangeRate(from, to, read(from.getName(), to.getName()));
    }

    private double read(String codeFrom, String codeTo) {
        try{
            
            String line = read2(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+codeFrom.toLowerCase()
                    +"/"+codeTo.toLowerCase()+".json"));
            return Double.parseDouble(getStringRateFromJSONLine(line));
            //more code goes here
        }catch(MalformedURLException ex){
            System.out.println(ex);
        }catch( IOException ex){}
        
        
        return 0.0;
    }

    private String getStringRateFromJSONLine(String line) {
        String[] split = line.split(",");
        return split[1].split(":")[1].split("\n")[0];
     }
    
    private String read(URL url) throws IOException{
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024] ;
        int lenght = inputStream.read(buffer);
        return new String(buffer,1,lenght);
    }
    private String read2(URL url){
        String line="";
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = in.read()) != -1)sb.append((char) cp);
            return sb.toString();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
}
