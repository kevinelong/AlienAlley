import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.TimerTask;

public class GameTask extends TimerTask {
    ArrayList<Sprite> spriteList;
    Main game;

    GameTask(ArrayList<Sprite> spriteList, Main game) {
        this.game = game;
        this.spriteList = spriteList;
    }

    @Override
    public void run() {
        spriteList.forEach(s -> s.update(100));

        // Collision detection for all sprites
        for (int i = 0; i < spriteList.size(); i++) {
            for (int j = i + 1; j < spriteList.size(); j++) {
                Sprite sprite1 = spriteList.get(i);
                Sprite sprite2 = spriteList.get(j);

                Rectangle2D rect1 = new Rectangle2D.Double(sprite1.getX(), sprite1.getY(), sprite1.getImage().getWidth(), sprite1.getImage().getHeight());
                Rectangle2D rect2 = new Rectangle2D.Double(sprite2.getX(), sprite2.getY(), sprite2.getImage().getWidth(), sprite2.getImage().getHeight());

                if (rect1.intersects(rect2)) {
                    System.out.println("Collision between " + sprite1.getClass().getSimpleName() + " and " + sprite2.getClass().getSimpleName());
                    // Add your collision handling logic here.  This could be different depending on the sprites involved.
                    sprite2.x = (int) (Math.random() * (game.width - sprite2.getImage().getWidth()));
                    sprite2.y = (int) (Math.random() * (game.height - sprite2.getImage().getHeight()));
                }
            }
        }
        //game.repaint();
    }
}