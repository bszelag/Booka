package com.boot.search.impl;

import com.boot.department.model.Department;
import com.boot.department.repository.DepartmentRepository;
import com.boot.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SearchServiceImpl implements SearchService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public String searchQuery(String title, String author, Integer department) {

        HttpURLConnection connection = null;
        String targetURL ="https://katalog.biblioteka.wroc.pl/F?func=find-c&ccl_term=";
        if (title != null && author != null)
            targetURL = targetURL +"(WTI=("+title+"?))AND(WAU="+author+"?)";
        else {
            if (title != null){
                targetURL = targetURL +"(WTI=("+title+"?))";
            }
            else{
                targetURL = targetURL +"(WAU="+author+"?)";
            }
        }
        if (department != null) {
            if (departmentRepository.exists(department)) {
                Department department1 = departmentRepository.findOne(department);
                targetURL = targetURL + "&local_base=" + department1.getCode();
            }
        }
        /*
        try {
            //Create connection
            targetURL = "https://katalog.biblioteka.wroc.pl/F?func=find-c&ccl_term=WRD=(George%20Martin%20Pie%C5%9B%C5%84?)and(WFT=(BK))&local_base=31";
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.write(targetURL.getBytes( StandardCharsets.UTF_8 ));
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception  e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }*/
        return targetURL;
    }
}
