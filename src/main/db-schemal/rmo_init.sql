INSERT INTO rmp_business_module (s_name,s_code,s_desc) VALUES ('系统管理','1001','');
INSERT INTO rmp_business_module (s_name,s_code,s_desc) VALUES ('爱羽玩','2001','');



INSERT INTO rmo_privilege (s_pri_name,s_pri_code,s_pri_type,s_pri_url,s_parent_pri_code,s_business_module_code,s_business_module_name,s_pri_desc) VALUES
('群成员管理','200101','01','/smallowadmin/badmition/member/manage','2001','2001','爱羽玩','');


INSERT INTO rmo_privilege (s_pri_name,s_pri_code,s_pri_type,s_pri_url,s_parent_pri_code,s_business_module_code,s_business_module_name,s_pri_desc) VALUES
('群活动管理','200102','01','/smallowadmin/badmition/activity/manage','2001','2001','爱羽玩','');
