use kyapi;
-- 接口信息
create table if not exists kyapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创始人',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('洪烨伟', '段鑫鹏', 'www.chery-bradtke.net', '赵耀杰', '何烨华', 0, '沈擎宇', 998220403);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('李琪', '郑乐驹', 'www.bryan-murazik.co', '高哲瀚', '吕正豪', 0, '任建辉', 792410691);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('戴睿渊', '龙健柏', 'www.dwain-farrell.co', '莫修洁', '方瑾瑜', 0, '杨泽洋', 88);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('马擎宇', '黄航', 'www.lucina-gottlieb.com', '蒋鹏', '赵伟泽', 0, '毛金鑫', 34575228);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('莫明哲', '阎鹭洋', 'www.rocky-johnson.io', '张子默', '吕修洁', 0, '丁浩', 88);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('薛文轩', '吕立轩', 'www.kate-nicolas.info', '卢晋鹏', '袁泽洋', 0, '夏昊强', 56078282);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('冯昊焱', '卢文轩', 'www.phillip-wisozk.net', '沈晓啸', '魏文博', 0, '余智辉', 3);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孔健雄', '姚立辉', 'www.naomi-feeney.org', '邓梓晨', '谢鸿煊', 0, '洪航', 7241);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('曹瑾瑜', '任立果', 'www.prince-corkery.biz', '王智渊', '武晓啸', 0, '毛峻熙', 525);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('何炫明', '秦靖琪', 'www.elinore-bernhard.net', '曹立轩', '蒋鸿煊', 0, '冯鹤轩', 355);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('王煜祺', '范鹏煊', 'www.luigi-crist.biz', '杨绍齐', '梁正豪', 0, '唐风华', 37712);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('谢笑愚', '段峻熙', 'www.vennie-yundt.org', '曾烨伟', '王鹏煊', 0, '蔡晟睿', 956241);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙伟泽', '姜黎昕', 'www.aliza-abbott.com', '李鑫鹏', '孟黎昕', 0, '姜正豪', 2429667894);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('周健柏', '叶峻熙', 'www.ashlea-berge.biz', '林昊强', '阎金鑫', 0, '万天翊', 3519);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('廖煜城', '李梓晨', 'www.bernice-champlin.net', '谢嘉熙', '戴健柏', 0, '武智辉', 48);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('沈峻熙', '冯烨华', 'www.lavonda-altenwerth.net', '余鹏', '龙黎昕', 0, '洪明辉', 64386802);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('彭鑫磊', '邹志强', 'www.refugia-trantow.info', '何嘉懿', '徐金鑫', 0, '龙致远', 77);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陆立轩', '姚锦程', 'www.maida-leannon.info', '杨泽洋', '吴浩宇', 0, '陈立辉', 27455086);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('雷绍齐', '苏嘉熙', 'www.emerson-auer.org', '黄致远', '万烨伟', 0, '吕越泽', 79530382);
insert into kyapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('顾胤祥', '萧瑞霖', 'www.alina-gislason.biz', '董懿轩', '邓熠彤', 0, '杜弘文', 52222884);


-- 用户调用接口关系表
create table if not exists kyapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户id',
    `interfaceInfoId` bigint not null comment '接口id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '调用接口状态（0-关闭，1-开启）',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系表';