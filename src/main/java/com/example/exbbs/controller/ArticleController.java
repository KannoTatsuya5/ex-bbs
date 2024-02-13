package com.example.exbbs.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbbs.form.ArticleForm;
import com.example.exbbs.model.Article;
import com.example.exbbs.repository.ArticleRepository;

@Controller
@RequestMapping("/bbs")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 記事とコメントの一覧表示画面に遷移.
     * 
     * @param model
     * @return 一覧表示画面.
     */
    @GetMapping("/index")
    public String index(Model model) {
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "bbs/articleList";
    }
    
    /**
     * 記事を追加し、一覧画面にリダイレクトする処理.
     * 
     * @param form
     * @return 一覧画面
     */
    @PostMapping("/insert")
    public String insertArticle(ArticleForm form) {
        Article article = new Article();
        BeanUtils.copyProperties(form, article);
        articleRepository.insertArticle(article);
        return "redirect:/bbs/index";
    }

    /**
     * 記事を削除し、一覧画面にリダレクトする処理.
     * 
     * @param id
     * @return 一覧画面　
     */
    @PostMapping("/delete")
    public String deleteArtcle(Article article) {
        articleRepository.deleteArticle(article.getId());
        return "redirect:/bbs/index";
    }

}
