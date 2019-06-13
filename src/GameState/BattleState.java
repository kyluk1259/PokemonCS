/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import static GameState.PlayState.player;
import Graphics.Background;
import Graphics.Sprite;
import Pokemon.Pokeballs;
import Pokemon.Pokedex;
import static Pokemon.Pokedex.pokedex;
import Pokemon.Pokemon;
import Pokemon.Potions;
import Utility.KeyHandler;
import Utility.Vector2d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kyle's PC
 */
public class BattleState extends GameState {

    private boolean isStarted, loadBattle, interact, inMenu, displayingText, fightMenu, pokemonMenu, bagMenu;
    private Background battleBackground, currentBackground;
    private int color, count, index, scale, textChoice, flash, bagItem, x, y, pokemonCount;
    private int[][] currentSelection = new int[2][2];
    private final int BATTLE = 0;
    private final int PLAYER = 1;
    private String text, lastMoveUsed;
    private String[] strings;
    private Pokemon wildPokemon, playerPokemon;
    private boolean useItem;

    public BattleState(GameStateManager gsm) {
        super(gsm);
        isStarted = false;
        scale = 0;
        count = 50;
        battleBackground = new Background("Backgrounds/battlescenes.png", 257, 145, 1, 147);
        currentBackground = battleBackground;
        text = "You are challenged by Kyle.";

        System.out.println("New Battle: Loading Assets...\n_____________________________\n");
        isStarted = false;
        inMenu = false;
        fightMenu = false;
        pokemonMenu = false;
        bagMenu = false;
        displayingText = true;
        useItem = false;

        scale = 0;
        count = 50;
        textChoice = 0;
        flash = 0;
        bagItem = 0;
        x = 0;
        y = 0;
        currentSelection[0][0] = 0;
        currentSelection[1][0] = 1;
        currentSelection[0][1] = 2;
        currentSelection[1][1] = 3;
        pokemonCount = 0;

        battleBackground = new Background("Backgrounds/battlescenes.png", 257, 145, 1, 147);
        currentBackground = battleBackground;

        playerPokemon = player.getPokemon(0);
        wildPokemon =  Pokedex.generatePokemon(1);

        //lastMoveUsed = playerPokemon.getMove(0).getName();
        strings = new String[]{"You are challenged by PKMN Trainer Kyle.",
            "PKMN Trainer Kyle sent out " + wildPokemon.getName() + " !",
            "Go " + playerPokemon.getName() + " !", ""};
        text = strings[textChoice];
    }

    @Override
    public void update() {
        if (count != 255 && isStarted == false) {
            count++;
        } else {
            count = 0;
        }

        color = count;

        if (loadBattle == true) {
            if (scale < 840) {
                scale += 5;
            } else {
                scale = 0;
                isStarted = true;
                loadBattle = false;
            }

            if (count != 254 && isStarted == false) {
                count += 2;
            } else {
                count = 0;
                loadBattle = true;
            }

            color = count;

            if (loadBattle == true) {
                if (scale <= 840) {
                    scale += 10;
                } else {
                    scale = 0;
                    loadBattle = false;
                    isStarted = true;
                }
            }

            if (flash != 5) {
                flash++;
            } else {
                flash = 0;

            }
        }
    }

