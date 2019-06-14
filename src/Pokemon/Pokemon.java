/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemon;

import Graphics.Animation;
import Graphics.Sprite;
import Utility.Vector2d;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Kyle's PC
 */
public class Pokemon {

    private String pokemon;
    private int currentAnimation;
    private int maxHp, hp, att, def, spAtt, spDef, spd, exp, lvl;
    private double diff;
    private float evasion, accuracy, captureRate;
    private Move moves[];
    private Type typeA, typeB;
    private Status currentStatus;
    private BufferedImage fSprite, bSprite;
    private Animation ani;

    //One type, no status, remember to add type and moves once implemented
    public Pokemon(String name, int maxHealth, int health, int attack, int defense, int spAttack, int spDefense, int speed, int experience, int level, float capture, Type tA, Sprite sprite, Sprite backSprite) {
        pokemon = name;
        maxHp = maxHealth;
        hp = health;
        att = attack;
        def = defense;
        spAtt = spAttack;
        spDef = spDefense;
        spd = speed;
        exp = experience;
        lvl = level;
        captureRate = capture;
        typeA = tA;
        fSprite = sprite.getSpriteSheet();
        bSprite = backSprite.getSpriteSheet();
        ani = new Animation();
        diff = (float) (hp / maxHp);
    }

    //One type, status effect, add moveset
    public Pokemon(String name, int maxHealth, int health, int attack, int defense, int spAttack, int spDefense, int speed, int experience, int level, float capture, Type tA, Sprite sprite, Sprite backSprite, Status status) {
        pokemon = name;
        maxHp = maxHealth;
        hp = health;
        att = attack;
        def = defense;
        spAtt = spAttack;
        spDef = spDefense;
        spd = speed;
        exp = experience;
        lvl = level;
        captureRate = capture;
        //moves = moveset;
        typeA = tA;
        fSprite = sprite.getSpriteSheet();
        bSprite = backSprite.getSpriteSheet();
        currentStatus = status;
        ani = new Animation();
        diff = (hp / maxHp);
    }

    //Two types, no status, add moveset
    public Pokemon(String name, int maxHealth, int health, int attack, int defense, int spAttack, int spDefense, int speed, int experience, int level, float capture, Type tA, Type tB, Sprite sprite, Sprite backSprite) {
        pokemon = name;
        maxHp = maxHealth;
        hp = health;
        att = attack;
        def = defense;
        spAtt = spAttack;
        spDef = spDefense;
        spd = speed;
        exp = experience;
        lvl = level;
        captureRate = capture;
        //moves = moveset;
        typeA = tA;
        typeB = tB;
        fSprite = sprite.getSpriteSheet();
        bSprite = backSprite.getSpriteSheet();
        ani = new Animation();
        diff = (hp / maxHp);
    }

    //Two types, status effect
    public Pokemon(String name, int maxHealth, int health, int attack, int defense, int spAttack, int spDefense, int speed, int experience, int level, Move moveset[], float capture, Type tA, Type tB, Sprite frontSprite, Sprite backSprite, Status status) {
        pokemon = name;
        maxHp = maxHealth;
        hp = health;
        att = attack;
        def = defense;
        spAtt = spAttack;
        spDef = spDefense;
        spd = speed;
        exp = experience;
        lvl = level;
        captureRate = capture;
        moves = moveset;
        typeA = tA;
        typeB = tB;
        fSprite = frontSprite.getSpriteSheet();
        bSprite = backSprite.getSpriteSheet();
        currentStatus = status;
        ani = new Animation();
        diff = (hp / maxHp);
    }

    //All getters are below
    public String getName() {
        return pokemon;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAtt() {
        return att;
    }

    public int getDef() {
        return def;
    }

    public int getSpAtt() {
        return spAtt;
    }

    public int getSpDef() {
        return spDef;
    }

    public int getSpd() {
        return spd;
    }

    public int getExp() {
        return exp;
    }

    public int getLvl() {
        return lvl;
    }

    public float getEvasion() {
        return evasion;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public float getCaptureRate() {
        return captureRate;
    }

    public Move getMove(int i) {
        return moves[i];
    }

    public Type getTypeA() {
        return typeA;
    }

    public Type getTypeB() {
        return typeB;
    }

    public Status getStatus() {
        return currentStatus;
    }

    public BufferedImage getSprite(int i) {
        if (i == 0) {
            return fSprite;
        } else {
            return bSprite;
        }
    }

    public double getHpDiff() {
        double dhp = (double) hp;
        double dmhp = (double) maxHp;
        diff = dhp / dmhp;
        return diff;
    }
    //-------------------------------------------------------------

    //All setters are below
    public void setMaxHp(int diff) {
        maxHp += diff;
    }

    public void setHp(int diff) {
        hp += diff;
    }

    public void setAtt(int diff) {
        att += diff;
    }

    public void setDef(int diff) {
        def += diff;
    }

    public void setSpAtt(int diff) {
        spAtt += diff;
    }

    public void setSpDef(int diff) {
        spDef += diff;
    }

    public void setSpd(int diff) {
        spd += diff;
    }

    public void setExp(int gain) {
        exp += gain;
    }

    public void setLvl(int increase) {
        lvl += increase;
    }

    public void setEvasion(float evas) {
        evasion = evasion * evas;
    }

    public void setAccuracy(float acc) {
        accuracy = accuracy * acc;
    }

    public void setStatus(Status status) {
        currentStatus = status;
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }
    //------------------------------------------------------

    public void render(Graphics2D g, int i) {
        if (i == 0) {
            
            if(this.pokemon.equalsIgnoreCase("pikachu")){
            Sprite.drawImage(g, this.getSprite(i), new Vector2d(560, 180), 130, 140);
            }
            
            if(this.pokemon.equalsIgnoreCase("charizard")){
                Sprite.drawImage(g, this.getSprite(i), new Vector2d(550, 110), 160, 210);
            }
            
            if(this.pokemon.equalsIgnoreCase("weavile")){
            Sprite.drawImage(g, this.getSprite(i), new Vector2d(560, 140), 130, 160);
            }
            
             if(this.pokemon.equalsIgnoreCase("scizor")){
            Sprite.drawImage(g, this.getSprite(i), new Vector2d(560, 110), 130, 180);
            }
            
        } else {
            
            if (this.pokemon.equalsIgnoreCase("pikachu")) {
                Sprite.drawImage(g, this.getSprite(i), new Vector2d(130, 300), 160, 180);
            } 
            
            if (this.pokemon.equalsIgnoreCase("charizard")){
                Sprite.drawImage(g, this.getSprite(i), new Vector2d(105, 230), 300, 250);
            }
            
            if(this.pokemon.equalsIgnoreCase("weavile")){
            Sprite.drawImage(g, this.getSprite(i), new Vector2d(130, 280), 180, 200);
            }
            
            if(this.pokemon.equalsIgnoreCase("scizor")){
            Sprite.drawImage(g, this.getSprite(i), new Vector2d(130, 230), 210, 230);
            }
        }
    }
}
