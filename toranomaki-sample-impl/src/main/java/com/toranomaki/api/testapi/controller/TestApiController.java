package com.toranomaki.api.testapi.controller;

import com.toranomaki.accessor.mysql.entity.TestTableEntity;
import com.toranomaki.api.TestApi;
import com.toranomaki.api.testapi.service.TestApiService;
import com.toranomaki.model.InlineObject;
import com.toranomaki.model.InlineResponse200;
import com.toranomaki.model.InlineResponse2001;
import com.toranomaki.model.InlineResponse2002;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestApiController implements TestApi {

  @Autowired
  private TestApiService testApiService;

  @Override
  public ResponseEntity<InlineResponse200> testPostPost(
      String query, String header, InlineObject requestBody) {
    try {
      // 登録処理
      testApiService.insert(requestBody, query, header);

      return new ResponseEntity<>(new InlineResponse200().name(requestBody.getName()),
          HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<InlineResponse2001> testGetGet(String name) {
    try {
      // 取得処理
      TestTableEntity entity = testApiService.get(name);

      // レスポンス生成
      InlineResponse2001 response = new InlineResponse2001();
      if (Objects.nonNull(entity)) {
        response.setName(entity.getNamename());
        response.setAge(entity.getAge());
        response.setQuery(entity.getQuery());
        response.setHeader(entity.getHeader());
      }

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity<InlineResponse2002> testDeleteDelete(String name) {
    try {
      // 削除処理
      testApiService.delete(name);

      return new ResponseEntity<>(new InlineResponse2002()
          .message("削除完了!!"), HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
