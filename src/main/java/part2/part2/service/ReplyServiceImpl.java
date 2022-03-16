package part2.part2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import part2.part2.dto.ReplyDTO;
import part2.part2.entity.Board;
import part2.part2.entity.Reply;
import part2.part2.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository repository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        return repository.save(dtoToEntity(replyDTO)).getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result = repository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());

        return result.stream().map(i->entityToDto(i)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        repository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        repository.deleteById(rno);
    }
}
