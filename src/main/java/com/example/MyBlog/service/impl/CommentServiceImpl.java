package com.example.MyBlog.service.impl;

import com.example.MyBlog.dao.BlogCommentMapper;
import com.example.MyBlog.entity.BlogComment;
import com.example.MyBlog.service.CommentService;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private BlogCommentMapper blogCommentMapper;
    @Override
    public Boolean addComment(BlogComment blogComment) {
        return blogCommentMapper.insert(blogComment)>0;
    }

    @Override
    public PageResult getCommentsPage(PageQueryUtil pageUtil) {
        List<BlogComment> comments=blogCommentMapper.findBlogCommentList(pageUtil);
        int total=blogCommentMapper.getTotalBlogComments(pageUtil);
        PageResult pageResult=new PageResult(comments,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalComments() {
        return blogCommentMapper.getTotalBlogComments(null);
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return blogCommentMapper.checkDone(ids)>0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogCommentMapper.deleteBatch(ids)>0;
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        BlogComment blogComment=blogCommentMapper.selectByPrimaryKey(commentId);
        if(blogComment!=null&&blogComment.getCommentStatus().intValue()==1){
            blogComment.setReplyBody(replyBody);
            blogComment.setReplyCreateTime(new Date());
            return blogCommentMapper.updateByPrimaryKeySelective(blogComment)>0;
        }
        return false;
    }

    @Override
    public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
        if(page<1){
            return null;
        }
        Map params=new HashMap();
        params.put("page",page);
        params.put("limit",8);
        params.put("commentStatus",1);
        PageQueryUtil pageQueryUtil=new PageQueryUtil(params);
        List<BlogComment> comments=blogCommentMapper.findBlogCommentList(pageQueryUtil);
        if(!CollectionUtils.isEmpty(comments)){
            int total=blogCommentMapper.getTotalBlogComments(pageQueryUtil);
            PageResult pageResult=new PageResult(comments, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
            return pageResult;
        }
        return null;
    }
}
