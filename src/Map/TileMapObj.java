/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Graphics.Sprite;
import Utility.Vector2d;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kyle's PC
 */
public class TileMapObj extends TileMap {

    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static HashMap<String, Block> objects = new HashMap<String, Block>();

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns, int layer) {

        Block tempBlock;

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2d((int) ((i % width) * tileWidth), (int) (int) ((i / height) * tileHeight)), tileWidth, tileHeight, (int) (i % width), (int) (i / height));
                blocks.add(tempBlock);
                objects.put(String.valueOf(i % width) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }
    }
        
    public void render(Graphics2D g) {
        for (Block block : blocks) {
            block.render(g);
        }
    }

}
