/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

/**
 *
 * @author Kyle's PC
 */
public abstract class Item {
    protected String name, itemDescription;
    protected int heal;
    protected float captureChance;
    protected int salePrice;
    protected int purchasePrice;
    
    public Item(String item){
        name = item;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return itemDescription;
    }

    public int getHeal() {
        return heal;
    }
}
