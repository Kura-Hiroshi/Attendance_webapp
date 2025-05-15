-- データベースの初期設定
-- テーブルの作成
CREATE TABLE COMPANY(
    id VARCHAR(50) PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL,
    clock_pass VARCHAR(100) NOT NULL,
    admin_pass VARCHAR(100) NOT NULL
);

CREATE TABLE EMPLOYEE(
    id INTEGER PRIMARY KEY,
    company_id VARCHAR(50) PRIMARY KEY NOT NULL,
    employee_name VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    FOREIGN KEY(id) REFERENCES COMPANY(id)
);

CREATE TABLE ATTENDANCE(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    company_id VARCHAR(50),
    employee_id INTEGER,
    clock_date DATE,
    event_type VARCHAR(20),
    event_time TIMESTAMP,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id)
    FOREIGN KEY(employee_id) REFERENCES EMPLOYEE(id)
);

CREATE TABLE EMPLOYEE_NUM(
    company_id VARCHAR(50) PRIMARY KEY,
    next_employee_id INTEGER,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id)
)