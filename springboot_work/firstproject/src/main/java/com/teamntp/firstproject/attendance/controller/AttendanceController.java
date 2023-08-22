package com.teamntp.firstproject.attendance.controller;

import com.teamntp.firstproject.attendance.dto.AttendanceDTO;
import com.teamntp.firstproject.attendance.service.AttendanceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/attendance/")
@RequiredArgsConstructor
@Log4j2
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/form")
    public void saveform(Model model, @RequestParam(required = false, defaultValue = "") String campgZoneCd1, @RequestParam(required = false, defaultValue = "") String campgZoneCd2) {
        log.info("/attendance/form...................................");

        LocalDate startdate = LocalDate.now();
        LocalDate enddate = null;
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)");

        List<String> dataList = new ArrayList<>();

        if (campgZoneCd1 == null || campgZoneCd1.equals("")) {
            startdate = LocalDate.now(); // 시작일을 오늘로 세팅
        } else {
            startdate = LocalDate.of(
                    Integer.parseInt(campgZoneCd1.split("-")[0]),
                    Integer.parseInt(campgZoneCd1.split("-")[1]),
                    Integer.parseInt(campgZoneCd1.split("-")[2]));
        }
        // startdate 에서 월요일 찾기
//        LocalDate monday = startdate.minusDays(startdate.getDayOfWeek().getValue()-1);

        // 시작날짜 지정
        campgZoneCd1 = startdate.format(dateTimeFormatter1); // 시작일 포맷팅


        DayOfWeek dayOfWeek = startdate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (dayOfWeekNumber == 1 || dayOfWeekNumber == 6 || dayOfWeekNumber == 7) {
            for (int i = 0; i < 5; i++) {
                enddate = startdate.plusDays(i);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
            }
        } else {
            if (dayOfWeekNumber == 2) { // 화요일
                for (int i = 0; i < 4; i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                enddate = startdate.plusDays(6);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
            }
            if (dayOfWeekNumber == 3) { // 수요일
                for (int i = 0; i < 3; i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                for (int i = 5; i < 7; i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
            if (dayOfWeekNumber == 4) { // 목요일
                for (int i = 0; i < 2; i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
                for (int i = 4; i < 7; i++) {
                    enddate = startdate.plusDays(i);
                    String temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
            if (dayOfWeekNumber == 5) { // 금요일
                enddate = startdate.plusDays(0);
                String temp = enddate.format(dateTimeFormatter2);
                dataList.add(temp);
                for (int i = 2; i < 6; i++) {
                    enddate = startdate.plusDays(i);
                    temp = enddate.format(dateTimeFormatter2);
                    dataList.add(temp);
                }
            }
        }

        // 종료날짜 지정
        campgZoneCd2 = enddate.format(dateTimeFormatter1);

        List<AttendanceDTO> list = new ArrayList<>();
        list.add(new AttendanceDTO(1l, 1, "권지현"));
        list.add(new AttendanceDTO(2l, 2, "정유진"));
        list.add(new AttendanceDTO(3l, 3, "노은실"));
        list.add(new AttendanceDTO(4l, 4, "김지혜"));
        list.add(new AttendanceDTO(5l, 5, "문하나"));
        list.add(new AttendanceDTO(6l, 6, "박정연"));

        model.addAttribute("list", list);
        model.addAttribute("campgZoneCd1", campgZoneCd1);
        model.addAttribute("campgZoneCd2", campgZoneCd2);
        model.addAttribute("dataList", dataList);

        model.addAttribute("dataList", dataList);

    }

    @PostMapping("/form")
    public @ResponseBody HashMap<String, Object> save(HttpServletRequest request) {
        System.out.println("일로오나");

        String jsonStr = request.getParameter("list");
        System.out.println(jsonStr);

//        JSONArray jsonArray = new JSONArray(jsonStr);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObj = jsonArray.getJSONObject(i);
//            String cperiod = (String) jsonObj.get("cperiod");
//            String name = (String) jsonObj.get("name");
//            String member_idx = (String) jsonObj.get("member_idx");
//            String regDate = (String) jsonObj.get("regDate");
//            String status = (String) jsonObj.get("status");
//
//            AttendanceDTO attendanceDTO = AttendanceDTO.builder()
//                    .cperiod(Integer.parseInt(cperiod))
//                    .status(status)
//                    .member_idx(Long.parseLong(member_idx))
//                    .regDate(LocalDate.parse(regDate))
//                    .name(name)
//                    .build();
//            System.out.println(attendanceDTO);
//
//        }
        HashMap<String, Object> retVal = new HashMap<>();
        retVal.put("message", "success");

        return retVal;
    }
}
