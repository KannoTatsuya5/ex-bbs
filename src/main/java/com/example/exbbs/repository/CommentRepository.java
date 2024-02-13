package com.example.exbbs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.model.Comment;

@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * コメントを追加する処理.
     * 
     * @param comment
     */
    public void insertComment(Comment comment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        String sql = "INSERT INTO comments(name,content,article_id) values(:name,:content,:articleId)";
        template.update(sql, param);
    }
}
