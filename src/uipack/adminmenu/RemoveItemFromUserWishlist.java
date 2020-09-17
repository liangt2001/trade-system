package uipack.adminmenu;
import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.TradingUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//code adapted from https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
/**
 * A UI class that allows Admin to remove items from a User's wishlist
 */
public class RemoveItemFromUserWishlist {
    private JPanel mainPanel;
    private JLabel menuTitle;
    private JButton submit;
    private JScrollPane userItems;
    private JButton back;

    /** Creates the panel and its it as the visible panel on the frame
     *
     * @param useCases UseCaseGrouper object created on program startup
     * @param cpg ControllerPresenterGrouper object created on program startup
     * @param username username of the user who being viewed
     * @param window the current JFrame that is open
     */
    public RemoveItemFromUserWishlist(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, String username, JFrame window){
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        TradingUser user = (TradingUser) useCases.userManager.searchUser(username);
        menuTitle.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 7));
        submit.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 6));
        back.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWUSER, 5));

        DefaultListModel<String> items = new DefaultListModel<String>();
        for (String itemName : user.getWishlistItemNames()) {
            items.addElement(itemName);
        }
        JList list = new JList(items);//items has type string[]
        userItems.setViewportView(list);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        window.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                user.removeItemFromWishList(items.getElementAt(index));
                items.remove(index);

                int size = items.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    submit.setEnabled(false);

                } else { //Select an index.
                    if (index == items.getSize()) {
                        //removed item in last position
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);

                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewingUserAsAdmin viewingUserAsAdmin = new ViewingUserAsAdmin(useCases, cpg, username, window);
            }
        });
    }


}
