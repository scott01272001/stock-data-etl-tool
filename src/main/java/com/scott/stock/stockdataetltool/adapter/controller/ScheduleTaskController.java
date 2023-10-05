//package com.scott.stock.stockdataetltool.adapter.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.scott.stock.stockdataetltool.application.exception.ObjectNotFoundException;
//import com.scott.stock.stockdataetltool.infrastructure.databse.query.ScheduleTaskSpec;
//import com.scott.stock.stockdataetltool.model.ScheduleTask;
//import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
//import com.scott.stock.stockdataetltool.model.vo.TaskLifecycle.Lifecycle;
//import com.scott.stock.stockdataetltool.model.vo.TaskMetadata.TaskType;
//import com.scott.stock.stockdataetltool.repository.ScheduleTaskRepository;
//import com.scott.stock.stockdataetltool.web.dto.ScheduleTaskDto;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "Schedule-Task")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/api/schedule-tasks")
//public class ScheduleTaskController {
//
//    private final ObjectMapper objectMapper;
//    private final ScheduleTaskRepository scheduleTaskRepository;
//
//    @Operation(summary = "Get schedule tasks", responses = {
//        @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
//        @ApiResponse(responseCode = "403", description = "Forbidden")})
//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    public Page<ScheduleTask> getScheduleTask(
//        @RequestParam(required = false) @Parameter(description = "task name") String name,
//        @RequestParam(required = false) @Parameter(description = "task status") List<TaskStatus> taskStatuses,
//        @RequestParam(required = false) List<Lifecycle> lifecycles,
//        @RequestParam(required = false) List<TaskType> taskTypes,
//        Pageable pageable) {
//
//        ScheduleTaskSpec spec = ScheduleTaskSpec.where();
//        if (StringUtils.hasText(name)) {
//            spec.nameEqual(name);
//        }
//        if (!CollectionUtils.isEmpty(taskStatuses)) {
//            spec.statusIn(taskStatuses);
//        }
//        if (!CollectionUtils.isEmpty(lifecycles)) {
//            // TODO
//        }
//        Page<ScheduleTask> pages = scheduleTaskRepository.findAll(spec.build(), pageable);
//        return pages;
//    }
//
//    @Operation(summary = "Get schedule tasks by id", responses = {
//        @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json")),
//        @ApiResponse(responseCode = "403", description = "unAuthorize")})
//    @GetMapping(value = "/{id}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public ScheduleTask getScheduleTaskById(
//        @PathVariable(value = "id") @Parameter(description = "task id") Long id)
//        throws ObjectNotFoundException {
//        return scheduleTaskRepository.findById(id)
//            .orElseThrow(() -> new ObjectNotFoundException(id));
//    }
//
//    @Operation(summary = "Create schedule tasks", responses = {
//        @ApiResponse(responseCode = "201", description = "success", content = @Content(mediaType = "application/json")),
//        @ApiResponse(responseCode = "403", description = "unAuthorize")})
//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public ScheduleTask create(@RequestBody @Validated ScheduleTaskDto scheduleTask) {
//        ScheduleTask task = new ScheduleTask(scheduleTask.getName(), scheduleTask.getStatus(),
//            scheduleTask.getTaskLifecycle(), scheduleTask.getTaskMetadata());
//        return scheduleTaskRepository.save(task);
//    }
//
//}
