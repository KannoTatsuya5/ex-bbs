package com.example.exbbs.form;

import jakarta.validation.constraints.NotBlank;

public class CommentForm {
    /* ArticleID */
    private Integer articleId;
    /* 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;
    /* 内容 */
    @NotBlank(message = "コメントを入力してください")
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
    }

}
