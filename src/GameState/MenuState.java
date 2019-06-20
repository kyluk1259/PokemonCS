/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Player;
import static GameState.PlayState.player;
import Graphics.Font;
import Graphics.Sprite;
import Pokemon.Pokemon;
import Utility.KeyHandler;
import Utility.Vector2d;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kyle's PC
 */
public class MenuState extends GameState {

    //private Font font;
    private int flash, pokemonCount;
    private int selection;
    private int sele;
    private Sprite pokeBall, bag, save, exit;
    private Font Sprites;
    private int bagItem;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        flash = 0;
        sele = 0;
        selection = 1;
        bagItem = 0;

        pokeBall = new Sprite("Images/pokemonBall.png");
        bag = new Sprite("Images/bag.png");
        save = new Sprite("Images/save.png");
        exit = new Sprite("Images/exit.png");
        String num = "num";
        System.out.println(num.length());
    }

    @Override
    public void update() {

        if (flash != 5) {
            flash++;
        } else {
            flash = 0;
        }
    }

    @Override
    public void input(KeyHandler key) {

        System.out.println(selection);

        if (sele == 0) {
            if (key.down.clicked) {
                if (selection < 4) {
                    selection++;
                } else {
                    selection = 1;
                }

                key.down.clicked = false;
            }

            if (key.up.clicked) {
                if (selection > 1) {
                    selection--;
                } else {
                    selection = 4;
                }
                key.up.clicked = false;
            }

            if (key.A.clicked) {
                switch (selection) {
                    case 1:
                        sele = 1;
                        break;
                    case 2:
                        sele = 2;
                        break;
                    case 4:
                        PlayState.pause = false;
                        Player.unpause = true;
                        gsm.pop(1);
                        break;
                    default:
                        break;
                }
                key.A.clicked = false;
            }

            if (key.B.clicked) {
                if (sele > 0) {
                    sele = 0;
                } else {
                    PlayState.pause = false;
                    Player.unpause = true;
                    gsm.pop(1);
                }
                key.B.clicked = false;
            }
        }

        if (sele == 1) {
            if (key.up.clicked) {
                if (pokemonCount - 1 < 0) {
                    pokemonCount = player.pokemonCount;
                } else {
                    pokemonCount -= 1;
                    System.out.println(pokemonCount);
                }
                key.up.clicked = false;
            }

            if (key.down.clicked) {
                if (pokemonCount + 1 > player.pokemonCount) {
                    pokemonCount = 0;
                } else {
                    pokemonCount += 1;
                    System.out.println(pokemonCount);
                }
                key.down.clicked = false;
            }

            if (key.right.clicked && pokemonCount == 0) {
                pokemonCount = 1;
                key.right.clicked = false;
            }

            if (key.left.clicked && pokemonCount != 0) {
                pokemonCount = 0;
                key.left.clicked = false;
            }

            if (key.A.clicked) {
                player.swapPokemon(0, pokemonCount);
                key.A.clicked = false;
            }

            if (key.B.clicked) {
                sele = 0;
                selection = 1;
                key.B.clicked = false;
            }
        }

        if (sele == 2) {
            if (key.up.clicked) {
                if (bagItem - 1 <= 0) {
                    bagItem = 0;
                } else {
                    bagItem -= 1;
                }
                key.up.clicked = false;
            }

            if (key.down.clicked) {
                if (bagItem + 1 >= player.bagSize) {
                    bagItem = player.bagSize;
                    System.out.println("clicked");
                } else {
                    bagItem += 1;
                    System.out.println(bagItem);
                }
                key.down.clicked = false;
            }

            if (key.B.clicked) {
                sele = 0;
                selection = 1;
                key.B.clicked = false;
            }
        }
    }

    public void render(Graphics2D g) {
        //Rectangle
        g.setColor(Color.white);
        g.fillRect(550, 0, 300, 640);

        if (sele == 0) {
            menuBox(g);
        }

        if (sele == 1) {
            pokemonBox(g);
        }

        if (sele == 2) {
            bagBox(g);
        }
    }

    private void menuBox(Graphics2D g) {

        //ICONS
        Sprite.drawImage(g, pokeBall, new Vector2d(750, 160), 30, 30);
        Sprite.drawImage(g, bag, new Vector2d(640, 215), 35, 30);
        Sprite.drawImage(g, save, new Vector2d(670, 280), 25, 25);
        Sprite.drawImage(g, exit, new Vector2d(670, 340), 25, 25);

        //Text        
        Sprite.drawArray(g, font, "Menu", new Vector2d(605, 75), 52, 52, 40, 0);
        Sprite.drawArray(g, font, "Pokemon", new Vector2d(560, 160), 30, 30, 26, 0);
        Sprite.drawArray(g, font, "Bag", new Vector2d(560, 220), 30, 30, 26, 0);
        Sprite.drawArray(g, font, "Save", new Vector2d(560, 280), 30, 30, 26, 0);
        Sprite.drawArray(g, font, "Exit", new Vector2d(560, 340), 30, 30, 26, 0);

        //Selection
        switch (selection) {
            case 1:
                g.setColor(Color.black);
                if (flash != 0 && flash != 1) {
                    g.drawLine(560, 200, 780, 200);
                }
                break;
            case 2:
                g.setColor(Color.black);
                if (flash != 0 && flash != 1) {
                    g.drawLine(560, 260, 780, 260);
                }
                break;
            case 3:
                g.setColor(Color.black);
                if (flash != 0 && flash != 1) {
                    g.drawLine(560, 320, 780, 320);
                }
                break;
            case 4:
                g.setColor(Color.black);
                if (flash != 0 && flash != 1) {
                    g.drawLine(560, 380, 780, 380);
                }
                break;
        }
    }

    private void bagBox(Graphics2D g) {
        g.setColor(Color.black);
        Sprite.drawArray(g, font, "Bag", new Vector2d(630, 80), 34, 34, 30, 0);
        //g.drawLine(300, 480, 300, 640);
        if (flash != 0 && flash != 1) {
            g.drawLine(560, 190, 620 + player.getBagItem(bagItem).getName().length() * 19, 190);
            Sprite.drawArray(g, font, player.getBagItem(bagItem).getName(), new Vector2d(560, 170), 24, 24, 20, 0);
        }

        g.setColor(Color.white);
        g.fillRect(0, 450, 300, 190);
        Sprite.drawArray(g, font, player.getBagItem(bagItem).getDescription(), new Vector2d(5, 470), 24, 24, 20, 0);

        if (bagItem + 1 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 1).getName(), new Vector2d(560, 230), 24, 24, 20, 0);
        }
        if (bagItem + 2 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 2).getName(), new Vector2d(560, 260), 24, 24, 20, 0);
        }
        if (bagItem + 3 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 3).getName(), new Vector2d(560, 290), 24, 24, 20, 0);
        }
    }

    private void pokemonBox(Graphics2D g) {

        g.setColor(Color.black);
        Sprite.drawArray(g, font, "Pokemon", new Vector2d(590, 80), 34, 34, 30, 0);
        if (flash != 0 && flash != 1) {

            Sprite.drawArray(g, font, player.getPokemon(pokemonCount).getName(), new Vector2d(560, 150 + ((40 * (pokemonCount)))), 22, 22, 22, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(pokemonCount).getLvl(), new Vector2d(760, 153 + ((40 * (pokemonCount)))), 15, 15, 15, 0);

        }
        if (player.pokemonCount >= 0 && pokemonCount != 0) {
            Sprite.drawArray(g, font, player.getPokemon(0).getName(), new Vector2d(560, 150), 22, 22, 22, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(0).getLvl(), new Vector2d(760, 153), 15, 15, 15, 0);
        }
        if (player.pokemonCount >= 1 && pokemonCount != 1) {
            Sprite.drawArray(g, font, player.getPokemon(1).getName(), new Vector2d(560, 190), 22, 22, 22, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(1).getLvl(), new Vector2d(760, 193), 15, 15, 15, 0);
        }
        if (player.pokemonCount >= 2 && pokemonCount != 2) {
            Sprite.drawArray(g, font, player.getPokemon(2).getName(), new Vector2d(560, 230), 22, 22, 22, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(2).getLvl(), new Vector2d(760, 233), 15, 15, 15, 0);
        }
        if (player.pokemonCount >= 3 && pokemonCount != 3) {
            Sprite.drawArray(g, font, player.getPokemon(3).getName(), new Vector2d(560, 270), 22, 22, 20, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(3).getLvl(), new Vector2d(760, 273), 15, 15, 15, 0);
        }
        if (player.pokemonCount >= 4 && pokemonCount != 4) {
            Sprite.drawArray(g, font, player.getPokemon(4).getName(), new Vector2d(560, 310), 20, 20, 20, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(4).getLvl(), new Vector2d(760, 313), 15, 15, 15, 0);
        }
        if (player.pokemonCount >= 5 && pokemonCount != 5) {
            Sprite.drawArray(g, font, player.getPokemon(5).getName(), new Vector2d(560, 350), 20, 20, 20, 0);
            Sprite.drawArray(g, font, "Lvl" + player.getPokemon(1).getLvl(), new Vector2d(760, 353), 15, 15, 15, 0);
        }
    }
}
