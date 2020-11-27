
import edu.princeton.cs.algs4.*;
import plt.Plot;

import java.util.Arrays;

public class JFreeChartTest {
    public static void main(String[] args) {
        plt.Plot plot = new Plot();
        String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

//        Double[] doubles = new Double[] { 49.9,50.0,41.0,52.0,54.0,57.0,54.0,53.2,47.6 ,54.0,53.2,47.6 };
        Double[] doubles = new Double[]{0.0,1.0,1.0,1.0,0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0};
        plot.plot(Arrays.asList(categories),Arrays.asList(doubles),"lol.png","dota","win_rate","month");
//        plot.plot(null,doubles,"lol.png","dota","win_rate","month");// Illegal ,can not be null
        // pay attention to that the length of doubleList and stringList must be the same or it will return " data load failed"
    }

}
