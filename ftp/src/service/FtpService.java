package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Ftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FtpService implements FtpServiceDao {
    @Autowired
    private Ftp ftp;

    /**
     * @param file          表单提交的file数据
     * @param directoryPath 要保存的文件夹地址
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String directoryPath) {
        try {
            ftp.uploadFile(directoryPath, file.getOriginalFilename(), file.getInputStream(), ftp.getFtpClient());
            return Boolean.TRUE.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE.toString();
        }
    }

    /**
     * @param directoryPath 文件夹地址
     * @return
     */
    public Map<String, List<String>> getDirectoryFiles(String directoryPath) {
        return ftp.getDirectoryFiles(directoryPath, ftp.getFtpClient());
    }

    /**
     * @param directoryPath 文件夹地址
     * @return
     */
    @Override
    public JSONObject getCatalog(String directoryPath) {
        Map<String, List<String>> map = getDirectoryFiles(directoryPath);
        JSONObject json = new JSONObject();
        json.put("directory", JSON.toJSON(map.get("directory")));
        json.put("file", JSON.toJSON(map.get("file")));
        return json;
    }
}
