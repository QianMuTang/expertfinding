package com.njust.utils;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import com.njust.bean.baseBean.Expert;
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
        String link = "";
        if(obj.has("link") && !obj.isNull("link")){
            link = obj.getString("link");
        }
        String result_desc = "";
        if (obj.has("summary") && !obj.isNull("summary")){
            result_desc = obj.getString("summary");
        }
        Integer result_type = 1;
        String result_time = "";
        if (obj.has("pubTime") && !obj.isNull("pubTime")){
            result_time = obj.getString("pubTime");
        }
        String keyword = "";
        if (obj.has("keyword") && !obj.isNull("keyword")){
            JSONArray keywords = obj.getJSONArray("keyword");
            keyword = keywords.toString();
        }
        Integer cited_num = 0;
        String classify_num = "";
        if (obj.has("extra") && !obj.isNull("extra")){
            JSONObject extra = obj.getJSONObject("extra");
            System.out.println("hhhhh"+obj.getJSONObject("extra"));
            if (extra.has("被引频次") && !extra.isNull("被引频次")){
                System.out.println("this is cited_num");
                cited_num = Integer.parseInt(extra.getString("被引频次"));
            }
            if(extra.has("分类号") && !extra.isNull("分类号")){
                System.out.println("this is classify_num");
                classify_num = extra.getString("分类号");
            }
        }
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
        map.put("link", link);
        map.put("result_desc", result_desc);
        map.put("result_type", result_type);
        map.put("result_time", result_time);
        map.put("keyword", keyword);
        map.put("cited_num", cited_num);
        map.put("classify_num", classify_num);
        map.put("publication_name", publication_name);
        map.put("author", author);
        return map;
    }

    //解析一条专利
    public static Map<String, Object> getPatent(JSONObject obj){
        Map<String, Object> map = new HashMap<>();

        String result_name = "";
        if (obj.has("title") && !obj.isNull("title")){
            result_name = obj.getString("title");
        }
        String link = "";
        if(obj.has("link") && !obj.isNull("link")){
            link = obj.getString("link");
        }
        String result_desc = "";
        if (obj.has("summary")){
            result_desc = obj.getString("summary");
        }
        Integer result_type = 2;
        String result_time = "";
        if (obj.has("pubTime") && !obj.isNull("pubTime")){
            result_time = obj.getString("pubTime");
        }
        String apply_num = "";
        String classify_num = "";
        if(obj.has("extra") && !obj.isNull("extra")){
            JSONObject extra = obj.getJSONObject("extra");
            if (extra.has("申请号") && !extra.isNull("申请号")){
                apply_num = extra.getString("申请号");
            }
            if (extra.has("专利分类号") && !extra.isNull("专利分类号")){
                classify_num = extra.getString("专利分类号");
            }
        }
        String authors = "";
        if (obj.has("author")){
            JSONArray author = obj.getJSONArray("author");
            authors = author.toString();
        }
        map.put("result_name", result_name);
        map.put("link", link);
        map.put("result_desc", result_desc);
        map.put("result_type", result_type);
        map.put("result_time", result_time);
        map.put("apply_num", apply_num);
        map.put("classify_num", classify_num);
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
        }else if (type == 2) {
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

    public static Expert getExpertInfo(String expertInfoStr){
        System.out.println("解析专家");
        try {
            JSONObject json = new JSONObject(expertInfoStr.replace('\\', ' ' ));
            Expert expert = new Expert();
            String expertName = json.getString("name");
            int gender = 0;
            if (json.has("gender") && !json.isNull("gender")){
                String temp = json.getString("gender");
                if (temp.equals("male") || temp.equals("男")){
                    gender = 1;
                }else{
                    gender = 2;
                }
            }
            String contact = "";
            if (json.has("phone") && !json.isNull("phone")){
                contact = json.getString("phone");
            }
            String introduce = "";
            if (json.has("desc") && !json.isNull("desc")){
                introduce = json.getString("desc");
            }
            String homepage = "";
            if (json.has("homepage") && !json.isNull("homepage")){
                homepage = json.getString("homepage");
            }
            String country = "";
            if (json.has("nation") && !json.isNull("nation")){
                country = json.getString("nation");
            }
            String tag = "";
            if (json.has("tags") && !json.isNull("tags")){
                JSONArray tags = json.getJSONArray("tags");
                StringBuilder stringBuilder = new StringBuilder("");
                for (int i = 0; i < tags.length(); i++){
                    if (i == tags.length()-1){
                        stringBuilder.append(tags.get(i).toString());
                    }else{
                        stringBuilder.append(tags.get(i).toString()+";");
                    }
                }
                tag = stringBuilder.toString();
                System.out.println(tag);
            }
            int hIndex = 0;
            if (json.has("h_index") && !json.isNull("h_index")){
                hIndex = json.getInt("h_index");
            }
            int gIndex = 0;
            if (json.has("g_index") && !json.isNull("g_index")){
                gIndex = json.getInt("g_index");
            }
            double sociability = 0.0;
            if (json.has("sociability") && !json.isNull("sociability")){
                sociability = json.getDouble("sociability");
            }
            double diversity = 0.0;
            if (json.has("diversity") && !json.isNull("diversity")){
                diversity = json.getDouble("diversity");
            }
            String professional_title = "";
            if (json.has("position") && !json.isNull("position")){
                professional_title = json.getString("position");
            }
            String employer = "";
            if (json.has("employer") && !json.isNull("employer")){
                employer = json.getString("employer");
            }

            expert.setExpertName(expertName);
            expert.setGender(gender);
            expert.setContact(contact);
            expert.setIntroduce(introduce);
            expert.setHomepage(homepage);
            expert.setCountry(country);
            expert.setTag(tag);
            expert.sethIndex(hIndex);
            expert.setgIndex(gIndex);
            expert.setSociability(sociability);
            expert.setDiversity(diversity);
            expert.setProfessionalTitle(professional_title);
            expert.setEmployer(employer);
            return expert;
        } catch (Exception e) {
            throw new CustomException(ResponseResultEnum.PARSE_EXPERT_FAIL);
        }
    }

    /**
     *
     * @param file 文件路径
     * @param type 读取成果的类型，1：论文，2：专利，3：专家信息
     * @return
     */
    public static List<Map<String, Object>> parseResult(String file, Integer type){
        String txtStr = ReadJsonUtil.getJson(file);
        List<Map<String, Object>> list =  ReadJsonUtil.getInfo(txtStr, type);
        return list;
    }

    public static Expert parseExpert(String file){
        String expertInfoStr = ReadJsonUtil.getJson(file);
        return ReadJsonUtil.getExpertInfo(expertInfoStr);
    }
}
