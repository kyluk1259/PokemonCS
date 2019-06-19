/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Entity.Player;
import Graphics.Sprite;
import Map.TileManager;
import Utility.KeyHandler;
import Utility.Vector2d;
import java.awt.Color;
import java.awt.Graphics2D;
import pokemoncs.GamePanel;

/**
 *
 * @author Kyle's PC
 */
public class PlayState extends GameState {

    public static Player player;
    public Sprite sprite;
    private TileManager tm;
    public static boolean loadText, textComplete, pause;
    public static String text;
    public static int index = 0;
    private float xStart;

    public static Vector2d world;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        world = new Vector2d();
        Vector2d.setWorldVar(world.x, world.y);
        float posx = (int) ((GamePanel.getW() / 2) - (38));
        float posy = (int) ((GamePanel.getH() / 2) - (38));

        tm = new TileManager("tileMaps/startArea.tmx");
        player = new Player(new Sprite("Sprites/playerwalking.png", 38, 38), new Vector2d(0 + posx, 0 + posy - 30), 80, gsm);
        float x = 0;
        float y = GamePanel.getH();

        loadText = false;
        index = 0;
        xStart = 25;
        pause = false;
        textComplete = false;

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
        }
    }

    public void update() {
        if (pause != true) {
            Vector2d.setWorldVar(world.x, world.y);
            player.update();
        }
    }

    public void input(KeyHandler key) {
        if (pause != true) {
            player.input(key);
        }
    }

    public void render(Graphics2D g) {

        if (pause != true) {
            tm.render(g);
            player.render(g);
            g.setColor(Color.red);
            Sprite.drawArray(g, font, String.valueOf(player.getXPosition()), new Vector2d(xStart + 600, 50), 32, 32, 24, 0);
            g.drawLine(420, 0, 420, 640);
            g.drawLine(0, 320, 840, 320);
            if (loadText == true) {
                Sprite.drawText(g, font, text, new Vector2d(xStart, 400), 32, 32, 24, 0, index);
                if (index == text.length()) {
                    index = text.length();
                    textComplete = true;
                } else {
                    index++;
                }
            } else {
                Sprite.drawArray(g, font, "Press Z to read message", new Vector2d(xStart, 400), 32, 32, 24, 0);
                Sprite.drawArray(g, font, "Hold X when not moving and move in a direction to run", new Vector2d(xStart, 450), 32, 32, 24, 0);
                Sprite.drawArray(g, font, "Press Enter to pause", new Vector2d(xStart, 550), 32, 32, 24, 0);
                Sprite.drawArray(g, font, "Press X to unpause game", new Vector2d(xStart, 600), 32, 32, 24, 0);
            }
        }
    }
}
