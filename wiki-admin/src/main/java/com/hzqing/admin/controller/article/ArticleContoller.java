package com.hzqing.admin.controller.article;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.article.Article;
import com.hzqing.admin.domain.system.UserInfo;
import com.hzqing.admin.service.article.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-07-1 18:55
 */
@RestController
@RequestMapping("/api/article")
public class ArticleContoller extends BaseController {


    @Autowired
    private IArticleService articleService;
    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage get(@PathVariable int id){
        Article article = articleService.get(id);
        return responseMessage(article);
    }
    @GetMapping("/release")
    public ResponseMessage release(int pageNum, int pageSize, Article article){
        startPage(pageNum,pageSize);
        List<Article> docs = articleService.selectList(article);
        return responseMessage(docs);
    }

    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Article article){
        startPage(pageNum,pageSize);
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        article.setUserId(userInfo.getId());
        List<Article> docs = articleService.selectList(article);
        return responseMessage(docs);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Article article){
        int res = -1;
        article = (Article) initAddOrUpdate(article);
        article.setUserId(article.getCreateBy());
        if (article.getId() == null){
            res = articleService.insert(article);
        }else {
            article.setCreateBy(null);
            res = articleService.update(article);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = articleService.deletedById(id);
        return responseMessage(res);
    }

    /**
     * 上传图片，返回图片路径
     * @param file
     * @return
     */
    @PostMapping("/uploadImages")
    public ResponseMessage uploadImages(MultipartFile file, String articleId , HttpServletRequest request){
        // 如果文章id为空，存储在tmp目录下
        if (articleId == null || articleId.equals("")) {
            articleId = "tmp";
        }
        return super.uploadImages(file,"article",articleId,request);
    }
}
