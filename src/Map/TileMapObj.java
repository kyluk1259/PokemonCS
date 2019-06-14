/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Graphics.Sprite;
import Utility.Vector2d;
import java.awt.Graphics2D;
import java.util.HashMap;
import Map.Block;

/**
 *
 * @author Kyle's PC
 */
public class TileMapObj extends TileMap {

    public static HashMap<String, Block> tmoBlocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tmoBlocks = new HashMap<String, Block>();

        System.out.println(sprite.file + "/ x:" + sprite.getWidth() + "/ y:" + sprite.getHeight());
        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            System.out.println(temp);
            if (temp != 172) {
                if (temp == 172) {
                    tempBlock = new HoleBlock(sprite.getSprite((int)(temp-1)%tileColumns, (int)(temp-1)/tileColumns), new Vector2d((int) ((int)(i%width)* 90)+600, (int) ((i / height))+300), tileWidth*5, tileHeight*5);
                } else {
                    tempBlock = new ObjBlock(sprite.getSprite((int)(temp-1)%tileColumns, (int)(temp-1)/tileColumns), new Vector2d((int) ((int)(i%width)* 90)+600, (int) ((i / height))+300), tileWidth*5, tileHeight*5);
                }
                tmoBlocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }
    }

    public void render(Graphics2D g) {
        for (Block block : tmoBlocks.values()) {
            block.render(g);
        }
    }

}
