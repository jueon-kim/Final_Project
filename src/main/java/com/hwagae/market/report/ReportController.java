package com.hwagae.market.report;

import com.hwagae.market.comment.CommentDTO;
import com.hwagae.market.comment.CommentService;
import com.hwagae.market.inquiry.InquiryDTO;
import com.hwagae.market.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final CommentService commentService;

    @GetMapping("myPage/insertReport")
    public String reportForm(){
        System.out.println("신고하러 가겠읍니다.");
        return "views/myPage/insertReport";
    }

    @PostMapping("myPage/insertReport")
    public String insertReport(@ModelAttribute ReportDTO reportDTO, Model model) throws IOException {
        System.out.println("값이 있는거니?"+reportDTO.getReport_Uname());
        System.out.println("reportDTO = " + reportDTO + ", model = " + model);
        model.addAttribute("reportDTO",reportDTO);
        reportService.save(reportDTO);
        return "redirect:/myPage/reportList/paging";
    }

    @GetMapping("myPage/reportList/paging")
    public String reportList(@PageableDefault(page=1) Pageable pageable,HttpSession session, Model model){
        System.out.println("나의 문의 목록이에요~");

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            Integer user_num = user.getUser_num();

            Page<ReportDTO> reportList = reportService.pagingByUserNum(user_num, pageable);

            System.out.println("reportList: " + reportList);
            System.out.println("Total Pages: " + reportList.getTotalPages());
            System.out.println("Page Number: " + pageable.getPageNumber());

            int blockLimit = 10;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = ((startPage + blockLimit - 1) < reportList.getTotalPages()) ? startPage + blockLimit - 1 : reportList.getTotalPages();

            System.out.println("Start Page: " + startPage);
            System.out.println("End Page: " + endPage);

            model.addAttribute("reportList", reportList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);

            return "views/myPage/reportList";
        } else {
            // 유저가 로그인되어 있지 않은 경우에 대한 처리
            return null;
        }
    }
//        Page<ReportDTO> reportList=reportService.paging(pageable);
//
//        System.out.println("reportList: " + reportList);
//        System.out.println("Total Pages: " + reportList.getTotalPages());
//        System.out.println("Page Number: " + pageable.getPageNumber());
//
//        int blockLimit=10;
//        int startPage=(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage=((startPage + blockLimit - 1) < reportList.getTotalPages()) ? startPage + blockLimit - 1 : reportList.getTotalPages();
//
//        System.out.println("Start Page: " + startPage);
//        System.out.println("End Page: " + endPage);
//
//        UserDTO user=(UserDTO)session.getAttribute("user");
//        if(user!=null){
//            Integer user_num=user.getUser_num();
//            System.out.println("user_num = " + user_num );
//            List<ReportDTO> reportDTOList=reportService.findAllByUserNum(user_num);
//            model.addAttribute("reportList",reportDTOList);
//            model.addAttribute("startPage", startPage);
//            model.addAttribute("endPage", endPage);
//            return "views/myPage/reportList";
//        }else{
//            return null;
//        }
//    }

    @GetMapping("myPage/report/{report_num}")
    public String findByNum(@PathVariable Integer report_num,Model model){
        ReportDTO reportDTO=reportService.findByNum(report_num);

        List<CommentDTO> commentDTOList=commentService.report_findAll(report_num);
        model.addAttribute("commentList",commentDTOList);
        model.addAttribute("report",reportDTO);
        return "views/myPage/report";
    }

    @GetMapping("/admin/deleteReport/{report_num}")
    public String delete(@PathVariable Integer report_num){
        reportService.delete(report_num);
        System.out.println("report_num = " + report_num);
        return "views/myPage/reportList";
    }


}