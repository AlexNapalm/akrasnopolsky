package ru.job4j.todolist.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.models.Item;
import ru.job4j.todolist.dao.ItemDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

public class TodolistController extends HttpServlet{

    ItemDao dao = new ItemDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Item> items;

        if (req.getParameter("include_done").equals("true")) {
            items = dao.getAll();
        } else {
            items = dao.getUndone();
        }

        JSONArray jsonArray = new JSONArray();
        for (Item item : items) {
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
            String created = df.format(item.getCreated()).toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("desc", item.getDesc());
            jsonObject.put("created", created);
            jsonObject.put("done", item.isDone());
            jsonArray.add(jsonObject);
        }

        Writer writer = resp.getWriter();
        writer.append(jsonArray.toJSONString());

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("item_description");
        String id = req.getParameter("item_id");
        String done = req.getParameter("item_done");

        if (description != null) {
            Item item = new Item();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setDesc(description);
            item.setCreated(timestamp);
            dao.addOrUpdate(item);
        }

        if (id != null && done != null) {
            dao.toggleDone(Integer.valueOf(id), Boolean.valueOf(done));
        }
    }

    @Override
    public void destroy() {
        dao.closeConnection();
        super.destroy();
    }
}
