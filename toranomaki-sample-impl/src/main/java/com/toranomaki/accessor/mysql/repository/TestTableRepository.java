package com.toranomaki.accessor.mysql.repository;

import com.toranomaki.accessor.mysql.entity.TestTableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TestTableRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private static final RowMapper<TestTableEntity> ROW_MAPPER =
      new BeanPropertyRowMapper<>(TestTableEntity.class);

  public void insert(String name, Integer age, String query, String header) {
    jdbcTemplate.update("INSERT INTO test_table VALUES (?,?,?,?) ",
        name, age, query, header);
  }

  public TestTableEntity findByName(String name) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM test_table WHERE namename=?",
          ROW_MAPPER, name);
    } catch (
        EmptyResultDataAccessException e) {
      return null;
    }
  }

  public void deleteByName(String name) {
    jdbcTemplate.update("DELETE FROM test_table WHERE namename=? ", name);
  }
}
