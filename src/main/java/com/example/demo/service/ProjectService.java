package com.example.demo.service;

import com.example.demo.entities.Data;
import com.example.demo.entities.Markings;
import com.example.demo.entities.Waveform_info;
import com.example.demo.mapper.ProjectMapper;
import org.apache.commons.io.FileUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Map getData(Map map){

        //project Folder path
        Map wi = projectMapper.getPath(map);

        if(wi.get("userId") != null && !wi.get("userId").toString().equals("")){
            map.put("checkedUserId",wi.get("userId"));
        }

        List<File> files = (List<File>) FileUtils.listFiles(new File(wi.get("filepath").toString()), null, true);

        for(int i=0; i<files.size();i++){
            map.put("dataId",wi.get("id"));
                List<Integer> list = getCsvFile(files.get(i).toString());
                List<int []> list_ = new ArrayList<>();
                for(int j = 0 ; j<list.size(); j++ ){
                    int temp[] = {j, list.get(j)};
                    list_.add(j, temp);
                }

                map.put("data", list_);
                map.put("point", projectMapper.getMarkings(map));
                map.put("markingsInfo", projectMapper.getMarkingsInfo());
                return map;
        }
        return map;
    }

    public List<Markings> getMarking(Map map){
        List<Markings> list = projectMapper.getMarkings(map);
        return list;
    }

    public int insertMarkings(Map map){
        if(Integer.parseInt(map.get("pageNo").toString())==2){
            map.put("pageNo","count");
            List<Markings> list = projectMapper.getMarkings(map);
            map.put("pageNo","2");
            if(list.size() <= Integer.parseInt(map.get("pointId").toString()) && Integer.parseInt(map.get("pointId").toString())!=1) {
                return projectMapper.insertMarkings(map);
            } else {
                return projectMapper.updateMarkings(map);
            }
        } else {
            return projectMapper.insertMarkings(map);
        }
    }

    public int deleteMarkings(Map map){
        if(Integer.parseInt(map.get("pageNo").toString())==2){
            return projectMapper.deleteMarkingsConfirm(map);
        } else {
            return projectMapper.deleteMarkings(map);
        }
    }

    public int confirmData(Map map){
        return projectMapper.confirmData(map);
    }

    public List<Integer> getCsvFile(String filePath){
        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader br = null;
        List<Integer> tmpList = new ArrayList<Integer>();
        try{
            br = Files.newBufferedReader(Paths.get(filePath));
            Charset.forName("UTF-8");
            String line = "";

            while((line = br.readLine()) != null){
                for (String field :line.split(",")){
                    tmpList.add(Integer.parseInt(field));
                }
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
        return tmpList;
    }

}
