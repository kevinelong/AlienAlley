public class Hero extends Sprite{
    Hero(int width, int height, String imagePath) {
        super(width, height, imagePath);
        this.y = height - (2 * image.getHeight());
    }
}
