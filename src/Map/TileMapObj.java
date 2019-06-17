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
    
    public static HashMap<String, Block> tmo_blocks;
    
    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        Block tempBlock;
        tmo_blocks = new HashMap<String, Block>();
        
        
        String[] block = data.split(",");
        
        for(int i = 0; i < (width*height); i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            /*
            if((int)((temp - 1)/tileColumns) > 3261){
            System.out.println(temp);
        }
*/
            if(temp != 0){
                if(temp == 172){
                    tempBlock = new HoleBlock(sprite.getSprite((int)((temp - 1)%tileColumns), (int)((temp - 1)/tileColumns)),new Vector2d((int)((i % width) * tileWidth), (int)((i / height)*tileHeight)),tileWidth, tileHeight);
                }else{
                    tempBlock = new ObjBlock(sprite.getSprite((int)((temp - 1)%tileColumns), (int)((temp - 1)/tileColumns)),new Vector2d((int)((i % width) * tileWidth), (int)(int)((i / height)*tileHeight)),tileWidth, tileHeight);
                }
                tmo_blocks.put(String.valueOf((int)(i%width)) + "," + String.valueOf((int)(i/height)), tempBlock);
            }
        }
    }
    
    public void render(Graphics2D g){
        for(Block block: tmo_blocks.values()){
            block.render(g);
        }
    }
}
