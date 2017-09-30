CREATE TABLE kyweb_dev.tb_user
(
  id BIGINT PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
  update_time VARCHAR(20)  COMMENT '更改时间',
  name VARCHAR(32) NOT NULL COMMENT '姓名',
  password VARCHAR(32) COMMENT '密码',
  insert_time VARCHAR(20) DEFAULT null COMMENT '插入时间'
);
ALTER TABLE kyweb_dev.tb_user COMMENT = '用户表';
CREATE INDEX tb_user_name_index ON tb_user (name);

ALTER TABLE kyweb_dev.tb_user ADD emall_adress VARCHAR(30) NULL;