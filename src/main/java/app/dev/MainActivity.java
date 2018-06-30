/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package app.dev;

import java.io.IOException;

import app.dev.page.Home;
import app.dev.provider.AppClassSelector;
import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.mwt.Desktop;
import ej.mwt.MWT;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.background.AbstractImageBackground;
import ej.style.background.Background;
import ej.style.background.SimpleImageBackground;
import ej.style.background.SimpleRoundedPlainBackground;
import ej.style.container.Rectangle;
import ej.style.outline.SimpleOutline;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.transition.SlideScreenshotTransitionContainer;
import ej.widget.container.transition.TransitionContainer;
import ej.widget.navigation.page.Page;

/**
 *
 */
public class MainActivity implements Activity {

	public static TransitionContainer transition;

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		MicroUI.start();

		initializeStyle();

		Panel panel = new Panel();
		Desktop desktop = new Desktop();
		transition = new SlideScreenshotTransitionContainer(MWT.LEFT, false, false);
		transition.show(new Home(), false);
		panel.setWidget(transition);
		panel.showFullScreen(desktop);
		desktop.show();
	}

	private void initializeStyle() {

		Display display = Display.getDefaultDisplay();
		Stylesheet stylesheet = StyleHelper.getStylesheet();

		EditableStyle btnStyle = new EditableStyle();
		btnStyle.setMargin(new SimpleOutline(5));
		btnStyle.setPadding(new SimpleOutline(0));

		Background bkgr = new SimpleRoundedPlainBackground(5);
		btnStyle.setBackground(bkgr);
		btnStyle.setBackgroundColor(Colors.MAROON);
		btnStyle.setForegroundColor(Colors.WHITE);
		btnStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// TODO style for labels
		EditableStyle labelStyle = new EditableStyle();
		TypeSelector lblSel = new TypeSelector(Label.class);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);

		ChildCombinator parentBtnSel = new ChildCombinator(btnSel, lblSel);
		stylesheet.addRule(parentBtnSel, btnStyle);
		stylesheet.addRule(parentBtnSel, labelStyle);

		EditableStyle GamePageTopLeftEditableStyle = new EditableStyle();
		GamePageTopLeftEditableStyle.setPadding(new SimpleOutline(0));
		GamePageTopLeftEditableStyle.setMargin(new SimpleOutline(5));
		stylesheet.addRule(AppClassSelector.gamePageTopLeftContainerSelector, GamePageTopLeftEditableStyle);

		// GamePage
		// Bottom
		EditableStyle gamePageBottomEnd = new EditableStyle();
		gamePageBottomEnd.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		stylesheet.addRule(AppClassSelector.bottomEndSelect, gamePageBottomEnd);

		// SnowMan
		EditableStyle gamePageSnowManEditableStyle = new EditableStyle();
		gamePageSnowManEditableStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.TOP);
		stylesheet.addRule(AppClassSelector.gamePageSnowManSelect, gamePageSnowManEditableStyle);

		EditableStyle blackBkgEditableStyle = new EditableStyle();
		blackBkgEditableStyle.setBackgroundColor(Colors.BLACK);
		stylesheet.addRule(AppClassSelector.blackBkgClassSelect, blackBkgEditableStyle);

		EditableStyle mainBackgroundEditStyle = new EditableStyle();
		AbstractImageBackground simpleBGrd = new SimpleImageBackground();

		simpleBGrd = new AbstractImageBackground() {
			@Override
			protected void drawImage(GraphicsContext g, Rectangle bounds) {
				try {
					g.drawImage(Image.createImage("/images/night_snow.png"), 250, bounds.getY(),
							GraphicsContext.VCENTER | GraphicsContext.HCENTER);

				} catch (IOException e) {
				}
			}

		};

		mainBackgroundEditStyle.setBackground(simpleBGrd);
		stylesheet.addRule(AppClassSelector.mainBackgroundImgSelect, mainBackgroundEditStyle);

		EditableStyle gamePageTopContainerEditStyle = new EditableStyle();
		gamePageTopContainerEditStyle.setBackgroundColor(Colors.NAVY);
		stylesheet.addRule(AppClassSelector.gameTopContainer, gamePageTopContainerEditStyle);

		EditableStyle gamePageLabelsEditStyle = new EditableStyle();
		gamePageLabelsEditStyle.setForegroundColor(Colors.WHITE);
		gamePageLabelsEditStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		stylesheet.addRule(AppClassSelector.gamePageLabelsSelect, gamePageLabelsEditStyle);

		EditableStyle gamePageFooterEditStyle = new EditableStyle();
		SimpleOutline outL = new SimpleOutline();
		gamePageFooterEditStyle.setPadding(new SimpleOutline(0));
		stylesheet.addRule(AppClassSelector.gamePageFooterSelect, gamePageFooterEditStyle);

		EditableStyle scoreLabelStyles = new EditableStyle();
		scoreLabelStyles.setForegroundColor(Colors.WHITE);
		// scoreLabelStyles.setFontProfile(new FontProfile("arial black", 100, Font.STYLE_BOLD));
		scoreLabelStyles.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		scoreLabelStyles.setBackgroundColor(Colors.MAROON);
		SimpleRoundedPlainBackground scoreLabelStylesBkgr = new SimpleRoundedPlainBackground(15);
		scoreLabelStyles.setBackground(scoreLabelStylesBkgr);
		stylesheet.addRule(AppClassSelector.scoresLabelsSelect, scoreLabelStyles);

		EditableStyle scoreItemContainerEditStyle = new EditableStyle();
		stylesheet.addRule(AppClassSelector.scoreItemContainerSelect, scoreItemContainerEditStyle);

		EditableStyle maroonBkgrEditStyle = new EditableStyle();
		SimpleRoundedPlainBackground maroonBkgr = new SimpleRoundedPlainBackground(5);
		maroonBkgrEditStyle.setBackground(maroonBkgr);
		maroonBkgrEditStyle.setBackgroundColor(Colors.MAROON);
		stylesheet.addRule(AppClassSelector.maroonBkgrSelect, maroonBkgrEditStyle);

		EditableStyle currentLevelEditStyle = new EditableStyle();
		SimpleRoundedPlainBackground currentLevelRoundedBkgr = new SimpleRoundedPlainBackground(5);
		currentLevelEditStyle.setBackgroundColor(Colors.GREEN);
		stylesheet.addRule(AppClassSelector.currentLevelBackgroundSelect, currentLevelEditStyle);

		EditableStyle whiteLabelEditableStyle = new EditableStyle();
		whiteLabelEditableStyle.setForegroundColor(Colors.WHITE);
		stylesheet.addRule(AppClassSelector.whiteLabelSelect, whiteLabelEditableStyle);

		AbstractImageBackground widgetGoRightImgBkgr = new AbstractImageBackground() {
			@Override
			protected void drawImage(GraphicsContext g, Rectangle bounds) {
				try {
					g.drawImage(Image.createImage("/imges/go_right.png"), bounds.getWidth(), bounds.getHeight(),
							GraphicsContext.LEFT | GraphicsContext.VCENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		EditableStyle widgetGoRightEditableStyle = new EditableStyle();
		widgetGoRightEditableStyle.setBackground(widgetGoRightImgBkgr);
		stylesheet.addRule(AppClassSelector.widgetGoRightSelect, widgetGoRightEditableStyle);

	}

	public static void show(Page page) {
		transition.show(page, true);
	}

	public static void home() {
		transition.show(new Home(), false);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}
