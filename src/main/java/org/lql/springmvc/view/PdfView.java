package org.lql.springmvc.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Title: PdfView <br>
 * ProjectName: spring-boot-example <br>
 * description: PDF导出视图类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 22:26 <br>
 */
public class PdfView extends AbstractPdfView {

    private PdfExportService pdfExportService;

    public PdfView(PdfExportService pdfExportService) {
        this.pdfExportService = pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        pdfExportService.make(map, document, pdfWriter, httpServletRequest, httpServletResponse);
    }
}
