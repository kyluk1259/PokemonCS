/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import static GameState.PlayState.player;

/**
 *
 * @author Kyle's PC
 */
public class Potions extends Item {
    
    public Potions(String item){
        super(item);
    if(item.equalsIgnoreCase("Hyper Potion")){
            newHyperPotion();
        }else if(item.equalsIgnoreCase("Full Restore")){
            newFullRestore();
        }
    }
    
    private void newHyperPotion(){
        itemDescription = "Heal a pokemon\nfor 200HP.";
        heal = 200;
    }
    
    private void newFullRestore(){
        itemDescription = "Fully heal\nand cure\n a pokemon.";
        heal = 1000;
    }
}
