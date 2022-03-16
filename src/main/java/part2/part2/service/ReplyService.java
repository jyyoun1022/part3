package part2.part2.service;

import part2.part2.dto.ReplyDTO;
import part2.part2.entity.Board;
import part2.part2.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList (Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO dto){

        Board board = Board.builder().bno(dto.getBno()).build();

        Reply entity = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replier(dto.getReplier())
                .board(board)
                .build();

        return entity;
    }

    default ReplyDTO entityToDto(Reply entity){

        ReplyDTO dto = ReplyDTO.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replier(entity.getReplier())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
