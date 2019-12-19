package com.example.demo.service;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import com.example.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Map getData(Map map){
        
        int dataInfoCnt = projectMapper.getDataInfoCount(map.get("userId").toString());
        if(map.get("dataId") == null || map.get("dataId").equals("") ){
            map.put("dataId",dataInfoCnt+1);
        }
        List<Integer> list = projectMapper.getData(map);
        List<int []> list_ = new ArrayList<>();
        for(int i = 0 ; i<list.size(); i++ ){
            int temp[] = {i, list.get(i)};
            list_.add(i, temp);
        }
        map.put("data", list_);
        map.put("point", projectMapper.getMarkings(map));
        map.put("markingsInfo", projectMapper.getMarkingsInfo());

        return map;
    }

    public List<Integer> getMarking(Map map){
        List<Integer> list = projectMapper.getMarkings(map);
        return list;
    }

    public int insertMarkings(Map map){
        return projectMapper.insertMarkings(map);
    }

    public int deleteMarkings(Map map){
        return projectMapper.deleteMarkings(map);
    }

    public int confirmData(Map map){
        return projectMapper.confirmData(map);
    }

    public int insertCsvFile(){
        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader br = null;

        try{
            br = Files.newBufferedReader(Paths.get("/Users/evidnet/Desktop/sample31.csv"));
            Charset.forName("UTF-8");
            String line = "";
            int dataId = 0;
            while((line = br.readLine()) != null){
                dataId = projectMapper.getDataId();

                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                tmpList = Arrays.asList(array);
                Map map = new HashMap();
                map.put("dataId",dataId+1);
                for(int i=0;i < tmpList.size(); i ++){
                    map.put("point",tmpList.get(i));
                    projectMapper.insertData(map);
                }

                System.out.println(tmpList);
                ret.add(tmpList);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return 0;
    }

}
