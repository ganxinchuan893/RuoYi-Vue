-- 血糖记录表
DROP TABLE IF EXISTS sys_blood_sugar;
CREATE TABLE sys_blood_sugar (
  sugar_id      BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '血糖ID',
  user_id       BIGINT(20)      NOT NULL                   COMMENT '用户ID',
  sugar_value   DECIMAL(5,2)    NOT NULL                   COMMENT '血糖值(mmol/L)',
  measure_time  DATETIME        NOT NULL                   COMMENT '测量时间',
  meal_type     CHAR(1)         DEFAULT '0'                COMMENT '餐前/餐后（0空腹 1餐前 2餐后）',
  remark        VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  create_by     VARCHAR(64)     DEFAULT ''                  COMMENT '创建者',
  create_time   DATETIME                                      COMMENT '创建时间',
  update_by     VARCHAR(64)     DEFAULT ''                  COMMENT '更新者',
  update_time   DATETIME                                      COMMENT '更新时间',
  del_flag      CHAR(1)         DEFAULT '0'                COMMENT '删除标志',
  PRIMARY KEY (sugar_id),
  INDEX idx_user_id (user_id),
  INDEX idx_measure_time (measure_time)
) ENGINE=InnoDB COMMENT='血糖记录表';

-- 插入测试数据
INSERT INTO sys_blood_sugar (sugar_id, user_id, sugar_value, measure_time, meal_type, remark, create_by, create_time) VALUES
(1, 1, 5.5, '2026-03-29 08:00:00', '0', '空腹测量', 'admin', NOW()),
(2, 1, 7.2, '2026-03-29 08:30:00', '1', '早餐前', 'admin', NOW()),
(3, 1, 8.5, '2026-03-29 09:00:00', '2', '早餐后1小时', 'admin', NOW()),
(4, 1, 6.8, '2026-03-29 12:00:00', '1', '午餐前', 'admin', NOW()),
(5, 1, 9.1, '2026-03-29 13:00:00', '2', '午餐后1小时', 'admin', NOW()),
(6, 1, 5.9, '2026-03-29 18:00:00', '1', '晚餐前', 'admin', NOW()),
(7, 1, 7.8, '2026-03-29 19:00:00', '2', '晚餐后1小时', 'admin', NOW()),
(8, 1, 6.2, '2026-03-28 08:00:00', '0', '空腹测量', 'admin', NOW()),
(9, 1, 7.8, '2026-03-28 12:00:00', '1', '午餐前', 'admin', NOW()),
(10, 1, 8.9, '2026-03-28 13:00:00', '2', '午餐后', 'admin', NOW()),
(11, 1, 5.7, '2026-03-27 08:00:00', '0', '空腹测量', 'admin', NOW()),
(12, 1, 7.0, '2026-03-27 12:00:00', '1', '午餐前', 'admin', NOW()),
(13, 1, 6.3, '2026-03-26 08:00:00', '0', '空腹测量', 'admin', NOW()),
(14, 1, 8.2, '2026-03-26 12:00:00', '1', '午餐前', 'admin', NOW()),
(15, 1, 6.0, '2026-03-25 08:00:00', '0', '空腹测量', 'admin', NOW()),
(16, 1, 7.5, '2026-03-25 12:00:00', '1', '午餐前', 'admin', NOW()),
(17, 1, 9.3, '2026-03-24 08:00:00', '0', '空腹测量', 'admin', NOW()),
(18, 1, 8.0, '2026-03-24 12:00:00', '1', '午餐前', 'admin', NOW());
