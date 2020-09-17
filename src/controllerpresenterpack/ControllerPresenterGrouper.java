package controllerpresenterpack;

/**
 * class that groups the various controller actions
 */
public class ControllerPresenterGrouper {

    public GuiMenuPresenter menuPresenter;
    public AdminActions adminActions;
    public TradingUserActions tradingUserActions;
    public BrowsingUserActions browsingUserActions;

    /**
     * grouper class
     * @param adminActions for all of the admin's possible actions
     * @param tradingUserActions for all of the trading user's possible actions
     * @param browsingUserActions for all of the browsing-only user's possible actions
     *
     */
    public ControllerPresenterGrouper(AdminActions adminActions,
                                      TradingUserActions tradingUserActions, BrowsingUserActions browsingUserActions){
        this.adminActions = adminActions;
        this.tradingUserActions = tradingUserActions;
        this.browsingUserActions = browsingUserActions;

    }

    /**
     * sets the GUI menu presenter class
     * @param guiMenuPresenter for all text-related actions depending on preferred language
     */
    public void setMenuPresenter(GuiMenuPresenter guiMenuPresenter){
        this.menuPresenter = guiMenuPresenter;
    }
}
