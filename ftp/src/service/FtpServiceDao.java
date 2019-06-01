package service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FtpServiceDao {
    /**
     * 文件上传
     *
     * @param file          表单提交的file数据
     * @param directoryPath 要保存的文件夹地址
     * @return 是否成功提交
     */
    String uploadFile(MultipartFile file, String directoryPath);

    /**
     * 获取文件夹下文件信息
     *
     * @param directoryPath 文件夹地址
     * @return 文件以及文件夹map
     */
    Map<String, List<String>> getDirectoryFiles(String directoryPath);

    /**
     * 获取指定路径下文件信息
     *
     * @param directoryPath 文件夹地址
     * @return 文件信息json
     */
    JSONObject getCatalog(String directoryPath);
}
