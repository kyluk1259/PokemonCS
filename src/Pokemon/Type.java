/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import java.util.ArrayList;

/**
 *
 * @author Kyle's PC
 */
public class Type {

    public static final int NORMAL = 0;
    public static final int ELECTRIC = 1;
    public static final int FIRE = 2;
    public static final int FLYING = 3;
    public static final int ICE = 4;
    public static final int DARK = 5;
    public static final int BUG = 6;
    public static final int STEEL = 7;
    public static final int ROCK = 8;
    public static final int WATER = 9;
    public static final int GROUND = 10;
    public static final int GHOST = 11;
    public static final int PSYCHIC = 12;
    public static final int DRAGON = 13;
    public static final int FIGHT = 14;
    public static final int GRASS = 15;
    private static final ArrayList<Type> types = new ArrayList();
    private String typeName;
    private int typeValue;

    public Type() {
        initializeTypes();
    }

    public Type(int id) {
        switch (id) {
            case 0:
                typeValue = id;
                typeName = "Normal";
                break;

            case 1:
                typeValue = id;
                typeName = "Electric";
                break;

            case 2:
                typeValue = id;
                typeName = "Fire";
                break;

            case 3:
                typeValue = id;
                typeName = "Flying";
                break;

            case 4:
                typeValue = id;
                typeName = "Ice";
                break;

            case 5:
                typeValue = id;
                typeName = "Dark";
                break;

            case 6:
                typeValue = id;
                typeName = "Bug";
                break;

            case 7:
                typeValue = id;
                typeName = "Steel";
                break;

            case 8:
                typeValue = id;
                typeName = "Rock";
                break;

            case 9:
                typeValue = id;
                typeName = "Water";
                break;

            case 10:
                typeValue = id;
                typeName = "Ground";
                break;

            case 11:
                typeValue = id;
                typeName = "Ghost";
                break;

            case 12:
                typeValue = id;
                typeName = "Psychic";
                break;

            case 13:
                typeValue = id;
                typeName = "Dragon";
                break;

            case 14:
                typeValue = id;
                typeName = "Fight";
                break;

            case 15:
                typeValue = id;
                typeName = "Grass";
                break;
        }
    }

    private void initializeTypes() {
        for (int i = 0; i < 15; i++) {
            types.add(new Type(i));
        }
    }
    
    public static Type getType(int i){
        return types.get(i);
    }
}
