--テスト用のデータを作成

INSERT INTO COMPANY
VALUES('xxxxcompany','xxxx株式会社','1234','1234');

INSERT INTO EMPLOYEE_NUM
VALUES('xxxxcompany',2);


INSERT INTO EMPLOYEE
VALUES(1,'xxxxcompany','山田　一郎','1234');

INSERT INTO ATTENDANCE(company_id, employee_id, clock_date, event_type, event_time)
VALUES('xxxxcompany',1,current_date,'出勤',current_timestamp);

SELECT * FROM COMPANY;
SELECT * FROM EMPLOYEE;
SELECT * FROM ATTENDANCE;
SELECT * FROM EMPLOYEE_NUM;