package controllerpresenterpack;

import usecasepack.*;

import java.io.*;

/**
 * A gateway class that handles the input and output to and from files for serialization.
 */
public class FileManager {
    //used provided code in file StudentManager.java as a reference
    //used https://www.tutorialspoint.com/java/java_serialization.htm and
    //https://attacomsian.com/blog/java-write-object-to-file as a reference

    private final static String dir = "phase2/data/"; //file directory

    private static void saveToFile(Object obj, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(dir + fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializes an admin object to a .ser file
     * @param admin admin which is being saved to a file
     */
    public static void saveAdminToFile(AdminUser admin) {
        saveToFile(admin, "adminUser.ser");
    }

    /**
     * Serializes a UseCasePack.UserManager object to a .ser file
     * @param userManager which is being saved to a file
     */
    public static void saveUserManagerToFile(UserManager userManager) {
        saveToFile(userManager, "userManager.ser");
    }

    /**
     * Serializes a UseCasePack.TradeCreator object to a .ser file
     * @param tradeCreator which is being saved to a file
     */
    public static void saveTradeCreatorToFile(TradeCreator tradeCreator) {
        saveToFile(tradeCreator, "tradeCreator.ser");
    }

    /**
     * Serializes a UseCasePack.ItemManager object to a .ser file
     * @param itemManager which is being saved to a file
     */
    public static void saveItemManagerToFile(ItemManager itemManager){
        saveToFile(itemManager, "itemManager.ser");
    }

    /**
     * Loads Admin from .ser file
     * @return UseCasePack.AdminUser object
     */
    public static AdminUser loadAdminUser(){
        AdminUser obj;
        try {
            FileInputStream file = new FileInputStream(dir + "adminUser.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (AdminUser) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    /**
     * Loads a UseCasePack.UserManager from a .ser file
     * @return the UseCasePack.UserManager object stored in the file
     */
    public static UserManager loadUserManager(){
        UserManager obj;
        try {
            FileInputStream file = new FileInputStream(dir + "userManager.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (UserManager) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    /**
     * Loads a UseCasePack.TradeCreator from a .ser file
     * @return the UseCasePack.UserManager object stored in the file
     */
    public static TradeCreator loadTradeCreator(){
        TradeCreator obj;
        try {
            FileInputStream file = new FileInputStream(dir + "tradeCreator.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (TradeCreator) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }

    /**
     * Loads a UseCasePack.ItemManager from a .ser file
     * @return the UseCasePack.ItemManager object stored in the file
     */
    public static ItemManager loadItemManager(){
        ItemManager obj;
        try {
            FileInputStream file = new FileInputStream(dir + "itemManager.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            obj = (ItemManager) in.readObject();
            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }
}