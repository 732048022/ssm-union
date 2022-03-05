package com.everybodydance.controller;

import com.alibaba.fastjson.JSONObject;
import com.everybodydance.commons.response.ETResponse;
import com.everybodydance.commons.utils.RedisUtil;
import com.everybodydance.commons.utils.SetNodes;
import com.everybodydance.commons.utils.SetResponse;
import com.everybodydance.pojo.Book;
import com.everybodydance.pojo.Cart;
import com.everybodydance.pojo.Menus;
import com.everybodydance.pojo.Users;
import com.everybodydance.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SetResponse sr;
    @Autowired
    private UserService service;

    /**
     * 校验用户名和密码
     * @param uname 用户名
     * @param pwd 密码
     * @param checkCode 验证码
     * @param sessionId sessionId
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse queryUserByUsernameAndPwd(@RequestParam("username") String uname,@RequestParam("password") String pwd,String checkCode,@RequestParam("sessionid") String sessionId){
        log.info("进入校验用户名密码....");
        HttpSession hs =UserControllerListener.getSessionById(sessionId);
        String str =hs.getAttribute("checkcode")+"";
        if(str.equalsIgnoreCase(checkCode)) {
            String token = UUID.randomUUID().toString().replace("-", "");
            Users u = service.queryUserByUsernameAndPwd(uname, pwd);
            if (u != null && uname.equals(u.getUname())) {
                try {
                    RedisUtil ru = RedisUtil.getRedisUtil();
                    u.setToken(token);
                    ru.setex(token, 1800, JSONObject.toJSONString(u));
                } catch (Exception e) {
                    e.printStackTrace();
                    return sr.setResponse("", "446");
                }
            } else {
                return sr.setResponse("", "445");
            }
            return sr.setResponse(u,"200");//这里回去了Cookie
        }else {
            return sr.setResponse("","447");//验证码输入错误
        }
    }

    /**
     * 注册用户
     * @param uname 用户名
     * @param pwd 密码
     * @param checkCode 验证码
     * @param sessionId sessionId
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse regist(@RequestParam("username") String uname,@RequestParam("password") String pwd,String checkCode,@RequestParam("sessionid") String sessionId){
        log.info("进入注册新用户......");
        HttpSession hs =UserControllerListener.getSessionById(sessionId);
        String str =hs.getAttribute("checkcode")+"";
        if(str.equalsIgnoreCase(checkCode)) {
          //  String token = UUID.randomUUID().toString().replace("-", "");
            Users u = service.queryUserByUsernameAndPwd(uname, pwd);
            if (u !=null) {
                log.info("该数据库中已存在该用户，请重新注册！");
                return sr.setResponse("", "449");
            }else{
                log.info("该数据库中不存在该用户，可以注册！");
                log.info("UserController--regist--insertUser:Param:{},{}",uname,pwd);
                service.insertUser(uname,pwd);
                log.info("新增用户成功......");
                return sr.setResponse(u,"200");//这里回去了Cookie
            }
        }else {
            return sr.setResponse("","447");//验证码输入错误
        }
    }
    @RequestMapping("/queryMenuByUserId")
    @ResponseBody
    public ETResponse queryMenuByUserID(String token){
        RedisUtil ru =RedisUtil.getRedisUtil();//打开Redis工具 我资源里上传了
        String userInfo =ru.get(token);// 相当于是一个Map
        Users u =JSONObject.parseObject(userInfo,Users.class);
        List<Menus> menus =service.queryMenuByUserId(u.getId());
        List<Menus> tops =new ArrayList<>();
        for (Menus menu : menus) {
            if(menu.getPid()==-1){
                tops.add(menu);
            }
        }
        for (Menus top : tops) {
            SetNodes.setNodes(top,menus);
        }
        return sr.setResponse(tops,"200");
    }

    /**
     * 发送验证码信息
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("进入发送验证码信息......");
        int WIDTH = 120;//生成图片的宽度
        int HEIGHT = 30;
        // TODO Auto-generated method stub
        String createTypeFlag = req.getParameter("createTypeFlag");//接收客户端传递的createTypeFlag标识
        //在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        //得到图片
        Graphics g = bi.getGraphics();
        //设置图片的背景色
        setBackGround(g);
        //设置图片的边框
        setBorder(g);
        //在图片上画干扰线
        drawRandomLine(g);
        //在图片上放上随机字符
        String randomString = this.drawRandomNum((Graphics2D)g, createTypeFlag);

        //将随机数存在session中
        HttpSession hs =req.getSession();
        hs.setAttribute("checkcode", randomString);
        log.info("登录验证码：{}",randomString);

        //设置响应头通知浏览器以图片的形式打开
        resp.setContentType("image/jpeg");

        //设置响应头控制浏览器不要缓存
        resp.setDateHeader("expries", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("sessionid",hs.getId());
        //将图片传给浏览器
        ImageIO.write(bi, "jpg", resp.getOutputStream());
    }

    //设置图片背景色
    //@param g
    private void setBackGround(Graphics g) {
        //设置颜色
        g.setColor(Color.WHITE);
        //填充区域
        g.fillRect(0, 0, 120, 30);
    }

    /*
     * 设置图片的边框
     * @param g
     * */

    private void setBorder(Graphics g) {
        //设置边框颜色
        g.setColor(Color.BLUE);
        //边框区域
        g.drawRect(1, 1, 120 - 2, 30 -2);
    }

    /*
     * 在图片上画随机线条
     * @param g
     * */
    private void drawRandomLine(Graphics g) {
        //设置颜色
        g.setColor(Color.GREEN);
        //设置线条个数并画线
        for ( int i = 0 ; i < 3 ; i++ ) {
            int x1 = new Random().nextInt(120);
            int y1 = new Random().nextInt(30);
            int x2 = new Random().nextInt(120);
            int y2 = new Random().nextInt(30);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /*
     * 在图片上画随机字符
     * @param g
     * @param createTypeFlag
     * @return String
     * */
    private String drawRandomNum(Graphics g,String createTypeFlag) {
        //设置颜色
        g.setColor(Color.RED);
        g.setFont(new Font("宋体",Font.BOLD,20));

        //数字字母的组合
        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        String baseNum = "0123456789";
        String baseLetter = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
        if ( createTypeFlag != null  && createTypeFlag.length() > 0 ) {
            if( createTypeFlag.equals("nl") ) {
                //截取数字和字母的组合
                return createRandomChar((Graphics2D) g,baseNumLetter);
            } else if ( createTypeFlag.equals("n") ) {
                //截取数字的组合
                return createRandomChar((Graphics2D) g,baseNum);
            } else if ( createTypeFlag.equals("l") ) {
                //截取字母的组合
                return createRandomChar((Graphics2D) g,baseLetter);
            }
        } else {
            //截取数字和字母的组合
            return createRandomChar((Graphics2D) g,baseNumLetter);
        }
        return "";
    }

    /*
     * 创建随机字符
     * @param g
     * @param baseChar
     * @return String
     * */
    private String createRandomChar(Graphics2D g , String baseChar) {
        StringBuffer b = new StringBuffer();
        int x = 5;
        String ch = "";
        for ( int i = 0 ; i < 4 ; i++ ) {
            //设置字体的旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            b.append(ch);

            //正向角度
            g.rotate(degree  * Math.PI / 180 , x,20);
            g.drawString(ch, x, 20);
            //反向角度
            g.rotate(-degree  * Math.PI / 180 , x,20);
            x+=30;
        }
        return b.toString();
    }
    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse addToCart(String token,String id){
        RedisUtil ru =RedisUtil.getRedisUtil();
        String userInfo =ru.get(token);
        Users u =JSONObject.parseObject(userInfo,Users.class);
        if(u!=null&&token.equals(u.getToken())){
            try{
                Book b =service.queryBookByBookId(id);
                if(!ru.hexists(u.getId()+"",id)){
                    ru.hset(u.getId()+"",id,"1");
                }else {
                    ru.hincrby(u.getId()+"",b.getId(),1L);
                }return sr.setResponse("","200");
            }catch (Exception e){
                return sr.setResponse("","444");
            }
        }
        else {
            return sr.setResponse("","444");
        }
    }
    @RequestMapping(value = "/queryCart",method = RequestMethod.POST)
    @ResponseBody
    public ETResponse queryCart(String token){
        List<Cart> result =new LinkedList<>();
        RedisUtil ru =RedisUtil.getRedisUtil();
        String userInfo =ru.get(token);
        Users u =JSONObject.parseObject(userInfo,Users.class);
        Map<String,String> map =ru.hgetall(u.getId()+"");
        Set<Map.Entry<String,String>> b_c =map.entrySet();
        for (Map.Entry<String, String> bc : b_c) {
            Cart car =new Cart();
            Book b =service.queryBookByBookId(bc.getKey());
            car.setB(b);
            car.setCount(Integer.parseInt(bc.getValue()));
            car.setSum();
            result.add(car);
        }
        return sr.setResponse(result,"200");
    }
}
