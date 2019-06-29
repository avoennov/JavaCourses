package lessons.les2;

public class GetDistance {

    public static void main(String[] args){

        Point p1 = new Point(1,2);
        Point p2 = new Point(6,2);

        System.out.println("Расстояние между точками P1 и P2 составляет: " + Point.distance(p1, p2));
    }
}
