package com.projects.echoes_of_adventure;

import Characters.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import utilities.ManageMap;

public class GameScreen extends ScreenAdapter {
    /*final MyGame game;
    Texture playerTexture;
    float playerX, playerY;

    public GameScreen(final MyGame game) {
        this.game = game;
        playerTexture = new Texture("player.png"); // Add your player image
        playerX = Gdx.graphics.getWidth() / 2;
        playerY = Gdx.graphics.getHeight() / 2;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(playerTexture, playerX, playerY);
        game.batch.end();

        // Basic player movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) playerX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) playerX += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) playerY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) playerY -= 200 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        playerTexture.dispose();
    }*/

    OrthographicCamera camera;
    SpriteBatch batch;
    World world;
    Box2DDebugRenderer box2DDebugRenderer;

    OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    ManageMap loadResources;
    private Player player;

    public GameScreen(OrthographicCamera camera){
        System.out.println("in gamescreen constructor");
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, -50f), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.loadResources = new ManageMap(this);
        this.orthogonalTiledMapRenderer = loadResources.setUpMap();

    }

    private void updateCamera() {
        Vector3 position = camera.position;
        position.x = (float) Math.round(player.getBody().getPosition().x * 32 * 10) /10;
        position.y = (float) Math.round(player.getBody().getPosition().y * 32 * 10) /10;
        camera.position.set(position);
        camera.update();
    }

    private void update(){
        System.out.println("in gamescreen update");
        world.step(1f/60, 6, 2);
        updateCamera();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
    }

    @Override
    public void render(float delta){
        this.update();
        System.out.println("in gamescreen render");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        batch.begin();

        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(32f));

    }

    public World getWorld() {
        return world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}