package part2.part2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import part2.part2.dto.BoardDTO;
import part2.part2.dto.PageRequestDTO;
import part2.part2.dto.PageResultDTO;
import part2.part2.entity.Board;
import part2.part2.entity.Member;
import part2.part2.repository.BoardRepository;
import part2.part2.repository.ReplyRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();

    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());
        Page<Object[]> result = repository.getBoardWithReplyCount(pageable);

        Function<Object[],BoardDTO> fn = (en ->entityToDto((Board)en[0],(Member)en[1],(Long)en[2]));

        return new PageResultDTO<>(result,fn);


    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = repository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;

        return entityToDto((Board) arr[0],(Member) arr[1],(Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWIthReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);

    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = repository.findById(boardDTO.getBno());

        if(result.isPresent()){
            Board board = result.get();
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            repository.save(board);
        }
    }
}
