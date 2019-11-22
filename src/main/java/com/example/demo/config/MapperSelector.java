package com.example.demo.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * MapperSelector
 * 멀티 database 사용 시 mapper 객체를 선택해 준다.
 *
 * 참고) ApplicationReadyEvent 메소드를 구현해서 초기화 한다. (ApplicationListener.readyAfterStartup 참조)
 *      즉, SpringBoot 모두 초기화 된 상태에서 접속할 DB정보를 초기화 한다.
 */
@Component
public class MapperSelector {

    private Map<String, SqlSessionTemplate> map = new HashMap<>();

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    public void init() {
        this.map.put("DEFAULT", sqlSessionTemplate);
    }

    public void add(String dbName, SqlSessionTemplate sqlSessionTemplate) {
        this.map.put(dbName, sqlSessionTemplate);
    }

    /**
     * 해당 db에 맞는 mapper 객체를 가져온다.
     * @param type Mapper.class
     * @param dbName 접속할 dbName (add 에서 넣은 키값)
     * @param <T> Mapper 클래스
     * @return
     */
    public <T> T getMapper(Class<T> type, String dbName) {
        if (this.map.containsKey(dbName)) {
            return this.map.get(dbName).getMapper(type);
        } else {
            return null;
        }
    }
}

/*
*   {Mapper Interface Class} = mapperSelector.getMapper({Mapper Interface Class}.class, {value}) ;
*
*/