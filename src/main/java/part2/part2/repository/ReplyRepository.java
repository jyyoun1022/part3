package part2.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import part2.part2.entity.Board;
import part2.part2.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(@Param("bno")Long bno);

    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
