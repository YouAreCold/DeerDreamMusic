package com.xky.roll.music_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_service.pojo.TaskScheduleJob;
import com.xky.roll.music_service.service.TaskService;

/**
 * 
 * @Description:TODO(定时任务增删改查)
 * @author:liyifan
 * @time:2017年5月31日 下午4:36:52
 */
@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskServiceImpl;

	@ResponseBody
	@RequestMapping("/insertTask")
	public int insertTask(@RequestBody TaskScheduleJob taskScheduleJob) {
		return taskServiceImpl.insertTask(taskScheduleJob);
	}

	@RequestMapping("/getTaskList")
	public String toTaskPage(Model model) {
		int taskCount = taskServiceImpl.getTaskCount();
		model.addAttribute("taskCount", taskCount);
		return "timedTask";
	}

	@ResponseBody
	@RequestMapping("/getList/{pageNo}")
	public List<TaskScheduleJob> getTaskList(@PathVariable String pageNo) {
		return taskServiceImpl.getTaskList(pageNo);
	}

	@ResponseBody
	@RequestMapping("/updateTask")
	public int updateTask(@RequestBody TaskScheduleJob taskScheduleJob) {
		return taskServiceImpl.updateTask(taskScheduleJob);
	}

	@ResponseBody
	@RequestMapping("/getTaskDetail/{jobId}")
	public TaskScheduleJob getTaskDetail(@PathVariable int jobId) {
		return taskServiceImpl.findTaskById(jobId);
	}

	@ResponseBody
	@RequestMapping("/deleteTask")
	public void deleteTask(@RequestBody TaskScheduleJob taskScheduleJob) {
		taskServiceImpl.deleteTask(taskScheduleJob);
	}

	@ResponseBody
	@RequestMapping("/pauseTask")
	public void pauseTask(@RequestBody TaskScheduleJob taskScheduleJob) {
		taskServiceImpl.pauseTask(taskScheduleJob);
	}

	@ResponseBody
	@RequestMapping("/continueTask")
	public void continueTask(@RequestBody TaskScheduleJob taskScheduleJob) {
		taskServiceImpl.continueTask(taskScheduleJob);
	}

	@ResponseBody
	@RequestMapping("/runNow")
	public void runNow(@RequestBody TaskScheduleJob taskScheduleJob) {
		taskServiceImpl.runNow(taskScheduleJob);
	}
}
