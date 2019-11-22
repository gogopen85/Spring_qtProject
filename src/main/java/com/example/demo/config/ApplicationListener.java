package com.example.demo.config;

import com.example.demo.entities.ConnectionInfo;
import com.example.demo.mapper.ConnectionInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ApplicationListener {

    @Value("${mapper.xmlpath}")
    private String mapperXmlPath;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private MapperSelector mapperSelector;

    @Autowired
    private ConnectionInfoMapper connectionInfoMapper;

    /**
     * SpringBoot 가 시작된 후 호출~
     * http://javainfinite.com/spring-boot/springboot-applicationreadyevent-applicationfailedevent-contextrefreshedevent/
     */
    @EventListener(ApplicationReadyEvent.class)
    public void readyAfterStartup() {
        System.out.println("========-------->");
        System.out.println("========--------> ApplicationReady!!! ");

        if (mapperXmlPath == null || mapperXmlPath.isEmpty())
            return;

        List<ConnectionInfo> lst = connectionInfoMapper.getConnectionInfos();
        for (int i = 0; i < lst.size(); i++) {
            try {
                ConnectionInfo connectionInfo = lst.get(i);
                String url = String.format("%s?currentSchema=%s", connectionInfo.getHost(), connectionInfo.getSchemaName());
                DataSource ds = this.createDataSource(url, connectionInfo.getUserName(), connectionInfo.getPassword());
                SqlSessionFactory sqlSessionFactory = this.createSqlSessionFactory(ds, appContext);
                SqlSessionTemplate sqlSessionTemplate = this.createSqlSessionTemplate(sqlSessionFactory);

                mapperSelector.add(connectionInfo.getName(), sqlSessionTemplate);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("========-------->");
    }

    private DataSource createDataSource(String url, String username, String password) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    private SqlSessionFactory createSqlSessionFactory(
            DataSource ds,
            ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(String.format("classpath:%s", mapperXmlPath)));
        return sqlSessionFactoryBean.getObject();
    }

    public SqlSessionTemplate createSqlSessionTemplate(
            SqlSessionFactory db2SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }
}
