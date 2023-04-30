package com.study.yanxin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.study.yanxin.common.Result;
import com.study.yanxin.controller.request.UserPageRequest;
import com.study.yanxin.pojo.User;
import com.study.yanxin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 26414
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService iUserService;
    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = iUserService.ListUser();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
       /* //自定义标题别名
        writer.addHeaderAlias("id", "序号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("username", "卡号");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("account", "账号");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("sex", "性别");*/

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

/*    *//**
     * excel 导入
     *
     * @param file
     * @throws Exception
     *//*
    @PostMapping("")
    public Boolean imp(MultipartFile file) throws Exception {
        *//*从数据库中查出所有的数据*//*
        List<User> data = iUserService.ListUser();
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        *//*
        *  CollUtil.newArrayList方法表示新建ArrayList并填充元素
        *   HashMap<String, String> map = CollUtil.newHashMap();
            List<String> list1 = CollUtil.newArrayList();
            List<Integer> list1 = CollUtil.newArrayList(1, 2, 3);

        * *//*
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setId(Integer.valueOf(row.get(0).toString()));
            user.setName(row.get(1).toString());
            user.setUsername(row.get(2).toString());
            user.setAge(Integer.valueOf(row.get(3).toString()));
            user.setAccount(Integer.valueOf(row.get(4).toString()));
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());


            users.add(user);
        }

        boolean b = userService.saveBatch(users);
        // return Result.success(true);

        return b;


    }*/

    /*更新接口*/
    @PutMapping ("/update")
    public Result update(@RequestBody User user)
    {
        int i=iUserService.update(user);

        System.out.println("<====user=====>"+i);

        return Result.success();


    }

    /*根据id查询用户*/
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id)
    {
        User user=iUserService.getById(id);
        System.out.println("<==id==>"+id);

        return Result.success(user);


    }
    /*增加*/
    @PostMapping("/save")
    public Result save(@RequestBody User user)
    {

        iUserService.save(user);

        return Result.success();
    }

    @GetMapping("/list")
    public List<User> ListUser()
    {
        List<User> users = iUserService.ListUser();
        System.out.println(users);
        return users;

    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest) {
        return Result.success(iUserService.page(userPageRequest));
    }
}
