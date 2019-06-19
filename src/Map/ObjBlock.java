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
public class ObjBlock extends Block {


    public ObjBlock(BufferedImage img, Vector2d pos, int w, int h, int x, int y) {
        super(img, pos, w, h, x, y);
    }

    public boolean update(AABB p) {
        return true;
    }

    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.green);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
        //g.setColor(Color.red);
        //g.drawString(x + "/" + y, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y);
    }
   
}
