import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class Main extends Frame implements KeyListener {
    int width = 320;
    int height = 240;
    Hero hero;
    Sprite enemy;
    BufferedImage backBuffer;
    Graphics2D g2d;

    public Main() {
        hero = new Hero(width, height, "hero.png");
        enemy = new Enemy(width, height, "alien.png");
        var spriteList = new ArrayList<Sprite>();
        spriteList.add(hero);
        spriteList.add(enemy);
        setBackground(Color.BLACK);
        addKeyListener(this);
        setSize(width, height); // Set size to match image
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // Create double buffer
        backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = backBuffer.createGraphics();

        var timer = new Timer();
        GameTask task = new GameTask(spriteList, this);
        timer.scheduleAtFixedRate(task, 0, 30);
    }

    @Override
    public void paint(Graphics g) {
        // Draw to the back buffer
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);

        // Draw the back buffer to the screen
        g.drawImage(backBuffer, 0, 0, this);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed " + e);
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.left();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.right();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased " + e);
    }
}
