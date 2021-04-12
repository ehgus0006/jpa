package springboot.jpa.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.jpa.dto.GuestBookDTO;
import springboot.jpa.dto.PageRequestDTO;
import springboot.jpa.dto.PageResultDTO;
import springboot.jpa.entity.GuestBook;
import springboot.jpa.service.GuestBookService;


@Controller
@RequestMapping("/guestbook")
@Slf4j
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestBookService service;

    private static final Logger logger = LogManager.getLogger();


    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        log.debug("[Post] remove" + gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, Model model, @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
        log.debug("read request");
        GuestBookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(GuestBookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.debug("[Post] modify ..................... :" + dto);
        service.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());
        return "redirect:/guestbook/read";
    }

    @GetMapping("/register")
    public void register() {

        log.debug("register form request");
    }

    @PostMapping("/register")
    public String registerPost(GuestBookDTO dto, Model model, RedirectAttributes redirectAttributes){
        log.debug("register request...");
        Long gno = service.register(dto);
        model.addAttribute("result", service.getList(new PageRequestDTO()));
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }
    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {

        PageResultDTO<GuestBookDTO, GuestBook> list = service.getList(pageRequestDTO);
        model.addAttribute("result", list);
        model.addAttribute("requestDTO", pageRequestDTO);
        return "/guestbook/list";
    }

    @GetMapping("/exTemplate")
    public void exTemplate(){
        log.debug("exLayout.......");
    }

}
