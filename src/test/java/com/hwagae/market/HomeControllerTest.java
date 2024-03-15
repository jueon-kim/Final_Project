package com.hwagae.market;

import com.hwagae.market.HomeController;
import com.hwagae.market.event.EventDTO;
import com.hwagae.market.event.EventService;
import com.hwagae.market.inquiry.InquiryDTO;
import com.hwagae.market.inquiry.InquiryService;
import com.hwagae.market.notice.NoticeDTO;
import com.hwagae.market.notice.NoticeService;
import com.hwagae.market.post.PostDTO;
import com.hwagae.market.post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private EventService eventService;

    @Mock
    private NoticeService noticeService;

    @Mock
    private InquiryService inquiryService;

    @Mock
    private Model model;

    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        homeController = new HomeController(postService, eventService, noticeService, inquiryService);
    }

    @Test
    void testIndex() {
        // Given
        List<PostDTO> postDTOList = new ArrayList<>();
        postDTOList.add(new PostDTO());
        List<EventDTO> eventDTOList = new ArrayList<>();
        eventDTOList.add(new EventDTO());
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        noticeDTOList.add(new NoticeDTO());

        when(postService.findAll()).thenReturn(postDTOList);
        when(eventService.findAll()).thenReturn(eventDTOList);
        when(noticeService.findAll()).thenReturn(noticeDTOList);

        // When
        String viewName = homeController.index(model);

        // Then
        assertEquals("/views/user/index", viewName);
        verify(postService, times(1)).findAll();
        verify(eventService, times(1)).findAll();
        verify(noticeService, times(1)).findAll();
        verify(model, times(1)).addAttribute("postList", postDTOList);
        verify(model, times(1)).addAttribute("eventList", eventDTOList);
        verify(model, times(1)).addAttribute("noticeList", noticeDTOList);
    }

    // Add tests for other methods as needed
}