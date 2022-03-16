package part2.part2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import part2.part2.entity.Board;
import part2.part2.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;

    @Test
    void insertBoard(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Member member = Member.builder()
                    .email("user"+i+"@naver.com")
                    .build();

            Board board = Board.builder()
                    .title("Title "+i)
                    .content("Content "+i)
                    .member(member)
                    .build();

            repository.save(board);
        });
    }
    @Test
    void testRead1(){
        Optional<Board> result = repository.findById(100L);

        if(result.isPresent()){
            Board board = result.get();
            System.out.println(board);
            System.out.println("====");
            System.out.println(board.getMember());
        }


    }
    @Test
    void testReadWithMember(){
        Object result = repository.getBoardWithMember(1L);

        Object[] arr = (Object[]) result;

        System.out.println(arr);
        System.out.println("=====================");
        System.out.println(Arrays.toString(arr));

    }
    @Test
    void testReadWIthReply(){
        List<Object[]> result = repository.getBoardWithReply(6L);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }
    @Test
    void testReadWithReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = repository.getBoardWithReplyCount(pageable);

        List<Object[]> content = result.getContent();
        content.forEach(i-> System.out.println(Arrays.toString(i)));
    }
    @Test
    void testRead3(){
        Object result = repository.getBoardByBno(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    void test2(){
        repository.search1();
    }

    @Test
    void testSearchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = repository.searchPage("t", "1", pageable);

    }
    }

