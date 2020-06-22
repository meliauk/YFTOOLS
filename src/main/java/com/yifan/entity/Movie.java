package com.yifan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Movie对象", description="")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mid", type = IdType.AUTO)
    private Long mid;

    @ApiModelProperty(value = "节目名称")
    private String mname;

    @ApiModelProperty(value = "节目链接")
    private String murl;

    @ApiModelProperty(value = "链接格式")
    private String mformat;

    @ApiModelProperty(value = "节目类型")
    private String mtype;


}
