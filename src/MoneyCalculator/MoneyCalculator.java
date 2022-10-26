package MoneyCalculator;

import Control.Controller;
import View.GUI;


public class MoneyCalculator {

    public static void main(String[] args){
        GUI gui = new GUI();
        Controller controller  = new Controller(gui);
        controller.ini();
    }
    
}
