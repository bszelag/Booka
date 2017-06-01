package com.boot.search.impl;

import com.boot.book.model.Book;
import com.boot.institution.department.model.Department;
import com.boot.institution.department.repository.DepartmentRepository;
import com.boot.search.LibraryQuery;
import com.boot.search.SearchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchServiceImpl implements SearchService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public String searchQuery(LibraryQuery query) {

        String title = query.getTitle();
        String author = query.getAuthor();
        Integer department = query.getDepartment();

        String targetURL ="https://katalog.biblioteka.wroc.pl/F?func=find-c&ccl_term=";
        if (title != null && author != null && !Objects.equals(title, "") && !Objects.equals(author, ""))
            targetURL = targetURL +"(WTI=("+title+"?))AND(WAU="+author+"?)";
        else {
            if (title != null && !Objects.equals(title, "")){
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
    public Collection<Object> searchBook(String URL) {
        Collection<Book> books = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36")
                    .timeout(30000).get();

            Elements tables = doc.select("table");

            if(tables.size()>2) {
                Element table = tables.get(3);
                for (Element row : table.select("tr")) {
                    Elements tds = row.select("td");
                    if (tds.size() > 6) {
                        if(tds.size()>4) {
                            Element link = tds.get(5);
                            Element Link = link.select("a").first();
                            if (Link != null) {
                                String departmentsURL = Link.attr("abs:href");
                                Document departmentsDoc = Jsoup.connect(departmentsURL).get();
                                Elements departmentsTables = departmentsDoc.select("table");
                                if (departmentsTables.size()>7){
                                    Element departments = departmentsTables.get(7).select("table").get(0);
                                    Elements currentRow = departments.select("tr");
                                    if(currentRow.size()>1) {
                                        for (int i = 1; i < currentRow.size(); i++) {
                                            Elements newRow = currentRow.get(i).select("td");
                                            Department department = departmentRepository.getByCode(newRow.get(1).text().split(" - ")[0]);
                                            if (department != null && !newRow.get(4).hasText()) {
                                                Book book = new Book();
                                                book.setAuthor(tds.get(2).text());
                                                book.setTitle(tds.get(3).text().split("/")[0]);
                                                book.setFormat('b');
                                                book.setOwnerType('l');
                                                book.setDepartment(department);
                                                books.add(book);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Book,Long> map = books.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        books.clear();
        List<Book> booksOut = new ArrayList<>(map.keySet());

        return booksOut.stream().map(b -> new JsonWrapper<>(b, (int)(long)map.get(b),
                "www.google.pl/maps/place/"+b.getDepartment().getAddress().getStreet()+"+"
                                                    +b.getDepartment().getAddress().getBuildNr()+",+"
                                                    +b.getDepartment().getAddress().getCity())).collect(Collectors.toList());
    }
}
