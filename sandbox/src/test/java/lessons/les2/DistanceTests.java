package lessons.les2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void testDistanceInt(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(10,3);
        Assert.assertEquals(p1.distanceMethod(p2), 8.0);
    }

    @Test
    public void testDistanceDouble(){
        Point p1 = new Point(1.5,2.1);
        Point p2 = new Point(5.3,0.22);
        Assert.assertEquals(p1.distanceMethod(p2), 4.239622624715554);
    }

    @Test
    public void testDistanceNeg(){
        Point p1 = new Point(-3,2);
        Point p2 = new Point(6,-6);
        Assert.assertEquals(p1.distanceMethod(p2), 12.041594578792296);
    }

    @Test
    public void testDistanceZeroIn(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distanceMethod(p2), 3.605551275463989);
    }

    @Test
    public void testDistanceZeroOut(){
        Point p1 = new Point(5,1);
        Point p2 = new Point(5,1);
        Assert.assertEquals(p1.distanceMethod(p2), 0.0);
    }

}
