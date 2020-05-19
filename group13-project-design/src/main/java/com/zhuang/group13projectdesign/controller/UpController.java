package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.*;
import com.zhuang.group13projectdesign.mapper.*;
import com.zhuang.group13projectdesign.result.ResultMap;

import com.zhuang.group13projectdesign.utils.MyUtil;
import com.zhuang.group13projectdesign.utils.OSSUtils;
import com.zhuang.group13projectdesign.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Up")
@Api(value = "/上传", description = "上传")
public class UpController {
    @Autowired
    private VideoListMapper videoListMapper;
    @Autowired
    private WorkListMapper workListMapper;
    @Autowired
    private NoticeListMapper noticeListMapper;
    @Autowired
    private ArtcileListMapper artcileListMapper;
    @Autowired
    private WorkTeachMapper workTeachMapper;

    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传视频", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadVideo(@ApiParam(name = "data", value = "{\"userid\":\"用户ID\",\"url\":\"上传后的URL\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String userid = jsonObject.optString("userid");
            String url = jsonObject.optString("url");
            if(MyUtil.isEmpty(userid)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(url)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            VideoList videoList = new VideoList();
            videoList.setId(null);
            videoList.setUserid(Integer.valueOf(userid));
            videoList.setVideoUrl(url);
            videoList.setCreatetime(new Date());
            videoListMapper.insert(videoList);
            map.setState(ResultMap.ERROR);
            map.setMsg("视频上传成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }

    @RequestMapping(value = "/uploadwork", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学生上传作业", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadwork(@ApiParam(name = "data", value = "{\"workid\":\"\",\"userid\":\"用户ID\",\"url\":\"上传后的URL\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String userid = jsonObject.optString("userid");
            String url = jsonObject.optString("url");
            String workid = jsonObject.optString("workid");
            if(MyUtil.isEmpty(userid)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(url)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(workid)){
                map.setMsg("workid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            WorkTeachQuery query = new WorkTeachQuery();
            WorkTeachQuery.Criteria criteria = query.createCriteria();
            criteria.andIdEqualTo(Integer.valueOf(Integer.valueOf(workid)));
            List<WorkTeach> workTeaches = workTeachMapper.selectByExample(query);
            if(workTeaches==null||workTeaches.size()<1){
                map.setMsg("workid不存在");
                map.setState(ResultMap.ERROR);
                return map;
            }
            WorkList workList = new WorkList();
            workList.setId(null);
            workList.setStates("已提交");
            workList.setUserid(Integer.valueOf(userid));
            workList.setWorkUrl(url);
            workList.setWorkid(Integer.valueOf(workid));
            workList.setWork(workTeaches.get(0).getWork());
            workList.setCreatetime(new Date());
            workListMapper.insert(workList);
            map.setState(ResultMap.ERROR);
            map.setMsg("作业上传成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }
    @RequestMapping(value = "/uploadnotice", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发布公告", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadnotice(@ApiParam(name = "data", value = "{\"userid\":\"用户ID\",\"notice\":\"公告内容\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String userid = jsonObject.optString("userid");
            String notice = jsonObject.optString("notice");
            if(MyUtil.isEmpty(userid)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(notice)){
                map.setMsg("notice不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            NoticeList noticeList = new NoticeList();
            noticeList.setId(null);
            noticeList.setUserid(Integer.valueOf(userid));
            noticeList.setCreatetime(new Date());
            noticeList.setNotice(notice);
            noticeListMapper.insert(noticeList);
            map.setState(ResultMap.ERROR);
            map.setMsg("公告发布成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }


    @RequestMapping(value = "/uploadartcile", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发布文章", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadartcile(@ApiParam(name = "data", value = "{\"userid\":\"用户ID\",\"artcile\":\"文章内容\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String userid = jsonObject.optString("userid");
            String artcile = jsonObject.optString("artcile");
            if(MyUtil.isEmpty(userid)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(artcile)){
                map.setMsg("artcile不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            ArtcileList artcileList = new ArtcileList();
            artcileList.setArtcile(artcile);
            artcileList.setId(null);
            artcileList.setUserid(Integer.valueOf(userid));
            artcileList.setCreatetime(new Date());
            artcileListMapper.insert(artcileList);
            map.setState(ResultMap.ERROR);
            map.setMsg("文章发布成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }

    @RequestMapping(value = "/fabuwork", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "发布作业", notes = " ", httpMethod = "POST", response = ResultMap.class)
    public ResultMap fabuwork(@ApiParam(name = "data", value = "{\"userid\":\"用户ID\",\"work\":\"作业内容\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String userid = jsonObject.optString("userid");
            String work = jsonObject.optString("work");
            if(MyUtil.isEmpty(userid)){
                map.setMsg("userid不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            if(MyUtil.isEmpty(work)){
                map.setMsg("artcile不能为空");
                map.setState(ResultMap.ERROR);
                return map;
            }
            WorkTeach workTeach = new WorkTeach();
            workTeach.setId(null);
            workTeach.setUserid(Integer.valueOf(userid));
            workTeach.setWork(work);
            workTeach.setCratetime(new Date());
            workTeachMapper.insert(workTeach);
            map.setState(ResultMap.ERROR);
            map.setMsg("作业发布成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.setState(ResultMap.ERROR);
            map.setMsg("异常");
        }
        return map;
    }


    @RequestMapping(value="/upload")
    @ResponseBody
    @ApiOperation(value = "上传资料", notes = "上传资料", httpMethod = "POST", response = ResultMap.class)
    public ResultMap uploadPic(@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request){
        ResultMap map = new ResultMap();
        try {
            String multifilename = multipartFile.getOriginalFilename();
            String suffix = multifilename.substring(multifilename.indexOf("."));
            String uuid = StringUtils.getUUID();
            String filename = uuid+suffix;
            String uri = request.getSession().getServletContext().getRealPath("/");
            File file = makefile(uri+"/",filename);
            multipartFile.transferTo(file);
            String filePath = file.getAbsolutePath();
            String pathName = "jiao-xue/"+filename;
            String result = OSSUtils.uploadFile("jiao-xue",pathName,filePath);
            if(!"success".equals(result)){
                map.setState(ResultMap.ERROR);
                map.setMsg("OSS图片上传失败");
                return map;
            }
            file.delete();
            String url = "https://jiao-xue.oss-cn-beijing.aliyuncs.com/"+pathName;
            Map<String,String> res = new HashMap<>();
            res.put("url",url);
            map.setState(ResultMap.SUCCESS);
            map.setMsg("上传成功");
            map.setData(res);
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }


    private  File makefile(String path, String filename) throws IOException {
        if (path == null || "".equals(path.trim()))
            return null;
        File dir = new File(path);
        if (!dir.exists()) { //先建目录
            dir.mkdirs();
        }
        File file = new File(path+filename);
        if (!file.exists()) {//再建文件
            file.createNewFile();
        }
        return file;
    }

}
