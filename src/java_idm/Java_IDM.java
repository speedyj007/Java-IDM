
package java_idm;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Java_IDM {

    public static void main(String[] args) {
        
        try{
            System.setProperty("http.agent", "Chrome");
            String url = "https://thumbs2.redgifs.com/RepulsiveFullGangesdolphin-mobile.mp4";
            File name = null;
            name = new File(url);
            
             String file_name = "D:\\prabeer\\Data\\java\\Coding\\Project\\"+name.getName();
            
            download_nio(url,file_name);
            System.out.println("Download Completed. Please verify in saved folder !!!");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    private static void download_nio(String url_str, String file)
    {
        try{
            
            
            URL url = new URL(url_str);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("FIle is downloading. Please wait !!!");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
