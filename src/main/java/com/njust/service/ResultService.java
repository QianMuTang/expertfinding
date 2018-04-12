package com.njust.service;

import java.util.List;
import java.util.Map;

public interface ResultService {
    String insertPaper(List<Map<String, Object>> list) throws Exception;
    String insertPatent(List<Map<String, Object>> list) throws Exception;
}
