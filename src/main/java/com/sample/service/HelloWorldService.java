/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.elastic.apm.api.ElasticApm;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sample.dto.Staff;
import com.sample.dto.ErrorTesting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HelloWorldService {

	private static Log logger = LogFactory.getLog(HelloWorldService.class);

	@Value("${name:World}")
	private String name;

	public String getHelloMessage() {
		logger.trace("Hello World!");
		logger.debug("How are you today?");
		logger.info("I am fine.");
		logger.warn("I love programming.");
		logger.error("I am programming.");
		return "Hello " + this.name;
	}

	public String getUnhandledException() {
		int x = 1 / 0;
		System.out.println(x);
		return "Hello Unhandled Exception";
	}

	public String getHandledException() {
		try {
			throw new Exception("Throwing handled exception");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ElasticApm.currentSpan().captureException(e);
		}

		return "Hello Handled Exception";
	}

	private Staff createStaff() {
		Staff staff = new Staff();
		staff.setName("mkyong");
		staff.setAge(38);
		staff.setPosition(new String[] { "Founder", "CTO", "Writer" });
		Map<String, BigDecimal> salary = new HashMap() {
			{
				put("2010", new BigDecimal(10000));
				put("2012", new BigDecimal(12000));
				put("2018", new BigDecimal(14000));
			}
		};
		staff.setSalary(salary);
		staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
		return staff;
	}

	public String getNestedLogging() {
		Staff staff = createStaff();
		ObjectMapper mapper = new ObjectMapper();
		logger.info("nested logging");
		try {
			logger.info(mapper.writeValueAsString(staff));

		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}

		return "Hello Nested logging";
	}

	public String getErrorTestingLogging() {
		ErrorTesting obj = new ErrorTesting();
		obj.setFileName("TSFTP.HUB.NETWORK.BACKFILL.MF.FILE");
		obj.setContent(
				"FED|D|00000953|00000930||70082221|2019-10-28 17:59:48|\nRATE|D|00000930|00000953|||70082221|2019-10-28 17:59:48|\nFED|A|00000953|00000090|1|70082221|2019-10-28 17:59:48|\nRATE|D|00000090|00000953|||70082221|2019-10-28 17:59:48|\nFED|D|00000953|00001137||70082221|2019-10-28 17:59:49|\nRATE|D|00001137|00000953|||70082221|2019-10-28 17:59:49|\nFED|D|00000972|00000930||70082221|2019-10-28 17:59:49|\nRATE|D|00000930|00000972|||70082221|2019-10-28 17:59:49|\nFED|A|00000972|00000090|1|70082221|2019-10-28 17:59:49|\nRATE|D|00000090|00000972|||70082221|2019-10-28 17:59:49|\nFED|D|00000972|00001137||70082221|2019-10-28 17:59:49|\nRATE|D|00001137|00000972|||70082221|2019-10-28 17:59:49|\nFED|A|00003423|00000031|1|70082221|2019-10-28 17:59:49|\nRATE|D|00000031|00003423|||70082221|2019-10-28 17:59:49|\nFED|A|00003423|00000110|2|70082221|2019-10-28 17:59:49|\nRATE|A|00000110|00003423|9999|9999.00|70082221|2019-10-28 17:59:49|\nFED|D|00004767|00005126||70082221|2019-10-28 17:59:49|\nRATE|D|00005126|00004767|||70082221|2019-10-28 17:59:49|\nFED|A|00004767|00000110|1|70082221|2019-10-28 17:59:49|\nRATE|D|00000110|00004767|||70082221|2019-10-28 17:59:49|\nFED|D|00004767|00006831||70082221|2019-10-28 17:59:49|\nRATE|D|00006831|00004767|||70082221|2019-10-28 17:59:49|\nFED|D|00006404|00000930||70082221|2019-10-28 17:59:49|\nRATE|D|00000930|00006404|||70082221|2019-10-28 17:59:49|\nFED|A|00006404|00000090|1|70082221|2019-10-28 17:59:49|\nRATE|D|00000090|00006404|||70082221|2019-10-28 17:59:49|\nFED|D|00006404|00001137||70082221|2019-10-28 17:59:50|\nRATE|D|00001137|00006404|||70082221|2019-10-28 17:59:50|\nFED|A|00006802|00006708|1|70082221|2019-10-28 17:59:50|\nRATE|D|00006708|00006802|||70082221|2019-10-28 17:59:50|\nFED|A|00006937|00000090|1|70082221|2019-10-28 17:59:50|\nRATE|D|00000090|00006937|||70082221|2019-10-28 17:59:50|\nFED|A|00006996|00000090|1|70082221|2019-10-28 17:59:50|\nRATE|D|00000090|00006996|||70082221|2019-10-28 17:59:50|\nFED|A|00006998|00000090|1|70082221|2019-10-28 17:59:50|\nRATE|D|00000090|00006998|||70082221|2019-10-28 17:59:50|\nFED|A|00007001|00000090|1|70082221|2019-10-28 17:59:51|\nRATE|D|00000090|00007001|||70082221|2019-10-28 17:59:51|\nFED|A|00007005|00006708|1|70082221|2019-10-28 17:59:51|\nRATE|D|00006708|00007005|||70082221|2019-10-28 17:59:51|\nFED|A|00007012|00000090|1|70082221|2019-10-28 17:59:51|\nRATE|D|00000090|00007012|||70082221|2019-10-28 17:59:51|\nFED|A|00007013|00000090|1|70082221|2019-10-28 17:59:51|\nRATE|D|00000090|00007013|||70082221|2019-10-28 17:59:51|\nFED|A|00007014|00000090|1|70082221|2019-10-28 17:59:52|\nRATE|D|00000090|00007014|||70082221|2019-10-28 17:59:52|\n");
		ObjectMapper mapper = new ObjectMapper();
		logger.info("Error Testing");
		try {
			logger.info(mapper.writeValueAsString(obj));

		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}

		return "Hello Error Testing logging";
	}
}
