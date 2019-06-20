/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Graphics.Sprite;
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
 * @author Kyle's PC
 */
public class TileManager {

    public static int layers;
    public static ArrayList<TileMap> tm;

    public TileManager() {
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path) {
        tm = new ArrayList<TileMap>();
        addTileMap(path, 20, 20);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
        String imagePath, imagePathTSX, name;

        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        Sprite sprite;

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePathTSX = eElement.getAttribute("source");
            imagePath = imagePathTSX.substring(0, imagePathTSX.length() - 4);

            list = doc.getElementsByTagName("map");
            node = list.item(0);
            eElement = (Element) node;

            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
            sprite = new Sprite("TileSets/" + imagePath + ".png", tileWidth, tileHeight);

            list = doc.getElementsByTagName("layer");
            layers = list.getLength();
            String[] data = new String[layers];

            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                name = eElement.getAttribute("name");

                if (i < 1) {
                    tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns, i));
                }

                System.out.println("Layer #" + i + ":\n" + data[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR TileManager: Couldn't load tilemap");
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g);
        }
    }
}
