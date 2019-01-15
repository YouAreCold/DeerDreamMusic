package com.xky.roll.music_api.quartz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xky.roll.music_api.pojo.Order;
import com.xky.roll.music_api.service.OrderService;
import com.xky.roll.music_api.util.http.DealResponse;
import com.xky.roll.music_api.util.http.HttpUtil;

/**
 * 
 * @Description:TODO(需要定时执行的任务)
 * @author:liyifan
 * @time:2017年6月2日 上午9:16:42
 */
@Configuration
@Component
@EnableScheduling
public class ScheduleTask {
	@Autowired
	private JobFactory jobFactory;
	@Autowired
	private OrderService orderServiceImpl;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ScheduleTask.class);

	@Bean(name = "schedulerFactoryBean")
	public SchedulerFactoryBean init() {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setJobFactory(jobFactory);
		return schedulerFactory;
	}

	public void sayHello(String name) {
		System.out.println("Hello world " + name);
	}

	public void sayHelloAgain(String name) {
		System.out.println("Hello world again " + name);
	}

	public void apiTest(String url) {
		HttpUtil HttpUtil = new HttpUtil();
		final Map<String, Object> head = new HashMap<>();
		final Map<String, Object> body = new LinkedHashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, +1);// 取后一天
		body.put("sdate", sdf.format(cal.getTime()));
		body.put("orgUuid", "59");
		head.put("accept", "*/*");
		head.put("connection", "Keep-Alive");
		head.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		head.put("Content-Type", "application/json");
		HttpUtil.httpPost(url, head, body, new DealResponse() {
			@Override
			public Object onSuccess(CloseableHttpResponse response) throws IOException {
				String str = null;
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					byte[] bytes = IOUtils.toByteArray(entity.getContent());
					if (null != entity) {
						EntityUtils.consume(entity);
					}
					str = new String(bytes);
				} else {

				}
				JSONObject jsonObj = JSONObject.parseObject(str);
				String code = jsonObj.getString("code");
				if (code != null && Integer.valueOf(code) == 0) {
					int dataLen = jsonObj.getIntValue("dataLen");// 应收
					JSONArray json = jsonObj.getJSONArray("data");
					if (json != null && json.size() > 0) {
						for (int i = 0; i < json.size(); i++) {
							Order order = new Order();
							JSONObject orderJSON = json.getJSONObject(i); // 遍历jsonarray数组，把每一个对象转成json对象
							orderJSON.getString("orgUuid");// 音乐平台HIS编码(TODO)
							order.setHisDepId(orderJSON.getString("orgDeptUuid"));
							order.setDeptName(orderJSON.getString("orgDeptName"));
							order.setDoctorId(orderJSON.getString("orgDeptEmpUuid"));// 科室医生HIS编码(TODO)
							order.setDocName(orderJSON.getString("orgDeptEmpName"));
							orderJSON.getString("scheduleUuid");// 排班编号(TODO)
							order.setRegDate(orderJSON.getString("sdate"));
							order.setRegBegtime(orderJSON.getString("timeBegin"));
							order.setRegEndtime(orderJSON.getString("timeEnd"));
							order.setOrderNo(orderJSON.getString("hisOrderId"));// 预约挂号HIS订单号(TODO)
							order.setPatientName(orderJSON.getString("pname"));
							order.setSex(orderJSON.getString("sex"));
							order.setBirth(orderJSON.getString("birthday"));
							orderJSON.getString("idcard");// 身份证号码(TODO)
							orderJSON.getString("insuranceCard");// 社保卡号码 (TODO)
							orderJSON.getString("medicalCard");// 就诊卡号码 (TODO)
							orderJSON.getString("medicalId");// 就诊卡(病人)ID号(TODO)
							order.setPhone(orderJSON.getString("mobile"));// 手机号码
							order.setAddress(orderJSON.getString("homeAddr"));// 家庭住址
							orderJSON.getString("medicalType");// 就医类型代码 (TODO)
							orderJSON.getString("condition");// (TODO)
							orderJSON.getString("payChargeType");// (TODO)
							orderJSON.getString("extDataJson");// (TODO)
							JSONObject refPerson = orderJSON.getJSONObject("refPersonJSON");// 建档或监护人信息
							order.setGuardRelation(refPerson.getString("pref"));// 关系代码(TODO)
							order.setGuardName(refPerson.getString("pname"));
							refPerson.getString("sex");// 监护人性别(TODO)
							refPerson.getString("birthday");// (TODO)
							order.setGuardCardNo(refPerson.getString("idcard"));
							refPerson.getString("insuranceCard");// (TODO)
							refPerson.getString("medicalCard");// (TODO)
							refPerson.getString("mobile");// (TODO)
							order.setPayRegCost(orderJSON.getBigDecimal("payAmount"));
							orderJSON.getString("cashAmount");// 现金/自费总金额(TODO)
							orderJSON.getString("insurAmount");// 医保总金额 (TODO)
							orderJSON.getString("couponAmount");// 优惠总金额(TODO)
							order.setPayRegTime(orderJSON.getString("payTime"));// 时间格式(TODO)
							JSONObject payDetailJSON = orderJSON.getJSONObject("payDetailJSON");
							payDetailJSON.getString("payType");
							payDetailJSON.getString("payWayTag");
							payDetailJSON.getString("paySerial");
							payDetailJSON.getString("pname");
							payDetailJSON.getString("idcard");
							payDetailJSON.getString("payAmount");
							payDetailJSON.getString("cashAmount");
							payDetailJSON.getString("insurAmount");
							payDetailJSON.getString("insurSelfAmount");
							payDetailJSON.getString("couponAmount");
							payDetailJSON.getString("payTime");
							orderJSON.getString("hisTriageCode");// 分诊队列编码(TODO)
							order.setHisPatId(orderJSON.getString("hisPatientId"));
							order.setOradeState(orderJSON.getString("status"));// (TODO)
							orderJSON.getString("payStatus");
							orderJSON.getString("serviceUuid");// 渠道商编号(TODO)
							orderJSON.getString("serviceName");// 渠道商名称(TODO)
							orderServiceImpl.insertOrder(order);
						}
					}
					// (TODO)回调
					callBack("http://127.0.0.1:8080/api/testCallBack", json.size());
					return str;
				} else if (code != null && Integer.valueOf(code) == 9999) {

				}
				return null;
			}

			@Override
			public void onFailure(HttpUriRequest response, Exception arg) {
				// TODO (对应异常的处理)

			}

			@Override
			public void callBack(String url, Object message) {
				HttpUtil HttpUtil = new HttpUtil();
				body.put("amount", message);
				DealResponse x = new DealResponse() {
					@Override
					public Object onSuccess(CloseableHttpResponse arg0) throws IOException {
						return null;
					}

					@Override
					public void onFailure(HttpUriRequest arg0, Exception arg) {
					}

					@Override
					public void callBack(String url, Object message) {
						// TODO Auto-generated method stub

					}
				};
				HttpUtil.httpPost(url, head, body,x);
			}

		});
	}

}
