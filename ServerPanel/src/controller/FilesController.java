package controller;

import Server.FileServer;
import com.alibaba.fastjson.JSON;
import model.files.FileContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FilesController {
    @Autowired
    private FileServer fileServer;

    /**
     * 文件管理界面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "files", method = RequestMethod.GET)
    public ModelAndView fille(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("files");

        String path = fileServer.getCookieParam(request, "path");
        if (path == null) {
            path = "/";
        }
        mav.addObject("path", path);
        return mav;
    }

    /**
     * 获取cookie中path路径下所有文件信息
     *
     * @param request
     * @param respons
     * @throws IOException
     */
    @RequestMapping(value = "/getfiles", method = RequestMethod.POST)
    @ResponseBody
    public void getFiles(HttpServletRequest request, HttpServletResponse respons) throws IOException {
        String path = fileServer.getCookieParam(request, "path");
        if (path == null)
            path = "/";
        respons.setCharacterEncoding("utf-8");
        // 输出文件信息
        PrintWriter writer = respons.getWriter();
        String json = fileServer.getFiles(path);
        writer.println(json);
        writer.close();
    }

    /**
     * 新建文件/文件夹/重命名
     *
     * @param type    文件类型（文件f/文件夹d）
     * @param name    新文件名
     * @param cusName 旧文件名
     * @param request
     * @return
     */
    @RequestMapping(value = "/filehandle", method = RequestMethod.POST)
    @ResponseBody
    public String fileHandle(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("cusname") String cusName, HttpServletRequest request) {
        String path = fileServer.getCookieParam(request, "path");
        if (fileServer.file_handle(path, name, type, cusName)) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 上传文件
     *
     * @param mf      上传文件属性
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("upFile") MultipartFile mf, HttpServletRequest request) throws IOException {
        String path = fileServer.getCookieParam(request, "path") + "/" + mf.getOriginalFilename();
        boolean flag = fileServer.saveFile(path, mf.getInputStream());
        return "";
    }


    /**
     * 获取文件内容
     *
     * @param name    文件名
     * @param request
     * @param respons
     * @throws IOException
     */
    @RequestMapping(value = "/getcontent", method = RequestMethod.GET)
    @ResponseBody
    public void getContent(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse respons) throws IOException {
        FileContent fc = new FileContent();
        fc.setName(name);
        fc.setPath(fileServer.getCookieParam(request, "path"));
        fileServer.getContent(fc);
        respons.setCharacterEncoding("utf-8");
        // 输出文件内容
        PrintWriter writer = respons.getWriter();
        writer.println(JSON.toJSON(fc).toString());
        writer.close();
    }

    /**
     * 写入文件内容
     *
     * @param name    文件名
     * @param content 内容
     * @param request
     * @return
     */
    @RequestMapping(value = "/setcontent", method = RequestMethod.POST)
    @ResponseBody
    public String setContent(@RequestParam("name") String name, @RequestParam String content, HttpServletRequest request) {
        FileContent fc = new FileContent();
        fc.setName(name);
        fc.setPath(fileServer.getCookieParam(request, "path"));
        fc.setContent(content);
        if (fileServer.setContent(fc)) {
            return "[true]";
        } else {
            return "[false]";
        }
    }

    /**
     * 删除文件
     *
     * @param name    文件名
     * @param request
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public String remove(@RequestParam("name") String name, HttpServletRequest request) {
        boolean flag = fileServer.remove(fileServer.getCookieParam(request, "path"), name);
        if (flag) {
            return "[true]";
        } else {
            return "[false]";
        }
    }

    /**
     * 文件压缩为zip
     *
     * @param name    文件名
     * @param request
     * @return
     */
    @RequestMapping(value = "/tozip", method = RequestMethod.GET)
    @ResponseBody
    public String tozip(@RequestParam("name") String name, HttpServletRequest request) {
        boolean flag = fileServer.fileToZip(fileServer.getCookieParam(request, "path"), name);
        if (flag) {
            return "[true]";
        } else {
            return "[false]";
        }
    }

    /**
     * zip文件解压
     *
     * @param name    文件名
     * @param request
     * @return
     */
    @RequestMapping(value = "/tofiles", method = RequestMethod.GET)
    @ResponseBody
    public String tofile(@RequestParam("name") String name, HttpServletRequest request) {
        boolean flag = fileServer.zipToFiles(fileServer.getCookieParam(request, "path"), name);
        if (flag) {
            return "[true]";
        } else {
            return "[false]";
        }
    }

    /**
     * 文件剪切（shear）
     * 文件复制（copy）
     *
     * @param path    需处理的文件路径
     * @param name    文件名
     * @param type    操作类型
     * @param request 获取cookie中当前操作路径
     * @return
     */
    @RequestMapping(value = "/paste", method = RequestMethod.GET)
    @ResponseBody
    public String filePaste(@RequestParam("path") String path, @RequestParam("name") String name, @RequestParam("type") String type, HttpServletRequest request) {
        // 当前操作路径
        String cupath = fileServer.getCookieParam(request, "path");
        boolean flag = fileServer.filePaste(path, type, cupath + "/" + name);
        if (flag) {
            return "[true]";
        } else {
            return "[false]";
        }
    }
}
