package cn.cie.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/10.
 */
public class CommonTest {

    @Test
    public void createSql() {
        int nums = 31;
        List<Integer> sixPics = new ArrayList<Integer>();
        sixPics.add(7);
        sixPics.add(10);
        sixPics.add(11);
        sixPics.add(12);
        sixPics.add(13);
        sixPics.add(16);
        sixPics.add(17);
        sixPics.add(18);
        sixPics.add(21);
        sixPics.add(30);
        sixPics.add(31);
        for (int i = 1 ; i <= nums ; ++i) {
            System.out.print("(" + i + ", '/" + i + "/header.jpg'), ");
            if (sixPics.contains(i)) {
                for (int j = 1 ; j <= 6 ; ++j) {
                    System.out.print("(" + i + ", '/" +i + "/" + j + ".jpg'), ");
                }
            } else {
                for (int j = 1 ; j <= 5 ; ++j) {
                    System.out.print("(" + i + ", '/" +i + "/" + j + ".jpg'), ");
                }
            }
        }
    }
}
