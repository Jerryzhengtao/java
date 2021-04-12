package ntp.xxl.egather.service;

import ntp.xxl.egather.po.Excel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
    List<Excel> findExcels(Long id);
    List<Excel> findAllExcels();
    String getExcel(Long id, HttpServletResponse response);
    String saveExcel(MultipartFile file, String name, HttpSession session) throws IOException;
    void deleteExcel(Long id );
}
