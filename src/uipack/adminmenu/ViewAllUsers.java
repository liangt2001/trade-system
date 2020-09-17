package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;
import entitypack.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A UI class that allows Admin to view all Users and select one
 */
public class ViewAllUsers {
    private JComboBox<String> selectUserBox;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton confirmButton;

    /**
     * Constructs the interface to view all Users and select one
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param frame the main window displayed to the administrative user of the program
     */
    public ViewAllUsers(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, boolean isAdmin, JFrame frame)
    {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        selectUserBox.addItem(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 0));
        backButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 1));
        confirmButton.setText(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 2));
        ArrayList<User> allUsers = cpg.adminActions.viewAllUsers(useCases.userManager);
        for (int i = 0; i < allUsers.size(); i++){
            selectUserBox.addItem(allUsers.get(i).getUsername());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminActionsMenu adminOptions = new AdminActionsMenu(useCases, cpg, frame);
            }});

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((selectUserBox.getSelectedItem()).equals(cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 0))) {
                    JOptionPane.showMessageDialog(frame, cpg.menuPresenter.getText(Frame.VIEWALLOTHERUSERSMENU, 3));
                }
                else {
                    new ViewingUserAsAdmin(useCases, cpg, (String) selectUserBox.getSelectedItem(), frame);
                }
            }
        });
    }}

