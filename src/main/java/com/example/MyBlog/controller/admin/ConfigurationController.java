package com.example.MyBlog.controller.admin;

import com.example.MyBlog.service.ConfigService;
import com.example.MyBlog.util.Result;
import com.example.MyBlog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ConfigurationController {

    @Resource
    private ConfigService configService;

    /**
     * 处理点击跳转到对应页面的请求
     * @param request
     * @return
     */
    @GetMapping("/configurations")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "configurations");
        request.setAttribute("configurations", configService.getAllConfigs());
        return "admin/configuration";
    }

    /**
     * 更改站点信息
     * @param websiteName
     * @param websiteDescription
     * @param websiteLogo
     * @param websiteIcon
     * @return
     */
    @PostMapping("/configurations/website")
    @ResponseBody
    public Result website(@RequestParam(value = "websiteName", required = false) String websiteName,
                          @RequestParam(value = "websiteDescription", required = false) String websiteDescription,
                          @RequestParam(value = "websiteLogo", required = false) String websiteLogo,
                          @RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(websiteName)) {
            updateResult += configService.updateConfig("websiteName", websiteName);
        }
        if (!StringUtils.isEmpty(websiteDescription)) {
            updateResult += configService.updateConfig("websiteDescription", websiteDescription);
        }
        if (!StringUtils.isEmpty(websiteLogo)) {
            updateResult += configService.updateConfig("websiteLogo", websiteLogo);
        }
        if (!StringUtils.isEmpty(websiteIcon)) {
            updateResult += configService.updateConfig("websiteIcon", websiteIcon);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    /**
     * 更改用户信息
     * @param yourAvatar
     * @param yourName
     * @param yourEmail
     * @return
     */
    @PostMapping("/configurations/userInfo")
    @ResponseBody
    public Result userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
                           @RequestParam(value = "yourName", required = false) String yourName,
                           @RequestParam(value = "yourEmail", required = false) String yourEmail) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(yourAvatar)) {
            updateResult += configService.updateConfig("yourAvatar", yourAvatar);
        }
        if (!StringUtils.isEmpty(yourName)) {
            updateResult += configService.updateConfig("yourName", yourName);
        }
        if (!StringUtils.isEmpty(yourEmail)) {
            updateResult += configService.updateConfig("yourEmail", yourEmail);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }

    /**
     * 更改底部设置
     * @param footerAbout
     * @param footerICP
     * @param footerCopyRight
     * @param footerPoweredBy
     * @param footerPoweredByURL
     * @return
     */
    @PostMapping("/configurations/footer")
    @ResponseBody
    public Result footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
                         @RequestParam(value = "footerICP", required = false) String footerICP,
                         @RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
                         @RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
                         @RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
        int updateResult = 0;
        if (!StringUtils.isEmpty(footerAbout)) {
            updateResult += configService.updateConfig("footerAbout", footerAbout);
        }
        if (!StringUtils.isEmpty(footerICP)) {
            updateResult += configService.updateConfig("footerICP", footerICP);
        }
        if (!StringUtils.isEmpty(footerCopyRight)) {
            updateResult += configService.updateConfig("footerCopyRight", footerCopyRight);
        }
        if (!StringUtils.isEmpty(footerPoweredBy)) {
            updateResult += configService.updateConfig("footerPoweredBy", footerPoweredBy);
        }
        if (!StringUtils.isEmpty(footerPoweredByURL)) {
            updateResult += configService.updateConfig("footerPoweredByURL", footerPoweredByURL);
        }
        return ResultGenerator.genSuccessResult(updateResult > 0);
    }
}
