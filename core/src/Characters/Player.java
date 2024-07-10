package Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends Character{
    private int jmpCounter;
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10.0f;
        jmpCounter = 0;
    }

    @Override
    public void update() {
        x= body.getPosition().x*32;
        y= body.getPosition().y*32;
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    private void handleInput(){
        velx = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            velx = -1;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            velx = 1;
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jmpCounter<2)
        {
            float force = body.getMass()*25;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            jmpCounter++;
        }
        if(body.getLinearVelocity().y == 0)
            jmpCounter = 0;
        body.setLinearVelocity(velx*speed, body.getLinearVelocity().y);
    }
}
