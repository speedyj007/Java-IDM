package java_idm;

import java.text.DecimalFormat;

public class testing_file {
    
    public static void main(String[] args) {
        double f =  0;
        
        f = 142.25444;
        
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println(df.format(f));
    }
}
