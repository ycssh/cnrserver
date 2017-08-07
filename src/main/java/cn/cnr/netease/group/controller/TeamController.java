package cn.cnr.netease.group.controller;

import cn.cnr.admin.base.model.Result;
import cn.cnr.admin.base.util.BeanMapConvert;
import cn.cnr.admin.base.util.LoggerUtils;
import cn.cnr.netease.NeteaseUtil;
import cn.cnr.netease.group.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc： 群相关操作
 */
@Controller
@RequestMapping("/team")
public class TeamController {



    /**
     *  创建群
     * @param teamCreateDto
     * @param request
     * @return
     */
    @RequestMapping("/create")
    public @ResponseBody
    Result teamCreate(TeamCreateDto teamCreateDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamCreate], data:{}", teamCreateDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamCreateDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/add.action", params);
    }

    /**
     *拉人 进群
     * @param teamAddDto
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody
    Result teamAdd(TeamAddDto teamAddDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamAdd], data:{}", teamAddDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamAddDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/add.action", params);
    }

    /**
     * 踢人
     * @param teamKickDto
     * @param request
     * @return
     */
    @RequestMapping("/kick")
    public @ResponseBody
    Result teamKick(TeamKickDto teamKickDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamKick], data:{}", teamKickDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamKickDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/kick.action", params);
    }

    /**
     * 踢人
     * @param teamRemoveDto
     * @param request
     * @return
     */
    @RequestMapping("/remove")
    public @ResponseBody
    Result teamRemove(TeamRemoveDto teamRemoveDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamRemove], data:{}", teamRemoveDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamRemoveDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/remove.action", params);
    }

    /**
     * 踢人
     * @param teamUpdateDto
     * @param request
     * @return
     */
    @RequestMapping("/remove")
    public @ResponseBody
    Result teamRemove(TeamUpdateDto teamUpdateDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamRemove], data:{}", teamUpdateDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamUpdateDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/update.action", params);
    }

    /**
     * 移交群主
     * @param teamChangeOwnerDto
     * @param request
     * @return
     */
    @RequestMapping("/changeOwner")
    public @ResponseBody
    Result teamChangeOwner(TeamChangeOwnerDto teamChangeOwnerDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamChangeOwner], data:{}", teamChangeOwnerDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamChangeOwnerDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/changeOwner.action", params);
    }

    /**
     * 添加管理员
     * @param teamAddManageerDto
     * @param request
     * @return
     */
    @RequestMapping("/addManager")
    public @ResponseBody
    Result teamAddManager(TeamAddManageerDto teamAddManageerDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamAddManageer], data:{}", teamAddManageerDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamAddManageerDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/addManager.action", params);
    }

    /**
     * 移交管理员
     * @param teamRemoveManageerDto
     * @param request
     * @return
     */
    @RequestMapping("/removeManager")
    public @ResponseBody
    Result teamRemoveManager(TeamRemoveManageerDto teamRemoveManageerDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:teamRemoveManager], data:{}", teamRemoveManageerDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamRemoveManageerDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/removeManager.action", params);
    }

    /**
     * 查询某个用户的所有群
     * @param accid
     * @param request
     * @return
     */
    @RequestMapping("/joinTeams")
    public @ResponseBody
    Result joinTeams(String accid, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:joinTeams], data:{}", accid);
        Map<String, String> params = new HashMap(){
            {
                put("accid",accid);
            }
        };
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/removeManager.action", params);
    }

    /**
     * 修改群昵称
     * @param teamUpdateNickDto
     * @param request
     * @return
     */
    @RequestMapping("/updateTeamNick")
    public @ResponseBody
    Result updateTeamNick(TeamUpdateNickDto teamUpdateNickDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:updateTeamNick], data:{}", teamUpdateNickDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamUpdateNickDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/updateTeamNick.action", params);
    }

    /**
     * 高级群修改消息提醒开关
     * @param teamMuteDto
     * @param request
     * @return
     */
    @RequestMapping("/muteTeam")
    public @ResponseBody
    Result muteTeam(TeamMuteDto teamMuteDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:muteTeam], data:{}", teamMuteDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamMuteDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/muteTeam.action", params);
    }

    /**
     * 高级群修改消息提醒开关
     * @param teamMuteTlistDto
     * @param request
     * @return
     */
    @RequestMapping("/muteTlist")
    public @ResponseBody
    Result muteTlist(TeamMuteTlistDto teamMuteTlistDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:muteTlist], data:{}", teamMuteTlistDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamMuteTlistDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/muteTlist.action", params);
    }

    /**
     * 退群
     * @param teamLeaveDto
     * @param request
     * @return
     */
    @RequestMapping("/leave")
    public @ResponseBody
    Result leave(TeamLeaveDto teamLeaveDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:muteTlist], data:{}", teamLeaveDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamLeaveDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/leave.action", params);
    }

    /**
     * 禁言群组，普通成员不能发送消息，创建者和管理员可以发送消息
     * @param teamLeaveDto
     * @param request
     * @return
     */
    @RequestMapping("/muteTlistAll")
    public @ResponseBody
    Result muteTlistAll(TeamLeaveDto teamLeaveDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:muteTlist], data:{}", teamLeaveDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamLeaveDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/muteTlistAll.action", params);
    }

    /**
     * 获取群组禁言的成员列表
     * @param teamLeaveDto
     * @param request
     * @return
     */
    @RequestMapping("/listTeamMute")
    public @ResponseBody
    Result listTeamMute(TeamLeaveDto teamLeaveDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:muteTlist], data:{}", teamLeaveDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(teamLeaveDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/team/listTeamMute.action", params);
    }

}
