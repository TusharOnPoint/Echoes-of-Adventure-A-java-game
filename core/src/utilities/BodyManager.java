package utilities;

import com.badlogic.gdx.physics.box2d.*;

public class BodyManager {
    public static Body createBody(float x, float y, float w, float h, boolean isStatic, World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/32, y/32);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w/2/32, h/2/32);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }
}
