package game;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boar implements Enemy{

    private double health;
    private ImageView imageview = new ImageView();
    private int imageSize = 64;
    private Image image1 = new Image(getClass().getResourceAsStream("res/boar/Boar1.png"), imageSize, imageSize, false, false);
    private Image image2 = new Image(getClass().getResourceAsStream("res/boar/Boar2.png"), imageSize, imageSize, false, false);
    private Image image3 = new Image(getClass().getResourceAsStream("res/boar/Boar3.png"), imageSize, imageSize, false, false);
    private Image imageCharge = new Image(getClass().getResourceAsStream("res/boar/BoarCharge.png"), imageSize, imageSize, false, false);
    private Image imageAttack = new Image(getClass().getResourceAsStream("res/boar/BoarAttack.png"), imageSize, imageSize, false, false);
    Image boarImagesRun[] = {image1, image2, image1, image3};
    Image boarImagesAttack[] = {imageCharge, imageAttack};
    Rectangle healthBar = new Rectangle(70, 10, Color.RED);
    Rectangle healthBarOutline = new Rectangle(71, 11);
    private double dest_x;
    private double dest_y;
    private boolean reDirect = false;
    private int reDirectTimer = 0;
    Random random = new Random();
    private double speed = random.nextDouble(0.5, 2.5);

    public Boar(Double health, double x, double y) {
        this.health = health;
        this.imageview.setImage(image1);
        healthBarOutline.setStyle("-fx-fill: transparent; -fx-stroke: black; -fx-stroke-width: 1;");
        setX(x);
        setY(y);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
        this.healthBar.setWidth(health);
    }

    public void changeHealth(double change) {
        this.health += change;
        this.healthBar.setWidth(this.healthBar.getWidth() + change);
    }

    public ImageView getImageView() {
        return imageview;
    }

    public void setX(double x) {
        this.imageview.setX(x);
    }

    public void setY(double y) {
        this.imageview.setY(y);
    }

    public double getX() {
        return this.imageview.getX();
    }

    public double getY() {
        return this.imageview.getY();
    }

    public Rectangle getRectangle() {
        return this.healthBar;
    }
    
    public Rectangle getRectangleOutline() {
        return this.healthBarOutline;
    }

    public double getDestX() {
        return this.dest_x;
    }

    public double getDestY() {
        return this.dest_y;
    }

    public void setDestX(double x) {
        this.dest_x = x;
    }

    public void setDestY(double y) {
        this.dest_y = y;
    }

    public boolean getRedirect() {
        return this.reDirect;
    }

    public void setRedirect(boolean bool) {
        reDirect = bool;
    }

    public int getRedirectTimer() {
        return this.reDirectTimer;
    }

    public void setRedirectTimer(int tid) {
        reDirectTimer = tid;
    }

    public void incrementRedirectTimer(int tid) {
        reDirectTimer += tid;
    }

    public Image[] getRunninImages(){
        return boarImagesRun;
    }
    
    public Image[] getAttackImages(){
        return boarImagesAttack;
    }

    public double getSpeed() {
        return speed;
    }
}
