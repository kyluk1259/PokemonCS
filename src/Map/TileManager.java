/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Map;

import Graphics.Sprite;
import Utility.Vector2d.*;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author kyleluka
 */
public class TileManager {
    
    public static ArrayList<TileMap> tm;
    
    public TileManager(String file){
        tm = new ArrayList<TileMap>();
        //Size of every tile from file
        addTileMap(file, 40, 40);        
    }
    
    private void addTileMap(String file, int bw, int bh){
        
        String tileImageTSX, tileImage;
        int tileWidth, tileHeight, tileCount, tileColumns;
        int width, height, layers = 0;
        Sprite tileSprite;
        
        String[] data = new String[10];
        
        try{
            DocumentBuilderFactory builderF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderF.newDocumentBuilder();
            Document document = builder.parse(new File(getClass().getClassLoader().getResource(file).toURI()));
            document.normalize();
            
            NodeList list = document.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element ele = (Element) node;
            
            tileImageTSX = ele.getAttribute("source");
            tileImage = tileImageTSX.substring(0, tileImageTSX.length() - 4);
            
            list = document.getElementsByTagName("map");
            node = list.item(0);
            ele = (Element)node;
            
            tileWidth = Integer.parseInt(ele.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(ele.getAttribute("tileheight"));
            
            tileSprite = new Sprite("TileSets/"+tileImage+".png", tileWidth, tileHeight);
            
            tileColumns = tileSprite.getWidth() / tileWidth;
            tileCount = tileColumns * (tileSprite.getHeight() / tileHeight);        
            
            list = document.getElementsByTagName("layer");
            layers = list.getLength();
            
            for(int i = 0; i < layers; i++){
                node = list.item(i);
                ele = (Element)node;
                
                if(i <= 0){
                    width = Integer.parseInt(ele.getAttribute("width"));
                    height = Integer.parseInt(ele.getAttribute("height"));
                }
                
                data[i] = ele.getElementsByTagName("data").item(0).getTextContent();
                System.out.println("--------" + data[i]);
            }
            
            
        }catch(Exception e){
            System.out.println("ERROR TileManager: Couldn't Load TileMap");
        }
        
    }
    
    private void render(Graphics2D g){
        
    }
    
}
