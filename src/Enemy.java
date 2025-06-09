public class Enemy extends Sprite{
    Enemy(int width, int height, String imagePath) {
        super(width, height, imagePath);
        this.y = 2 * image.getHeight();
        this.my = 1;
        this.mx = 2;
    }
}
