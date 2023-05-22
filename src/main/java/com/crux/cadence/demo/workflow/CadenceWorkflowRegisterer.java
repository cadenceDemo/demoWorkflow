package com.crux.cadence.demo.workflow;

import com.crux.cadence.demo.workflow.workflow.WeatherWorkflowImpl;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CadenceWorkflowRegisterer implements ApplicationListener<ContextRefreshedEvent> {
    @NonNull
    private String taskList;
    @NonNull
    WorkflowClient workflowClient;

    public CadenceWorkflowRegisterer(@Value("${app.cadence.tasklist}") String taskList,
                                     WorkflowClient workflowClient) {
        this.taskList = taskList;
        this.workflowClient = workflowClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(taskList);
        worker.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);
        factory.start();
    }
}