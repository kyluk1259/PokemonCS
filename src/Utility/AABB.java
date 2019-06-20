/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Entity.Entity;
import Map.TileManager;
import Map.TileMapNorm;
import Map.TileMapObj;

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
    private int flash;

    //Initialize a square hitbox somewhere on the map
    public AABB(Vector2d pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        flash = 0;

        size = Math.max(w, h);
    }

    //Initialize a circle hitbox object on an entity
    public AABB(Vector2d pos, int r, Entity e) {
        this.pos = pos;
        this.r = r;
        size = r;
        this.e = e;
        flash = 0;
    }

    //Getters are below
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

    public float getXOff() {
        return xOff;
    }

    public float getYOff() {
        return yOff;
    }

    //Set hitbox size
    public void setBox(Vector2d pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    //Set hitbox radius
    public void setCircle(Vector2d pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    //Setters
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

    //calculates collision for a circle hitbox
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

    //Checks for collision with object tile on map, checks every corner from the hitbox
    //Uses player position and checks if the map object hashmap contains those positions
    public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; c++) {

            int xt = (int) ((2 * (((pos.x + ax) + (c % 2) * xOff + xOff) / 38)));
            int yt = (int) ((2 * ((pos.y + ay) + ((int) (c / 2)) * h + 20) / 38));

            if (TileMapObj.objects.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                return TileMapObj.objects.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this); //"this" returns true
            }
        }

        return false;

    }

    //Checks to see if an entity is within bounds
    public boolean outsideMap(float ax, float ay) {

        int xt = (int) ((2 * (((pos.x + ax)) / 38)));
        int yt = (int) ((2 * ((pos.y + ay)) / 38));

        if ((xt > 0 || xt < 50) && (yt > 0 || yt < 50)) {
            return false;
        }

        return true;
    }
}
