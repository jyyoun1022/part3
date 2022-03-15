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
    public void list(Model model,@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO){

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
}
