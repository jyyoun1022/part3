package part2.part2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import part2.part2.dto.BoardDTO;
import part2.part2.dto.PageRequestDTO;
import part2.part2.dto.PageResultDTO;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                        .title("Test~~")
                                .content("Test..~~")
                .writerEmail("user44@naver.com")
                                        .build();

        service.register(dto);
    }

    @Test
    void testGetList(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();


        PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);

        List<BoardDTO> dtoList = result.getDtoList();
        dtoList.forEach(i-> System.out.println(i));
    }
    @Test
    void testGet(){

        BoardDTO boardDTO = service.get(100L);
        System.out.println(boardDTO);
    }
    @Test
    void testDelete(){

        service.removeWIthReplies(101L);
    }
    @Test
    void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                        .bno(99L)
                                .title("제목변경")
                                        .content("내용변경")
                                                .build();

        service.modify(boardDTO);
        System.out.println(boardDTO);
    }
}
