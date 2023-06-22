package com.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private Integer id;

    private Integer goodsId;

    private Integer userId;

    private Integer adminId;

    private Integer count;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String remark;
    @TableField(exist = false)
    private String action;
}
