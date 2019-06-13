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
public class Pokeballs extends Item {

    public Pokeballs(String item) {
        super(item);
        if (item.equalsIgnoreCase("Ultra Ball")) {
            newUltraBall();
        } else if (item.equalsIgnoreCase("Master Ball")) {
            newMasterBall();
        }
    }

    private void newUltraBall() {
        itemDescription = "Higher Catch\nRate than a\ngreat ball.";
        captureChance = 2;
    }

    private void newMasterBall() {
        itemDescription = "Guaranteed\ncapture of\n a pokemon.";
        captureChance = 2;
    }

    public void useItem(Pokemon p) {
        float capture = p.getCaptureRate();
        
        capture = capture*captureChance;
    }
}
