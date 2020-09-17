package controllerpresenterpack;

import entitypack.TemporaryTrade;
import entitypack.Trade;
import usecasepack.AdminUser;
import usecasepack.TradeCreator;
import usecasepack.UserManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * controller class that's called on startup
 */
public class StartupController {

    /**
     * Called on startup. Handles all startup functionality for users and admins.
     * @param adminUser handling admin user related functionality
     * @param userManager handing trading user and browsing user related functionality
     * @param tradeCreator handles the creation of trades
     */
    public void onStartUp(AdminUser adminUser, UserManager userManager, TradeCreator tradeCreator){
        userManager.onStartUp();
        adminUser.onStartUp();
        tradeCreator.onStartup();

    }



}
