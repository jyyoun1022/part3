package part2.part2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import part2.part2.entity.Board;
import part2.part2.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository repository;

    @Test
    void insertReply(){

        IntStream.rangeClosed(1,300).forEach(i->{

            long bno = (long) (Math.random() * 100) + 1;

            Board board = Board.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                            .text("Reply"+i)
                                    .board(board)
                                            .replier("replier"+i)
                                                    .build();


            repository.save(reply);
        });
    }
    @Test
    @Transactional
    void readReply(){
        Optional<Reply> result = repository.findById(100L);
        Reply reply = result.get();

        System.out.println(reply);
        System.out.println("===========");
        System.out.println(reply.getBoard());
    }

    @Test
    void testListByBoard(){
        Board board = Board.builder().bno(97L).build();
        List<Reply> replyList = repository.getRepliesByBoardOrderByRno(board);

        replyList.forEach(i-> System.out.println(i));
    }
}