    public void input(KeyHandler key) {
        if (key.B.clicked && !inMenu) {
            inMenu = true;
            fightMenu = false;
            bagMenu = false;
            pokemonMenu = false;
            key.B.clicked = false;
        }
        
        if (displayingText) {
            if (key.A.clicked) {
                interact = true;
                System.out.println(player.bagSize);
            } else {
                interact = false;
            }
        }

        if (inMenu) {
            if (key.A.clicked) {
                interact = true;
                System.out.println(player.bagSize);
            } else {
                interact = false;
            }

            if (key.up.clicked) {
                if (y - 1 <= 0) {
                    y = 0;
                } else {
                    y -= 1;
                }
                playerPokemon.setHp(2);
                System.out.println(playerPokemon.getHp() + " : " + playerPokemon.getMaxHp());
                key.up.clicked = false;
            }
            if (key.down.clicked) {
                if (y + 1 >= 1) {
                    y = 1;
                } else {
                    y += 1;
                }
                playerPokemon.setHp(-2);
                System.out.println(playerPokemon.getHp() + " : " + playerPokemon.getMaxHp() + " : " + playerPokemon.getHpDiff());
                key.down.clicked = false;
            }
            if (key.left.clicked) {
                if (x - 1 <= 0) {
                    x = 0;
                } else {
                    x -= 1;
                }
                key.left.clicked = false;
            }
            if (key.right.clicked) {
                if (x + 1 >= 1) {
                    x = 1;
                } else {
                    x += 1;
                }
                key.right.clicked = false;
            }
        }
        
        if(useItem){
            if (key.A.clicked) {
                player.getBagItem(bagItem).useItem(pokemonCount);
                player.removeBagItem(bagItem);
                player.bagSize -= 1;
                useItem = false;
                inMenu = true;
                key.A.clicked = false;
            }
            
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
        }

        if (bagMenu) {
            
            if (key.A.clicked) {
                if (player.getBagItem(bagItem).getClass().equals(Potions.class)){
                    useItem = true;
                    bagMenu = false;
                    key.A.clicked = false;
                }else if(player.getBagItem(bagItem).getClass().equals(Pokeballs.class)){
                    player.getBagItem(bagItem).useItem(wildPokemon);
                    player.removeBagItem(bagItem);
                    player.bagSize -= 1;
                    bagMenu = false;
                    inMenu = true;
                }
                key.A.clicked = false;
            }
            
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
                } else {
                    bagItem += 1;
                    System.out.println(bagItem);
                }
                key.down.clicked = false;
            }
        }

        if (pokemonMenu) {
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
            
            if(key.A.clicked){
                playerPokemon = player.getPokemon(pokemonCount);
                player.swapPokemon(0, pokemonCount);
                inMenu = true;
                pokemonMenu = false;
                key.A.clicked = false;
            }
        }
    }

    public void render(Graphics2D g) {
        if (isStarted == false && loadBattle == false) {
            g.setColor(new Color(color, color, color));
            g.fillRect(0, 0, 840, 640);
            g.setColor(Color.white);
            g.fillRect(0, 480, 840, 160);
            Sprite.drawText(g, font, text, new Vector2d(25, 510), 24, 24, 20, 0, index);
            if (index == text.length()) {
                index = text.length();
                loadBattle = true;
            } else {
                index++;
            }
        } else if (isStarted == false && loadBattle == true) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 840, 640);
            g.setColor(Color.white);
            g.fillRect(0 + scale / 2, 0 + scale / 2, 840 - scale, 640 - scale);
        }

        if (isStarted == true) {
            Sprite.drawText(g, font, text, new Vector2d(25, 510), 24, 24, 20, 0, index);
            if (index == text.length()) {
                index = text.length();
            } else {
                index++;
            }
            Sprite.drawImage(g, currentBackground.getBackground(), new Vector2d(0, 0), 840, 640);
            //Start of battle
            if (!isStarted && !loadBattle) {
                g.setColor(new Color(color, color, color));
                g.fillRect(0, 0, 840, 640);

            } else if (!isStarted && loadBattle) {
                g.setColor(Color.black);
                g.fillRect(0, 0, 840, 640);
                g.setColor(Color.white);
                g.fillRect(0 + scale / 2, 0 + scale / 2, 840 - scale, 640 - scale);
            }

            if (isStarted) {

                //Draw background
                Sprite.drawImage(g, currentBackground.getBackground(), new Vector2d(0, 0), 840, 480);
                g.setColor(Color.black);
                g.fillRect(0, 480, 840, 160);
                g.setColor(Color.white);
                g.fillRoundRect(0, 480, 840, 160, 50, 50);

                //Draw text
                if (displayingText == true) {
                    textBox(g);
                }

                if (inMenu == true) {
                    playerBar(g);
                    opponentBar(g);
                    menuBox(g);
                    if (currentSelection[x][y] == 0 && interact) {
                        fightMenu = true;
                        inMenu = false;
                    }

                    if (currentSelection[x][y] == 1 && interact) {
                        bagMenu = true;
                        inMenu = false;
                    }
                    if (currentSelection[x][y] == 2 && interact) {
                        pokemonMenu = true;
                        inMenu = false;
                    }
                    if (currentSelection[x][y] == 3 && interact) {
                        gsm.pop(1);
                        try {
                            TimeUnit.MILLISECONDS.sleep(200
                            );
                        } catch (InterruptedException e) {
                            System.out.println("BattleState ERROR: Thread Interruption On Battle Exit");
                        }
                        PlayState.pause = false;
                        inMenu = false;
                    }
                }

                if (fightMenu == true) {
                    playerBar(g);
                    opponentBar(g);
                    //moveBox(g);
                }

                if (bagMenu == true) {
                    playerBar(g);
                    opponentBar(g);
                    bagBox(g);

                }

                if (pokemonMenu == true) {
                    playerBar(g);
                    opponentBar(g);
                    pokemonBox(g);
                }
                
                if(useItem == true){
                    playerBar(g);
                    opponentBar(g);
                    pokemonBox(g);
                }

                //Draw pokemon
                wildPokemon.render(g, BATTLE);
                playerPokemon.render(g, PLAYER);

            }
        }
    }

    private void textBox(Graphics2D g) {

        Sprite.drawText(g, font, text, new Vector2d(25, 510), 24, 24, 20, 0, index);
        if (index == text.length() && interact && !inMenu) {
            textChoice += 1;
            if (textChoice == 3) {
                interact = false;
                inMenu = true;
                playerBar(g);
                opponentBar(g);
                displayingText = false;
            } else {
                text = strings[textChoice];
                index = 0;
            }
        } else if (index == text.length()) {
            if (flash != 0 && flash != 1) {
                index = text.length();
                Sprite.drawArray(g, font, "...", new Vector2d(760, 600), 24, 24, 20, 0);
            }
        } else {
            index++;
        }
    }

    private void menuBox(Graphics2D g) {
        g.setColor(Color.black);
        g.drawLine(300, 480, 300, 640);

        Sprite.drawArray(g, font, "What will", new Vector2d(25, 535), 24, 24, 20, 0);
        Sprite.drawArray(g, font, playerPokemon.getName() + " do?", new Vector2d(25, 565), 24, 24, 20, 0);

        if (currentSelection[x][y] == 0) {
            if (flash != 0 && flash != 1) {
                Sprite.drawArray(g, font, "Fight", new Vector2d(325, 520), 24, 24, 20, 0);
            }
        } else {
            Sprite.drawArray(g, font, "Fight", new Vector2d(325, 520), 24, 24, 20, 0);
        }

        if (currentSelection[x][y] == 1) {
            if (flash != 0 && flash != 1) {
                Sprite.drawArray(g, font, "Bag", new Vector2d(625, 520), 24, 24, 20, 0);
            }
        } else {
            Sprite.drawArray(g, font, "Bag", new Vector2d(625, 520), 24, 24, 20, 0);
        }

        if (currentSelection[x][y] == 2) {
            if (flash != 0 && flash != 1) {
                Sprite.drawArray(g, font, "Pokemon", new Vector2d(325, 580), 24, 24, 20, 0);
            }
        } else {
            Sprite.drawArray(g, font, "Pokemon", new Vector2d(325, 580), 24, 24, 20, 0);
        }

        if (currentSelection[x][y] == 3) {
            if (flash != 0 && flash != 1) {
                Sprite.drawArray(g, font, "Run", new Vector2d(625, 580), 24, 24, 20, 0);
            }
        } else {
            Sprite.drawArray(g, font, "Run", new Vector2d(625, 580), 24, 24, 20, 0);
        }
    }

    /*
     public void moveBox(Graphics2D g) {
     Sprite.drawArray(g, font, playerPokemon.getMove(0).getName(), new Vector2d(100, 520), 24, 24, 20, 0);
     Sprite.drawArray(g, font, playerPokemon.getMove(1).getName(), new Vector2d(540, 520), 24, 24, 20, 0);
     Sprite.drawArray(g, font, playerPokemon.getMove(2).getName(), new Vector2d(100, 580), 24, 24, 20, 0);
     Sprite.drawArray(g, font, playerPokemon.getMove(3).getName(), new Vector2d(540, 580), 24, 24, 20, 0);
     }
     */
    private void bagBox(Graphics2D g) {
        g.setColor(Color.black);
        g.drawLine(300, 480, 300, 640);
        if (flash != 0 && flash != 1) {
            g.drawLine(540, 525, 540 + player.getBagItem(bagItem).getName().length() * 19, 525);
            Sprite.drawArray(g, font, player.getBagItem(bagItem).getName(), new Vector2d(540, 500), 24, 24, 20, 0);
        }
        Sprite.drawArray(g, font, player.getBagItem(bagItem).getDescription(), new Vector2d(5, 500), 24, 24, 20, 0);
        if (bagItem + 1 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 1).getName(), new Vector2d(540, 535), 24, 24, 20, 0);
        }
        if (bagItem + 2 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 2).getName(), new Vector2d(540, 570), 24, 24, 20, 0);
        }
        if (bagItem + 3 <= player.bagSize) {
            Sprite.drawArray(g, font, player.getBagItem(bagItem + 3).getName(), new Vector2d(540, 605), 24, 24, 20, 0);
        }
    }

    private void pokemonBox(Graphics2D g) {

        g.setColor(Color.black);
        g.drawLine(390, 480, 390, 640);
        if (flash != 0 && flash != 1) {
            if (pokemonCount == 0) {
                Sprite.drawArray(g, font, player.getPokemon(pokemonCount).getName(), new Vector2d(150, 500), 24, 24, 20, 0);
                Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(0).getLvl(), new Vector2d(15, 500), 20, 20, 20, 0);
            } else {
                Sprite.drawArray(g, font, player.getPokemon(pokemonCount).getName(), new Vector2d(540, 500 + ((25 * (pokemonCount - 1)))), 24, 24, 20, 0);
                Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(pokemonCount).getLvl(), new Vector2d(405, 500 + ((25 * (pokemonCount - 1)))), 20, 20, 20, 0);
            }
        }
        if (player.pokemonCount >= 0 && pokemonCount != 0) {
            Sprite.drawArray(g, font, player.getPokemon(0).getName(), new Vector2d(150, 500), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(0).getLvl(), new Vector2d(15, 500), 20, 20, 20, 0);
        }
        if (player.pokemonCount >= 1 && pokemonCount != 1) {
            Sprite.drawArray(g, font, player.getPokemon(1).getName(), new Vector2d(540, 500), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(1).getLvl(), new Vector2d(405, 500), 20, 20, 20, 0);
        }
        if (player.pokemonCount >= 2 && pokemonCount != 2) {
            Sprite.drawArray(g, font, player.getPokemon(2).getName(), new Vector2d(540, 525), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(2).getLvl(), new Vector2d(405, 525), 20, 20, 20, 0);
        }
        if (player.pokemonCount >= 3 && pokemonCount != 3) {
            Sprite.drawArray(g, font, player.getPokemon(3).getName(), new Vector2d(540, 550), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(3).getLvl(), new Vector2d(405, 550), 20, 20, 20, 0);
        }
        if (player.pokemonCount >= 4 && pokemonCount != 4) {
            Sprite.drawArray(g, font, player.getPokemon(4).getName(), new Vector2d(540, 575), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(4).getLvl(), new Vector2d(405, 575), 20, 20, 20, 0);
        }
        if (player.pokemonCount >= 5 && pokemonCount != 5) {
            Sprite.drawArray(g, font, player.getPokemon(5).getName(), new Vector2d(540, 600), 24, 24, 20, 0);
            Sprite.drawArray(g, font, "Lvl:" + player.getPokemon(1).getLvl(), new Vector2d(405, 600), 20, 20, 20, 0);
        }
    }

    private void playerBar(Graphics2D g) {
        //Set background and border of pokemon window
        g.setColor(Color.white);
        g.fillRoundRect(495, 350, 340, 100, 30, 30);
        g.setColor(Color.blue);
        g.drawRoundRect(495, 350, 340, 100, 30, 30);
        Sprite.drawArray(g, font, playerPokemon.getName(), new Vector2d(505, 365), 26, 26, 22, 0);
        Sprite.drawArray(g, font, "Lvl" + playerPokemon.getLvl(), new Vector2d(740, 365), 20, 20, 14, 0);

        //Health Bar
        g.setColor(Color.black);
        g.fillRoundRect(600, 400, 210, 5, 15, 15);
        if (playerPokemon.getHp() <= ((playerPokemon.getMaxHp())) && (playerPokemon.getHp() > (playerPokemon.getMaxHp() * 0.5))) {
            g.setColor(Color.green);
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        }

        if ((playerPokemon.getHp() > ((playerPokemon.getMaxHp() * 0.2))) && (playerPokemon.getHp() <= ((playerPokemon.getMaxHp() * 0.5)))) {
            g.setColor(Color.yellow);
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        }

        if (playerPokemon.getHp() <= ((playerPokemon.getMaxHp() * 0.2))) {
            g.setColor(Color.red);
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(600, 400, (int) (210 * playerPokemon.getHpDiff()), 5, 15, 15);
        }

        g.setColor(Color.black);
        g.drawRoundRect(600, 400, 210, 5, 15, 15);
        Sprite.drawArray(g, font, "HP", new Vector2d(570, 397), 15, 15, 9, 0);
        Sprite.drawArray(g, font, playerPokemon.getHp() + ":" + playerPokemon.getMaxHp(), new Vector2d(570, 410), 15, 15, 9, 0);

        //EXP Bar
        g.setColor(Color.white);
        g.fillRect(570, 450, 220, 5);
        g.setColor(Color.black);
        g.drawRect(570, 450, 220, 5);
    }

    private void opponentBar(Graphics2D g) {
        //Set background and border of pokemon window
        g.setColor(Color.white);
        g.fillRoundRect(5, 100, 360, 110, 30, 30);
        g.setColor(Color.red);
        g.drawRoundRect(5, 100, 360, 110, 30, 30);
        Sprite.drawArray(g, font, wildPokemon.getName(), new Vector2d(15, 115), 26, 26, 22, 0);
        Sprite.drawArray(g, font, "Lvl" + wildPokemon.getLvl(), new Vector2d(250, 115), 20, 20, 14, 0);

        //Health Bar
        g.setColor(Color.black);
        g.fillRoundRect(110, 150, 210, 5, 15, 15);
        if (wildPokemon.getHp() <= ((wildPokemon.getMaxHp())) && (wildPokemon.getHp() > (wildPokemon.getMaxHp() * 0.5))) {
            g.setColor(Color.green);
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        }

        if ((wildPokemon.getHp() > ((wildPokemon.getMaxHp() * 0.2))) && (wildPokemon.getHp() <= ((wildPokemon.getMaxHp() * 0.5)))) {
            g.setColor(Color.yellow);
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        }

        if (wildPokemon.getHp() <= ((wildPokemon.getMaxHp() * 0.2))) {
            g.setColor(Color.red);
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        } else {
            g.fillRoundRect(110, 150, (int) (210 * wildPokemon.getHpDiff()), 5, 15, 15);
        }

        g.setColor(Color.black);
        g.drawRoundRect(110, 150, 210, 5, 15, 15);
    }
}
