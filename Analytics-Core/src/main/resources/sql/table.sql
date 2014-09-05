
DROP TABLE `analytics`.`usr` IF EXISTS;
CREATE  TABLE `analytics`.`usr` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `name` VARCHAR(256) NOT NULL COMMENT '用户昵称' ,
  `email` VARCHAR(128) NOT NULL COMMENT '电子邮件' ,
  `mobile` VARCHAR(128) NOT NULL COMMENT '手机号码联系方式' ,
  `weixin` VARCHAR(128) NULL COMMENT '微信联系方式' ,
  `password` VARCHAR(256) NULL COMMENT '登录密码' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '用户信息';

DROP TABLE `analytics`.`app` IF EXISTS;
CREATE  TABLE `analytics`.`app` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `user_id` BIGINT NOT NULL COMMENT '用户ID' ,
  `name` VARCHAR(256) NOT NULL COMMENT 'App名称' ,
  `token` VARCHAR(128) NOT NULL COMMENT '访问token，注册之后系统自动分配' ,
  `description` VARCHAR(1024) NULL COMMENT 'App描述' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '标识统计的一个应用领域';

DROP TABLE `analytics`.`event` IF EXISTS;
CREATE  TABLE `analytics`.`event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `app_id` BIGINT NOT NULL COMMENT 'App_ID' ,
  `name` VARCHAR(256) NOT NULL COMMENT 'Event名称' ,
  `description` VARCHAR(1024) NULL COMMENT 'Event描述' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '标识一个应用领域事件统计领域模型';

DROP TABLE `analytics`.`label` IF EXISTS;
CREATE  TABLE `analytics`.`label` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `event_id` BIGINT NOT NULL COMMENT '所在的Event事件ID' ,
  `model_id` BIGINT NOT NULL COMMENT '统计模型ID' ,
  `name` VARCHAR(256) NOT NULL COMMENT 'Label名称' ,
  `description` VARCHAR(1024) NULL COMMENT 'Label描述' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '事件细分类别标签统计领域模型';

DROP TABLE `analytics`.`stats` IF EXISTS;
CREATE  TABLE `analytics`.`stats` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `label_id` BIGINT NOT NULL COMMENT '所在的事件分类标签ID' ,
  `year` int(11) NOT NULL COMMENT '年' ,
  `month` int(11) NOT NULL COMMENT '月' ,
  `day` int(11) NOT NULL COMMENT '日' ,
  `hour` int(11) NOT NULL COMMENT '时' ,
  `type` int(11) NOT NULL COMMENT '统计维度类型：年，月，日，时' ,
  `accumulation` double NOT NULL COMMENT '统计数据值' ,
  `attr` BIGINT NOT NULL COMMENT 'attr' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '统计数据';

DROP TABLE `analytics`.`model` IF EXISTS;
CREATE  TABLE `analytics`.`model` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID序列' ,
  `model` int(11) NOT NULL COMMENT '模型的数字表示' ,
  `name` VARCHAR(256) NOT NULL COMMENT '模型名称' ,
  `description` VARCHAR(1024) NULL COMMENT '模型描述' ,
  `gmt_created` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NOT NULL COMMENT '最后修改时间' ,
  PRIMARY KEY (`id`) )
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '统计数据模型, 表示这样一组统计所属的数据统计计算以及展示模式';
