package com.example.exbbs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbbs.model.Article;
import com.example.exbbs.model.Comment;

@Repository
public class ArticleRepository {
    @Autowired
    private  NamedParameterJdbcTemplate template;

    private static final ResultSetExtractor<List<Article>> ARTICLE_COMMENT_RESULTSET = (rs) -> {
        List<Article> articleList = new ArrayList<>();
        List<Comment> commentList = null;
        int beforeArticleId = 0;
        while(rs.next()) {
            int nowArticleId = rs.getInt("article_id");
            if(beforeArticleId != nowArticleId) {
                Article article = new Article();
				article.setId(nowArticleId);
				article.setName(rs.getString("article_name"));
				article.setContent(rs.getString("article_content"));
				commentList = new ArrayList<Comment>();
				article.setCommentList(commentList);
				articleList.add(article);
            }

			if (rs.getInt("comment_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("comment_id"));
				comment.setName(rs.getString("comment_name"));
				comment.setContent(rs.getString("comment_content"));
				comment.setArticleId(rs.getInt("comment_article_id"));
				commentList.add(comment);
			}

			beforeArticleId = nowArticleId;
        }
        return articleList;
    };

    /**
     * 記事とコメントを結合させて取得する処理.
     * 
     * @return 記事とコメント情報
     */
    public List<Article> findAll() {
        String sql = """
            SELECT 
                a.id as article_id,
                a.name as article_name,
                a.content as article_content,
                c.id as comment_id,
                c.name as comment_name,
                c.content as comment_content,
                c.article_id as comment_article_id
            FROM 
                articles as a
            LEFT JOIN 
                comments as c
            ON 
                a.id = c.article_id
            ORDER BY 
                a.id asc;
                """;
        
        List<Article> articleList = template.query(sql,ARTICLE_COMMENT_RESULTSET);
        return articleList;
    }

    /**
     * 記事を追加する処理.
     * 
     * @param article
     * @return
     */
    public void insertArticle(Article article) {
        String sql = "INSERT INTO articles(name,content) VALUES(:name,:content)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        template.update(sql, param);
    }

    /**
     * 削除する処理.
     * 
     * @param id
     */
    public void deleteArticle(Integer id) {
        String sql = "DELETE FROM articles WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id",id);
        template.update(sql, param);
    }
}
