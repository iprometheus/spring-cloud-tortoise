package com.fangxing.configordertest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvConfig implements EnvironmentAware {


    private String JAVA_HOME;

    public String getJAVA_HOME() {
        return JAVA_HOME;
    }

    public void setJAVA_HOME(String JAVA_HOME) {
        this.JAVA_HOME = JAVA_HOME;
    }

    @Override
    public void setEnvironment(Environment environment) {
        //这里读取了系统的环境变量
        this.JAVA_HOME=environment.getProperty("JAVA_HOME");
    }
}
