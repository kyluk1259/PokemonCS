/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Entity;
import Map.TileMapNorm;
import Map.TileMapObj;

/**
 *
 * @author Kyle's PC
 */
//THIS IS THE COLLISION CLASS
public class AABB {

    private Vector2d pos;
    private float xOff;
    private float yOff;
    private float w;
    private float h;
    private float r;
    private int size;
    private Entity e;

    public AABB(Vector2d pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public AABB(Vector2d pos, int r, Entity e) {
        this.pos = pos;
        this.r = r;
        size = r;
        this.e = e;
    }

    public Vector2d getPos() {
        return pos;
    }

    public float getRadius() {
        return r;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public void setBox(Vector2d pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2d pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth(float f) {
        w = f;
    }

    public void setHeight(float f) {
        h = f;
    }

    public void setXOff(float f) {
        xOff = f;
    }

    public void setYOff(float f) {
        yOff = f;
    }

    public float getXOff() {
        return xOff;
    }

    public float getYOff() {
        return yOff;
    }

    //MAIN COLLISION MATH
    public boolean collides(AABB bBox) {
        float ax = ((pos.getWorldVar().x + (xOff)) + (w / 2));
        float ay = ((pos.getWorldVar().y + (yOff)) + (h / 2));
        float bx = ((bBox.pos.getWorldVar().x + (bBox.xOff / 2)) + (w / 2));
        float by = ((bBox.pos.getWorldVar().y + (bBox.yOff / 2)) + (h / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2)) {
                return true;
            }
        }
        return false;
    }

    public boolean colCircleBox(AABB aBox) {

        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt(2) - e.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cy, aBox.pos.getWorldVar().y)); //maybe get width

        if ((xDelta * xDelta + yDelta * yDelta) < ((this.r / Math.sqrt(2)))) {
            return true;
        }

        return false;
    }

    public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; c++) {

            int xt = (int) ((2 * (((pos.x + ax) + (c % 2) * xOff + xOff) / 38)));
            int yt = (int) ((2 * ((pos.y + ay) + ((int) (c / 2)) * h + 20) / 38));
            for (int i = 0; i < TileMapObj.blocks.size(); i++) {
                if (((xt - 1 == TileMapObj.blocks.get(i).getX()) && (yt == TileMapObj.blocks.get(i).getY()))) {
                    System.out.println("Collision: " + xt + "/" + yt);
                    return TileMapObj.blocks.get(i).update(this);
                }
            }
        }
        return false;
    }

    public boolean outsideMap(float ax, float ay) {
        for (int c = 0; c < 4; c++) {

            int xt = (int) ((2 * (((pos.x + ax)) / 38)));
            int yt = (int) ((2 * ((pos.y + ay)) / 38));
            for (int i = 0; i < TileMapObj.blocks.size(); i++) {
                if (((xt == TileMapNorm.blocks.get(i).getX()) && (yt == TileMapNorm.blocks.get(i).getY()))) {
                    System.out.println("Inside Map at: " + xt + "/" + yt);
                    return TileMapNorm.blocks.get(i).update(this);
                }
            }
        }
        return true;
    }
}
