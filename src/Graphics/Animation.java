/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Kyle's PC
 */
public class Animation {
    
    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;
    
    private int count;
    private int delay;
    
    private int timesPlayed;
    public boolean animating;
    
    public Animation(BufferedImage[] frames){
        timesPlayed = 0;
        setFrames(frames);
        animating = true;
    }
    
    public Animation(){
        timesPlayed = 0;
        animating = true;
    }
    
    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }
    
    public void setDelay(int i) {
        delay = i;
    }
    
    public void setFrame(int i){
        currentFrame = i;
    }
    
    public void setNumFrames(int i){
        numFrames = i;
    }
    
    public void update(){
        
        if(delay == -1){
            return;
        }
        
        count++;
        
        if(count == delay){
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames){
            currentFrame = 0;
            timesPlayed++;
        }
    }
    
    public void startAnimating(){
        animating = true;
    }
    
    public void stopAnimating(){
        animating = false;
    }
    
    public int getDelay(){
        return delay;
    }
    
    public int getFrame(){
        return currentFrame;
    }
    
    public BufferedImage getImage(){
        return frames[currentFrame];
    }
    
    public boolean hasPlayedOnce(){
        return timesPlayed > 0;
    }
    
    public boolean hasPlayed(int i){
        return timesPlayed == i;
    }
}
