package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class to display the ability for users to request an item creation
 */
public class CreateItemValidationRequestMenu {
    private JButton createRequestButton;
    private JButton backButton;
    private JTextField itemNameField;
    private JTextField itemDescriptionField;
    private JLabel itemDescriptionLabel;
    private JLabel itemNameLabel;
    private JPanel mainPanel;

    /**
     * Constructs the interface to create an item validation request
     * @param useCases use case grouper
     * @param cpg the controller presenter grouper
     * @param username current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public CreateItemValidationRequestMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                           String username, JFrame frame) {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        itemNameLabel.setText(cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 0));

        itemDescriptionLabel.setText(cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 1));

        backButton.setText(cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 2));
        createRequestButton.setText(cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 3));



        createRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String itemName = itemNameField.getText();
                if (itemName.equals("")){
                    JOptionPane.showMessageDialog(frame,
                            cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 4));
                    return;
                }

                String itemDescription = itemDescriptionField.getText();
                if (itemDescription.equals("")){
                    JOptionPane.showMessageDialog(frame,
                            cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU, 5));
                    return;
                }

                cpg.tradingUserActions.createItemValidationRequest(useCases.itemManager, username, itemName,
                        itemDescription);
                JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.CREATEITEMVALIDATIONREQUESTMENU,
                        6));
                new ViewItemsAndWishlistMenu(useCases, cpg, username, frame);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewItemsAndWishlistMenu viewItemsAndWishlistMenu = new ViewItemsAndWishlistMenu(useCases, cpg,
                        username, frame);
            }
        });
    }
}
