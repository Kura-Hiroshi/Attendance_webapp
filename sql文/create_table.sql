-- データベースの初期設定
-- テーブルの作成
CREATE TABLE COMPANY(
    id VARCHAR(50) PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL,
    clock_pass VARCHAR(100) NOT NULL,
    admin_pass VARCHAR(100) NOT NULL
);

CREATE TABLE EMPLOYEE(
    id INTEGER NOT NULL,
    company_id VARCHAR(50) NOT NULL,
    employee_name VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id),
    PRIMARY KEY(id,company_id)
);

CREATE TABLE ATTENDANCE(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    company_id VARCHAR(50),
    employee_id INTEGER,
    clock_date DATE,
    event_type VARCHAR(20),
    event_time TIMESTAMP,
    FOREIGN KEY (employee_id, company_id) REFERENCES EMPLOYEE(id, company_id)
);

CREATE TABLE EMPLOYEE_NUM(
    company_id VARCHAR(50) PRIMARY KEY,
    next_employee_id INTEGER,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id)
);

CREATE VIEW ATTENDANCE_VIEW AS
SELECT
  BASE.company_id,
  BASE.employee_id,
  EM.employee_name,
  BASE.clock_date,
  TYPEin.work_in,
  TYPEout.work_out
FROM ATTENDANCE AS BASE
LEFT JOIN (
  SELECT company_id, employee_id, clock_date, MIN(event_time) AS work_in
  FROM ATTENDANCE
  WHERE event_type = '出勤'
  GROUP BY company_id, employee_id, clock_date
) AS TYPEin
  ON BASE.company_id = TYPEin.company_id
  AND BASE.employee_id = TYPEin.employee_id
  AND BASE.clock_date = TYPEin.clock_date
LEFT JOIN (
  SELECT company_id, employee_id, clock_date, MAX(event_time) AS work_out
  FROM ATTENDANCE
  WHERE event_type = '退勤'
  GROUP BY company_id, employee_id, clock_date
) AS TYPEout
  ON BASE.company_id = TYPEout.company_id
  AND BASE.employee_id = TYPEout.employee_id
  AND BASE.clock_date = TYPEout.clock_date
JOIN EMPLOYEE AS EM
  ON BASE.company_id = EM.company_id
  AND BASE.employee_id = EM.id
GROUP BY BASE.company_id, BASE.employee_id, BASE.clock_date;