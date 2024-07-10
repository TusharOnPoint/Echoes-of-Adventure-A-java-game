/*package com.projects.echoes_of_adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {
    final MyGame game;
    Texture playButton;

    public MainMenu(final MyGame game) {
        this.game = game;
        playButton = new Texture("assets/image/play_button.png"); // Add your button image
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(playButton, (float) Gdx.graphics.getWidth() / 2 - (float) playButton.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - (float) playButton.getHeight() / 2);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
        playButton.dispose();
    }
}*/