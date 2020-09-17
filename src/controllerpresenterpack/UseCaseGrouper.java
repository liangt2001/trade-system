package controllerpresenterpack;

import entitypack.Trade;
import usecasepack.*;

/**
 * Class to group together our use cases for readability.
 */
public class UseCaseGrouper {

    public AdminUser adminUser;
    public UserManager userManager;
    public TradeCreator tradeCreator;
    public ItemManager itemManager;

    /**
     * organizes the use cases for easy access and readability
     * @param adminUser handling admin user related functionality
     * @param userManager handing trading-user and browsing-user related functionality
     * @param tradeCreator handles the creation of trades
     * @param itemManager manages the items for each user
     */
    public UseCaseGrouper(AdminUser adminUser, UserManager userManager,
                          TradeCreator tradeCreator, ItemManager itemManager){
        this.adminUser = adminUser;
        this.userManager = userManager;
        this.tradeCreator = tradeCreator;
        this.itemManager = itemManager;
    }
}
