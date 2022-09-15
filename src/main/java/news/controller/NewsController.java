package news.controller;

import news.dao.entity.Author;
import news.dao.entity.News;
import news.dao.entity.enums.StatusNews;
import news.dto.ResponseUpdate;
import news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    private Long save(@RequestParam("URI") String uri, @RequestParam("title") String title,
                      @RequestParam("content") String content, @RequestBody Author author) {
        return newsService.save(uri, title, content, author);
    }

    @GetMapping("/get")
    private News get(@RequestParam("news_id") Long id) {
        return newsService.get(id);
    }

    @PutMapping("/update")
    private ResponseUpdate update(@RequestParam("id") Long id, @RequestParam("status") StatusNews status) {
        return newsService.update(id, status);
    }

    @GetMapping("/timeline")
    private List<News> timeLine(@RequestParam(value = "status", required = false) StatusNews status,
                              @RequestParam(value = "publishDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp publishDate,
                              @RequestParam(value = "endPublishDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp endPublishDate) {
        return newsService.getTimeLine(status, publishDate, endPublishDate);
    }
}
