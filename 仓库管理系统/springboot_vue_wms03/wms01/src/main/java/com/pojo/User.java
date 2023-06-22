package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author 12
 * @since 2022-11-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_name")
    @ApiModelProperty(value = "账号")
    private String UserName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别 0男性，1女性")
    private Integer sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @TableField("role_id")
    @ApiModelProperty(value = "0超级管理员，1管理员，2普通账号")
    private Integer roleId;


}
