# 建表脚本
CREATE TABLE `t_template_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` varchar(16) NOT NULL COMMENT '模板编号',
  `template_desc` varchar(64) DEFAULT NULL COMMENT '模板描述',
  `template_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '模板状态(0:不使用,1:使用)',
  `check_level` int(11) NOT NULL COMMENT '检查优先级',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='校验模板表';

CREATE TABLE `t_template_rule_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_id` varchar(16) NOT NULL COMMENT '规则编号',
  `template_id` varchar(16) NOT NULL COMMENT '模板编号',
  `rule_express` varchar(128) NOT NULL COMMENT '规则表达式',
  `toast_msg` varchar(128) NOT NULL COMMENT '提示信息',
  `rule_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '规则状态(0:不使用,1:使用)',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='校验模板规则表';

CREATE TABLE `t_bean_rule_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bean_id` varchar(32) NOT NULL COMMENT '实体类编号',
  `rule_id` varchar(16) NOT NULL COMMENT '规则编号',
  `field_name` varchar(32) NOT NULL COMMENT '字段名',
  `field_desc` varchar(128) DEFAULT NULL COMMENT '字段描述',
  `check_status` tinyint(4) DEFAULT '1' COMMENT '是否校验(0:不校验,1:校验)',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='实体规则关联表';

# 初始化数据脚本
# t_template_info表
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('notBlank', '不能为空,包括不能为null和空字符串', '1', '0');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('notNull', '不能为null', '1', '0');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('lengthMax', '长度最大值,包含最大值', '1', '1');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('lengthMin', '长度最小值,包含最小值', '1', '2');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('valueMax', '属性值最大值,包含最大值', '1', '3');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('valueMin', '属性值最小值,包含最小值', '1', '4');
INSERT INTO `t_template_info` (`template_id`, `template_desc`, `template_status`, `check_level`) VALUES ('regex', '正则表达式校验', '1', '5');
# t_template_rule_info表
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_001', 'notBlank', '', '{0}不能为空', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_002', 'notNull', '', '{0}不能为空', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_003', 'lengthMax', '10', '{0}长度不能超过{1}', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_003', 'lengthMin', '3', '{0}长度不能小于{1}', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_004', 'valueMax', '7', '{0}最大值不超过{1}', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_004', 'valueMin', '0', '{0}最小值不小于{1}', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_003', 'regex', '[0-9]+', '{0}格式不正确', '1');
INSERT INTO `t_template_rule_info` (`rule_id`, `template_id`, `rule_express`, `toast_msg`, `rule_status`) VALUES ('rule_004', 'notNull', '', '{0}不能为空', '1');
# t_bean_rule_info表
INSERT INTO `t_bean_rule_info` (`bean_id`, `rule_id`, `field_name`, `field_desc`, `check_status`) VALUES ('DynamicCheckReqDto', 'rule_004', 'intCheck', '数字校验', '1');
INSERT INTO `t_bean_rule_info` (`bean_id`, `rule_id`, `field_name`, `field_desc`, `check_status`) VALUES ('DynamicCheckReqDto', 'rule_003', 'strCheck', '字符串校验', '1');