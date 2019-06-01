package dao;

import model.files.FilesInfo;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Component
public class FileImpl {

    /**
     * 获取当前路径下文件信息
     *
     * @param path 路径
     * @return
     */
    public List<FilesInfo> getFilesInfo(String path) {
        // 获取文件属性文件数量上限
        int count = 100;

        File file = new File(path);
        File[] files = file.listFiles();
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        // 文件夹列表
        List<FilesInfo> listd = new ArrayList<FilesInfo>();
        // 文件列表
        List<FilesInfo> listf = new ArrayList<FilesInfo>();
        for (File f : files) {
            // 文件最后修改日期
            data.setTime(f.lastModified());
            // 文件信息
            FilesInfo fi = new FilesInfo();
            fi.setName(f.getName());
            fi.setSize((int) Math.ceil(f.length() / 1000.0));
            fi.setLastModified(sdf.format(data));

            // 判断是否是文件夹，添加到对应列表
            if (f.isDirectory()) {
                if (f.getName().charAt(0) == '.') {
                    fi.setDirectory(false);
                    listf.add(fi);
                } else {
                    fi.setDirectory(true);
                    listd.add(fi);
                }
            } else {
                fi.setDirectory(false);
                listf.add(fi);
            }
            // 列表容量
            count--;
            if (count == 0) break;
        }
        listd.addAll(listf);
        return listd;
    }

    public void setFile(FileOutputStream fos, InputStream is) throws IOException {
        byte[] b = new byte[4096];
        int length;
        while ((length = is.read(b)) != -1) {
            fos.write(b, 0, length);
        }
    }

    /**
     * 新建文件夹
     *
     * @param path 路径
     * @return
     */
    public boolean mkdir(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            flag = file.mkdir();
        }
        return flag;
    }

    /**
     * 创建文件
     *
     * @param path 路径
     * @return
     * @throws IOException
     */
    public boolean createNewFile(String path) throws IOException {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            flag = file.createNewFile();
        }
        return flag;
    }

    /**
     * 文件重命名
     *
     * @param path    文件路径
     * @param name    新文件名
     * @param cusName 旧文件名
     * @return
     */
    public boolean modifyName(String path, String name, String cusName) {
        boolean flag = false;
        File file = new File(path + "/" + cusName);
        if (file.exists()) {
            flag = file.renameTo(new File(path + "/" + name));
        }
        return flag;
    }

    /**
     * 保存文件
     *
     * @param path 文件路径
     * @param is   输出流
     * @throws IOException
     */
    public void saveFile(String path, InputStream is) throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file, true);
        setFile(fos, is);
        fos.close();
    }

    /**
     * 获取文件内容
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String getContent(File file) throws IOException {
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        read.close();
        if (sb.length() > 0)
            return sb.delete(sb.length() - 1, sb.length()).toString();
        else return "";
    }

    /**
     * 写入文件内容
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public void setContent(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public boolean remove(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                remove(f);
            }
        } else {
            return file.delete();
        }
        return file.delete();
    }

    /**
     * 文件转zip
     *
     * @param file    文件
     * @param zos     zip输出流
     * @param baseDir zip内路径
     * @throws IOException
     */
    public void fileToZip(File file, ZipOutputStream zos, String baseDir) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        zos.putNextEntry(new ZipEntry(baseDir + file.getName()));
        int length;
        byte[] b = new byte[4096];
        while ((length = fis.read(b)) != -1) {
            zos.write(b, 0, length);
        }
        fis.close();
    }

    /**
     * 文件夹转zip
     *
     * @param file    文件夹
     * @param zos     zip输出流
     * @param baseDir zip内路径
     * @throws IOException
     */
    public void filesToZip(File file, ZipOutputStream zos, String baseDir) throws IOException {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                zos.putNextEntry(new ZipEntry(baseDir + f.getName() + "/"));
                filesToZip(f, zos, baseDir + f.getName() + "/");
            } else
                fileToZip(f, zos, baseDir);
        }
    }

    /**
     * zip解压
     *
     * @param path
     * @param name
     * @throws IOException
     */
    public void zipToFiles(String path, String name) throws IOException {
        File f = new File(path + "/" + name);
        ZipFile zip = new ZipFile(f);
        Enumeration<?> entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                File file = new File(f.getParent() + "/" + entry.getName());
                file.mkdirs();
            } else {
                File file = new File(f.getParent() + "/" + entry.getName());
                if (file.isHidden())
                    continue;
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = zip.getInputStream(entry);
                setFile(fos, is);
                is.close();
                fos.close();
            }
        }
    }

    /**
     * 文件复制
     *
     * @param file 文件
     * @param path 路径
     * @throws IOException
     */
    public void fileCopy(File file, String path) throws IOException {
        File f = new File(path);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = fis.read(b)) != -1) {
                fos.write(b, 0, length);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 文件剪切
     *
     * @param file 文件
     * @param path 路径
     * @throws IOException
     */
    public void fileShear(File file, String path) throws IOException {
        File newFile = new File(path);
        file.renameTo(newFile);
    }

}
