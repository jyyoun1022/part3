package part2.part2.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import part2.part2.entity.Board;

public interface SearchBoardRepository {

    Board search1();

    Page<Object[]>searchPage(String type, String keyword, Pageable pageable);
}
