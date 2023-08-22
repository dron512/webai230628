package com.excel.read.controller;

import com.excel.read.domain.dto.AttendanceDto;
import com.excel.read.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/form")
    public String saveform(Model model, @RequestParam(required = false,defaultValue = "") String campgZoneCd1, @RequestParam(required = false,defaultValue = "") String campgZoneCd2) {
        LocalDate startdate = null;
        LocalDate enddate = null;
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)");

        List<String> dataList = new ArrayList<>();

        if (campgZoneCd1 == null || campgZoneCd1.equals("")) {
            startdate = LocalDate.now();
        } else {
            startdate = LocalDate.of(
                    Integer.parseInt(campgZoneCd1.split("-")[0]),
                    Integer.parseInt(campgZoneCd1.split("-")[1]),
                    Integer.parseInt(campgZoneCd1.split("-")[2]));
        }
        // 시작날짜 지정
        campgZoneCd1 = startdate.format(dateTimeFormatter1);

        DayOfWeek dayOfWeek = startdate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (dayOfWeekNumber == 1) {
            for (int i =0; i<5;i++) {
                enddate = startdate.plusDays(i);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
            }
        } else {
            if(dayOfWeekNumber==2) { // 화요일
                for (int i =0; i<4;i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                enddate = startdate.plusDays(6);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
            }
            if(dayOfWeekNumber==3) { // 수요일
                for (int i =0; i<3;i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                for (int i =5; i<7;i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
            if(dayOfWeekNumber==4) { // 목요일
                for (int i =0; i<2;i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                for (int i =4; i<7;i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
            if(dayOfWeekNumber==5) { // 금요일
                enddate = startdate.plusDays(0);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
                for (int i =2; i<6;i++) {
                    enddate = startdate.plusDays(i);
                    temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
        }
        // 종료날짜 지정
        campgZoneCd2 = enddate.format(dateTimeFormatter1);

        List<AttendanceDto> list = new ArrayList<>();
        list.add(new AttendanceDto(1l, 1, "권지현", LocalDate.of(2023,8,18)));
        list.add(new AttendanceDto(2l, 2, "정유진", LocalDate.of(2023,8,18)));
        list.add(new AttendanceDto(3l, 3, "노은실", LocalDate.of(2023,8,18)));
        list.add(new AttendanceDto(4l, 4, "김지혜", LocalDate.of(2023,8,18)));
        list.add(new AttendanceDto(5l, 5, "문하나", LocalDate.of(2023,8,18)));
        list.add(new AttendanceDto(6l, 6, "박정연", LocalDate.of(2023,8,18)));

        model.addAttribute("list", list);
        model.addAttribute("campgZoneCd1", campgZoneCd1);
        model.addAttribute("campgZoneCd2", campgZoneCd2);
        model.addAttribute("dataList", dataList);

        model.addAttribute("dataList", dataList);

        return "form";
    }

    @PostMapping("/form")
    public String save(@ModelAttribute AttendanceDto attendanceDto) {
        System.out.println("AttendanceDto" + attendanceDto);
        attendanceService.save(attendanceDto);
        return "redirect:/";
    }

}
