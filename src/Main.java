import uipack.LanguagePrompt;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.TradeSystem;
import controllerpresenterpack.UseCaseGrouper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args){
        TradeSystem ts = new TradeSystem();
        UseCaseGrouper useCases = ts.loadData();
        ts.onStartUp();

        ControllerPresenterGrouper controllerPresenters = ts.getControllerPresenters();

        JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                ts.saveData();
            }
        });

        new LanguagePrompt(useCases, controllerPresenters, frame);

    }
}
