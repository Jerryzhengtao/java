package ntp.xxl.egather.service.impl;

import ntp.xxl.egather.dao.ExcelRepository;
import ntp.xxl.egather.po.Excel;
import ntp.xxl.egather.po.User;
import ntp.xxl.egather.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Value("${savePath}")
    String path;

    @Autowired
    ExcelRepository excelRepository;

    @Override
    public List<Excel> findExcels(Long id) {
        return excelRepository.findAllByUserId(id);
    }

    @Override
    public String getExcel(Long id, HttpServletResponse response) {
        Excel excel = excelRepository.getById(id);
        if (excel != null) {
            //待下载文件名
            String fileName = excel.getData();
            //设置为png格式的文件
            response.setHeader("content-type", "excel");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + excel.getName());
            byte[] buff = new byte[1024];
            //创建缓冲输入流
            BufferedInputStream bis = null;
            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();

                //这个路径为待下载文件的路径
                bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
                int len = 0;
                //通过while循环写入到指定了的文件中
                while ((len = bis.read(buff))!= -1) {
                    outputStream.write(buff, 0, len);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String saveExcel(MultipartFile file, String name, HttpSession session) throws IOException {
        String msg = "";
        User user = (User) session.getAttribute("user");
        if (user == null) {
            msg = "noUser";
        } else {
            try {
                String filename = UUID.randomUUID().toString().substring(0, 6) + "_" + name;
                Excel excel = new Excel();
                excel.setName(name);
                excel.setData(path + filename);
                excel.setTime(new Date());
                excel.setUser(user);
                excel.setNickname(user.getNickName());
                excelRepository.save(excel);
                File desFile = new File(path + filename);
                file.transferTo(desFile);
                msg = "上传成功!";
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }

    @Override
    public void deleteExcel(Long id) {
        excelRepository.deleteById(id);
    }

    @Override
    public List<Excel> findAllExcels() {
        return excelRepository.findAll();
    }
}

