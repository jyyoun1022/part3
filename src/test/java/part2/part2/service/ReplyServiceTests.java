package part2.part2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import part2.part2.dto.ReplyDTO;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService service;


    @Test
    void testRegisterReply(){
        ReplyDTO replyDTO = ReplyDTO.builder().text("Test").replier("TestUser").bno(91L).build();
        service.register(replyDTO);
    }

    @Test
    void testServiceImpl(){
        List<ReplyDTO> list = service.getList(91L);
        list.forEach(i-> System.out.println(i));
    }
}
