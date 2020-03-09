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
        Waveform_info wi = projectMapper.getPath(map);

        List<File> files = (List<File>) FileUtils.listFiles(new File(wi.getFilepath()), null, true);

        for(int i=0; i<files.size();i++){
            System.out.println(files.get(i));

            map.put("dataId",wi.getId());
            //System.out.println(projectMapper.checkIsValid(map));
            //if(projectMapper.checkIsValid(map)==0){
                List<Integer> list = getCsvFile(map.get("dataId").toString());
                List<int []> list_ = new ArrayList<>();
                for(int j = 0 ; j<list.size(); j++ ){
                    System.out.println(list.get(j));
                    int temp[] = {j, list.get(j)};
                    list_.add(j, temp);
                }

                map.put("data", list_);
                map.put("point", "");
                map.put("markingsInfo", "");
                //map.put("point", projectMapper.getMarkings(map));
                //map.put("markingsInfo", projectMapper.getMarkingsInfo());
                return map;
            //}

        }

        /*int dataInfoCnt = projectMapper.getDataInfoCount(map.get("userId").toString());
        if(map.get("dataId") == null || map.get("dataId").equals("") ){
            map.put("dataId",dataInfoCnt+1);
        }*/
        /*List<Integer> list = projectMapper.getData(map);
        List<int []> list_ = new ArrayList<>();
        for(int i = 0 ; i<list.size(); i++ ){
            int temp[] = {i, list.get(i)};
            list_.add(i, temp);
        }
        map.put("data", list_);
        map.put("point", projectMapper.getMarkings(map));
        map.put("markingsInfo", projectMapper.getMarkingsInfo());*/

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
                    System.out.println(Integer.parseInt(field));
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
