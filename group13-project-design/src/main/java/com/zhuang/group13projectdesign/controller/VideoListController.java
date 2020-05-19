package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.*;
import com.zhuang.group13projectdesign.mapper.*;
import com.zhuang.group13projectdesign.result.ResultMap;
import com.zhuang.group13projectdesign.utils.MyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/Video")
@Api(value = "/视频", description = "视频")
public class VideoListController {
    @Autowired
    private VideoListMapper videoListMapper;
    @Autowired
    private NoticeListMapper noticeListMapper;
    @Autowired
    private ArtcileListMapper artcileListMapper;
    @Autowired
    private WorkListMapper workListMapper;
    @Autowired
    private WorkTeachMapper workTeachMapper;
    @Autowired
    private UserListMapper userListMapper;
    @RequestMapping(value = "/videoList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "视频列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadVideo(@ApiParam(name = "data", value = "{\"id\":\"可以根据视频id查询\",\"pageNum\":\"\",\"pageSize\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String id = json.optString("id");
            VideoListQuery query = new VideoListQuery();
            VideoListQuery.Criteria criteria = query.createCriteria();
            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            if(!MyUtil.isEmpty(id)){
                criteria.andIdEqualTo(Integer.valueOf(id));
            }
            int count = videoListMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<VideoList> videoLists = videoListMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(videoLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }
    @RequestMapping(value = "/noticeList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "公告列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap noticeList(@ApiParam(name = "data", value = "{\"id\":\"可以根据视频id查询\",\"pageNum\":\"\",\"pageSize\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String id = json.optString("id");
            NoticeListQuery query = new NoticeListQuery();
            NoticeListQuery.Criteria criteria = query.createCriteria();
            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            if(!MyUtil.isEmpty(id)){
                criteria.andIdEqualTo(Integer.valueOf(id));
            }
            int count = noticeListMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<NoticeList> noticeLists = noticeListMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(noticeLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }

    @RequestMapping(value = "/artcileList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "文章列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap artcileList(@ApiParam(name = "data", value = "{\"id\":\"可以根据视频id查询\",\"pageNum\":\"页码\",\"pageSize\":\"条数\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String id = json.optString("id");
            ArtcileListQuery query = new ArtcileListQuery();
            ArtcileListQuery.Criteria criteria = query.createCriteria();
            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            if(!MyUtil.isEmpty(id)){
                criteria.andIdEqualTo(Integer.valueOf(id));
            }
            int count = artcileListMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<ArtcileList> artcileLists = artcileListMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(artcileLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }

    @RequestMapping(value = "/workteachList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看老师布置的作业列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap workteachList(@ApiParam(name = "data", value = "{\"userid\":\"学生id\",\"pageNum\":\"页码\",\"pageSize\":\"条数\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String userid = json.optString("userid");
            if(MyUtil.isEmpty(userid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("userid不能为空");
                return map;
            }
            UserList userList = userListMapper.selectByPrimaryKey(Integer.valueOf(userid));
            if(userList==null){
                map.setMsg("未查询到该学生");
                map.setState(ResultMap.ERROR);
                return  map;
            }
            if(MyUtil.isEmpty(userList.getTeacherid())){
                map.setMsg("该学生暂未分配老师");
                map.setState(ResultMap.ERROR);
                return map;
            }
            WorkTeachQuery query = new WorkTeachQuery();
            WorkTeachQuery.Criteria criteria = query.createCriteria();
            criteria.andUseridEqualTo(userList.getTeacherid());
            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            int count = workTeachMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<WorkTeach> workTeachLists = workTeachMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(workTeachLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }
    @RequestMapping(value = "/workList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看已经提交的作业列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap workLists(@ApiParam(name = "data", value = "{\"workid\":\"布置作业列表的作业id\",\"pageNum\":\"页码\",\"pageSize\":\"条数\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String workid = json.optString("workid");
            if(MyUtil.isEmpty(workid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("workid不能为空");
                return map;
            }
            WorkListQuery query = new WorkListQuery();
            WorkListQuery.Criteria criteria = query.createCriteria();
            criteria.andWorkidEqualTo(Integer.valueOf(workid));
            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            int count = workListMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<WorkList> workLists = workListMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(workLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }
    @RequestMapping(value = "/studentList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "该老师名下所有学生", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap studentList(@ApiParam(name = "data", value = "{\"teacherid\":\"该登录的老师id\",\"pageNum\":\"页码\",\"pageSize\":\"条数\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String teacherid = json.optString("teacherid");
            if(MyUtil.isEmpty(teacherid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("teacherid不能为空");
                return map;
            }
            UserListQuery query = new UserListQuery();
            query.createCriteria().andTeacheridEqualTo(Integer.valueOf(teacherid));

            int pageNum = 1;
            int pageSize = 10;
            if (json.has("pageNum")&& MyUtil.isNotEmpty(json.getString("pageNum"))) {
                pageNum = Integer.parseInt(json.getString("pageNum"));
            }
            if(json.has("pageSize")&& MyUtil.isNotEmpty(json.getString("pageSize"))) {
                pageSize = Integer.parseInt(json.getString("pageSize"));
            }
            int count = userListMapper.countByExample(query);
            query.setPageNo(pageNum);
            query.setPageSize(pageSize);
            List<UserList> userLists = userListMapper.selectByExample(query);
            map.setMsg(count+"");
            map.setState(ResultMap.SUCCESS);
            map.setData(userLists);
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }
    @RequestMapping(value = "/studentToteacher", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学生绑定老师id", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap studentToteacher(@ApiParam(name = "data", value = "{\"studentid\":\"该账号登录的id\",\"teacherid\":\"要绑定老师的id\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String studentid = json.optString("studentid");
            String teacherid = json.optString("teacherid");
            if(MyUtil.isEmpty(studentid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("studentid不能为空");
                return map;
            }
            if(MyUtil.isEmpty(teacherid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("teacherid不能为空");
                return map;
            }
            UserList userList = userListMapper.selectByPrimaryKey(Integer.valueOf(studentid));
            if(userList==null){
                map.setState(ResultMap.ERROR);
                map.setMsg("studentid不存在");
                return map;
            }
            UserList teachList = userListMapper.selectByPrimaryKey(Integer.valueOf(teacherid));
            if(teachList==null){
                map.setState(ResultMap.ERROR);
                map.setMsg("teacherid不存在");
                return map;
            }
            userList.setTeacherid(Integer.valueOf(teacherid));
            userListMapper.updateByPrimaryKey(userList);
            map.setMsg("绑定成功");
            map.setState(ResultMap.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }


    @RequestMapping(value = "/teacherList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "全部老师列表", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap teacherList(@ApiParam(name = "data", value = "{}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            UserListQuery query = new UserListQuery();
            query.createCriteria().andRoleEqualTo("老师");
            List<UserList> userLists = userListMapper.selectByExample(query);
            map.setData(userLists);
            map.setState(ResultMap.SUCCESS);
            map.setMsg("请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }


    @RequestMapping(value = "/delstudent", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "老师解除关系", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap delstudent(@ApiParam(name = "data", value = "{\"teacherid\":\"\",\"studentid\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String studentid = json.optString("studentid");
            String teacherid = json.optString("teacherid");
            if(MyUtil.isEmpty(studentid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("studentid不能为空");
                return map;
            }
            if(MyUtil.isEmpty(teacherid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("teacherid不能为空");
                return map;
            }
            UserList userList = userListMapper.selectByPrimaryKey(Integer.valueOf(studentid));
            if(userList==null){
                map.setState(ResultMap.ERROR);
                map.setMsg("studentid不存在");
                return map;
            }
            UserList teachList = userListMapper.selectByPrimaryKey(Integer.valueOf(teacherid));
            if(teachList==null){
                map.setState(ResultMap.ERROR);
                map.setMsg("teacherid不存在");
                return map;
            }
            UserListQuery query = new UserListQuery();
            UserListQuery.Criteria criteria = query.createCriteria();
            criteria.andTeacheridEqualTo(Integer.valueOf(teacherid));
            criteria.andIdEqualTo(Integer.valueOf(studentid));
            List<UserList> userLists = userListMapper.selectByExample(query);
            if(userLists==null|| userLists.size()<1){
                map.setMsg("该师生没有绑定");
                map.setState(ResultMap.ERROR);
                return map;
            }
            UserList u = userLists.get(0);
            u.setTeacherid(null);
            userListMapper.updateByPrimaryKey(u);
            map.setState(ResultMap.SUCCESS);
            map.setMsg("解绑成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }

    @RequestMapping(value = "/downloadvideo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学生下载视频", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap downloadvideo(@ApiParam(name = "data", value = "{\"videoid\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String videoid = json.optString("videoid");
            if(MyUtil.isEmpty(videoid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("videoid不能为空");
                return map;
            }
            VideoList videoList = videoListMapper.selectByPrimaryKey(Integer.valueOf(videoid));
            if(videoList==null){
                map.setMsg("该视频不存在");
                map.setState(ResultMap.ERROR);
                return map;
            }
            String videourl = videoList.getVideoUrl();
            map.setMsg(videourl);
            map.setState(ResultMap.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }

    @RequestMapping(value = "/downloadwork", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "下载作业，返回作业的url，需要前端js在url获取内容", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap downloadwork(@ApiParam(name = "data", value = "{\"workid\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String workid = json.optString("workid");
            if(MyUtil.isEmpty(workid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("workid不能为空");
                return map;
            }
            WorkList workList = workListMapper.selectByPrimaryKey(Integer.valueOf(workid));
            if(workList==null){
                map.setMsg("该作业不存在");
                map.setState(ResultMap.ERROR);
                return map;
            }
            String workUrl = workList.getWorkUrl();
            map.setMsg(workUrl);
            map.setState(ResultMap.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }
    @RequestMapping(value = "/pigaiWork", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "老师批改作业", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap pigaiWork(@ApiParam(name = "data", value = "{\"workid\":\"布置作业列表的作业id\",\"states\":\"作业反馈，可以是得分或者已完成什么的\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject json = JSONObject.fromObject(data);
            String workid = json.optString("workid");
            if(MyUtil.isEmpty(workid)){
                map.setState(ResultMap.ERROR);
                map.setMsg("workid不能为空");
                return map;
            }
            String states = json.optString("states");
            if(MyUtil.isEmpty(states)){
                map.setState(ResultMap.ERROR);
                map.setMsg("workid不能为空");
                return map;
            }
            WorkList workList = new WorkList();
            workList.setId(Integer.valueOf(workid));
            workList.setStates(states);
            workListMapper.updateByPrimaryKeySelective(workList);
            map.setMsg("修改完成");
            map.setState(ResultMap.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("修改完成");
            map.setState(ResultMap.ERROR);
        }

        return map;
    }
}
