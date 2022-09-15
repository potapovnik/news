package news.dao.entity;

import news.dao.entity.enums.StatusNews;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "news", schema = "public")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    @SequenceGenerator(name = "news_seq", sequenceName = "news_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusNews status;
    @Column(name = "uri_picture")
    private String uriPicture;
    @Column(name = "publish_date")
    private Timestamp publishDate;
    @Column(name = "end_publish_date")
    private Timestamp endPublishDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

}
