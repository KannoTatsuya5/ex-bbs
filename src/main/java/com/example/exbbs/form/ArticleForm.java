package com.example.exbbs.form;

import jakarta.validation.constraints.NotBlank;

public class ArticleForm {
    /* 名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;
    /* 内容 */
    @NotBlank(message = "内容を入力してください")
    private String content;

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
        return "ArticleForm [name=" + name + ", content=" + content + "]";
    }

}
