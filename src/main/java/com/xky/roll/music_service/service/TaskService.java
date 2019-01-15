package com.xky.roll.music_service.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.TaskScheduleJob;
@Transactional(value="masterTransactionManager")
public interface TaskService {

	int insertTask(TaskScheduleJob taskScheduleJob);

	List<TaskScheduleJob> getTaskList(String page);

	int updateTask(TaskScheduleJob taskScheduleJob);

	TaskScheduleJob findTaskById(int jobId);

	void deleteTask(TaskScheduleJob taskScheduleJob);

	void pauseTask(TaskScheduleJob taskScheduleJob);

	void continueTask(TaskScheduleJob taskScheduleJob);

	void runNow(TaskScheduleJob taskScheduleJob);

	void update(TaskScheduleJob taskScheduleJob);

	int getTaskCount();

}
