package com.boot.search.impl;

import com.boot.book.model.Book;
import com.boot.department.model.Department;
import com.boot.department.repository.DepartmentRepository;
import com.boot.search.LibraryQuery;
import com.boot.search.SearchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class SearchServiceImpl implements SearchService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public String searchQuery(LibraryQuery query) {

        String title = query.getTitle();
        String author = query.getAuthor();
        Integer department = query.getDepartment();

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
        targetURL = targetURL +"AND(WFT=BK)";
        if (department != null) {
            if (departmentRepository.exists(department)) {
                Department department1 = departmentRepository.findOne(department);
                targetURL = targetURL + "&local_base=" + department1.getCode();
            }
        }
        return targetURL;
    }

    @Override
    public Collection<Book> searchBook(String URL) {
        Collection<Book> books = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements tables = doc.select("table");

            Element table = tables.get(3);
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                if (tds.size() > 6) {
                    Book book = new Book();
                    book.setAuthor(tds.get(2).text());
                    String title[] = tds.get(3).text().split("/");
                    book.setTitle(title[0]);
                    book.setFormat('b');
                    book.setOwnerType('l');
                    Element link  = tds.get(5);
                    Element Link = link.select("a").first();
                    if (Link != null) {
                        String departmentsURL = Link.attr("abs:href");

                        Document departmentsDoc = Jsoup.connect(departmentsURL).get();

                        Elements departmentsTables = departmentsDoc.select("table");
                        Element departments = departmentsTables.get(5);
                        for (Element element : departments.select("tr")) {
                            Elements elements = element.select("td");
                            String list[] = elements.get(5).text().split(" - ");
                            for (int i = 0 ; i<(list.length-1) ; i++) {
                                String split[] = list[i].split(" ");
                                Department department = departmentRepository.getByCode(split[split.length-1]);
                                if (department != null) {
                                    book.setDepartment(department);
                                    books.add(book);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
