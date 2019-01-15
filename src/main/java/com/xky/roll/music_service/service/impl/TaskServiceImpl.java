package com.xky.roll.music_service.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.xky.roll.music_api.quartz.TaskSetting;
import com.xky.roll.music_service.mapper.TaskScheduleJobMapper;
import com.xky.roll.music_service.pojo.TaskScheduleJob;
import com.xky.roll.music_service.service.TaskService;
import com.xky.roll.music_service.util.StringTool;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskScheduleJobMapper taskScheduleJobMapper;
	@Autowired
	private TaskSetting taskSetting;
	private static Logger log = LoggerFactory.getLogger(TaskService.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int insertTask(TaskScheduleJob taskScheduleJob) {
		int flag = 0;
		if (!org.quartz.CronExpression.isValidExpression(taskScheduleJob.getCronExpression())) {
			return flag;
		}
		try {
			taskScheduleJob.setCreateTime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int id = taskScheduleJobMapper.insert(taskScheduleJob);
		try {
			taskSetting.create(taskScheduleJob);
		} catch (SchedulerException e) {
			log.error("*******************任务添加失败");
		}
		return id;
	}

	@Override
	public List<TaskScheduleJob> getTaskList(String page) {
		int pageNum = 0;
		if (!StringTool.isNull(page)) {
			pageNum = Integer.valueOf(page);
			pageNum = (pageNum <= 0) ? 1 : pageNum;
		}
		Example example = new Example(TaskScheduleJob.class);
		PageHelper.startPage(pageNum, 10);
		List<TaskScheduleJob> list = taskScheduleJobMapper.selectByExample(example);
		return list;
	}

	@Override
	public int updateTask(TaskScheduleJob taskScheduleJob) {
		int flag = 0;
		if (!org.quartz.CronExpression.isValidExpression(taskScheduleJob.getCronExpression())) {
			return flag;
		}
		taskSetting.reSet(taskScheduleJob);
		return taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
	}

	@Override
	public TaskScheduleJob findTaskById(int jobId) {
		return taskScheduleJobMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public void deleteTask(TaskScheduleJob taskScheduleJob) {
		TaskScheduleJob job = findTaskById(taskScheduleJob.getJobId());
		taskScheduleJobMapper.deleteByPrimaryKey(taskScheduleJob.getJobId());
		taskSetting.delete(job);
	}

	@Override
	public void pauseTask(TaskScheduleJob taskScheduleJob) {
		taskScheduleJob.setJobStatus((byte) 0);
		taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
		TaskScheduleJob job = findTaskById(taskScheduleJob.getJobId());
		taskSetting.pause(job);
	}

	@Override
	public void continueTask(TaskScheduleJob taskScheduleJob) {
		taskScheduleJob.setJobStatus((byte) 1);
		taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
		TaskScheduleJob job = findTaskById(taskScheduleJob.getJobId());
		taskSetting.resume(job);

	}

	@Override
	public void runNow(TaskScheduleJob taskScheduleJob) {
		TaskScheduleJob job = findTaskById(taskScheduleJob.getJobId());
		taskSetting.runNow(job);
	}

	@Override
	public void update(TaskScheduleJob taskScheduleJob) {
		taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
	}

	@Override
	public int getTaskCount() {
		Example example = new Example(TaskScheduleJob.class);
		return taskScheduleJobMapper.selectCountByExample(example);
	}
}
