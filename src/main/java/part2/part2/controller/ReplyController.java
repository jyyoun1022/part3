package part2.part2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import part2.part2.dto.ReplyDTO;
import part2.part2.service.ReplyService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/replies/")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService service;

    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {

        return new ResponseEntity<>(service.getList(bno), HttpStatus.OK);
    }

    @PostMapping("reply")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {

        log.info(replyDTO);

        Long rno = service.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove (@PathVariable("rno")Long rno){

       service.remove(rno);

        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
        service.modify(replyDTO);

        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
