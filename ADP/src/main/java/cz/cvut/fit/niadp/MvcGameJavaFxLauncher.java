package cz.cvut.fit.niadp;

import cz.cvut.fit.niadp.mvcgame.bridge.GameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.JavaFxGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;

import cz.cvut.fit.niadp.mvcgame.MvcGame;

public class MvcGameJavaFxLauncher extends Application {
    private Stage primaryStage;
    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        initializeLevelSelectionScreen();
    }

    private void initializeLevelSelectionScreen() {
        Image initialBackground = new Image(MvcGameConfig.BACKGROUND_IMAGE_RESOURCE);
        Group root = new Group();
        Scene initialScene = new Scene(root, 1920, 1080, new ImagePattern(initialBackground));

        Button level1Button = createLevelButton("Level    1", 1, 300, 370);
        Button level2Button = createLevelButton("Level    2", 2, 300, 490);
        Button level3Button = createLevelButton("Level    3", 3, 300, 610);
        root.getChildren().addAll(level1Button, level2Button, level3Button);

        this.primaryStage.setTitle(MvcGameConfig.GAME_TITLE);
        this.primaryStage.setScene(initialScene);
        this.primaryStage.show();
    }

    private Button createLevelButton(String level, int levelNum, double x, double y) {
        Button button = new Button(level);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(220);
        button.setPrefHeight(70);

        button.setStyle(
            "-fx-background-color: rgb(254, 238, 94);" +
            "-fx-background-insets: 0;" +
            "-fx-border-color: black;" +
            "-fx-border-width: 1px;" +
            "-fx-border-insets: 0;" +
            "-fx-background-radius: 5.0;" +
            "-fx-border-radius: 5.0;" +
            "-fx-font-family: 'Courier New';" +
            "-fx-font-size: 20px;"
        );
        button.setOnAction(e -> startGame(levelNum));
        return button;
    }

    public void startGame(int level) {
        theMvcGame.init(level);
        int winWidth = theMvcGame.getWindowWidth();
        int winHeight = theMvcGame.getWindowHeight();
        this.primaryStage.setTitle(theMvcGame.getWindowTitle());

        Group root = new Group();

        Scene theScene = new Scene(root, winWidth, winHeight);
        this.primaryStage.setScene(theScene);
        Image background = new Image(MvcGameConfig.BACK_IMAGE_RESOURCE);
        ImagePattern pattern = new ImagePattern(background);
        theScene.setFill(pattern);

        Canvas canvas = new Canvas(winWidth, winHeight);
        root.getChildren().add(canvas);


        IGameGraphics gameGraphics = new GameGraphics(new JavaFxGraphics(canvas.getGraphicsContext2D()));
        theMvcGame.render(gameGraphics);

        ArrayList<String> pressedKeysCodes = new ArrayList<>();
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if (!pressedKeysCodes.contains(code))
                        pressedKeysCodes.add(code);
                }
        );
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove( code );
                }
        );
        // the game-loop
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Clear the canvas
                theMvcGame.processPressedKeys(pressedKeysCodes);
                theMvcGame.update();
            }
        }.start();
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}