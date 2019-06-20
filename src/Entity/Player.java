/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import GameState.GameStateManager;
import static GameState.GameStateManager.BATTLESTATE;
import static GameState.GameStateManager.MENUSTATE;
import GameState.PlayState;
import Graphics.Sprite;
import Pokemon.Pokeballs;
import static Pokemon.Pokedex.pokedex;
import Pokemon.Potions;
import Utility.KeyHandler;
import Utility.Vector2d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Kyle's PC
 */
public class Player extends Entity {

    private boolean pause, back;
    private Image renderImage;

    private GameStateManager gsm;
    private final int OPENBAG = 0;
    private final int CLOSEBAG = 1;
    public int bagSize, pokemonCount;

    public Player(Sprite sprite, Vector2d origin, int size, GameStateManager gs) {
        super(sprite, origin, size);
        gsm = gs;
        this.addPokemon(pokedex.get(0));
        this.addPokemon(pokedex.get(1));
        this.addPokemon(pokedex.get(1));
        this.addBagItem(new Pokeballs("Ultra Ball"));
        this.addBagItem(new Potions("Hyper Potion"));
        this.addBagItem(new Pokeballs("Master Ball"));
        this.addBagItem(new Potions("Full Restore"));
        this.addBagItem(new Pokeballs("Ultra Ball"));
        this.addBagItem(new Potions("Hyper Potion"));
        this.addBagItem(new Pokeballs("Master Ball"));
        this.addBagItem(new Potions("Full Restore"));
        this.addBagItem(new Pokeballs("Ultra Ball"));
        this.addBagItem(new Potions("Hyper Potion"));
        this.addBagItem(new Pokeballs("Master Ball"));
        this.addBagItem(new Potions("Full Restore"));
        this.addBagItem(new Pokeballs("Ultra Ball"));
        this.addBagItem(new Potions("Hyper Potion"));
        this.addBagItem(new Pokeballs("Master Ball"));
        this.addBagItem(new Potions("Full Restore"));
        bagSize = playerBag.size() - 1;
        pokemonCount = pokemon.size() - 1;
        bounds.setWidth(30);
        bounds.setHeight(30);
        bounds.setXOff(24);
        bounds.setYOff(38);
    }

    public void move() {
        if (sprint) {
            sprite = runSprite;
            moveSpeed = 0.05;
            maxSpeed = 1.5;
        } else {
            maxSpeed = 1;
            moveSpeed = 0.005;
        }

        if (up) {
            dy -= moveSpeed;
        } else {
            if (dy < 0) {
                dy += stopSpeed;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }

        if (down) {
            dy += moveSpeed;
        } else {
            if (dy > 0) {
                dy -= stopSpeed;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (right) {
            dx += moveSpeed;
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }

        if (left) {
            dx -= moveSpeed;
        } else {
            if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        //ADD
        if (openBag) {
            pause = true;

            if (ani.hasPlayed(3)) {
                ani.stopAnimating();
                openBag = false;
                gsm.add(MENUSTATE);
            }
        }

        if (interact && PlayState.loadText == false) {
            PlayState.index = 0;
            PlayState.text = "Hello and welcome to the world of pokekaune. Maps and Pokemon will be added soon.";
            PlayState.textComplete = false;
            PlayState.loadText = true;
            interact = false;
        }

        if (back && PlayState.textComplete == true && !pause) {
            PlayState.loadText = false;
            interact = false;
            back = false;
        } else if (back && PlayState.textComplete == false && !pause) {
            back = false;
        }

        if (back && pause) {

            ani.startAnimating();
            closeBag = true;
            animate();

            if (ani.hasPlayed(2)) {
                pause = false;
                back = false;
                closeBag = false;
                sprite = walkSprite;
            }
        }
    }

    public void update() {
        super.update();
        move();
        //bounds.update();
        if (!bounds.collisionTile(dx, 0) && !bounds.outsideMap(dx, 0)) {
            PlayState.world.x += dx;
            pos.x += dx;
        }

        if (!bounds.collisionTile(0, dy) && !bounds.outsideMap(0, dy)) {
            PlayState.world.y += dy;
            pos.y += dy;
        }
        renderImage = ani.getImage();
    }

    public void input(KeyHandler key) {

        if (key.up.down && !pause) {
            up = true;
        } else {
            up = false;
        }

        if (key.down.down && !pause) {
            down = true;
        } else {
            down = false;
        }

        if (key.right.down && !pause) {
            right = true;
        } else {
            right = false;
        }

        if (key.left.down && !pause) {
            left = true;
        } else {
            left = false;
        }

        if (key.A.clicked && !pause) {
            interact = true;
            PlayState.pause = true;
            gsm.add(BATTLESTATE);
        }

        if (key.menu.clicked) {
            openBag = true;
        }

        if (key.B.clicked) {
            back = true;
        }

        if (key.B.down && !sprint) {
            sprint = true;
        }

        if (!key.B.down && sprint) {
            sprint = false;
            sprite = walkSprite;
        }

    }

    public void render(Graphics2D g) {
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOff()), (int) (pos.getWorldVar().y + bounds.getYOff()), (int)bounds.getWidth(), (int)bounds.getHeight());
        g.drawImage(renderImage, (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }
}
