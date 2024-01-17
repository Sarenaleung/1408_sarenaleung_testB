package com.example.Q5.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/crime-records")
public class CrimeRecordController {

    private final CrimeRecordService crimeRecordService;

    public CrimeRecordController(CrimeRecordService crimeRecordService) {
        this.crimeRecordService = crimeRecordService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<CrimeRecordResponse>> searchCrimeRecords(@RequestBody SearchRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<CrimeRecordResponse> records = crimeRecordService.searchByDateRange(LocalDateTime.parse(request.getFromDate(), formatter), LocalDateTime.parse(request.getToDate(), formatter));
        return ResponseEntity.ok(records);
    }
}
