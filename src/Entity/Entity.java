/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import static GameState.PlayState.player;
import Graphics.Animation;
import Graphics.Sprite;
import Pokemon.Item;
import Pokemon.Pokemon;
import Utility.AABB;
import Utility.KeyHandler;
import Utility.Vector2d;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kyle's PC
 */
public abstract class Entity {

    private final int DOWN = 0;
    private final int UP = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    protected int currentAnimation;

    protected Sprite sprite;
    protected Sprite walkSprite;
    protected Sprite bagSprite;
    protected Sprite runSprite;
    protected Animation ani;
    protected Vector2d pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean interact;
    protected boolean sprint;
    protected boolean openBag;
    protected boolean closeBag;

    protected float dx;
    protected float dy;

    protected double maxSpeed = 1;
    protected double moveSpeed = 0.005;
    protected double stopSpeed = 100f;

    protected AABB hitBounds;
    protected AABB bounds;

    protected ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
    protected ArrayList<Item> playerBag = new ArrayList<Item>();

    public Entity(Sprite sprite, Vector2d origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2d(origin.x + (size / 2), origin.y), size, size);

        walkSprite = sprite;
        bagSprite = new Sprite("Sprites/playerbag.png", 38, 37);
        runSprite = new Sprite("Sprites/playerrunning.png", 38, 38);
        ani = new Animation();
        setAnimation(DOWN, sprite.getMainSpriteArray(DOWN), 10);
    }

    //Getters and Setters
    public void setSize(int i) {
        size = i;
    }

    public void setMoveSpeed(int i) {
        moveSpeed = i;
    }

    public void setStopSpeed(int i) {
        stopSpeed = i;
    }

    public AABB getBounds() {
        return bounds;
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return ani;
    }

    public Item getBagItem(int i) {
        return playerBag.get(i);
    }

    public int getXPosition() {
        return (int) ((2 * ((bounds.getPos().x + dx) + bounds.getXOff()) / 38));
    }

    public int getYPosition() {
        return (int) ((2 * (bounds.getPos().y + dy) + bounds.getYOff()) / 38);
    }

    //Cycles through sprite arrays for movements and various animation
    public void animate() {

        if (up) {
            if (currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getMainSpriteArray(UP), 10);
            }
        } else if (down) {
            if (currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getMainSpriteArray(DOWN), 10);
            }
        } else if (left) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getMainSpriteArray(RIGHT), 10);
            }
        } else if (right) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getMainSpriteArray(LEFT), 10);
            }
        } else if (openBag) {
            sprite = bagSprite;
            if (currentAnimation != 0 || ani.getDelay() == -1) {
                setAnimation(0, sprite.getMainSpriteArray(0), 10);
            }
        } else if (closeBag) {
            sprite = bagSprite;
            if (currentAnimation != 1 || ani.getDelay() == -1) {
                setAnimation(1, sprite.getMainSpriteArray(1), 10);
            }

        } else {
            setAnimation(currentAnimation, sprite.getMainSpriteArray(currentAnimation), -1);

        }
    }

    //Sets the direction of which an interaction occurs
    private void setInteractDirection() {
        if (up) {
            hitBounds.setXOff(-size / 2);
            hitBounds.setYOff(-size / 2);
        } else if (down) {
            hitBounds.setXOff(-size / 2);
            hitBounds.setYOff(-size / 2);
        } else if (right) {
            hitBounds.setXOff(0);
            hitBounds.setYOff(0);
        } else if (left) {
            hitBounds.setXOff(-size);
            hitBounds.setYOff(0);
        }
    }

    //Updates variables and objects associated with an entity
    public void update() {
        if (ani.animating == true) {
            animate();
            setInteractDirection();
            ani.update();
        }

        if (dx > maxSpeed) {
            dx = (float) maxSpeed;
        } else if (dx < ((-1) * maxSpeed)) {
            dx = (float) ((-1) * maxSpeed);
        }

        if (dy > maxSpeed) {
            dy = (float) maxSpeed;
        } else if (dy < ((-1) * maxSpeed)) {
            dy = (float) ((-1) * maxSpeed);
        }
    }

    public abstract void render(Graphics2D g);

    public void input(KeyHandler key) {

    }

    //Bag array list controls
    public void addBagItem(Item item) {
        playerBag.add(item);
    }

    public void useBagItem(int i) {
        playerBag.remove(i);
        player.bagSize = playerBag.size() - 1;
    }

    //Pokmon array list controls
    public Pokemon getPokemon(int i) {
        return pokemon.get(i);
    }

    public void addPokemon(Pokemon pkm) {
        pokemon.add(pkm);
    }

    public void swapPokemon(int i, int j) {
        Pokemon temp = pokemon.get(i);
        pokemon.set(i, pokemon.get(j));
        pokemon.set(j, temp);
    }
}
