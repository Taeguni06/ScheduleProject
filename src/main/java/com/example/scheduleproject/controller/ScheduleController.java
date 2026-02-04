package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.scheduleDto.*;
import com.example.scheduleproject.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateResponse> create(@RequestBody @Valid CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(request));
    }

    @GetMapping("/schedules/{sId}")
    public ResponseEntity<GetOneResponse> findOne(@PathVariable @Valid Long sId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(sId));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetResponse>> findAll(@RequestParam @Valid String name) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByName(name));
    }

    @GetMapping("/schedules/all") // 테스트용 전체 조회
    public ResponseEntity<List<GetResponse>> find() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.find());
    }

    @PutMapping("/schedules/{sId}")
    public ResponseEntity<UpdateResponse> updateSchedule(@PathVariable Long sId, @RequestBody @Valid UpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(sId, request));
    }

    @DeleteMapping("/schedules/{sId}")
    public ResponseEntity<Void> delete(@PathVariable Long sId, @RequestBody @Valid DeleteRequest request) {
        scheduleService.delete(sId,request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
