package org.lql.springmvc.controller;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.lql.springmvc.service.MvcUserService;
import org.lql.springmvc.view.PdfExportService;
import org.lql.springmvc.view.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;
import java.util.List;

/**
 * Title: UserController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 16:04 <br>
 */
@Controller
@RequestMapping("/mvcUserController")
public class MvcUserController {

    @Autowired
    private MybatisUserService userService;

    // 展示用户详情
    @RequestMapping("/details")
    public ModelAndView details(Long id) {
        User user = userService.getUser(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/details");
        modelAndView.addObject("user", user);

        return modelAndView;
//        return "user/details";
    }


    @RequestMapping("/table")
    public ModelAndView table() {
        List<User> userList = userService.findUsers(null, null);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList", userList);

        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "note", required = false) String note) {
        List<User> userList = userService.findUsers(userName, note);

        return userList;
    }

    @RequestMapping("/export/pdf")
    public ModelAndView exportPdf(String userName, String note) {
        List<User> userList = userService.findUsers(userName, note);

        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        mv.setView(view);
        mv.addObject("userList", userList);

        return mv;
    }

    private PdfExportService exportService() {
        return (model, document, writer, request, response) -> {
            try {
                // A4纸张
                document.setPageSize(PageSize.A4);
                // 标题
                document.addTitle("用户信息");
                // 换行
                document.add(new Chunk("\n"));
                // 表格，3列
                PdfPTable table = new PdfPTable(3);
                // 单元格
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                // 标题
                cell = new PdfPCell(new Paragraph("id", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);

                // 获取数据模型中的用户列表
                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }

                // 在文档中加入表格
                document.add(table);
            }catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
