package creational.Prototype;


class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        Swordsman s1 = new Swordsman();
        s1.move(new Point3D(-10,0,0), 20);
        s1.attack();

        System.out.println(s1);
        Swordsman s2 = (Swordsman)s1.clone();
        System.out.println("Cloned swordsman"+s2);

    }

}


/**
 * classe base para o exemplo
 */
class Point3D {

    private float x, y, z;

    public static final Point3D ZERO = new Point3D(0, 0, 0);

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D normalize() {
        float mag = magnitude();
        return new Point3D(x/mag, y/mag, z/mag);
    }

    private float magnitude() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public Point3D multiply(float scale) {
        return new Point3D(x*scale, y*scale, z*scale);
    }

    public Point3D add(Point3D vect) {
        return new Point3D(x+vect.x, y+vect.y, z+vect.z);
    }
}


abstract class GameUnit implements Cloneable {

    private Point3D position;

    public GameUnit() {
        position = Point3D.ZERO;
    }


    @Override
    public GameUnit clone() throws CloneNotSupportedException {
        GameUnit unit = (GameUnit)super.clone();
        unit.initialize();
        return unit;
    }

    protected void initialize() {
        this.position = Point3D.ZERO;
        reset();
    }

    protected abstract void reset();

    public GameUnit(float x, float y, float z) {
        position = new Point3D(x, y, z);
    }

    public void move(Point3D direction, float distance) {
        Point3D finalMove = direction.normalize();
        finalMove = finalMove.multiply(distance);
        position = position.add(finalMove);
    }

    public Point3D getPosition() {
        return position;
    }
}


class General extends GameUnit{

    private String state = "idle";

    public void boostMorale() {
        this.state = "MoralBoost";
    }

    @Override
    public String toString() {
        return "General "+state+" @ "+getPosition();
    }

    @Override
    public GameUnit clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Ganerals are unique");
    }

    @Override
    protected void reset() {
        throw new UnsupportedOperationException("Reset not supported");
    }

}


class Swordsman extends GameUnit {

    private String state = "idle";

    public void attack() {
        this.state = "attacking";
    }

    @Override
    public String toString() {
        return "Swordsman "+state+" @ "+getPosition();
    }

    @Override
    protected void reset() {
        state = "idle";
    }


}
