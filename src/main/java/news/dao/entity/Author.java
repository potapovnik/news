package news.dao.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "author", schema = "public")
@Data
public class Author {
    @Id
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "FIO")
    private Long FIO;
}
