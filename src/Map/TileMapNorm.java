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

/**
 *
 * @author Kyle's PC
 */
public class TileMapNorm extends TileMap {
    
    private ArrayList<Block> blocks;
    
    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
        blocks = new ArrayList<Block>();
        
        String[] block = data.split(",");
        for(int i = 0; i < (width*height); i++){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if(temp != 0){
                blocks.add(new NormBlock(sprite.getSprite((int)((temp - 1)%tileColumns), (int)((temp - 1)/tileColumns)), new Vector2d((int) ((i % width) * tileWidth), (int) (int)((i / height)*tileHeight)), tileWidth, tileHeight));
            }
        }
    }
    
    public void render(Graphics2D g){
        for(int i=0; i<blocks.size(); i++){
            blocks.get(i).render(g);
        }
    }
    
}
