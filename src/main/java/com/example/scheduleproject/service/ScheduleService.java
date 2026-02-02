package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.internal.StrictIdGeneratorResolverSecondPass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateResponse create(CreateRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Schedule save = scheduleRepository.save(schedule);

        return new CreateResponse(
                save.getId(),
                save.getTitle(),
                save.getContent(),
                save.getName(),
                save.getCreatedAt(),
                save.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetResponse findOne(Long sId) {
        Schedule schedule = scheduleRepository.findById(sId).orElseThrow(
                () -> new IllegalArgumentException("오류: 존재하지 않음")
        );

        return new GetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetResponse> findAll(String name) {
        List<Schedule> schedules = scheduleRepository.findByName(name);

        List<GetResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetResponse dto = new GetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UpdateResponse update(Long sId, UpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(sId).orElseThrow(
                () -> new IllegalArgumentException("오류: 존재하지 않음")
        );

        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalStateException("오류: 비밀번호 불일치");
        }

        schedule.update(request.getTitle(), request.getName());

        return new UpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long sId, DeleteRequest request) {
        Schedule schedule = scheduleRepository.findById(sId).orElseThrow(
                () -> new IllegalArgumentException("오류: 존재하지 않음")
        );

        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalStateException("오류: 비밀번호 불일치");
        }

        scheduleRepository.deleteById(sId);
    }

    public List<GetResponse> find() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetResponse dto = new GetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
