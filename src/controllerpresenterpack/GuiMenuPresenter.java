package controllerpresenterpack;

import entitypack.*;
import usecasepack.ItemManager;

import java.io.*;
import java.util.*;

/**
 * A presenter class that handles the output to the user for menus and alert handling.
 */
public class GuiMenuPresenter {

    private final LinkedHashMap<Frame, ArrayList<String>> menusMap = new LinkedHashMap<Frame, ArrayList<String>>();
    /**
     * The file from which we read our menu text.
     */
    File menu;

    /**
     * Initializes a new ControllerPresenterPack.MenuPresenter object,
     * while also populating the menusMap HashMap with the lines from Menu.txt
     *
     * @param language indicating the preferred language (in this case English or French)
     */
    public GuiMenuPresenter(String language) {
        try {
            if (language.equals("English")) {
                menu = new File("phase2/data/GuiMenu.txt");
            } else if (language.equals("French")) {
                menu = new File("phase2/data/GuiFrenchMenu.txt");
            }
            BufferedReader br = new BufferedReader(new FileReader(menu));

            try {
                String readBuff = br.readLine();
                // add newline char back into sb
                ArrayList<String> section = new ArrayList<String>();
                while (readBuff != null) {
                    String[] arr = readBuff.split("- ");
                    readBuff = arr[0];
                    if (readBuff.equals("Frame{ ") || readBuff.equals("Cadre{ ")) {
                        readBuff = br.readLine();
                        section.clear();
                        while (!readBuff.equals("}")) {
                            section.add(readBuff);
                            readBuff = br.readLine();
                        }
                        menusMap.put(Frame.valueOf(arr[1]), (ArrayList<String>) section.clone());

                    }
                    readBuff = br.readLine();
                }

            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occured, please try again later.");
            e.printStackTrace();
        }
    }

    /**
     * Prints to the user the line in Menu.txt corresponding to the menuIndex and lineIndex.
     *
     * @param frameIndex the index of menu at which the line is located
     * @param lineIndex  the index of the line within the menu.
     */
    public String getText(Frame frameIndex, int lineIndex) {
        try {
            return menusMap.get(frameIndex).get(lineIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * Prints to the user the line in Menu.txt corresponding to the menuIndex and lineIndex along with a specific object
     *
     * @param frameIndex he index of menu at which the line is located
     * @param lineIndex  the index of the line within the menu.
     * @param variable   which object is being printed along with the text
     * @return the printed text or an error message in case the index for the wanted menu is out of bounds
     */
    public String getText(Frame frameIndex, int lineIndex, Object variable) {
        try {
            return menusMap.get(frameIndex).get(lineIndex) + " " + variable;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * Prints to the user the line in Menu.txt corresponding to the menuIndex and lineIndex along with specific objects
     *
     * @param menuIndex he index of menu at which the line is located
     * @param lineIndex the index of the line within the menu.
     * @param variable1 which object is being printed along with the text
     * @param variable2 which secondary object is being printed along with the text
     * @return he printed text or an error message in case the index for the wanted menu is out of bounds
     */
    public String getText(Frame menuIndex, int lineIndex, Object variable1, Object variable2) {
        try {
            return variable1 + menusMap.get(menuIndex).get(lineIndex) + variable2;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound, menu either doesn't exist in file or the line doesn't exist :/");
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * Prints a string representation of a trade.
     *
     * @param trade a trade object
     */
    public String printTradeToString(ItemManager itemManager, Trade trade) {
        StringBuilder acc = new StringBuilder();
        acc.append("<html>");
        acc.append(getText(Frame.TRADETOSTRING, 1, trade.getUsername1()));
        acc.append(getText(Frame.TRADETOSTRING, 2, trade.getUsername2()) + "<br/>");
        acc.append(getText(Frame.TRADETOSTRING, 3, getItemNamesFromUser1ToUser2(trade, itemManager)) + "<br/>");
        acc.append(getText(Frame.TRADETOSTRING, 4, getItemNamesFromUser2ToUser1(trade, itemManager))+ "<br/>");
        acc.append(getText(Frame.TRADETOSTRING, 5, trade.getTimeOfTrade().toString()) + "<br/>");
        acc.append(getText(Frame.TRADETOSTRING, 6, trade.getMeetingPlace())+ "<br/>");
        acc.append(getText(Frame.TRADETOSTRING, 7, trade.getTradeID())+ "<br/><html>");

        return acc.toString();
    }

    // helper method which lists the names of the items going from user 1 to user 2 - Louis
    private String getItemNamesFromUser1ToUser2(Trade trade, ItemManager itemManager) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int itemID : trade.getItemIDsSentToUser2()) {
            Item item = itemManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }

    // helper method which lists the names of the items going from user 2 to user 1 - Louis
    private String getItemNamesFromUser2ToUser1(Trade trade, ItemManager itemManager) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int itemID : trade.getItemIDsSentToUser1()) {
            Item item = itemManager.searchItem(itemID);
            stringBuilder.append(item.getName()).append(" ");
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * gets the items strings from User 1 to User 2
     *
     * @param trade       the trade between the users related to the items
     * @param itemManager the item manager for the items
     * @return list of the items from User 1 to User 2
     */
    public ArrayList<String> getItemStringsFromUser1ToUser2(Trade trade, ItemManager itemManager) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i : trade.getItemIDsSentToUser2()) {
            Item item = itemManager.searchItem(i);
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName());
            sb.append(" (").append(item.getId()).append(")");
            sb.append(", ");
            sb.append(item.getDescription());
            String string = sb.toString();
            list.add(string);
        }
        return list;
    }

    /**
     * gets the items strings from User 2 to User 1
     *
     * @param trade       the trade between the users related to the items
     * @param itemManager the item manager for the items
     * @return list of the items from User 2 to User 1
     */
    public ArrayList<String> getItemStringsFromUser2ToUser1(Trade trade, ItemManager itemManager) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i : trade.getItemIDsSentToUser1()) {
            Item item = itemManager.searchItem(i);
            StringBuilder sb = new StringBuilder();
            sb.append(item.getName());
            sb.append(" ===== ");
            sb.append(item.getId());
            sb.append("\n");
            sb.append(item.getDescription());
            String string = sb.toString();
            list.add(string);
        }
        return list;
    }
}

