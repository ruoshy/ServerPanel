package model;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Ftp {
    // 本地字符编码
    private static String LOCAL_CHARSET = "GBK";
    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";

    /**
     * 获取ftp服务器连接
     *
     * @return ftp服务器连接
     */
    public FTPClient getFtpClient() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect("101.132.106.24", 21);// 连接FTP服务器
            ftpClient.login("adming", "asd456");// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP。");
                ftpClient.disconnect();
            } else {
                System.out.println("连接成功。");
            }
//            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
//                LOCAL_CHARSET = "UTF-8";
//            }
//            ftpClient.setControlEncoding(LOCAL_CHARSET);
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接失败！");
        }
        return ftpClient;
    }

    /**
     * 上传文件
     *
     * @param pathname    文件在ftp服务器存储位置
     * @param fileName    文件在ftp服务器存储的名字
     * @param inputStream 文件输入流
     * @param ftpClient   ftp服务器连接
     * @return 是否上传成功
     */
    public boolean uploadFile(String pathname, String fileName, InputStream inputStream, FTPClient ftpClient) {
        boolean storeFlag = false;
        try {
            System.out.println("开始上传文件");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            // 观察是否上传成功
            storeFlag = ftpClient.storeFile(fileName, inputStream);
            //new String(fileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET)
            inputStream.close();
            ftpClient.logout();
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            storeFlag = false;
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return storeFlag;
    }

    /**
     * 获取列表内文件名称
     *
     * @param DirectoryPath 文件夹路径
     * @param ftpClient     ftp服务器连接
     * @return 文件名称map
     */
    public Map<String, List<String>> getDirectoryFiles(String DirectoryPath, FTPClient ftpClient) {
        Map<String, List<String>> filemap = new HashMap<>();
        List<String> directorys = new ArrayList<>();
        List<String> files = new ArrayList<>();
        try {
            FTPFile[] dfiles = ftpClient.listFiles(DirectoryPath);
            for (int i = 2; i < dfiles.length; i++) {
                if (dfiles[i].isDirectory()) {
                    directorys.add(new String(dfiles[i].getName().getBytes(SERVER_CHARSET), "UTF-8"));
                } else {
                    files.add(new String(dfiles[i].getName().getBytes(SERVER_CHARSET), "UTF-8"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            filemap.put("directory", directorys);
            filemap.put("file", files);
        }
        return filemap;
    }

//    public Map<String, Boolean> getDirectoryFiles(String DirectoryPath, FTPClient ftpClient) {
//        Map<String, Boolean> filemap = new HashMap<String, Boolean>();
//        try {
//            FTPFile[] files = ftpClient.listFiles(DirectoryPath);
//            for (int i = 2; i < files.length; i++)
//                filemap.put(new String(files[i].getName().getBytes(SERVER_CHARSET), "UTF-8"), files[i].isDirectory());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftpClient.isConnected()) {
//                try {
//                    ftpClient.disconnect();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return filemap;
//    }
}
