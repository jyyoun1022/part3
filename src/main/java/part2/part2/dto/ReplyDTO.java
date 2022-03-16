package part2.part2.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Data
public class ReplyDTO {

    private Long rno;

    private String text;

    private String replier;

    private Long bno;

    private LocalDateTime regDate,modDate;
}
