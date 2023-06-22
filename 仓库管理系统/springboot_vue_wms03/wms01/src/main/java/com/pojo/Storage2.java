package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author leo
 * @since 2022-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Storage2对象", description = "")
public class Storage2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "仓库名")
    private String name;

    @ApiModelProperty(value = "负责人")
    private String person;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "性别")
    private String sex;
}
