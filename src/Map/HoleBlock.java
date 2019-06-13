/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Utility.AABB;
import Utility.Vector2d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kyle's PC
 */
public class HoleBlock extends Block {

    public HoleBlock(BufferedImage img, Vector2d pos, int w, int h) {
        super(img, pos, w, h);
    }

    public boolean update(AABB a) {
        return false;
    }
    
    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.green);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
    }
    
    
}
