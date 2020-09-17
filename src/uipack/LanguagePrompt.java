package uipack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllerpresenterpack.*;

/**
 * The UI class which begins the program – allowing users to select their language
 */
public class LanguagePrompt {
    public JPanel panelMain;
    private JButton englishButton;
    private JButton frenchButton;


    /**
     * Constructs the interface for language selection
     * @param useCases the use case grouper
     * @param controllerPresenterGrouper the controller presenter grouper
     * @param frame the main window displayed to the trading user of the program
     */
    public LanguagePrompt(UseCaseGrouper useCases, ControllerPresenterGrouper controllerPresenterGrouper, JFrame frame){
        frenchButton.setIcon(new ImageIcon("phase2/data/baguette.png"));
        englishButton.setIcon(new ImageIcon("phase2/data/tea.png"));
        frame.setMinimumSize(new Dimension(800, 600));
        panelMain.setSize(800, 600);
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GuiMenuPresenter menuPresenter = new GuiMenuPresenter("English");
                controllerPresenterGrouper.setMenuPresenter(menuPresenter);
                new MainMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
        frenchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GuiMenuPresenter menuPresenter = new GuiMenuPresenter("French");
                controllerPresenterGrouper.setMenuPresenter(menuPresenter);
                new MainMenu(useCases, controllerPresenterGrouper, frame);
            }
        });
    }
}
