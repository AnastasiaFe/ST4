package ua.nure.fedorenko.SummaryTask4.web.command;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.fedorenko.SummaryTask4.Path;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.TariffDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.Tariff;
import ua.nure.fedorenko.SummaryTask4.exception.AppException;
import ua.nure.fedorenko.SummaryTask4.web.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


class DownloadCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
       int tariffId=Integer.parseInt(request.getParameter(Constant.TARIFF_ID));
        DAOFactory daoFactory=new MySqlDAOFactory();
        TariffDAO tariffDAO=daoFactory.getTariffDAO();
        Tariff tariff=tariffDAO.getById(tariffId);
        String fileName="Tariff"+tariff.getTariffId()+".pdf";
        String tariffName=tariff.getName();
        String tariffDesc=tariff.getDesc();
        String tariffPrice=String.valueOf(tariff.getPrice());
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        response.setContentType("application/pdf");
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();
            // step 4
            Font redFont = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, new CMYKColor(0, 255, 0, 0));
            Font blackFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 0, 0, 255));
            document.add(new Paragraph("Tariff name: ",redFont));
            document.add(new Paragraph(tariffName,blackFont));
            document.add(new Paragraph("Tariff description: ",redFont));
            document.add(new Paragraph(tariffDesc,blackFont));
            document.add(new Paragraph("Price: ",redFont));
            document.add(new Paragraph(tariffPrice,blackFont));
            document.add(new Paragraph(new Date().toString(),blackFont));
            // step 5
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
        /**try {
            InputStream input = new ByteArrayInputStream(text.getBytes("UTF8"));

            int read = 0;
            byte[] bytes = new byte[4096];
            OutputStream os = response.getOutputStream();

            //data form resultset

            while ((read = input.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (Exception e) {
            // TODO: handle exception
        }*/
        request.setAttribute(Constant.SERVICE_ID,tariff.getService().getServiceId());
        return Path.ADMIN_SERVICE_TARIFF;

    }
}
