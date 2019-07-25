package com.wenzi.yuyin.Controller;

import com.wenzi.yuyin.Model.Person;
import com.wenzi.yuyin.uitls.ExportExcel;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonAction {
    //导出excel表
    public String exportExcel() throws Exception {

        // 初始化HttpServletResponse对象
        HttpServletResponse response = ServletActionContext.getResponse();

        // 定义表的标题
        String title = "员工列表一览";

        //定义表的列名
        String[] rowsName = new String[] { "学号", "姓名", "签到", "迟到", "请假",
                "旷课" };

        //定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        List<Person> listPerson = ps.listPerson();
        for (int i = 0; i < listPerson.size(); i++) {
            Person per = listPerson.get(i);
            objs = new Object[rowsName.length];
            objs[0] = per.getPid();
            objs[1] = per.getPname();
            objs[2] = per.getPsex();
            objs[3] = per.getSkilled();
            objs[4] = per.getDegree();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(per.getJobtime());
            objs[5] = date;
            objs[6] = per.getResume();
            objs[7] = per.getFilepath();
            objs[8] = per.getDept().getDname();
            dataList.add(objs);
        }

        // 创建ExportExcel对象
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);

        // 输出Excel文件
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition",
                    "attachment; filename="+new String("员工表".getBytes("gbk"), "iso8859-1")+".xls");
            response.setContentType("application/msexcel");
            response.setCharacterEncoding("utf-8");
            ex.export(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "tolist";// 返回列表显示
    }

}
