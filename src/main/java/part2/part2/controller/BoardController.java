package part2.part2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import part2.part2.dto.BoardDTO;
import part2.part2.dto.PageRequestDTO;
import part2.part2.entity.Board;
import part2.part2.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO){

        model.addAttribute("result",boardService.getList(pageRequestDTO));
    }

    @PostMapping("/register")
    public String registerPost (@ModelAttribute("BoardDTO")BoardDTO dto, RedirectAttributes redirectAttributes){

        Long bno = boardService.register(dto);

        redirectAttributes.addFlashAttribute("msg",bno);

        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public void register(){

    }
    @GetMapping({"/read","/modify"})
    public void read(Model model,@ModelAttribute("pageRequestDTO")PageRequestDTO pageRequestDTO,Long bno){

        BoardDTO boardDTO = boardService.get(bno);

        model.addAttribute("dto",boardDTO);
    }
    @PostMapping("/modify")
    public String modify(Model model,@ModelAttribute("pageRequestDTO")PageRequestDTO pageRequestDTO,
                         @ModelAttribute("boardDTO")BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes){

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addFlashAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addFlashAttribute("keyword",pageRequestDTO.getKeyword());

        redirectAttributes.addFlashAttribute("bno",boardDTO.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno,RedirectAttributes redirectAttributes){

        boardService.removeWIthReplies(bno);

        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }
}
