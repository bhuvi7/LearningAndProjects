package com.bhuvi.RabbitMQ;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/scheduler")
public class EventSchedulerController {
    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private EventSchedulerService eventSchedulerService;
    
    @Autowired
    private EventSchedulerRepository schedulerRepository;

    @GetMapping(value = "/stop")
    public String stopSchedule() {
        postProcessor.postProcessBeforeDestruction(eventSchedulerService, SCHEDULED_TASKS);
        return "Stopped";
    }

    @GetMapping(value = "/start")
    public String startSchedule() {
        postProcessor.postProcessAfterInitialization(eventSchedulerService, SCHEDULED_TASKS);
        return "Started";
    }

    @GetMapping(value = "/list")
    public String listSchedules() throws JsonProcessingException {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        if (!setTasks.isEmpty()) {
            return setTasks.toString();
        } else {
            return "Currently no scheduler tasks are running";
        }
    }
    
    @GetMapping("/get")
    public List<EventScheduler> all() {
     return	schedulerRepository.findAll();
    }
    
    @PostMapping("/post")
    public EventScheduler postData(@RequestBody EventScheduler scheduler) {
     return	schedulerRepository.save(scheduler);
    }
}