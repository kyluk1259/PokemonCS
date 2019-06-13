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

    public TileManager(String file) {
        tm = new ArrayList<TileMap>();
        //Size of every tile from file
        addTileMap(file, 50, 50);
    }

    private void addTileMap(String file, int bw, int bh) {

        String tileImageTSX, tileImage;
        int tileWidth, tileHeight, tileCount, tileColumns;
        int width = 0;
        int height = 0;
        int layers = 0;
        Sprite tileSprite;

        String[] data = new String[15];

        try {
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
            ele = (Element) node;

            tileWidth = Integer.parseInt(ele.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(ele.getAttribute("tileheight"));
            width = Integer.parseInt(ele.getAttribute("width"));
            height = Integer.parseInt(ele.getAttribute("height"));
            System.out.println(width + " / " + height);

            tileSprite = new Sprite("TileSets/" + tileImage + ".png",  tileWidth, tileHeight);

            System.out.println(tileSprite.getWidth());

            tileColumns = (tileSprite.getSpriteSheet().getWidth() / (tileWidth));
            tileCount = tileColumns * (tileSprite.getSpriteSheet().getHeight() / (tileHeight));

            list = document.getElementsByTagName("layer");
            layers = list.getLength();
            System.out.println(tileColumns);

            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                ele = (Element) node;

                data[i] = ele.getElementsByTagName("data").item(0).getTextContent();
                System.out.println("Successfully loaded " + file + " layer #" + i + data[i]);

                if (i >= 1) {
                    tm.add(new TileMapNorm(data[i], tileSprite, width, height, tileWidth, tileHeight, tileColumns));
                } else {
                    tm.add(new TileMapObj(data[i], tileSprite, width, height, tileWidth, tileHeight, tileColumns));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR TileManager: Couldn't Load TileMap");
        }

    }

    public void render(Graphics2D g) {
        for (int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g);
        }
    }

}
