package com.toranomaki.api.testapi.service;

import com.toranomaki.accessor.mysql.entity.TestTableEntity;
import com.toranomaki.accessor.mysql.repository.TestTableRepository;
import com.toranomaki.model.InlineObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestApiService {

  @Autowired
  private TestTableRepository testTableRepository;

  public void insert(InlineObject requestBody, String query, String header) {
    // DBに登録
    testTableRepository.insert(requestBody.getName(), requestBody.getAge(), query, header);
  }

  public TestTableEntity get(String name) {
    // DBから取得
    return testTableRepository.findByName(name);
  }

  public void delete(String name) {
    // 削除
    testTableRepository.deleteByName(name);
  }
}
