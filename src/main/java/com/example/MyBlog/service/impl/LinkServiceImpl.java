package com.example.MyBlog.service.impl;

import com.example.MyBlog.dao.BlogLinkMapper;
import com.example.MyBlog.entity.BlogLink;
import com.example.MyBlog.service.LinkService;
import com.example.MyBlog.util.PageQueryUtil;
import com.example.MyBlog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Resource
    private BlogLinkMapper blogLinkMapper;

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLink> links=blogLinkMapper.findLinkList(pageUtil);
        int total=blogLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult=new PageResult(links,total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalLinks() {
        return blogLinkMapper.getTotalLinks(null);
    }

    @Override
    public Boolean saveLink(BlogLink link) {
        return blogLinkMapper.insert(link)>0;
    }

    @Override
    public BlogLink selectById(Integer id) {
        return blogLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(BlogLink tempLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(tempLink)>0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogLinkMapper.deleteBatch(ids)>0;
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        List<BlogLink> links=blogLinkMapper.findLinkList(null);
        if(!CollectionUtils.isEmpty(links)){
            Map<Byte,List<BlogLink>> listMap=links.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
            return listMap;
        }
        return null;
    }
}
