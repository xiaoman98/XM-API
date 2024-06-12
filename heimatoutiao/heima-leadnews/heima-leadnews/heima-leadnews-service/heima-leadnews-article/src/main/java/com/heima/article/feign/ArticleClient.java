package com.heima.article.feign;

import com.heima.apis.article.IArticleClient;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleCommentDto;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.comment.dtos.CommentConfigDto;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.StatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ArticleClient implements IArticleClient {

    @Autowired
    private ApArticleService apArticleService;

    @PostMapping("/api/v1/article/save")
    @Override
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) {
        return apArticleService.saveArticle(dto);
    }

    @Override
    public ResponseResult queryLikesAndConllections(Integer wmUserId, Date beginDate, Date endDate) {
        return null;
    }

    @Override
    public PageResponseResult newPage(StatisticsDto dto) {
        return null;
    }

    @Override
    public ResponseResult findArticleConfigByArticleId(Long articleId) {
        return null;
    }

    @Override
    public PageResponseResult findNewsComments(ArticleCommentDto dto) {
        return null;
    }

    @Override
    public ResponseResult updateCommentStatus(CommentConfigDto dto) {
        return null;
    }
}
