package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class that allows user to add items to their wishlist
 */
public class AddItemToWishlistMenu {
    private JScrollPane userToViewItemsPane;
    private JLabel title;
    private JPanel mainPanel;
    private JButton backButton;

    /**
     * Constructs the interface to add items to the wishlist for trading user
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param activeUsername the username of the User adding items
     * @param userToViewUsername the username of the User whose items are being viewed at
     * @param frame the window displayed to the trading user of the program
     * @param isTradingUserViewing whether the User viewing is a TradingUser
     * @param isUserToViewTrading whether the User being viewed at is a TradingUser
     */
    public AddItemToWishlistMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                 String activeUsername, String userToViewUsername, JFrame frame,
                                 boolean isTradingUserViewing, boolean isUserToViewTrading){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        backButton.setText(cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 5));

        title.setText(cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 0) + userToViewUsername +
                cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 1));

        JPanel content = new JPanel();

        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        ArrayList<Item> userToViewItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                userToViewUsername);


        int n = 2;
        for (Item item : userToViewItems){

            JTextArea itemText = new JTextArea();
            itemText.setText(item.getName());
            c.gridy = n;
            n++;
            content.add(itemText, c);

            JButton addToWishlistButton = new JButton();
            addToWishlistButton.setText(cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU, 2));



            addToWishlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cpg.tradingUserActions.addItemToWishlist(useCases.userManager, activeUsername, item.getName())){
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU,
                                3));
                    } else{
                        JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.ADDITEMTOWISHLISTMENU,
                                4));
                    }

                }
            });
            content.add(addToWishlistButton, c);
        }

        userToViewItemsPane.setViewportView(content);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewUserMenu(useCases, cpg, activeUsername, userToViewUsername, frame,
                        isTradingUserViewing, isUserToViewTrading);
            }
        });
    }
}
