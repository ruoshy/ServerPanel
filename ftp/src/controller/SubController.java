package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.FtpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@Controller
public class SubController {
    @Autowired
    private FtpService ftpService;

    @RequestMapping("/hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse respons) {
        ModelAndView mav = new ModelAndView("index");
        Map<String, List<String>> map = ftpService.getDirectoryFiles("/");
        mav.addObject("file", map.get("file"));
        return mav;
    }

    @RequestMapping("/v")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse respons) {
        ModelAndView mav = new ModelAndView("view");
        Map<String, List<String>> map = ftpService.getDirectoryFiles("/");
        mav.addObject("directory", map.get("directory"));
        mav.addObject("file", map.get("file"));
        return mav;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("upFile") MultipartFile file, @RequestParam("dp") String directoryPath) {
        return ftpService.uploadFile(file, directoryPath);
    }

    @RequestMapping(value = "/glist", method = RequestMethod.GET)
    @ResponseBody
    public void openCatalog(@RequestParam("dp") String directoryPath, HttpServletResponse respons) throws IOException {
        respons.setCharacterEncoding("utf-8");
        PrintWriter writer = respons.getWriter();
        String json = ftpService.getCatalog(directoryPath).toJSONString();
        writer.println(json);
    }
}
