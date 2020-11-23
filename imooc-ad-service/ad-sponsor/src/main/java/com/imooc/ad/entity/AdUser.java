package com.imooc.ad.entity;

import com.imooc.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//实现get，set
@Data
//无参构造函数
@NoArgsConstructor
//全参构造函数
@AllArgsConstructor
//实体类
@Entity
//对应的数据库表
@Table(name = "ad_user")
public class AdUser {

    //主键并且使用自增策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //对应的字段为id，并且不能为空
    @Column(name = "id",nullable = false)
    private Long id;

    //基本属性
    @Basic
    //对应的字段为username，并且不能为空
    @Column(name = "username",nullable = false)
    private String username;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "token",nullable = false)
    private String token;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "user_status",nullable = false)
    private Integer userStatus;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    //传入的参数只有 username 和 token
    public AdUser(String username,String token){
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
