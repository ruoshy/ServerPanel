package Server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import dao.FileImpl;
import model.files.FileContent;
import model.files.FilesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileServer {

    @Autowired
    private FileImpl fileDao;

    /**
     * 获取路径下文件信息
     *
     * @param path 路径
     * @return
     */
    public String getFiles(String path) {
        // 文件列表
        List<FilesInfo> list = fileDao.getFilesInfo(path);
        // 输入文件列表为json
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(list));
        return json.toJSONString();
    }

    /**
     * 文件处理
     * 创建文件夹
     * 重命名
     *
     * @param path    路径
     * @param name    新文件名
     * @param type    文件类型
     * @param cusName 旧文件名
     * @return
     */
    public boolean file_handle(String path, String name, String type, String cusName) {
        boolean flag = false;

        if (path == null) {
            return flag;
        }
        if (type.equals("f")) {
            try {
                // 创建文件
                flag = fileDao.createNewFile(path + "/" + name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("d")) {
            // 创建文件夹
            flag = fileDao.mkdir(path + "/" + name);
        } else {
            // 重命名
            flag = fileDao.modifyName(path, name, cusName);
        }
        return flag;
    }

    /**
     * 获取cookie中属性
     *
     * @param request
     * @param name    属性名
     * @return
     */
    public String getCookieParam(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param path 保存路径
     * @param is   输入流
     * @return
     */
    public boolean saveFile(String path, InputStream is) {
        try {
            fileDao.saveFile(path, is);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取文件内容
     *
     * @param fc
     */
    public void getContent(FileContent fc) {
        try {
            File file = new File(fc.getPath() + "/" + fc.getName());
            if (file.exists())
                fc.setContent(fileDao.getContent(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件内容
     *
     * @param fc
     * @return
     */
    public boolean setContent(FileContent fc) {
        try {
            File file = new File(fc.getPath() + "/" + fc.getName());
            if (file.exists()) {
                fileDao.setContent(file, fc.getContent());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param path 路径
     * @param name 文件名
     * @return
     */
    public boolean remove(String path, String name) {
        File file = new File(path + "/" + name);
        if (file.exists()) {
            return fileDao.remove(file);
        }
        return false;
    }

    /**
     * 递归文件夹/文件夹
     * 压缩为zip
     *
     * @param path 当前路径
     * @param name 文件名
     * @return
     */
    public boolean fileToZip(String path, String name) {
        File f = new File(path + "/" + name);
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(new File(path + "/" + name.split("[.]")[0] + ".zip"));
            zos = new ZipOutputStream(fos);
            // 判断文件是否是文件夹
            if (f.isDirectory()) {
                // 压缩文件内路径
                String baseDir = f.getName() + "/";
                // 写入压缩文件内当前路径
                zos.putNextEntry(new ZipEntry(baseDir));
                fileDao.filesToZip(f, zos, baseDir);
            } else {
                fileDao.fileToZip(f, zos, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (zos != null)
                    zos.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 解压zip压缩文件
     *
     * @param path
     * @param name
     * @return
     */
    public boolean zipToFiles(String path, String name) {
        try {
            fileDao.zipToFiles(path, name);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 文件剪切
     * 文件复制
     *
     * @param path    原来路径
     * @param type    操作类型
     * @param newPath 新路径
     * @return
     */
    public boolean filePaste(String path, String type, String newPath) {
        try {
            File file = new File(path);
            // 判断路径是否存在
            if (file.exists()) {
                if (type.equals("shear")) {
                    // 剪切
                    fileDao.fileShear(file, newPath);
                } else {
                    // 复制
                    fileDao.fileCopy(file, newPath);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}