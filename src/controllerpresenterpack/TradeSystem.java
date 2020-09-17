package controllerpresenterpack;

import controllerpresenterpack.*;
import entitypack.*;
import usecasepack.*;

import javax.swing.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main TradeSystem class that links everything together. Technically this is a controller class.
 */
public class TradeSystem {

    private AdminUser adminUser;
    private UserManager userManager;
    private TradeCreator tradeCreator;
    private ItemManager itemManager;


    private AdminActions adminActions = new AdminActions();
    private TradingUserActions tradingUserActions = new TradingUserActions();
    private BrowsingUserActions browsingUserActions = new BrowsingUserActions();
    private StartupController startupController = new StartupController();

    private String dir = "phase2/data/";

    /**
     * Empty constructor. Creates a new instance of controllerpresenterpack.TradeSystem.
     */
    public TradeSystem(){}

    /**
     * @return ControllerPresenterGrouper object containing instances of all our controllers and presenters.
     */
    public ControllerPresenterGrouper getControllerPresenters(){
        return new ControllerPresenterGrouper(adminActions, tradingUserActions, browsingUserActions);
    }
    /**
     * Loads all classes in that need to be serialized.
     * @return a use case grouper object containing all recently loaded use cases
     */
    public UseCaseGrouper loadData(){
        File directory = new File("phase2/data");
        if (! directory.exists()) {
            directory.mkdir();
        }

        if (!((new File(dir + "adminUser.ser"))).exists()) {
            createAdminUser();
        }
        if (!((new File(dir + "userManager.ser"))).exists()) {
            createUserManager();
        }
        if (!((new File(dir + "tradeCreator.ser"))).exists()){
            createTradeCreator();
        }
        if (!((new File(dir + "itemManager.ser"))).exists()) {
            createItemManager();
        }

        adminUser = FileManager.loadAdminUser();
        userManager = FileManager.loadUserManager();
        tradeCreator = FileManager.loadTradeCreator();
        itemManager = FileManager.loadItemManager();

        return new UseCaseGrouper(adminUser, userManager, tradeCreator, itemManager);
    }

    /**
     * Handles startup functionality for all classes.
     */
    public void onStartUp(){
        startupController.onStartUp(adminUser, userManager, tradeCreator);
    }


    /**
     * Serializes all inputted use cases to their respective files. Called on program shutdown.
     */
    public void saveData(){
        FileManager.saveAdminToFile(adminUser);
        FileManager.saveTradeCreatorToFile(tradeCreator);
        FileManager.saveUserManagerToFile(userManager);
        FileManager.saveItemManagerToFile(itemManager);

    }

    private void createAdminUser(){
        AdminUser adminUser = new AdminUser("admin", "admin");
        FileManager.saveAdminToFile(adminUser);
    }

    private void createUserManager(){
        UserManager userManager = new UserManager();
        FileManager.saveUserManagerToFile(userManager);
    }

    private void createTradeCreator(){
        TradeCreator tradeCreator = new TradeCreator();
        FileManager.saveTradeCreatorToFile(tradeCreator);
    }

    private void createItemManager(){
        ItemManager itemManager = new ItemManager();
        FileManager.saveItemManagerToFile(itemManager);
    }
}

