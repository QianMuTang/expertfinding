package com.njust.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadJsonUtil {

    //读取json文件
    public static String getJson(String filename){
        System.out.println("文件路径："+filename);
        StringBuilder laststr = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStreamReader reader = null;
        try{
            File file = new File(filename);
            if (file.isFile() && file.exists()){
                reader = new InputStreamReader(new FileInputStream(file), "gbk");
                bufferedReader = new BufferedReader(reader);
                String tempString = null;
                String temp = null;
                while ((tempString=bufferedReader.readLine()) != null) {
                    temp = removeMarks(tempString);
                    laststr.append(temp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(bufferedReader!=null){
                try{
                    bufferedReader.close();
                    reader.close();
                }catch(IOException el){
                }
            }
        }
        return laststr.toString();
    }

    //去除多余的双引号
    public static String removeMarks(String temp){
        StringBuilder stringBuilder = new StringBuilder(temp);
        for(int i = 0; i < stringBuilder.length()-1; i++){
            if (temp.charAt(i) == '"'){
                if (temp.charAt(i-1) != '{' && temp.charAt(i-1) != ' ' && temp.charAt(i-1) != '['){
                    if(temp.charAt(i+1) != '}' && temp.charAt(i+1) != ':' && temp.charAt(i+1) != ',' && temp.charAt(i+1) != ']') {
                        stringBuilder.setCharAt(i,' ');
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    //解析一条论文
    public static Map<String, Object> getPaper(JSONObject obj){
        Map<String, Object> map = new HashMap<>();
        String result_name = "";
        if (obj.has("title") && !obj.isNull("title")){
            result_name = obj.getString("title");
        }
        String result_desc = "";
        if (obj.has("summary") && !obj.isNull("summary")){
            result_desc = obj.getString("summary");
        }
        Integer result_type = 1;
        String result_time = obj.getString("pubTime");
        String publication_name = "";
        if (obj.has("source") && !obj.isNull("source")){
            publication_name = obj.getString("source");
        }
        String author = "";
        if (obj.has("author")){
            JSONArray authors = obj.getJSONArray("author");
            author = authors.toString();
        }

        map.put("result_name", result_name);
        map.put("result_desc", result_desc);
        map.put("result_type", result_type);
        map.put("result_time", result_time);
        map.put("publication_name", publication_name);
        map.put("author", author);
        return map;
    }

    //解析一条专利
    public static Map<String, Object> getPatent(JSONObject obj){
        Map<String, Object> map = new HashMap<>();

        String result_name = obj.getString("title");
        String result_desc = "";
        if (obj.has("summary")){
            result_desc = obj.getString("summary");
        }
        Integer result_type = 2;
        String result_time = obj.getString("pubTime");
        String authors = "";
        if (obj.has("author")){
            JSONArray author = obj.getJSONArray("author");
            authors = author.toString();
        }
        map.put("result_name", result_name);
        map.put("result_desc", result_desc);
        map.put("result_type", result_type);
        map.put("result_time", result_time);
        map.put("author", authors);
        return map;
    }

    /**
     * type=1 or type=2
     * @param txtStr
     * @param type
     * @return
     */
    public static List<Map<String, Object>> getInfo(String txtStr, Integer type) {
        JSONObject json = new JSONObject(txtStr.replace('\\', ' ' ));
        JSONArray data = json.getJSONArray("data");
        List<Map<String, Object>> list_paper = new ArrayList<>();
        List<Map<String, Object>> list_patent = new ArrayList<>();

        int correct = 0;
        int error = 0;
        if (type == 1) {
            // 解析论文
            System.out.println("解析论文");
            for (int i = 0; i < data.length(); i++) {
                if((i+1) % 150 == 0){
                    System.out.print(".\n");
                }else{
                    System.out.print(".");
                }

                try{
                    JSONObject obj = data.getJSONObject(i);
                    if (!obj.getString("type").equals("60")) {
                        list_paper.add(getPaper(obj));
                        correct++;
                    }
                }catch (Exception e){
                    error++;
                    continue;
                }
            }
            System.out.println("\n正确解析论文："+correct+"个，错误解析论文："+error+"个");
            return list_paper;
        }
        if (type == 2) {
            //解析专利
            System.out.println("解析专利");
            for (int i = 0; i < data.length(); i++) {
                if((i+1) % 150 == 0){
                    System.out.print(".\n");
                }else{
                    System.out.print(".");
                }

                try {
                    JSONObject obj = data.getJSONObject(i);
                    if (obj.getString("type").equals("60")) {
                        list_patent.add(getPatent(obj));
                        correct++;
                    }
                } catch (Exception e) {
                    error++;
                    continue;
                }
            }
            System.out.println("\n正确解析专利："+correct+"个，错误解析专利："+error+"个");
            return list_patent;
        }
        return null;
    }

    /**
     *
     * @param file 文件路径
     * @param type 读取成果的类型，1：论文，2：专利，3：获奖
     * @return
     */
    public static List<Map<String, Object>> parse(String file, Integer type){
        String txtStr = ReadJsonUtil.getJson(file);
        List<Map<String, Object>> list =  ReadJsonUtil.getInfo(txtStr, type);
        return list;
    }
}
