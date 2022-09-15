package news.service;

import news.dao.entity.Author;
import news.dao.entity.News;
import news.dao.entity.enums.StatusNews;
import news.dao.repository.NewsRepository;
import news.dto.ResponseUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public Long save(String uri, String title, String content, Author author) {
        News save = newsRepository.save(createNewsByParameter(uri, title, content, author));
        return save.getId();
    }

    private News createNewsByParameter(String uri, String title, String content, Author author) {
        News news = new News();
        news.setUriPicture(uri);
        news.setTitle(title);
        news.setContent(content);
        news.setAuthor(author);
        return news;
    }

    public News get(Long id) {
        News findedNews = newsRepository.findById(id).orElse(null);
        if (findedNews == null) {

        }
        return findedNews;
    }

    public ResponseUpdate update(Long id, StatusNews status) {
        Optional<News> findedNews = newsRepository.findById(id);
        if (findedNews.isPresent()) {
            News newsForSave = findedNews.get();
            newsForSave.setStatus(status);
            newsForSave.setPublishDate(new Timestamp(System.currentTimeMillis()));
            newsRepository.save(newsForSave);
            return createResponseForUpdate(newsForSave);
        }
        return null;
    }

    public ResponseUpdate createResponseForUpdate(News news) {
        ResponseUpdate response = new ResponseUpdate();
        response.setId(news.getId());
        response.setPublishDate(news.getPublishDate());
        response.setStatus(news.getStatus());
        return response;
    }

    public List<News> getTimeLine(StatusNews status, Timestamp publishDate, Timestamp endPublishDate) {
        return newsRepository.findAll(createTimeLineSpec(status, publishDate, endPublishDate));
    }

    private static Specification<News> createTimeLineSpec(StatusNews status, Timestamp publishDate, Timestamp endPublishDate) {
        Specification<News> specification = getEmptySpec();
        if (status != null) {
           specification = specification.and(getStatusSpec(status));
        }
        if (publishDate != null) {
            specification = specification.and(getPublishDateSpec(publishDate));
        }
        if (endPublishDate != null) {
            specification = specification.and(getEndPublishDateSpec(endPublishDate));
        }
        return specification;
    }

    private static Specification<News> getPublishDateSpec(Timestamp publishDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("publish_date"), publishDate);
    }

    private  static  Specification<News> getEndPublishDateSpec(Timestamp endPublishDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("end_publish_date"), endPublishDate);
    }

    private static Specification<News> getStatusSpec(StatusNews status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<News> getEmptySpec() {
        return (root, query, cb) -> cb.and();
    }
}
