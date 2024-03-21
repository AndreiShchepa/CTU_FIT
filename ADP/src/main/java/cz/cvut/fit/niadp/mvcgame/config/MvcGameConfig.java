package cz.cvut.fit.niadp.mvcgame.config;

import javafx.geometry.Dimension2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MvcGameConfig {
    private MvcGameConfig() { }


    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final double ANGLE_STEP = Math.PI/18;
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 5;
    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 10;
    public static final double INIT_ANGLE = 0.0;
    public static final double MIN_ANGLE = -1.4;
    public static final double MAX_ANGLE = 1.4;
    public static final double GRAVITY = 9.81;
    public static final int CANNON_POS_X = 200;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int BOUND_POS_X = 200;
    public static final int BOUND_UP_POS_Y = 300;
    public static final int BOUND_DOWN_POS_Y = 950;
    public static final int GAME_INFO_POS_X = 5;
    public static final int GAME_INFO_POS_Y = 15;
    public static final int GAME_INFO_TEXT_SPACING = 18;
    public static final int MIN_MISSILES = 1;
    public static final int MAX_MISSILES = 10;
    public static final int INIT_CAPACITY = 9;
    public static final int STAR1_POS_X = 800;
    public static final int STAR1_POS_Y = 50;
    public static final int STAR2_POS_X = 920;
    public static final int STAR2_POS_Y = 20;
    public static final int STAR3_POS_X = 1040;
    public static final int STAR3_POS_Y = 50;
    public static final Dimension2D STAR_SIZE = new Dimension2D(100, 100);
    public static final int MISSILE_A_COST = 10;
    public static final int MISSILE_B_COST = 20;
    public static final int ENEMY_A_COST = 250;
    public static final int ENEMY_A_LIVES = 2;
    public static final int ENEMY_B_LIVES = 4;
    public static final int ENEMY_B_COST = 500;
    public static final int MISSILE_A_DAMAGE = 1;
    public static final int MISSILE_B_DAMAGE = 2;
    public static final int MISSILE_A_RADIUS_HIT = 10;
    public static final int MISSILE_B_RADIUS_HIT = 15;
    public static final int HIT_X_INTERVAL = 15;


    public static final Dimension2D CANNON_SIZE = new Dimension2D(25, 60);
    public static final Dimension2D OBJECT_SIZE = new Dimension2D(30, 30);


    public static final String GAME_TITLE = "The NI-ADP MvcGame";
    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String AIM_UP_KEY = "A";
    public static final String AIM_DOWN_KEY = "Y";
    public static final String POWER_UP_KEY = "F";
    public static final String POWER_DOWN_KEY = "D";
    public static final String EXIT_KEY = "ESCAPE";
    public static final String SHOOT_KEY = "SPACE";
    public static final String CHANGE_MISSILE_KEY = "L";
    public static final String CHANGE_MOVING_STRATEGY_KEY = "M";
    public static final String CHANGE_SHOOTING_MODE_KEY = "N";
    public static final String UNDO_LAST_COMMAND_KEY = "S";
    public static final String INCREASE_MISSILES_KEY = "P";
    public static final String DECREASE_MISSILES_KEY = "O";
    public static final String CANNON_RELOAD_KEY = "R";


    public static final String BACK_IMAGE_RESOURCE = "images/back.jpg";
    public static final String CANNON_IMAGE_RESOURCE = "images/cannon.png";
    public static final String CANNON_SHOOT_SOUND_RESOURCE = "src/main/resources/sounds/shoot.wav";
    public static final String END_GAME_SOUND_RESOURCE = "src/main/resources/sounds/finish.wav";
    public static final String EMPTY_STAR_RESOURCE = "images/emptystar.png";
    public static final String FILLED_STAR_RESOURCE = "images/star.png";
    public static final String MISSILE_A_IMAGE_RESOURCE = "images/missileA.png";
    public static final String MISSILE_B_IMAGE_RESOURCE = "images/missileB.png";
    public static final String ENEMY_A_IMAGE_RESOURCE = "images/enemyA.png";
    public static final String ENEMY_B_IMAGE_RESOURCE = "images/enemyB.png";
    public static final String COLLISION_A_IMAGE_RESOURCE = "images/collisionA.png";
    public static final String COLLISION_B_IMAGE_RESOURCE = "images/collisionB.png";
    public static final String BOUND_IMAGE_RESOURCE = "images/bound.png";
    public static final String BACKGROUND_IMAGE_RESOURCE = "images/menubackground.jpg";


    public static final Map<Integer, Map<String, Object>> LEVEL_ENEMIES = new HashMap<>() {{
        put(1, new HashMap<>() {{
            put("enemies", new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 600);
                    put("y_pos", 200);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 850);
                    put("y_pos", 550);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1000);
                    put("y_pos", 800);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1600);
                    put("y_pos", 1000);
                }});
            }});
            put("expectedScore", 920);
        }});
        put(2, new HashMap<>() {{
            put("enemies", new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 600);
                    put("y_pos", 200);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 850);
                    put("y_pos", 550);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1000);
                    put("y_pos", 300);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1000);
                    put("y_pos", 800);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1200);
                    put("y_pos", 650);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1400);
                    put("y_pos", 450);
                }});
            }});
            put("expectedScore", 1800);
        }});
        put(3, new HashMap<>() {{
            put("enemies", new ArrayList<>() {{
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1000);
                    put("y_pos", 250);
                }});
                add(new HashMap<>() {{
                    put("type", "A");
                    put("x_pos", 1000);
                    put("y_pos", 780);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1100);
                    put("y_pos", 350);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1100);
                    put("y_pos", 680);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1200);
                    put("y_pos", 350);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1200);
                    put("y_pos", 680);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1350);
                    put("y_pos", 600);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1350);
                    put("y_pos", 430);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1500);
                    put("y_pos", 600);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1350);
                    put("y_pos", 430);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1500);
                    put("y_pos", 550);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 1500);
                    put("y_pos", 480);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 400);
                    put("y_pos", 200);
                }});
                add(new HashMap<>() {{
                    put("type", "B");
                    put("x_pos", 400);
                    put("y_pos", 900);
                }});
            }});
            put("expectedScore", 6200);
        }});
    }};
}