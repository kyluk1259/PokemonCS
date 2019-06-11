/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import Graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import static Pokemon.Type.*;

/**
 *
 * @author Kyle's PC
 */
public class Pokedex {

    public static List<Pokemon> pokedex = new ArrayList<Pokemon>();
    private static Pokemon generated;
    private Type typing = new Type();

    public Pokedex() {
        loadPokemon();
    }

    //Add Types and Moves
    private void loadPokemon() {


        /*0. Pikachu*/ pokedex.add(new Pokemon("Pikachu", 122, 122, 80, 40, 104, 40, 122, 0, 50, 35.2f, Type.getType(ELECTRIC), new Sprite("Pokemon/pikachuFront.gif"), new Sprite("Pokemon/pikachuBack.gif")));
        /*1. Charizard*/ pokedex.add(new Pokemon("Charizard", 150, 150, 120, 104, 150, 100, 140, 0, 50, 11.9f, Type.getType(FIRE), Type.getType(FLYING), new Sprite("Pokemon/charizardFront.gif",200,200), new Sprite("Pokemon/charizardBack.gif", 200,200)));
 /*2. Weaville*/
 /*3. Scizor*/
 /*4. Tyrannitar*/
 /*5. Gyarados*/
 /*6. Swampert*/
 /*7. Snorlax*/
 /*8. Raichu*/
 /*9.*/
 /*10.*/
 /*11.*/
 /*12.*/
 /*13.*/
 /*14.*/
 /*15.*/
 /*16.*/
 /*17.*/
 /*18.*/
 /*19.*/
 /*20.*/
    }

    public static Pokemon generatePokemon(int pkmn) {

        switch (pkmn) {
            case 0:
                generated = (new Pokemon("Pikachu", 122, 122, 80, 40, 104, 40, 122, 0, 50, 35.2f, Type.getType(ELECTRIC), new Sprite("Pokemon/pikachuFront.gif"), new Sprite("Pokemon/pikachuBack.gif")));
                break;
            case 1:
                generated = (new Pokemon("Charizard", 150, 150, 120, 104, 150, 100, 140, 0, 50, 11.9f, Type.getType(FIRE), Type.getType(FLYING), new Sprite("Pokemon/charizardFront.gif",200,200), new Sprite("Pokemon/charizardBack.gif", 200,200)));
        }

        return generated;

    }
}
