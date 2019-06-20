/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Utility.Vector2d;
import Utility.AABB;

/**
 *
 * @author Kyle's PC
 */
public abstract class Block {
    
    protected int w;
    protected int h;
    
    protected BufferedImage img;
    protected Vector2d pos;
    
    protected int x;
    protected int y;
    
    public Block(BufferedImage img, Vector2d pos, int w, int h, int x, int y){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }
    
    public abstract boolean update(AABB p);
    
    public void render(Graphics2D g){
        g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h, null);
    }
    
     public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
