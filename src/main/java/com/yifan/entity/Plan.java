package com.yifan.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Plan对象", description="")
public class Plan implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "p_id", type = IdType.AUTO)
    private Long pId;

    @ApiModelProperty(value = "发布者")
    private String pUname;

    @ApiModelProperty(value = "更新的标题")
    private String pTitle;

    @ApiModelProperty(value = "更新详情")
    private String pDetail;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime pGmtCreate;

    @ApiModelProperty(value = "修改的时间")
    private LocalDateTime pGmtModified;


}
