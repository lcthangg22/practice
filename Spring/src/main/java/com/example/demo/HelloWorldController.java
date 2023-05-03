package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController

public class HelloWorldController {

//    @GetMapping("/first-day")
//    public String helloWorld() {
//        return "Hello World";
//    }
//
//    @PostMapping("/var/{ten}")
//    public String helloWithName(@PathVariable("ten") String name) {
//        return "Hello World" + name;
//    }

    private int count;

    private boolean validate(Long unixTime) {
        if (unixTime == 0) {
            throw new RuntimeException("Invalid unixTime: 0 is invalid");
        }
        if (unixTime < 0) {
            throw new RuntimeException("Invalid unixTime: can not be negative");
        }
        return true;
    }

//    Level 1:
//    Input: Một biến dạng unixtime
//    Output: Biến đó dưới dạng thời gian thông thường
//
//    Level 2:
//    Input: Một biến dạng unixtime
//    Output:
//    Số năm từ thời điểm input tới thời điểm hiện tại
//    Số ngày từ thời điểm input tới thời điểm hiện tại
//
//    Level 3:
//    Input: Một biến dạng unixtime nhưng có thể xuất hiện ở bất cứ đâu trong 4 nơi
//    Path Variable
//    Query String
//    Header
//            Body
//    Output:
//    Số năm từ thời điểm input tới thời điểm hiện tại
//    Số ngày từ thời điểm input tới thời điểm hiện tại
//
//    Level 4:
//    Input: Một biến thời gian dạng đọc được (DD-mm-yy)
//    Output:
//    Số năm từ thời điểm input tới thời điểm hiện tại
//    Số ngày từ thời điểm input tới thời điểm hiện tại
//
//    Level 5:
//    Input: Một biến thời gian dạng đọc được (DD-mm-yy)
//    Output:
//    Số năm từ thời điểm input tới thời điểm hiện tại
//    Số ngày từ thời điểm input tới thời điểm hiện tại
//    Số lần user gọi request này

    @GetMapping("/level1/{unixTime}")
    public ResponseEntity<String> level1(@PathVariable("unixTime") Long unixTime) {
        try {
            validate(unixTime);
            Date date = new Date(unixTime * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(date);
            return ResponseEntity.ok(formattedDate);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/level2/{unixTime}")
    public ResponseEntity<String> level2(@PathVariable("unixTime") Long unixTime) {
        try {
            Instant instant = Instant.ofEpochSecond(unixTime);
            LocalDate date = LocalDate.ofInstant(instant, ZoneId.of("UTC"));
            return ResponseEntity.ok("Years: " + (LocalDate.now().getYear() - date.getYear()) + "\n"
                    + "Days: " + ChronoUnit.DAYS.between(date, LocalDate.now()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/level3/{unixTimePath}")
    public ResponseEntity<String> level3(
            @PathVariable(value = "unixTimePath", required = false) Long unixTimePath,
            @RequestParam(required = false) Long unixTimeParam,
            @RequestHeader(value = "unixHeader", required = false) Long unixHeader,
            @RequestBody(required = false) Long unixBody
    ) {
        try {
            validate(unixTimePath);
            Instant instant = Instant.ofEpochSecond(unixTimePath);

            if (unixTimeParam != null) {
                validate(unixTimeParam);
                instant = Instant.ofEpochSecond(unixTimeParam);
            }
            if (unixHeader != null) {
                validate(unixHeader);
                instant = Instant.ofEpochSecond(unixHeader);
            }
            if (unixBody != null) {
                validate(unixBody);
                instant = Instant.ofEpochSecond(unixBody);
            }
            LocalDate date = LocalDate.ofInstant(instant, ZoneId.of("UTC"));
            return ResponseEntity.ok("Years: " + (LocalDate.now().getYear() - date.getYear()) + "\n"
                    + "Days: " + ChronoUnit.DAYS.between(date, LocalDate.now()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/level4")
    public ResponseEntity<String> level4(@RequestParam("stringTime") String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(str, formatter);
            return ResponseEntity.ok("Years: " + (LocalDate.now().getYear() - date.getYear()) + "\n"
                    + " - Days: " + ChronoUnit.DAYS.between(date, LocalDate.now()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Incorrect format String" + e.getMessage());
        }
    }

    @GetMapping("/level5")
    public ResponseEntity<String> level5(@RequestParam("stringTime") String str) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(str, formatter);
            count++;

            return ResponseEntity.ok("Years: " + (LocalDate.now().getYear() - date.getYear()) + "\n"
                    + " - Days: " + ChronoUnit.DAYS.between(date, LocalDate.now()) + "\n"
                    + " - Count: " + count);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Incorrect format String" + e.getMessage());
        }
    }
}
