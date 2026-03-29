-- 血糖记录表
DROP TABLE IF EXISTS sys_blood_sugar;
CREATE TABLE sys_blood_sugar (
  sugar_id      BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '血糖ID',
  user_id       BIGINT(20)      NOT NULL                   COMMENT '用户ID',
  sugar_value   DECIMAL(5,2)    NOT NULL                   COMMENT '血糖值(mmol/L)',
  measure_time  DATETIME        NOT NULL                   COMMENT '测量时间',
  meal_type     CHAR(1)         DEFAULT '0'                COMMENT '餐前/餐后（0空腹 1餐前 2餐后）',
  remark        VARCHAR(500)     DEFAULT NULL               COMMENT '备注',
  create_by     VARCHAR(64)      DEFAULT ''                  COMMENT '创建者',
  create_time   DATETIME                                      COMMENT '创建时间',
  update_by     VARCHAR(64)      DEFAULT ''                  COMMENT '更新者',
  update_time   DATETIME                                      COMMENT '更新时间',
  PRIMARY KEY (sugar_id),
  INDEX idx_user_id (user_id),
  INDEX idx_measure_time (measure_time)
) ENGINE=InnoDB COMMENT='血糖记录表';

-- 初始管理员数据（user_id=1）
INSERT INTO sys_blood_sugar VALUES(1, 1, 5.5, '2026-03-29 08:00:00', '0', '空腹测量', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(2, 1, 7.2, '2026-03-29 08:30:00', '1', '早餐前', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(3, 1, 8.5, '2026-03-29 09:00:00', '2', '早餐后1小时', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(4, 1, 6.8, '2026-03-29 12:00:00', '1', '午餐前', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(5, 1, 9.1, '2026-03-29 13:00:00', '2', '午餐后1小时', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(6, 1, 5.9, '2026-03-29 18:00:00', '1', '晚餐前', 'admin', NOW(), '', NULL);
INSERT INTO sys_blood_sugar VALUES(7, 1, 7.8, '2026-03-29 19:00:00', '2', '晚餐后1小时', 'admin', NOW(), '', NULL);
