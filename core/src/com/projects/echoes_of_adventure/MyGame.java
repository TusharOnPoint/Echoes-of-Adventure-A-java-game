package com.projects.echoes_of_adventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends Game {
	/*SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
//		ScreenUtils.clear(1, 0, 1, 1);
//		batch.begin();
//		batch.draw(img, 100, 100);
//		batch.end();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/
	public static MyGame INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthographicCamera;
	private GameScreen gameScreen;

	SpriteBatch batch;
	Texture img;

	public MyGame() {

		INSTANCE= this;
		System.out.println("in main constructor");
	}
	@Override
	public void create() {
		System.out.println("in main create");
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
		this.gameScreen = new GameScreen(orthographicCamera);
	}

	@Override
	public void render () {
		System.out.println("in main render");
		gameScreen.render(32f);
	}
}
