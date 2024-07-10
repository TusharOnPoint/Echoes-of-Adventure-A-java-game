package utilities;

import Characters.Player;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.projects.echoes_of_adventure.GameScreen;

import com.badlogic.gdx.utils.Logger;

public class LoadResources {
    private TiledMap tiledMap;
    private static final Logger logger = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private GameScreen gScreen;

    public LoadResources(GameScreen gs){
        gScreen = gs;
    }

    public OrthogonalTiledMapRenderer setUpMap(){
        System.out.println("in load setmap");
        tiledMap = new TmxMapLoader().load("world2.tmx");
        parseMapObjects(tiledMap.getLayers().get("Object layer").getObjects());
        if (tiledMap != null) {
            logger.debug("Tiled map loaded successfully.");
        } else {
            logger.error("Failed to load tiled map.");
        }
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void parseMapObjects(MapObjects mapObjects){
        for(MapObject mapObject : mapObjects){
            if(mapObject instanceof PolygonMapObject)
                createStaticBody((PolygonMapObject) mapObject);
            if(mapObject instanceof RectangleMapObject)
            {
                Rectangle rectangle = ((RectangleMapObject)mapObject).getRectangle();
                String rectName = mapObject.getName();
                if(rectName.equals("player")){
                    Body body = BodyManager.createBody(rectangle.x+rectangle.width/2,
                            rectangle.y+rectangle.height/2,
                            rectangle.width,
                            rectangle.height,
                            false, gScreen.getWorld());
                    gScreen.setPlayer(new Player(rectangle.width, rectangle.height, body));
                }
            }
        }
    }

    private void createStaticBody(PolygonMapObject mapObject)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gScreen.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(mapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject mapObject) {
        float[] points = mapObject.getPolygon().getTransformedVertices();
        Vector2[] polyVertices = new Vector2[points.length/2];
        for(int i=0; i < polyVertices.length; i++)
        {
            Vector2 temp = new Vector2(points[i*2]/32, points[i*2+1]/32);
            polyVertices[i] = temp;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(polyVertices);
        return shape;
    }

}
