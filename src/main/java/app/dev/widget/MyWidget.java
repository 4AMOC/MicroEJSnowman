/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev.widget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import app.dev.AppSingleton;
import app.dev.model.Flake;
import app.dev.model.FlakeTypes;
import app.dev.page.GamePage;
import app.dev.provider.AppComponentsPositions;
import app.dev.provider.AppStringSelector;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.widget.StyledWidget;
import ej.widget.basic.ButtonImage;
import ej.widget.listener.OnClickListener;

/**
 *
 */
public class MyWidget extends StyledWidget implements EventHandler {

	public ButtonImage goRightImg = new ButtonImage();
	public ButtonImage goLeftImg = new ButtonImage();

	GamePage container;
	Flake snowMan = new Flake();

	private final int[] flakesX = { 20, 50, 80, 110, 140, 170, 200 };
	private final int[] flakesY = { 0, 10, 20, 30, 40 };
	ArrayList<FlakeTypes> flakeTypes = new ArrayList<>();

	ArrayList<Flake> flakes = new ArrayList<>();

	private void loadFlakes() {

		if (!this.flakes.isEmpty()) {
			this.flakes.clear();
		}

		this.flakes.add(new Flake(20, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(60, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(100, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(140, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(180, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(220, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(260, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(300, randomY(), randomFlakeType(), this.container));
		this.flakes.add(new Flake(340, randomY(), randomFlakeType(), this.container));

		try {
			for (int i = 0; i < this.flakes.size(); i++) {
				this.flakes.get(i).setImg(Image.createImage("/images/snow.png"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MyWidget(GamePage container) {

		this.addClassSelector(AppStringSelector.mainBackgroundImg);
		this.container = container;

		this.flakeTypes.add(FlakeTypes.BASIC);
		this.flakeTypes.add(FlakeTypes.ICE_CUBE);

		this.loadImages();
		this.loadFlakes();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				MyWidget.this.container.repaint();
				repaint();
			}
		}, 300, AppSingleton.getInstance().currentLevel.speed);
	}

	@Override
	public boolean handleEvent(int event) {

		if (Event.getType(event) == Event.POINTER) {
			if (Pointer.isPressed(event) || Pointer.isDragged(event)) {

				Pointer ptr = (Pointer) Event.getGenerator(event);

				System.out.println(" X " + ptr.getX());
				System.out.println(" Y " + ptr.getY());

				if ((ptr.getY() >= AppComponentsPositions.widgetGoBtnYStart
						&& ptr.getY() <= AppComponentsPositions.widgetGoBtnYEnd)) {

					if ((ptr.getX() >= AppComponentsPositions.widgetGoLeftXStart
							&& ptr.getX() <= AppComponentsPositions.widgetGoLeftXEnd)) {

						if (this.snowMan.getPosX() - 10 > AppComponentsPositions.playgroundXStart) {
							this.snowMan.setPosX(this.snowMan.getPosX() - 10);

							AppSingleton.getInstance().setSnowmanCurrentX(this.snowMan.getPosX());

						} else if (this.snowMan.getPosX() - 10 <= AppComponentsPositions.playgroundXStart) {
							this.snowMan.setPosX(AppComponentsPositions.playgroundXStart);
							AppSingleton.getInstance().setSnowmanCurrentX(this.snowMan.getPosX());
						}

						this.repaint();
					}

					if ((ptr.getX() >= AppComponentsPositions.widgetGoRightXStart
							&& ptr.getX() <= AppComponentsPositions.widgetGoRightXEnd)) {

						if (this.snowMan.getPosX() + 10 < AppComponentsPositions.playgroundXEnd) {
							this.snowMan.setPosX(this.snowMan.getPosX() + 10);
						} else if (this.snowMan.getPosX() + 10 >= AppComponentsPositions.playgroundXEnd) {
							this.snowMan.setPosX(AppComponentsPositions.playgroundXEnd);
						}

						this.repaint();
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		g.drawImage(this.goRightImg.getSource(), bounds.getWidth() - 23, bounds.getHeight() - 30,
				GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		g.drawImage(this.goLeftImg.getSource(), bounds.getWidth() - 70, bounds.getHeight() - 30,
				GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		g.drawImage(this.snowMan.getImg(), this.snowMan.getPosX(), this.snowMan.getPoxY(),
				GraphicsContext.BOTTOM | GraphicsContext.LEFT);

		for (Flake current : this.flakes) {
			g.drawImage(current.getImg(), current.getPosX(), current.getPoxY(),
					GraphicsContext.TOP | GraphicsContext.LEFT);

			current.moveForward();
		}
	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		return bounds;
	}

	private void setMovesClickListener() {

		this.goRightImg.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Clicked Right");
			}
		});

		this.goLeftImg.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Clicked Left");
			}
		});
	}

	private void loadImages() {
		try {

			this.goRightImg.setSource(Image.createImage("/images/go_right.png")); //$NON-NLS-1$
			this.goLeftImg.setSource(Image.createImage("/images/go_left.png")); //$NON-NLS-1$
			this.snowMan.setImg(Image.createImage("/images/red_snow_man.png")); //$NON-NLS-1$

			this.snowMan.setPoxY(200);
			this.snowMan.setPosX(240);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int randomFlakesNumber() {
		Random random = new Random();
		return random.nextInt(9) + 1;
	}

	private FlakeTypes randomFlakeType() {
		Random random = new Random();
		int flakeIndex = random.nextInt(this.flakeTypes.size()) + 0;
		return this.flakeTypes.get(flakeIndex);
	}

	private int randomX() {
		Random random = new Random();
		int index = random.nextInt(this.flakesX.length) + 0;
		return this.flakesX[index];
	}

	private int randomY() {
		Random random = new Random();
		int index = random.nextInt(this.flakesY.length) + 0;
		return this.flakesY[index];
	}

	private String randomImgUrl() {
		Random random = new Random();
		String[] urls = { "/images/flocon.png", "/images/ice.png" }; //$NON-NLS-1$ //$NON-NLS-2$
		int index = random.nextInt(urls.length) + 0;
		return urls[index];
	}

	public void shuffleFlake(Flake flake) {
		flake.setPoxY(randomY());
	}

	private void resetFlakes() {

	}

}
