package ru.job4j.carsale.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carsale.dao.AdDao;
import ru.job4j.carsale.dao.CarBrandDao;
import ru.job4j.carsale.dao.CarTypeDao;
import ru.job4j.models.Ad;
import ru.job4j.models.CarBrand;
import ru.job4j.models.CarType;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Update extends HttpServlet {
    private final String directory = getServletContext().getInitParameter("upload-directory");
    private final String uploadPath = getServletContext().getRealPath("") + File.separator + directory;
    private final int maxFileSize = Integer.valueOf(getServletContext().getInitParameter("max-file-size"));
    private final AdDao adDao = new AdDao();
    private final CarBrandDao carBrandDao = new CarBrandDao();
    private final CarTypeDao carTypeDao = new CarTypeDao();

    @Override
    public void init() throws ServletException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("carbrands", carBrandDao.getAll());
        req.setAttribute("cartypes", carTypeDao.getAll());

        int id = Integer.valueOf(req.getParameter("id"));
        Ad ad = adDao.getById(id);
        req.setAttribute("ad", ad);
        req.getRequestDispatcher("WEB-INF/view/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ad ad = new Ad();
        ad.setId(Integer.valueOf(req.getParameter("id")));
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setFileSizeMax(maxFileSize);
        try {
            java.util.List<FileItem> parts = upload.parseRequest(req);

            for (FileItem item : parts) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case("sold"):
                            ad.setSold(true);
                            break;
                        case("carbrand"):
                            Integer carbrandId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setCarbrand((new CarBrand(carbrandId)));
                            break;
                        case("model"):
                            ad.setModel(item.getString("UTF-8"));
                            break;
                        case ("description"):
                            ad.setDescription(item.getString("UTF-8"));
                            break;
                        case("year"):
                            ad.setYear(Integer.valueOf(item.getString("UTF-8")));
                            break;
                        case("cartype"):
                            Integer cartypeId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setCartype(new CarType(cartypeId));
                            break;
                        case("price"):
                            ad.setPrice(Integer.valueOf(item.getString("UTF-8")));
                            break;
                        case("user"):
                            Integer userId = Integer.valueOf(item.getString("UTF-8"));
                            ad.setUser(new User(userId));
                            break;
                        default:
                            break;
                    }
                }
                if (!item.isFormField()) {
                    if (item.getFieldName().equals("picture")) {
                        String fileName = item.getName();
                        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                            String ext =  fileName.substring(fileName.lastIndexOf("."));
                            item.write(new File(uploadPath + File.separator + ad.getId() + ext));
                        }
                    }
                }
            }
            ad.setCreated(new Timestamp(new Date().getTime()));
            adDao.update(ad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }

    @Override
    public void destroy() {
        adDao.close();
        carBrandDao.close();
        carTypeDao.close();
    }
}
