package uipack;

import uipack.usermenu.BrowsingUserActionsMenu;
import uipack.usermenu.TradingUserActionsMenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.MetroArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to display the create user account functionality
 */
public class CreateUserAccount {
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmationField;
    private JButton back;
    private JButton createAccountButton;
    private JLabel username;
    private JLabel password;
    private JLabel passwordConfirmation;
    private JPanel mainPanel;
    private JCheckBox tradingUserCheckBox;
    private JLabel tradingUserCheckBoxLabel;
    private JComboBox<MetroArea> citySelectorComboBox;
    private JTextField usernameTextField;
    private JLabel citySelectorLabel;

    /**
     * Constructs the interface to create user accounts
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param frame the main window displayed to the trading user of the program
     */
    public CreateUserAccount(UseCaseGrouper useCases, ControllerPresenterGrouper
            cpg, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        tradingUserCheckBox.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,0));
        username.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,1));
        password.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,2));
        passwordConfirmation.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,3));
        citySelectorLabel.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT, 4));
        back.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,5));
        createAccountButton.setText(cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,6));


        for (MetroArea Area : MetroArea.values()){
            citySelectorComboBox.addItem(Area);
        }

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenu mainMenu = new MainMenu(useCases, cpg, frame);
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = usernameTextField.getText();
                if (username.equals("")){
                    //"Username field is empty"
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT, 11));
                    return;
                }
                boolean isBrowsingUser = tradingUserCheckBox.isSelected();
                MetroArea metroArea = (MetroArea)citySelectorComboBox.getSelectedItem();

                char[] passwordCharArr = passwordField.getPassword();
                if (passwordCharArr.length == 0){
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT, 10));
                    return;
                }
                char[] passwordConfirmationArr = passwordConfirmationField.getPassword();
                if (passwordCharArr.length != passwordConfirmationArr.length){
                    //"Passwords are not the same length. " +
                    //                            "Please ensure the same password is entered in both fields."
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,7));
                    return;
                }
                StringBuilder password = new StringBuilder();
                for (int i = 0; i < passwordCharArr.length; i++){
                    if (Character.compare(passwordCharArr[i], passwordConfirmationArr[i]) != 0){
                        // "Passwords do not match. Please ensure the same password is entered in both fields."
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,8));
                        return;
                    }
                    password.append(passwordCharArr[i]);
                }

                boolean userRegistered = cpg.tradingUserActions.addNewLogin(useCases.userManager,
                        username, password.toString(), isBrowsingUser, metroArea);
                if (!userRegistered){
                    //"Could not register this account because the username is taken."
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT,9));
                    return;
                }

                //"Account successfully created."
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEUSERACCOUNT, 12));
                frame.setTitle(cpg.menuPresenter.getText(Frame.LOGIN, 5) + " " + username);

                if (isBrowsingUser){
                    new BrowsingUserActionsMenu(useCases, cpg, username, frame);

                } else {
                    new TradingUserActionsMenu(useCases, cpg, username, frame);
                }


            }
        });
    }
}
