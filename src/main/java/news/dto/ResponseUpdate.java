package news.dto;

import news.dao.entity.enums.StatusNews;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseUpdate {
    private Long id;
    private StatusNews status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp publishDate;
}
