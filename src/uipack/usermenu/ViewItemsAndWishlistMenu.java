package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class for users to view their own items and wishlist
 */
public class ViewItemsAndWishlistMenu {
    private JScrollPane itemPane;
    private JPanel mainPanel;
    private JButton createItemValidationRequestButton;
    private JButton backButton;
    private JScrollPane wishlistItemPane;
    private JTextPane itemTextPane;
    private JTextPane wishlistTextPane;

    /**
     * Constructs the interface to view items and wishlist
     * @param useCases the user case grouper
     * @param cpg the controller presenter grouper
     * @param username the current user's username
     * @param frame the main window displayed to the trading user of the program
     */
    public ViewItemsAndWishlistMenu(UseCaseGrouper useCases, ControllerPresenterGrouper cpg,
                                    String username, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 2));
        createItemValidationRequestButton.setText(cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 3));

        StringBuilder availItemsString = new StringBuilder(
                cpg.menuPresenter.getText(Frame.VIEWITEMSANDWISHLISTMENU, 0));
        ArrayList<Item> availableItems = cpg.tradingUserActions.getAvailableItems(useCases.itemManager,
                username);
        availItemsString.append("\n");
        for (Item item : availableItems){
            availItemsString.append(item.getName() + " ID: " + item.getId() + "\n");
            //availableItemIDs.add(item.getId());
        }
        itemTextPane.setText(availItemsString.toString());

        ArrayList<String> wishlistItems = cpg.tradingUserActions.getWishListItems(useCases.userManager,
                username);
        StringBuilder wishlistItemsString = new StringBuilder(cpg.menuPresenter.getText(
                Frame.VIEWITEMSANDWISHLISTMENU, 1));
        wishlistItemsString.append("\n");
        for (String wishlistItem : wishlistItems){
            wishlistItemsString.append(wishlistItem + "\n");
        }
        wishlistTextPane.setText(wishlistItemsString.toString());

        for (Item item: availableItems){
            JButton thing = new JButton();
            thing.setText(item.getName());
            itemPane.add(thing);
            thing.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ItemViewer(useCases, cpg, username, item.getId(), frame);
                }
            });
        }


        createItemValidationRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                new CreateItemValidationRequestMenu(useCases, cpg, username, frame);
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new TradingUserActionsMenu(useCases, cpg, username, frame);
            }
        });
    }
}
