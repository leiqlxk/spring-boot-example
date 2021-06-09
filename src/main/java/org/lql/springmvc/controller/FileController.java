package org.lql.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: FileController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 22:58 <br>
 */
@Controller
@RequestMapping("/file")
public class FileController {

    // 使用HttpServletRequest作为参数
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;

        MultipartHttpServletRequest mreq = null;
        // 强转
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        }else {
            return dealResultMap(false, "上传失败");
        }

        // 获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        // 获取源文件名称
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);

        try {
            // 保存文件
            mf.transferTo(file);
        }catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }

        return dealResultMap(true, "上传成功");
    }


    // 使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        }catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    // 直接使用Part类作为参数
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file) {
        String fileName = file.getSubmittedFileName();
        try {
            file.write(fileName);
        }catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("msg", msg);
        return map;
    }
}
