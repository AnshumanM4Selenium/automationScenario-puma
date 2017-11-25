package com.modular.framework.Generic_Libraries;
	
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

//import com.modular.framework.utility.ResourceAssistance;


public class LoggerHelper {
	
	public static Logger writeLog(Class className)
    {
		Logger log=Logger.getLogger(className);
		
		Properties properties=new Properties();
		
		properties.put("log4j.rootLogger", "INFO,Console,File");
		properties.put("log4j.appender.Console", "org.apache.log4j.ConsoleAppender");
		properties.put("log4j.appender.Console.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.Console.layout.ConversionPattern",  "%-4r [%d] "+ "[%-5p] [%c %x] - %m%n");
		
		
		properties.put("log4j.appender.File","org.apache.log4j.FileAppender");
		properties.put("log4j.appender.File.file", "logs/ActiTimeLog.log");
		properties.put("log4j.appender.File.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.File.layout.ConversionPattern",  "%-4r [%d] "+ "[%-p] [%c %x] - %m%n");
		
		PropertyConfigurator.configure(properties);
		
		return log;
	}
}
