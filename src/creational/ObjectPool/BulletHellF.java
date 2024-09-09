package creational.ObjectPool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class BulletPool {
    private List<Bullet> availableBullets;
    private static final int MAX_POOL_SIZE = 10000;

    public BulletPool() {
        availableBullets = new ArrayList<>();
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            availableBullets.add(new Bullet());
        }
    }

    public Bullet acquireBullet() {
        if (availableBullets.isEmpty()) {
            return null; // ou criar uma nova bala se preferir
        }
        Bullet bullet = availableBullets.remove(availableBullets.size() - 1);
        return bullet;
    }

    public void releaseBullet(Bullet bullet) {
        bullet.deactivate();
        availableBullets.add(bullet);
    }
}


class Bullet {
    public int x, y;
    public int speedX, speedY;
    public boolean active;

    public Bullet() {
        this.active = false;
    }

    public void activate(int startX, int startY, int speedX, int speedY) {
        this.x = startX;
        this.y = startY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void update() {
        if (active) {
            x += speedX;
            y += speedY;
        }
    }

    public void draw(java.awt.Graphics g) {
        if (active) {
            g.fillOval(x, y, 5, 5);
        }
    }
}
class BulletHell extends JPanel {


    private List<Bullet> bullets = new CopyOnWriteArrayList<>();

    private BulletPool bulletPool = new BulletPool();
    private boolean usePool;

    public BulletHell(boolean usePool) {
        this.usePool = usePool;
    }

    public void spawnBullet(int x, int y, int speedX, int speedY) {
        for (int i = 0; i < 100; i++) { // Criar duas balas em vez de uma
            Bullet bullet;
            if (usePool) {
                bullet = bulletPool.acquireBullet();
                if (bullet != null) {
                    bullet.activate(x, y, i * 2, 5); // Múltiplas direções
                    bullets.add(bullet);
                }
            } else {
                bullet = new Bullet();
                bullet.activate(x, y, i * 2, 5); // Múltiplas direções
                bullets.add(bullet);
            }
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
        }
    }

    public void updateGame() {
        List<Bullet> bulletsToRemove = new ArrayList<>();

        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                bullet.update();
                if (bullet.x < 0 || bullet.x > getWidth() || bullet.y < 0 || bullet.y > getHeight()) {
                    bulletsToRemove.add(bullet);
                    if (usePool) {
                        bulletPool.releaseBullet(bullet);
                    }
                }
            }

            bullets.removeAll(bulletsToRemove);
        }
    }




    public static void main(String[] args) {
        boolean usePool = false; // Alterne entre true e false para comparar performance

        JFrame frame = new JFrame("Bullet Hell");
        BulletHell game = new BulletHell(usePool);
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 500.0;
        double delta = 0;
        int updates = 0;
        int frames = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                game.updateGame();
                game.spawnBullet((int) (Math.random() * game.getWidth()), 0, 0, 10);
                updates++;
                delta--;
            }

            game.repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " | Updates: " + updates);
                updates = 0;
                frames = 0;
            }
        }
    }
}