package uipack.usermenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class for user's to view item details including ID and description
 */
public class ItemViewer {
    private JButton removeItemButton;
    private JButton backButton;
    private JLabel itemDescription;
    private JPanel mainPanel;

    /**
     * Constructs the interface to view items
     * @param useCases use case grouper
     * @param cpg the controller presenter grouper
     * @param username current user's username
     * @param itemId the current item's ID
     * @param frame the main window displayed to the trading user of the program
     */
    public ItemViewer(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, int itemId, JFrame frame) {

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Item item = useCases.itemManager.searchItem(itemId);

        itemDescription.setText(item.getName() + "\n" + item.getDescription());

        removeItemButton.setText(cpg.menuPresenter.getText(Frame.ITEMVIEWER, 0));
        backButton.setText(cpg.menuPresenter.getText(Frame.ITEMVIEWER, 1));


        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                useCases.itemManager.removeFromInventory(itemId);
                new ViewItemsAndWishlistMenu(useCases, cpg, username, frame);
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
