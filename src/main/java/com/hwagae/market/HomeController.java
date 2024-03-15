package com.hwagae.market;

import com.hwagae.market.event.EventDTO;
import com.hwagae.market.event.EventService;
import com.hwagae.market.inquiry.InquiryService;
import com.hwagae.market.notice.NoticeDTO;
import com.hwagae.market.notice.NoticeService;
import com.hwagae.market.post.PostDTO;
import com.hwagae.market.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final EventService eventService;
    private final NoticeService noticeService;
    private final InquiryService inquiryService;
    @GetMapping("/")
    public String index(Model model) {
        List<PostDTO> postDTOList = postService.findAll();
        model.addAttribute("postList", postDTOList);

        List<EventDTO> eventDTOList = eventService.findAll();
        model.addAttribute("eventList", eventDTOList);

        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);



        System.out.println("Post 에서 넘어오는 데이터 postDTOList  = \n" + postDTOList);
        System.out.println("event 에서 넘어오는 데이터 eventDTOLIst  = \n"  + eventDTOList);
        System.out.println("notice 에서 넘어오는 데이터 noticeList  = \n"  + noticeDTOList);
        System.out.println("model = " + model);
        System.out.println("글 목록");

        System.out.println("홈페이지");

        return "/views/user/index";
    }
}