package com.yifan.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    @ApiModelProperty(value = "用户名")
    private String uname;

    @ApiModelProperty(value = "用户密码")
    private String upassword;

    @ApiModelProperty(value = "手机号")
    private String uphone;

    @ApiModelProperty(value = "用户头像")
    private String uphoto;

    @ApiModelProperty(value = "用户邮箱")
    private String uemail;

    @ApiModelProperty(value = "用户令牌")
    private String utoken;

    @ApiModelProperty(value = "用户信息")
    private String uinfo;

    @ApiModelProperty(value = "用户权限")
    private String upower;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE) //更新时填充字段
    private LocalDateTime gmtModified;


}
