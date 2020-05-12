package com.senlainc.gitcourses.kashko.raman.controller.webconfig;

import com.senlainc.gitcourses.kashko.raman.entity.Room;
import com.senlainc.gitcourses.kashko.raman.serviceimpl.DBConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {DBConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
