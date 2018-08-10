import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * @Author: shenxingbo
 * @Description:
 * @Date: 2018/8/10下午1:45
 **/

public class FTPTest {

    @Test
    public void test() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.56.3", 21);
        boolean status = ftpClient.login("ftpuser", "ftpuser");

        FileInputStream fis = new FileInputStream(new File("/Users/shenxingbo/Desktop/ceshi.png"));
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.storeFile("ceshi.png", fis);
        ftpClient.logout();
    }
}
