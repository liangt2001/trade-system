package entitypack;

import java.io.Serializable;

/**
 * Represents an EntityPack.Item in the trading system.
 */
public class Item implements Serializable {

    /**
     * The name of the item
     */
    private String name;
    /**
     * A short description of the item
     */
    private String description;
    /**
     * A unique ID for the item.
     */
    private final int id;
    private String owner;
    private String inPossessionOf;

    /**
     * Initializes a new EntityPack.Item with a name, description, and unique ID.
     * @param name the name of the item
     * @param description a short description of the item
     * @param itemID the unique ID of the item.
     */
    public Item(String name, String description, int itemID, String owner) {
        this.name = name;
        this.description = description;
        this.id = itemID; // id number is taken from the item validation request when the item is created within ControllerPresenterPack.AdminAlertManager
        this.owner = owner;
        this.inPossessionOf = owner;
    }

    /**
     * Initializes a new EntityPack.Item with a name and unique ID.
     * @param name the name of the item
     * @param itemID the unique ID of the item.
     */
    public Item(String name, int itemID, String owner){
        this.name = name;
        this.id = itemID;
        this.owner = owner;
        this.inPossessionOf = owner;
    }

    //getters

    /**
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the ID of the item
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return username of the user who is in possession of the item.
     */
    public String getInPossessionOf() {
        return inPossessionOf;
    }

    /**
     *
     * @return the username of the owner of the item
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the person who has the possession of the Item to be inPossessionOf
     * @param inPossessionOf username of the user who is in possession of the item via a temporary trade.
     */
    public void setInPossessionOf(String inPossessionOf) {
        this.inPossessionOf = inPossessionOf;
    }

    /**
     * Sets the owner of the Item to owner
     * @param owner username of the new owner of the item.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

}
